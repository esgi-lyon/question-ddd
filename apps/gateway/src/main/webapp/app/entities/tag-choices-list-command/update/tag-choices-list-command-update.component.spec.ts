import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { TagChoicesListCommandFormService } from './tag-choices-list-command-form.service';
import { TagChoicesListCommandService } from '../service/tag-choices-list-command.service';
import { ITagChoicesListCommand } from '../tag-choices-list-command.model';

import { TagChoicesListCommandUpdateComponent } from './tag-choices-list-command-update.component';

describe('TagChoicesListCommand Management Update Component', () => {
  let comp: TagChoicesListCommandUpdateComponent;
  let fixture: ComponentFixture<TagChoicesListCommandUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let tagChoicesListCommandFormService: TagChoicesListCommandFormService;
  let tagChoicesListCommandService: TagChoicesListCommandService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [TagChoicesListCommandUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(TagChoicesListCommandUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TagChoicesListCommandUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    tagChoicesListCommandFormService = TestBed.inject(TagChoicesListCommandFormService);
    tagChoicesListCommandService = TestBed.inject(TagChoicesListCommandService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const tagChoicesListCommand: ITagChoicesListCommand = { id: 456 };

      activatedRoute.data = of({ tagChoicesListCommand });
      comp.ngOnInit();

      expect(comp.tagChoicesListCommand).toEqual(tagChoicesListCommand);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITagChoicesListCommand>>();
      const tagChoicesListCommand = { id: 123 };
      jest.spyOn(tagChoicesListCommandFormService, 'getTagChoicesListCommand').mockReturnValue(tagChoicesListCommand);
      jest.spyOn(tagChoicesListCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tagChoicesListCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: tagChoicesListCommand }));
      saveSubject.complete();

      // THEN
      expect(tagChoicesListCommandFormService.getTagChoicesListCommand).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(tagChoicesListCommandService.update).toHaveBeenCalledWith(expect.objectContaining(tagChoicesListCommand));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITagChoicesListCommand>>();
      const tagChoicesListCommand = { id: 123 };
      jest.spyOn(tagChoicesListCommandFormService, 'getTagChoicesListCommand').mockReturnValue({ id: null });
      jest.spyOn(tagChoicesListCommandService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tagChoicesListCommand: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: tagChoicesListCommand }));
      saveSubject.complete();

      // THEN
      expect(tagChoicesListCommandFormService.getTagChoicesListCommand).toHaveBeenCalled();
      expect(tagChoicesListCommandService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITagChoicesListCommand>>();
      const tagChoicesListCommand = { id: 123 };
      jest.spyOn(tagChoicesListCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tagChoicesListCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(tagChoicesListCommandService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
