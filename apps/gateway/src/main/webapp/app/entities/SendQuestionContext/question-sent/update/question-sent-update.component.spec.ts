import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { QuestionSentFormService } from './question-sent-form.service';
import { QuestionSentService } from '../service/question-sent.service';
import { IQuestionSent } from '../question-sent.model';

import { QuestionSentUpdateComponent } from './question-sent-update.component';

describe('QuestionSent Management Update Component', () => {
  let comp: QuestionSentUpdateComponent;
  let fixture: ComponentFixture<QuestionSentUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let questionSentFormService: QuestionSentFormService;
  let questionSentService: QuestionSentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [QuestionSentUpdateComponent],
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
      .overrideTemplate(QuestionSentUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(QuestionSentUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    questionSentFormService = TestBed.inject(QuestionSentFormService);
    questionSentService = TestBed.inject(QuestionSentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const questionSent: IQuestionSent = { id: 456 };

      activatedRoute.data = of({ questionSent });
      comp.ngOnInit();

      expect(comp.questionSent).toEqual(questionSent);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IQuestionSent>>();
      const questionSent = { id: 123 };
      jest.spyOn(questionSentFormService, 'getQuestionSent').mockReturnValue(questionSent);
      jest.spyOn(questionSentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ questionSent });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: questionSent }));
      saveSubject.complete();

      // THEN
      expect(questionSentFormService.getQuestionSent).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(questionSentService.update).toHaveBeenCalledWith(expect.objectContaining(questionSent));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IQuestionSent>>();
      const questionSent = { id: 123 };
      jest.spyOn(questionSentFormService, 'getQuestionSent').mockReturnValue({ id: null });
      jest.spyOn(questionSentService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ questionSent: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: questionSent }));
      saveSubject.complete();

      // THEN
      expect(questionSentFormService.getQuestionSent).toHaveBeenCalled();
      expect(questionSentService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IQuestionSent>>();
      const questionSent = { id: 123 };
      jest.spyOn(questionSentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ questionSent });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(questionSentService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
