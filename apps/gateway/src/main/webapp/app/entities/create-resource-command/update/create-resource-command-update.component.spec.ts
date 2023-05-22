import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CreateResourceCommandFormService } from './create-resource-command-form.service';
import { CreateResourceCommandService } from '../service/create-resource-command.service';
import { ICreateResourceCommand } from '../create-resource-command.model';
import { IQuestionResource } from 'app/entities/QuestionContext/question-resource/question-resource.model';
import { QuestionResourceService } from 'app/entities/QuestionContext/question-resource/service/question-resource.service';

import { CreateResourceCommandUpdateComponent } from './create-resource-command-update.component';

describe('CreateResourceCommand Management Update Component', () => {
  let comp: CreateResourceCommandUpdateComponent;
  let fixture: ComponentFixture<CreateResourceCommandUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let createResourceCommandFormService: CreateResourceCommandFormService;
  let createResourceCommandService: CreateResourceCommandService;
  let questionResourceService: QuestionResourceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [CreateResourceCommandUpdateComponent],
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
      .overrideTemplate(CreateResourceCommandUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CreateResourceCommandUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    createResourceCommandFormService = TestBed.inject(CreateResourceCommandFormService);
    createResourceCommandService = TestBed.inject(CreateResourceCommandService);
    questionResourceService = TestBed.inject(QuestionResourceService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call QuestionResource query and add missing value', () => {
      const createResourceCommand: ICreateResourceCommand = { id: 456 };
      const questionId: IQuestionResource = { id: 11081 };
      createResourceCommand.questionId = questionId;

      const questionResourceCollection: IQuestionResource[] = [{ id: 14857 }];
      jest.spyOn(questionResourceService, 'query').mockReturnValue(of(new HttpResponse({ body: questionResourceCollection })));
      const additionalQuestionResources = [questionId];
      const expectedCollection: IQuestionResource[] = [...additionalQuestionResources, ...questionResourceCollection];
      jest.spyOn(questionResourceService, 'addQuestionResourceToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ createResourceCommand });
      comp.ngOnInit();

      expect(questionResourceService.query).toHaveBeenCalled();
      expect(questionResourceService.addQuestionResourceToCollectionIfMissing).toHaveBeenCalledWith(
        questionResourceCollection,
        ...additionalQuestionResources.map(expect.objectContaining)
      );
      expect(comp.questionResourcesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const createResourceCommand: ICreateResourceCommand = { id: 456 };
      const questionId: IQuestionResource = { id: 66374 };
      createResourceCommand.questionId = questionId;

      activatedRoute.data = of({ createResourceCommand });
      comp.ngOnInit();

      expect(comp.questionResourcesSharedCollection).toContain(questionId);
      expect(comp.createResourceCommand).toEqual(createResourceCommand);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreateResourceCommand>>();
      const createResourceCommand = { id: 123 };
      jest.spyOn(createResourceCommandFormService, 'getCreateResourceCommand').mockReturnValue(createResourceCommand);
      jest.spyOn(createResourceCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ createResourceCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: createResourceCommand }));
      saveSubject.complete();

      // THEN
      expect(createResourceCommandFormService.getCreateResourceCommand).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(createResourceCommandService.update).toHaveBeenCalledWith(expect.objectContaining(createResourceCommand));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreateResourceCommand>>();
      const createResourceCommand = { id: 123 };
      jest.spyOn(createResourceCommandFormService, 'getCreateResourceCommand').mockReturnValue({ id: null });
      jest.spyOn(createResourceCommandService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ createResourceCommand: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: createResourceCommand }));
      saveSubject.complete();

      // THEN
      expect(createResourceCommandFormService.getCreateResourceCommand).toHaveBeenCalled();
      expect(createResourceCommandService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreateResourceCommand>>();
      const createResourceCommand = { id: 123 };
      jest.spyOn(createResourceCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ createResourceCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(createResourceCommandService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareQuestionResource', () => {
      it('Should forward to questionResourceService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(questionResourceService, 'compareQuestionResource');
        comp.compareQuestionResource(entity, entity2);
        expect(questionResourceService.compareQuestionResource).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
