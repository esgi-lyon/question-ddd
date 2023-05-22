import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { EvaluationFormService } from './evaluation-form.service';
import { EvaluationService } from '../service/evaluation.service';
import { IEvaluation } from '../evaluation.model';
import { IEvaluationTag } from 'app/entities/evaluation-tag/evaluation-tag.model';
import { EvaluationTagService } from 'app/entities/evaluation-tag/service/evaluation-tag.service';
import { IEvaluationQuestion } from 'app/entities/evaluation-question/evaluation-question.model';
import { EvaluationQuestionService } from 'app/entities/evaluation-question/service/evaluation-question.service';
import { IAnsweringUser } from 'app/entities/answering-user/answering-user.model';
import { AnsweringUserService } from 'app/entities/answering-user/service/answering-user.service';

import { EvaluationUpdateComponent } from './evaluation-update.component';

describe('Evaluation Management Update Component', () => {
  let comp: EvaluationUpdateComponent;
  let fixture: ComponentFixture<EvaluationUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let evaluationFormService: EvaluationFormService;
  let evaluationService: EvaluationService;
  let evaluationTagService: EvaluationTagService;
  let evaluationQuestionService: EvaluationQuestionService;
  let answeringUserService: AnsweringUserService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [EvaluationUpdateComponent],
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
      .overrideTemplate(EvaluationUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(EvaluationUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    evaluationFormService = TestBed.inject(EvaluationFormService);
    evaluationService = TestBed.inject(EvaluationService);
    evaluationTagService = TestBed.inject(EvaluationTagService);
    evaluationQuestionService = TestBed.inject(EvaluationQuestionService);
    answeringUserService = TestBed.inject(AnsweringUserService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call EvaluationTag query and add missing value', () => {
      const evaluation: IEvaluation = { id: 456 };
      const tag: IEvaluationTag = { id: 29351 };
      evaluation.tag = tag;

      const evaluationTagCollection: IEvaluationTag[] = [{ id: 965 }];
      jest.spyOn(evaluationTagService, 'query').mockReturnValue(of(new HttpResponse({ body: evaluationTagCollection })));
      const additionalEvaluationTags = [tag];
      const expectedCollection: IEvaluationTag[] = [...additionalEvaluationTags, ...evaluationTagCollection];
      jest.spyOn(evaluationTagService, 'addEvaluationTagToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ evaluation });
      comp.ngOnInit();

      expect(evaluationTagService.query).toHaveBeenCalled();
      expect(evaluationTagService.addEvaluationTagToCollectionIfMissing).toHaveBeenCalledWith(
        evaluationTagCollection,
        ...additionalEvaluationTags.map(expect.objectContaining)
      );
      expect(comp.evaluationTagsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call EvaluationQuestion query and add missing value', () => {
      const evaluation: IEvaluation = { id: 456 };
      const question: IEvaluationQuestion = { id: 44601 };
      evaluation.question = question;

      const evaluationQuestionCollection: IEvaluationQuestion[] = [{ id: 13221 }];
      jest.spyOn(evaluationQuestionService, 'query').mockReturnValue(of(new HttpResponse({ body: evaluationQuestionCollection })));
      const additionalEvaluationQuestions = [question];
      const expectedCollection: IEvaluationQuestion[] = [...additionalEvaluationQuestions, ...evaluationQuestionCollection];
      jest.spyOn(evaluationQuestionService, 'addEvaluationQuestionToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ evaluation });
      comp.ngOnInit();

      expect(evaluationQuestionService.query).toHaveBeenCalled();
      expect(evaluationQuestionService.addEvaluationQuestionToCollectionIfMissing).toHaveBeenCalledWith(
        evaluationQuestionCollection,
        ...additionalEvaluationQuestions.map(expect.objectContaining)
      );
      expect(comp.evaluationQuestionsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call AnsweringUser query and add missing value', () => {
      const evaluation: IEvaluation = { id: 456 };
      const user: IAnsweringUser = { id: 80816 };
      evaluation.user = user;

      const answeringUserCollection: IAnsweringUser[] = [{ id: 44407 }];
      jest.spyOn(answeringUserService, 'query').mockReturnValue(of(new HttpResponse({ body: answeringUserCollection })));
      const additionalAnsweringUsers = [user];
      const expectedCollection: IAnsweringUser[] = [...additionalAnsweringUsers, ...answeringUserCollection];
      jest.spyOn(answeringUserService, 'addAnsweringUserToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ evaluation });
      comp.ngOnInit();

      expect(answeringUserService.query).toHaveBeenCalled();
      expect(answeringUserService.addAnsweringUserToCollectionIfMissing).toHaveBeenCalledWith(
        answeringUserCollection,
        ...additionalAnsweringUsers.map(expect.objectContaining)
      );
      expect(comp.answeringUsersSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const evaluation: IEvaluation = { id: 456 };
      const tag: IEvaluationTag = { id: 88516 };
      evaluation.tag = tag;
      const question: IEvaluationQuestion = { id: 733 };
      evaluation.question = question;
      const user: IAnsweringUser = { id: 89683 };
      evaluation.user = user;

      activatedRoute.data = of({ evaluation });
      comp.ngOnInit();

      expect(comp.evaluationTagsSharedCollection).toContain(tag);
      expect(comp.evaluationQuestionsSharedCollection).toContain(question);
      expect(comp.answeringUsersSharedCollection).toContain(user);
      expect(comp.evaluation).toEqual(evaluation);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEvaluation>>();
      const evaluation = { id: 123 };
      jest.spyOn(evaluationFormService, 'getEvaluation').mockReturnValue(evaluation);
      jest.spyOn(evaluationService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ evaluation });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: evaluation }));
      saveSubject.complete();

      // THEN
      expect(evaluationFormService.getEvaluation).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(evaluationService.update).toHaveBeenCalledWith(expect.objectContaining(evaluation));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEvaluation>>();
      const evaluation = { id: 123 };
      jest.spyOn(evaluationFormService, 'getEvaluation').mockReturnValue({ id: null });
      jest.spyOn(evaluationService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ evaluation: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: evaluation }));
      saveSubject.complete();

      // THEN
      expect(evaluationFormService.getEvaluation).toHaveBeenCalled();
      expect(evaluationService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEvaluation>>();
      const evaluation = { id: 123 };
      jest.spyOn(evaluationService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ evaluation });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(evaluationService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareEvaluationTag', () => {
      it('Should forward to evaluationTagService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(evaluationTagService, 'compareEvaluationTag');
        comp.compareEvaluationTag(entity, entity2);
        expect(evaluationTagService.compareEvaluationTag).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareEvaluationQuestion', () => {
      it('Should forward to evaluationQuestionService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(evaluationQuestionService, 'compareEvaluationQuestion');
        comp.compareEvaluationQuestion(entity, entity2);
        expect(evaluationQuestionService.compareEvaluationQuestion).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareAnsweringUser', () => {
      it('Should forward to answeringUserService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(answeringUserService, 'compareAnsweringUser');
        comp.compareAnsweringUser(entity, entity2);
        expect(answeringUserService.compareAnsweringUser).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
