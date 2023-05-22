import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CreateTagCommandFormService } from './create-tag-command-form.service';
import { CreateTagCommandService } from '../service/create-tag-command.service';
import { ICreateTagCommand } from '../create-tag-command.model';
import { ITag } from 'app/entities/SkillContext/tag/tag.model';
import { TagService } from 'app/entities/SkillContext/tag/service/tag.service';

import { CreateTagCommandUpdateComponent } from './create-tag-command-update.component';

describe('CreateTagCommand Management Update Component', () => {
  let comp: CreateTagCommandUpdateComponent;
  let fixture: ComponentFixture<CreateTagCommandUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let createTagCommandFormService: CreateTagCommandFormService;
  let createTagCommandService: CreateTagCommandService;
  let tagService: TagService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [CreateTagCommandUpdateComponent],
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
      .overrideTemplate(CreateTagCommandUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CreateTagCommandUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    createTagCommandFormService = TestBed.inject(CreateTagCommandFormService);
    createTagCommandService = TestBed.inject(CreateTagCommandService);
    tagService = TestBed.inject(TagService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Tag query and add missing value', () => {
      const createTagCommand: ICreateTagCommand = { id: 456 };
      const tag: ITag = { id: 51824 };
      createTagCommand.tag = tag;

      const tagCollection: ITag[] = [{ id: 50648 }];
      jest.spyOn(tagService, 'query').mockReturnValue(of(new HttpResponse({ body: tagCollection })));
      const additionalTags = [tag];
      const expectedCollection: ITag[] = [...additionalTags, ...tagCollection];
      jest.spyOn(tagService, 'addTagToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ createTagCommand });
      comp.ngOnInit();

      expect(tagService.query).toHaveBeenCalled();
      expect(tagService.addTagToCollectionIfMissing).toHaveBeenCalledWith(tagCollection, ...additionalTags.map(expect.objectContaining));
      expect(comp.tagsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const createTagCommand: ICreateTagCommand = { id: 456 };
      const tag: ITag = { id: 17078 };
      createTagCommand.tag = tag;

      activatedRoute.data = of({ createTagCommand });
      comp.ngOnInit();

      expect(comp.tagsSharedCollection).toContain(tag);
      expect(comp.createTagCommand).toEqual(createTagCommand);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreateTagCommand>>();
      const createTagCommand = { id: 123 };
      jest.spyOn(createTagCommandFormService, 'getCreateTagCommand').mockReturnValue(createTagCommand);
      jest.spyOn(createTagCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ createTagCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: createTagCommand }));
      saveSubject.complete();

      // THEN
      expect(createTagCommandFormService.getCreateTagCommand).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(createTagCommandService.update).toHaveBeenCalledWith(expect.objectContaining(createTagCommand));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreateTagCommand>>();
      const createTagCommand = { id: 123 };
      jest.spyOn(createTagCommandFormService, 'getCreateTagCommand').mockReturnValue({ id: null });
      jest.spyOn(createTagCommandService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ createTagCommand: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: createTagCommand }));
      saveSubject.complete();

      // THEN
      expect(createTagCommandFormService.getCreateTagCommand).toHaveBeenCalled();
      expect(createTagCommandService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreateTagCommand>>();
      const createTagCommand = { id: 123 };
      jest.spyOn(createTagCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ createTagCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(createTagCommandService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareTag', () => {
      it('Should forward to tagService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(tagService, 'compareTag');
        comp.compareTag(entity, entity2);
        expect(tagService.compareTag).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
