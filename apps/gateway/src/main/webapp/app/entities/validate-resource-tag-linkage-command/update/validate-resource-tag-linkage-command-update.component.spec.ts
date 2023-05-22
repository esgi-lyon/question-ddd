import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ValidateResourceTagLinkageCommandFormService } from './validate-resource-tag-linkage-command-form.service';
import { ValidateResourceTagLinkageCommandService } from '../service/validate-resource-tag-linkage-command.service';
import { IValidateResourceTagLinkageCommand } from '../validate-resource-tag-linkage-command.model';
import { IQuestionResource } from 'app/entities/QuestionContext/question-resource/question-resource.model';
import { QuestionResourceService } from 'app/entities/QuestionContext/question-resource/service/question-resource.service';

import { ValidateResourceTagLinkageCommandUpdateComponent } from './validate-resource-tag-linkage-command-update.component';

describe('ValidateResourceTagLinkageCommand Management Update Component', () => {
  let comp: ValidateResourceTagLinkageCommandUpdateComponent;
  let fixture: ComponentFixture<ValidateResourceTagLinkageCommandUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let validateResourceTagLinkageCommandFormService: ValidateResourceTagLinkageCommandFormService;
  let validateResourceTagLinkageCommandService: ValidateResourceTagLinkageCommandService;
  let questionResourceService: QuestionResourceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ValidateResourceTagLinkageCommandUpdateComponent],
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
      .overrideTemplate(ValidateResourceTagLinkageCommandUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ValidateResourceTagLinkageCommandUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    validateResourceTagLinkageCommandFormService = TestBed.inject(ValidateResourceTagLinkageCommandFormService);
    validateResourceTagLinkageCommandService = TestBed.inject(ValidateResourceTagLinkageCommandService);
    questionResourceService = TestBed.inject(QuestionResourceService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call QuestionResource query and add missing value', () => {
      const validateResourceTagLinkageCommand: IValidateResourceTagLinkageCommand = { id: 456 };
      const questionId: IQuestionResource = { id: 70377 };
      validateResourceTagLinkageCommand.questionId = questionId;

      const questionResourceCollection: IQuestionResource[] = [{ id: 5999 }];
      jest.spyOn(questionResourceService, 'query').mockReturnValue(of(new HttpResponse({ body: questionResourceCollection })));
      const additionalQuestionResources = [questionId];
      const expectedCollection: IQuestionResource[] = [...additionalQuestionResources, ...questionResourceCollection];
      jest.spyOn(questionResourceService, 'addQuestionResourceToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ validateResourceTagLinkageCommand });
      comp.ngOnInit();

      expect(questionResourceService.query).toHaveBeenCalled();
      expect(questionResourceService.addQuestionResourceToCollectionIfMissing).toHaveBeenCalledWith(
        questionResourceCollection,
        ...additionalQuestionResources.map(expect.objectContaining)
      );
      expect(comp.questionResourcesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const validateResourceTagLinkageCommand: IValidateResourceTagLinkageCommand = { id: 456 };
      const questionId: IQuestionResource = { id: 755 };
      validateResourceTagLinkageCommand.questionId = questionId;

      activatedRoute.data = of({ validateResourceTagLinkageCommand });
      comp.ngOnInit();

      expect(comp.questionResourcesSharedCollection).toContain(questionId);
      expect(comp.validateResourceTagLinkageCommand).toEqual(validateResourceTagLinkageCommand);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IValidateResourceTagLinkageCommand>>();
      const validateResourceTagLinkageCommand = { id: 123 };
      jest
        .spyOn(validateResourceTagLinkageCommandFormService, 'getValidateResourceTagLinkageCommand')
        .mockReturnValue(validateResourceTagLinkageCommand);
      jest.spyOn(validateResourceTagLinkageCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ validateResourceTagLinkageCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: validateResourceTagLinkageCommand }));
      saveSubject.complete();

      // THEN
      expect(validateResourceTagLinkageCommandFormService.getValidateResourceTagLinkageCommand).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(validateResourceTagLinkageCommandService.update).toHaveBeenCalledWith(
        expect.objectContaining(validateResourceTagLinkageCommand)
      );
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IValidateResourceTagLinkageCommand>>();
      const validateResourceTagLinkageCommand = { id: 123 };
      jest.spyOn(validateResourceTagLinkageCommandFormService, 'getValidateResourceTagLinkageCommand').mockReturnValue({ id: null });
      jest.spyOn(validateResourceTagLinkageCommandService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ validateResourceTagLinkageCommand: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: validateResourceTagLinkageCommand }));
      saveSubject.complete();

      // THEN
      expect(validateResourceTagLinkageCommandFormService.getValidateResourceTagLinkageCommand).toHaveBeenCalled();
      expect(validateResourceTagLinkageCommandService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IValidateResourceTagLinkageCommand>>();
      const validateResourceTagLinkageCommand = { id: 123 };
      jest.spyOn(validateResourceTagLinkageCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ validateResourceTagLinkageCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(validateResourceTagLinkageCommandService.update).toHaveBeenCalled();
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
