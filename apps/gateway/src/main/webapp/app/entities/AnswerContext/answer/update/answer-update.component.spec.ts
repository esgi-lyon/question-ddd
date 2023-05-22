import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { AnswerFormService } from './answer-form.service';
import { AnswerService } from '../service/answer.service';
import { IAnswer } from '../answer.model';
import { IQuestionId } from 'app/entities/question-id/question-id.model';
import { QuestionIdService } from 'app/entities/question-id/service/question-id.service';
import { IAnsweredTag } from 'app/entities/answered-tag/answered-tag.model';
import { AnsweredTagService } from 'app/entities/answered-tag/service/answered-tag.service';

import { AnswerUpdateComponent } from './answer-update.component';

describe('Answer Management Update Component', () => {
  let comp: AnswerUpdateComponent;
  let fixture: ComponentFixture<AnswerUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let answerFormService: AnswerFormService;
  let answerService: AnswerService;
  let questionIdService: QuestionIdService;
  let answeredTagService: AnsweredTagService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [AnswerUpdateComponent],
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
      .overrideTemplate(AnswerUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AnswerUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    answerFormService = TestBed.inject(AnswerFormService);
    answerService = TestBed.inject(AnswerService);
    questionIdService = TestBed.inject(QuestionIdService);
    answeredTagService = TestBed.inject(AnsweredTagService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call QuestionId query and add missing value', () => {
      const answer: IAnswer = { id: 456 };
      const question: IQuestionId = { id: 54040 };
      answer.question = question;

      const questionIdCollection: IQuestionId[] = [{ id: 20257 }];
      jest.spyOn(questionIdService, 'query').mockReturnValue(of(new HttpResponse({ body: questionIdCollection })));
      const additionalQuestionIds = [question];
      const expectedCollection: IQuestionId[] = [...additionalQuestionIds, ...questionIdCollection];
      jest.spyOn(questionIdService, 'addQuestionIdToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ answer });
      comp.ngOnInit();

      expect(questionIdService.query).toHaveBeenCalled();
      expect(questionIdService.addQuestionIdToCollectionIfMissing).toHaveBeenCalledWith(
        questionIdCollection,
        ...additionalQuestionIds.map(expect.objectContaining)
      );
      expect(comp.questionIdsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call AnsweredTag query and add missing value', () => {
      const answer: IAnswer = { id: 456 };
      const answeredTag: IAnsweredTag = { id: 62595 };
      answer.answeredTag = answeredTag;

      const answeredTagCollection: IAnsweredTag[] = [{ id: 73881 }];
      jest.spyOn(answeredTagService, 'query').mockReturnValue(of(new HttpResponse({ body: answeredTagCollection })));
      const additionalAnsweredTags = [answeredTag];
      const expectedCollection: IAnsweredTag[] = [...additionalAnsweredTags, ...answeredTagCollection];
      jest.spyOn(answeredTagService, 'addAnsweredTagToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ answer });
      comp.ngOnInit();

      expect(answeredTagService.query).toHaveBeenCalled();
      expect(answeredTagService.addAnsweredTagToCollectionIfMissing).toHaveBeenCalledWith(
        answeredTagCollection,
        ...additionalAnsweredTags.map(expect.objectContaining)
      );
      expect(comp.answeredTagsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const answer: IAnswer = { id: 456 };
      const question: IQuestionId = { id: 32760 };
      answer.question = question;
      const answeredTag: IAnsweredTag = { id: 56701 };
      answer.answeredTag = answeredTag;

      activatedRoute.data = of({ answer });
      comp.ngOnInit();

      expect(comp.questionIdsSharedCollection).toContain(question);
      expect(comp.answeredTagsSharedCollection).toContain(answeredTag);
      expect(comp.answer).toEqual(answer);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAnswer>>();
      const answer = { id: 123 };
      jest.spyOn(answerFormService, 'getAnswer').mockReturnValue(answer);
      jest.spyOn(answerService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ answer });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: answer }));
      saveSubject.complete();

      // THEN
      expect(answerFormService.getAnswer).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(answerService.update).toHaveBeenCalledWith(expect.objectContaining(answer));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAnswer>>();
      const answer = { id: 123 };
      jest.spyOn(answerFormService, 'getAnswer').mockReturnValue({ id: null });
      jest.spyOn(answerService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ answer: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: answer }));
      saveSubject.complete();

      // THEN
      expect(answerFormService.getAnswer).toHaveBeenCalled();
      expect(answerService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAnswer>>();
      const answer = { id: 123 };
      jest.spyOn(answerService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ answer });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(answerService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareQuestionId', () => {
      it('Should forward to questionIdService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(questionIdService, 'compareQuestionId');
        comp.compareQuestionId(entity, entity2);
        expect(questionIdService.compareQuestionId).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareAnsweredTag', () => {
      it('Should forward to answeredTagService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(answeredTagService, 'compareAnsweredTag');
        comp.compareAnsweredTag(entity, entity2);
        expect(answeredTagService.compareAnsweredTag).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
