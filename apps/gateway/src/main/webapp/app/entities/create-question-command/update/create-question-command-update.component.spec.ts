import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CreateQuestionCommandFormService } from './create-question-command-form.service';
import { CreateQuestionCommandService } from '../service/create-question-command.service';
import { ICreateQuestionCommand } from '../create-question-command.model';
import { IQuestionSentQuestionResourceTagId } from 'app/entities/question-sent-question-resource-tag-id/question-sent-question-resource-tag-id.model';
import { QuestionSentQuestionResourceTagIdService } from 'app/entities/question-sent-question-resource-tag-id/service/question-sent-question-resource-tag-id.service';

import { CreateQuestionCommandUpdateComponent } from './create-question-command-update.component';

describe('CreateQuestionCommand Management Update Component', () => {
  let comp: CreateQuestionCommandUpdateComponent;
  let fixture: ComponentFixture<CreateQuestionCommandUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let createQuestionCommandFormService: CreateQuestionCommandFormService;
  let createQuestionCommandService: CreateQuestionCommandService;
  let questionSentQuestionResourceTagIdService: QuestionSentQuestionResourceTagIdService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [CreateQuestionCommandUpdateComponent],
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
      .overrideTemplate(CreateQuestionCommandUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CreateQuestionCommandUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    createQuestionCommandFormService = TestBed.inject(CreateQuestionCommandFormService);
    createQuestionCommandService = TestBed.inject(CreateQuestionCommandService);
    questionSentQuestionResourceTagIdService = TestBed.inject(QuestionSentQuestionResourceTagIdService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call QuestionSentQuestionResourceTagId query and add missing value', () => {
      const createQuestionCommand: ICreateQuestionCommand = { id: 456 };
      const resource: IQuestionSentQuestionResourceTagId = { id: 84522 };
      createQuestionCommand.resource = resource;

      const questionSentQuestionResourceTagIdCollection: IQuestionSentQuestionResourceTagId[] = [{ id: 94286 }];
      jest
        .spyOn(questionSentQuestionResourceTagIdService, 'query')
        .mockReturnValue(of(new HttpResponse({ body: questionSentQuestionResourceTagIdCollection })));
      const additionalQuestionSentQuestionResourceTagIds = [resource];
      const expectedCollection: IQuestionSentQuestionResourceTagId[] = [
        ...additionalQuestionSentQuestionResourceTagIds,
        ...questionSentQuestionResourceTagIdCollection,
      ];
      jest
        .spyOn(questionSentQuestionResourceTagIdService, 'addQuestionSentQuestionResourceTagIdToCollectionIfMissing')
        .mockReturnValue(expectedCollection);

      activatedRoute.data = of({ createQuestionCommand });
      comp.ngOnInit();

      expect(questionSentQuestionResourceTagIdService.query).toHaveBeenCalled();
      expect(questionSentQuestionResourceTagIdService.addQuestionSentQuestionResourceTagIdToCollectionIfMissing).toHaveBeenCalledWith(
        questionSentQuestionResourceTagIdCollection,
        ...additionalQuestionSentQuestionResourceTagIds.map(expect.objectContaining)
      );
      expect(comp.questionSentQuestionResourceTagIdsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const createQuestionCommand: ICreateQuestionCommand = { id: 456 };
      const resource: IQuestionSentQuestionResourceTagId = { id: 95966 };
      createQuestionCommand.resource = resource;

      activatedRoute.data = of({ createQuestionCommand });
      comp.ngOnInit();

      expect(comp.questionSentQuestionResourceTagIdsSharedCollection).toContain(resource);
      expect(comp.createQuestionCommand).toEqual(createQuestionCommand);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreateQuestionCommand>>();
      const createQuestionCommand = { id: 123 };
      jest.spyOn(createQuestionCommandFormService, 'getCreateQuestionCommand').mockReturnValue(createQuestionCommand);
      jest.spyOn(createQuestionCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ createQuestionCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: createQuestionCommand }));
      saveSubject.complete();

      // THEN
      expect(createQuestionCommandFormService.getCreateQuestionCommand).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(createQuestionCommandService.update).toHaveBeenCalledWith(expect.objectContaining(createQuestionCommand));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreateQuestionCommand>>();
      const createQuestionCommand = { id: 123 };
      jest.spyOn(createQuestionCommandFormService, 'getCreateQuestionCommand').mockReturnValue({ id: null });
      jest.spyOn(createQuestionCommandService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ createQuestionCommand: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: createQuestionCommand }));
      saveSubject.complete();

      // THEN
      expect(createQuestionCommandFormService.getCreateQuestionCommand).toHaveBeenCalled();
      expect(createQuestionCommandService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreateQuestionCommand>>();
      const createQuestionCommand = { id: 123 };
      jest.spyOn(createQuestionCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ createQuestionCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(createQuestionCommandService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareQuestionSentQuestionResourceTagId', () => {
      it('Should forward to questionSentQuestionResourceTagIdService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(questionSentQuestionResourceTagIdService, 'compareQuestionSentQuestionResourceTagId');
        comp.compareQuestionSentQuestionResourceTagId(entity, entity2);
        expect(questionSentQuestionResourceTagIdService.compareQuestionSentQuestionResourceTagId).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
