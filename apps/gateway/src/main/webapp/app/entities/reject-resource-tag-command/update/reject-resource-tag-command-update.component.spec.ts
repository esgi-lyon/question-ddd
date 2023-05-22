import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { RejectResourceTagCommandFormService } from './reject-resource-tag-command-form.service';
import { RejectResourceTagCommandService } from '../service/reject-resource-tag-command.service';
import { IRejectResourceTagCommand } from '../reject-resource-tag-command.model';
import { IQuestionResource } from 'app/entities/QuestionContext/question-resource/question-resource.model';
import { QuestionResourceService } from 'app/entities/QuestionContext/question-resource/service/question-resource.service';

import { RejectResourceTagCommandUpdateComponent } from './reject-resource-tag-command-update.component';

describe('RejectResourceTagCommand Management Update Component', () => {
  let comp: RejectResourceTagCommandUpdateComponent;
  let fixture: ComponentFixture<RejectResourceTagCommandUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let rejectResourceTagCommandFormService: RejectResourceTagCommandFormService;
  let rejectResourceTagCommandService: RejectResourceTagCommandService;
  let questionResourceService: QuestionResourceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [RejectResourceTagCommandUpdateComponent],
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
      .overrideTemplate(RejectResourceTagCommandUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RejectResourceTagCommandUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    rejectResourceTagCommandFormService = TestBed.inject(RejectResourceTagCommandFormService);
    rejectResourceTagCommandService = TestBed.inject(RejectResourceTagCommandService);
    questionResourceService = TestBed.inject(QuestionResourceService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call QuestionResource query and add missing value', () => {
      const rejectResourceTagCommand: IRejectResourceTagCommand = { id: 456 };
      const questionId: IQuestionResource = { id: 22137 };
      rejectResourceTagCommand.questionId = questionId;

      const questionResourceCollection: IQuestionResource[] = [{ id: 56682 }];
      jest.spyOn(questionResourceService, 'query').mockReturnValue(of(new HttpResponse({ body: questionResourceCollection })));
      const additionalQuestionResources = [questionId];
      const expectedCollection: IQuestionResource[] = [...additionalQuestionResources, ...questionResourceCollection];
      jest.spyOn(questionResourceService, 'addQuestionResourceToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ rejectResourceTagCommand });
      comp.ngOnInit();

      expect(questionResourceService.query).toHaveBeenCalled();
      expect(questionResourceService.addQuestionResourceToCollectionIfMissing).toHaveBeenCalledWith(
        questionResourceCollection,
        ...additionalQuestionResources.map(expect.objectContaining)
      );
      expect(comp.questionResourcesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const rejectResourceTagCommand: IRejectResourceTagCommand = { id: 456 };
      const questionId: IQuestionResource = { id: 24402 };
      rejectResourceTagCommand.questionId = questionId;

      activatedRoute.data = of({ rejectResourceTagCommand });
      comp.ngOnInit();

      expect(comp.questionResourcesSharedCollection).toContain(questionId);
      expect(comp.rejectResourceTagCommand).toEqual(rejectResourceTagCommand);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRejectResourceTagCommand>>();
      const rejectResourceTagCommand = { id: 123 };
      jest.spyOn(rejectResourceTagCommandFormService, 'getRejectResourceTagCommand').mockReturnValue(rejectResourceTagCommand);
      jest.spyOn(rejectResourceTagCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ rejectResourceTagCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: rejectResourceTagCommand }));
      saveSubject.complete();

      // THEN
      expect(rejectResourceTagCommandFormService.getRejectResourceTagCommand).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(rejectResourceTagCommandService.update).toHaveBeenCalledWith(expect.objectContaining(rejectResourceTagCommand));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRejectResourceTagCommand>>();
      const rejectResourceTagCommand = { id: 123 };
      jest.spyOn(rejectResourceTagCommandFormService, 'getRejectResourceTagCommand').mockReturnValue({ id: null });
      jest.spyOn(rejectResourceTagCommandService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ rejectResourceTagCommand: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: rejectResourceTagCommand }));
      saveSubject.complete();

      // THEN
      expect(rejectResourceTagCommandFormService.getRejectResourceTagCommand).toHaveBeenCalled();
      expect(rejectResourceTagCommandService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRejectResourceTagCommand>>();
      const rejectResourceTagCommand = { id: 123 };
      jest.spyOn(rejectResourceTagCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ rejectResourceTagCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(rejectResourceTagCommandService.update).toHaveBeenCalled();
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
