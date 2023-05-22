import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { SendQuestionByTagsPreferencesCommandFormService } from './send-question-by-tags-preferences-command-form.service';
import { SendQuestionByTagsPreferencesCommandService } from '../service/send-question-by-tags-preferences-command.service';
import { ISendQuestionByTagsPreferencesCommand } from '../send-question-by-tags-preferences-command.model';
import { IQuestionSent } from 'app/entities/SendQuestionContext/question-sent/question-sent.model';
import { QuestionSentService } from 'app/entities/SendQuestionContext/question-sent/service/question-sent.service';

import { SendQuestionByTagsPreferencesCommandUpdateComponent } from './send-question-by-tags-preferences-command-update.component';

describe('SendQuestionByTagsPreferencesCommand Management Update Component', () => {
  let comp: SendQuestionByTagsPreferencesCommandUpdateComponent;
  let fixture: ComponentFixture<SendQuestionByTagsPreferencesCommandUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let sendQuestionByTagsPreferencesCommandFormService: SendQuestionByTagsPreferencesCommandFormService;
  let sendQuestionByTagsPreferencesCommandService: SendQuestionByTagsPreferencesCommandService;
  let questionSentService: QuestionSentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [SendQuestionByTagsPreferencesCommandUpdateComponent],
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
      .overrideTemplate(SendQuestionByTagsPreferencesCommandUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SendQuestionByTagsPreferencesCommandUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    sendQuestionByTagsPreferencesCommandFormService = TestBed.inject(SendQuestionByTagsPreferencesCommandFormService);
    sendQuestionByTagsPreferencesCommandService = TestBed.inject(SendQuestionByTagsPreferencesCommandService);
    questionSentService = TestBed.inject(QuestionSentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call QuestionSent query and add missing value', () => {
      const sendQuestionByTagsPreferencesCommand: ISendQuestionByTagsPreferencesCommand = { id: 456 };
      const questionSent: IQuestionSent = { id: 55494 };
      sendQuestionByTagsPreferencesCommand.questionSent = questionSent;

      const questionSentCollection: IQuestionSent[] = [{ id: 40025 }];
      jest.spyOn(questionSentService, 'query').mockReturnValue(of(new HttpResponse({ body: questionSentCollection })));
      const additionalQuestionSents = [questionSent];
      const expectedCollection: IQuestionSent[] = [...additionalQuestionSents, ...questionSentCollection];
      jest.spyOn(questionSentService, 'addQuestionSentToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ sendQuestionByTagsPreferencesCommand });
      comp.ngOnInit();

      expect(questionSentService.query).toHaveBeenCalled();
      expect(questionSentService.addQuestionSentToCollectionIfMissing).toHaveBeenCalledWith(
        questionSentCollection,
        ...additionalQuestionSents.map(expect.objectContaining)
      );
      expect(comp.questionSentsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const sendQuestionByTagsPreferencesCommand: ISendQuestionByTagsPreferencesCommand = { id: 456 };
      const questionSent: IQuestionSent = { id: 4422 };
      sendQuestionByTagsPreferencesCommand.questionSent = questionSent;

      activatedRoute.data = of({ sendQuestionByTagsPreferencesCommand });
      comp.ngOnInit();

      expect(comp.questionSentsSharedCollection).toContain(questionSent);
      expect(comp.sendQuestionByTagsPreferencesCommand).toEqual(sendQuestionByTagsPreferencesCommand);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISendQuestionByTagsPreferencesCommand>>();
      const sendQuestionByTagsPreferencesCommand = { id: 123 };
      jest
        .spyOn(sendQuestionByTagsPreferencesCommandFormService, 'getSendQuestionByTagsPreferencesCommand')
        .mockReturnValue(sendQuestionByTagsPreferencesCommand);
      jest.spyOn(sendQuestionByTagsPreferencesCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sendQuestionByTagsPreferencesCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sendQuestionByTagsPreferencesCommand }));
      saveSubject.complete();

      // THEN
      expect(sendQuestionByTagsPreferencesCommandFormService.getSendQuestionByTagsPreferencesCommand).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(sendQuestionByTagsPreferencesCommandService.update).toHaveBeenCalledWith(
        expect.objectContaining(sendQuestionByTagsPreferencesCommand)
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISendQuestionByTagsPreferencesCommand>>();
      const sendQuestionByTagsPreferencesCommand = { id: 123 };
      jest.spyOn(sendQuestionByTagsPreferencesCommandFormService, 'getSendQuestionByTagsPreferencesCommand').mockReturnValue({ id: null });
      jest.spyOn(sendQuestionByTagsPreferencesCommandService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sendQuestionByTagsPreferencesCommand: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sendQuestionByTagsPreferencesCommand }));
      saveSubject.complete();

      // THEN
      expect(sendQuestionByTagsPreferencesCommandFormService.getSendQuestionByTagsPreferencesCommand).toHaveBeenCalled();
      expect(sendQuestionByTagsPreferencesCommandService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISendQuestionByTagsPreferencesCommand>>();
      const sendQuestionByTagsPreferencesCommand = { id: 123 };
      jest.spyOn(sendQuestionByTagsPreferencesCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sendQuestionByTagsPreferencesCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(sendQuestionByTagsPreferencesCommandService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareQuestionSent', () => {
      it('Should forward to questionSentService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(questionSentService, 'compareQuestionSent');
        comp.compareQuestionSent(entity, entity2);
        expect(questionSentService.compareQuestionSent).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
