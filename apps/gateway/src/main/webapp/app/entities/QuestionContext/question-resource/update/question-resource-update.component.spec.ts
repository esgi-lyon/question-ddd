import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { QuestionResourceFormService } from './question-resource-form.service';
import { QuestionResourceService } from '../service/question-resource.service';
import { IQuestionResource } from '../question-resource.model';

import { QuestionResourceUpdateComponent } from './question-resource-update.component';

describe('QuestionResource Management Update Component', () => {
  let comp: QuestionResourceUpdateComponent;
  let fixture: ComponentFixture<QuestionResourceUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let questionResourceFormService: QuestionResourceFormService;
  let questionResourceService: QuestionResourceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [QuestionResourceUpdateComponent],
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
      .overrideTemplate(QuestionResourceUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(QuestionResourceUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    questionResourceFormService = TestBed.inject(QuestionResourceFormService);
    questionResourceService = TestBed.inject(QuestionResourceService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const questionResource: IQuestionResource = { id: 456 };

      activatedRoute.data = of({ questionResource });
      comp.ngOnInit();

      expect(comp.questionResource).toEqual(questionResource);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IQuestionResource>>();
      const questionResource = { id: 123 };
      jest.spyOn(questionResourceFormService, 'getQuestionResource').mockReturnValue(questionResource);
      jest.spyOn(questionResourceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ questionResource });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: questionResource }));
      saveSubject.complete();

      // THEN
      expect(questionResourceFormService.getQuestionResource).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(questionResourceService.update).toHaveBeenCalledWith(expect.objectContaining(questionResource));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IQuestionResource>>();
      const questionResource = { id: 123 };
      jest.spyOn(questionResourceFormService, 'getQuestionResource').mockReturnValue({ id: null });
      jest.spyOn(questionResourceService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ questionResource: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: questionResource }));
      saveSubject.complete();

      // THEN
      expect(questionResourceFormService.getQuestionResource).toHaveBeenCalled();
      expect(questionResourceService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IQuestionResource>>();
      const questionResource = { id: 123 };
      jest.spyOn(questionResourceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ questionResource });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(questionResourceService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
