import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CreateEvaluationCommandFormService } from './create-evaluation-command-form.service';
import { CreateEvaluationCommandService } from '../service/create-evaluation-command.service';
import { ICreateEvaluationCommand } from '../create-evaluation-command.model';
import { IEvaluatedAnswer } from 'app/entities/evaluated-answer/evaluated-answer.model';
import { EvaluatedAnswerService } from 'app/entities/evaluated-answer/service/evaluated-answer.service';

import { CreateEvaluationCommandUpdateComponent } from './create-evaluation-command-update.component';

describe('CreateEvaluationCommand Management Update Component', () => {
  let comp: CreateEvaluationCommandUpdateComponent;
  let fixture: ComponentFixture<CreateEvaluationCommandUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let createEvaluationCommandFormService: CreateEvaluationCommandFormService;
  let createEvaluationCommandService: CreateEvaluationCommandService;
  let evaluatedAnswerService: EvaluatedAnswerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [CreateEvaluationCommandUpdateComponent],
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
      .overrideTemplate(CreateEvaluationCommandUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CreateEvaluationCommandUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    createEvaluationCommandFormService = TestBed.inject(CreateEvaluationCommandFormService);
    createEvaluationCommandService = TestBed.inject(CreateEvaluationCommandService);
    evaluatedAnswerService = TestBed.inject(EvaluatedAnswerService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call EvaluatedAnswer query and add missing value', () => {
      const createEvaluationCommand: ICreateEvaluationCommand = { id: 456 };
      const answer: IEvaluatedAnswer = { id: 24708 };
      createEvaluationCommand.answer = answer;

      const evaluatedAnswerCollection: IEvaluatedAnswer[] = [{ id: 63728 }];
      jest.spyOn(evaluatedAnswerService, 'query').mockReturnValue(of(new HttpResponse({ body: evaluatedAnswerCollection })));
      const additionalEvaluatedAnswers = [answer];
      const expectedCollection: IEvaluatedAnswer[] = [...additionalEvaluatedAnswers, ...evaluatedAnswerCollection];
      jest.spyOn(evaluatedAnswerService, 'addEvaluatedAnswerToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ createEvaluationCommand });
      comp.ngOnInit();

      expect(evaluatedAnswerService.query).toHaveBeenCalled();
      expect(evaluatedAnswerService.addEvaluatedAnswerToCollectionIfMissing).toHaveBeenCalledWith(
        evaluatedAnswerCollection,
        ...additionalEvaluatedAnswers.map(expect.objectContaining)
      );
      expect(comp.evaluatedAnswersSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const createEvaluationCommand: ICreateEvaluationCommand = { id: 456 };
      const answer: IEvaluatedAnswer = { id: 54299 };
      createEvaluationCommand.answer = answer;

      activatedRoute.data = of({ createEvaluationCommand });
      comp.ngOnInit();

      expect(comp.evaluatedAnswersSharedCollection).toContain(answer);
      expect(comp.createEvaluationCommand).toEqual(createEvaluationCommand);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreateEvaluationCommand>>();
      const createEvaluationCommand = { id: 123 };
      jest.spyOn(createEvaluationCommandFormService, 'getCreateEvaluationCommand').mockReturnValue(createEvaluationCommand);
      jest.spyOn(createEvaluationCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ createEvaluationCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: createEvaluationCommand }));
      saveSubject.complete();

      // THEN
      expect(createEvaluationCommandFormService.getCreateEvaluationCommand).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(createEvaluationCommandService.update).toHaveBeenCalledWith(expect.objectContaining(createEvaluationCommand));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreateEvaluationCommand>>();
      const createEvaluationCommand = { id: 123 };
      jest.spyOn(createEvaluationCommandFormService, 'getCreateEvaluationCommand').mockReturnValue({ id: null });
      jest.spyOn(createEvaluationCommandService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ createEvaluationCommand: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: createEvaluationCommand }));
      saveSubject.complete();

      // THEN
      expect(createEvaluationCommandFormService.getCreateEvaluationCommand).toHaveBeenCalled();
      expect(createEvaluationCommandService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreateEvaluationCommand>>();
      const createEvaluationCommand = { id: 123 };
      jest.spyOn(createEvaluationCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ createEvaluationCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(createEvaluationCommandService.update).toHaveBeenCalled();
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
