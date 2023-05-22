import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { AnswerSubmitCommandFormService } from './answer-submit-command-form.service';
import { AnswerSubmitCommandService } from '../service/answer-submit-command.service';
import { IAnswerSubmitCommand } from '../answer-submit-command.model';
import { IAnswer } from 'app/entities/AnswerContext/answer/answer.model';
import { AnswerService } from 'app/entities/AnswerContext/answer/service/answer.service';

import { AnswerSubmitCommandUpdateComponent } from './answer-submit-command-update.component';

describe('AnswerSubmitCommand Management Update Component', () => {
  let comp: AnswerSubmitCommandUpdateComponent;
  let fixture: ComponentFixture<AnswerSubmitCommandUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let answerSubmitCommandFormService: AnswerSubmitCommandFormService;
  let answerSubmitCommandService: AnswerSubmitCommandService;
  let answerService: AnswerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [AnswerSubmitCommandUpdateComponent],
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
      .overrideTemplate(AnswerSubmitCommandUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AnswerSubmitCommandUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    answerSubmitCommandFormService = TestBed.inject(AnswerSubmitCommandFormService);
    answerSubmitCommandService = TestBed.inject(AnswerSubmitCommandService);
    answerService = TestBed.inject(AnswerService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Answer query and add missing value', () => {
      const answerSubmitCommand: IAnswerSubmitCommand = { id: 456 };
      const answer: IAnswer = { id: 51389 };
      answerSubmitCommand.answer = answer;

      const answerCollection: IAnswer[] = [{ id: 92199 }];
      jest.spyOn(answerService, 'query').mockReturnValue(of(new HttpResponse({ body: answerCollection })));
      const additionalAnswers = [answer];
      const expectedCollection: IAnswer[] = [...additionalAnswers, ...answerCollection];
      jest.spyOn(answerService, 'addAnswerToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ answerSubmitCommand });
      comp.ngOnInit();

      expect(answerService.query).toHaveBeenCalled();
      expect(answerService.addAnswerToCollectionIfMissing).toHaveBeenCalledWith(
        answerCollection,
        ...additionalAnswers.map(expect.objectContaining)
      );
      expect(comp.answersSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const answerSubmitCommand: IAnswerSubmitCommand = { id: 456 };
      const answer: IAnswer = { id: 3867 };
      answerSubmitCommand.answer = answer;

      activatedRoute.data = of({ answerSubmitCommand });
      comp.ngOnInit();

      expect(comp.answersSharedCollection).toContain(answer);
      expect(comp.answerSubmitCommand).toEqual(answerSubmitCommand);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAnswerSubmitCommand>>();
      const answerSubmitCommand = { id: 123 };
      jest.spyOn(answerSubmitCommandFormService, 'getAnswerSubmitCommand').mockReturnValue(answerSubmitCommand);
      jest.spyOn(answerSubmitCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ answerSubmitCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: answerSubmitCommand }));
      saveSubject.complete();

      // THEN
      expect(answerSubmitCommandFormService.getAnswerSubmitCommand).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(answerSubmitCommandService.update).toHaveBeenCalledWith(expect.objectContaining(answerSubmitCommand));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAnswerSubmitCommand>>();
      const answerSubmitCommand = { id: 123 };
      jest.spyOn(answerSubmitCommandFormService, 'getAnswerSubmitCommand').mockReturnValue({ id: null });
      jest.spyOn(answerSubmitCommandService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ answerSubmitCommand: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: answerSubmitCommand }));
      saveSubject.complete();

      // THEN
      expect(answerSubmitCommandFormService.getAnswerSubmitCommand).toHaveBeenCalled();
      expect(answerSubmitCommandService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAnswerSubmitCommand>>();
      const answerSubmitCommand = { id: 123 };
      jest.spyOn(answerSubmitCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ answerSubmitCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(answerSubmitCommandService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareAnswer', () => {
      it('Should forward to answerService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(answerService, 'compareAnswer');
        comp.compareAnswer(entity, entity2);
        expect(answerService.compareAnswer).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
