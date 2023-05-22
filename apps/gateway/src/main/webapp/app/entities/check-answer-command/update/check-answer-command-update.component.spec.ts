import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CheckAnswerCommandFormService } from './check-answer-command-form.service';
import { CheckAnswerCommandService } from '../service/check-answer-command.service';
import { ICheckAnswerCommand } from '../check-answer-command.model';
import { IEvaluatedAnswer } from 'app/entities/evaluated-answer/evaluated-answer.model';
import { EvaluatedAnswerService } from 'app/entities/evaluated-answer/service/evaluated-answer.service';

import { CheckAnswerCommandUpdateComponent } from './check-answer-command-update.component';

describe('CheckAnswerCommand Management Update Component', () => {
  let comp: CheckAnswerCommandUpdateComponent;
  let fixture: ComponentFixture<CheckAnswerCommandUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let checkAnswerCommandFormService: CheckAnswerCommandFormService;
  let checkAnswerCommandService: CheckAnswerCommandService;
  let evaluatedAnswerService: EvaluatedAnswerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [CheckAnswerCommandUpdateComponent],
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
      .overrideTemplate(CheckAnswerCommandUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CheckAnswerCommandUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    checkAnswerCommandFormService = TestBed.inject(CheckAnswerCommandFormService);
    checkAnswerCommandService = TestBed.inject(CheckAnswerCommandService);
    evaluatedAnswerService = TestBed.inject(EvaluatedAnswerService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call EvaluatedAnswer query and add missing value', () => {
      const checkAnswerCommand: ICheckAnswerCommand = { id: 456 };
      const answer: IEvaluatedAnswer = { id: 4421 };
      checkAnswerCommand.answer = answer;

      const evaluatedAnswerCollection: IEvaluatedAnswer[] = [{ id: 93535 }];
      jest.spyOn(evaluatedAnswerService, 'query').mockReturnValue(of(new HttpResponse({ body: evaluatedAnswerCollection })));
      const additionalEvaluatedAnswers = [answer];
      const expectedCollection: IEvaluatedAnswer[] = [...additionalEvaluatedAnswers, ...evaluatedAnswerCollection];
      jest.spyOn(evaluatedAnswerService, 'addEvaluatedAnswerToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ checkAnswerCommand });
      comp.ngOnInit();

      expect(evaluatedAnswerService.query).toHaveBeenCalled();
      expect(evaluatedAnswerService.addEvaluatedAnswerToCollectionIfMissing).toHaveBeenCalledWith(
        evaluatedAnswerCollection,
        ...additionalEvaluatedAnswers.map(expect.objectContaining)
      );
      expect(comp.evaluatedAnswersSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const checkAnswerCommand: ICheckAnswerCommand = { id: 456 };
      const answer: IEvaluatedAnswer = { id: 13182 };
      checkAnswerCommand.answer = answer;

      activatedRoute.data = of({ checkAnswerCommand });
      comp.ngOnInit();

      expect(comp.evaluatedAnswersSharedCollection).toContain(answer);
      expect(comp.checkAnswerCommand).toEqual(checkAnswerCommand);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICheckAnswerCommand>>();
      const checkAnswerCommand = { id: 123 };
      jest.spyOn(checkAnswerCommandFormService, 'getCheckAnswerCommand').mockReturnValue(checkAnswerCommand);
      jest.spyOn(checkAnswerCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ checkAnswerCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: checkAnswerCommand }));
      saveSubject.complete();

      // THEN
      expect(checkAnswerCommandFormService.getCheckAnswerCommand).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(checkAnswerCommandService.update).toHaveBeenCalledWith(expect.objectContaining(checkAnswerCommand));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICheckAnswerCommand>>();
      const checkAnswerCommand = { id: 123 };
      jest.spyOn(checkAnswerCommandFormService, 'getCheckAnswerCommand').mockReturnValue({ id: null });
      jest.spyOn(checkAnswerCommandService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ checkAnswerCommand: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: checkAnswerCommand }));
      saveSubject.complete();

      // THEN
      expect(checkAnswerCommandFormService.getCheckAnswerCommand).toHaveBeenCalled();
      expect(checkAnswerCommandService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICheckAnswerCommand>>();
      const checkAnswerCommand = { id: 123 };
      jest.spyOn(checkAnswerCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ checkAnswerCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(checkAnswerCommandService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareEvaluatedAnswer', () => {
      it('Should forward to evaluatedAnswerService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(evaluatedAnswerService, 'compareEvaluatedAnswer');
        comp.compareEvaluatedAnswer(entity, entity2);
        expect(evaluatedAnswerService.compareEvaluatedAnswer).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
