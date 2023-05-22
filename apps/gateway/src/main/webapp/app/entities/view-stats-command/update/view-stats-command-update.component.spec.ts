import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ViewStatsCommandFormService } from './view-stats-command-form.service';
import { ViewStatsCommandService } from '../service/view-stats-command.service';
import { IViewStatsCommand } from '../view-stats-command.model';
import { IStatisticSubjectUser } from 'app/entities/statistic-subject-user/statistic-subject-user.model';
import { StatisticSubjectUserService } from 'app/entities/statistic-subject-user/service/statistic-subject-user.service';
import { IStatisticSubjectQuestion } from 'app/entities/statistic-subject-question/statistic-subject-question.model';
import { StatisticSubjectQuestionService } from 'app/entities/statistic-subject-question/service/statistic-subject-question.service';
import { IStatisticSubjectTag } from 'app/entities/statistic-subject-tag/statistic-subject-tag.model';
import { StatisticSubjectTagService } from 'app/entities/statistic-subject-tag/service/statistic-subject-tag.service';

import { ViewStatsCommandUpdateComponent } from './view-stats-command-update.component';

describe('ViewStatsCommand Management Update Component', () => {
  let comp: ViewStatsCommandUpdateComponent;
  let fixture: ComponentFixture<ViewStatsCommandUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let viewStatsCommandFormService: ViewStatsCommandFormService;
  let viewStatsCommandService: ViewStatsCommandService;
  let statisticSubjectUserService: StatisticSubjectUserService;
  let statisticSubjectQuestionService: StatisticSubjectQuestionService;
  let statisticSubjectTagService: StatisticSubjectTagService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ViewStatsCommandUpdateComponent],
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
      .overrideTemplate(ViewStatsCommandUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ViewStatsCommandUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    viewStatsCommandFormService = TestBed.inject(ViewStatsCommandFormService);
    viewStatsCommandService = TestBed.inject(ViewStatsCommandService);
    statisticSubjectUserService = TestBed.inject(StatisticSubjectUserService);
    statisticSubjectQuestionService = TestBed.inject(StatisticSubjectQuestionService);
    statisticSubjectTagService = TestBed.inject(StatisticSubjectTagService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call StatisticSubjectUser query and add missing value', () => {
      const viewStatsCommand: IViewStatsCommand = { id: 456 };
      const user: IStatisticSubjectUser = { id: 53638 };
      viewStatsCommand.user = user;

      const statisticSubjectUserCollection: IStatisticSubjectUser[] = [{ id: 80955 }];
      jest.spyOn(statisticSubjectUserService, 'query').mockReturnValue(of(new HttpResponse({ body: statisticSubjectUserCollection })));
      const additionalStatisticSubjectUsers = [user];
      const expectedCollection: IStatisticSubjectUser[] = [...additionalStatisticSubjectUsers, ...statisticSubjectUserCollection];
      jest.spyOn(statisticSubjectUserService, 'addStatisticSubjectUserToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ viewStatsCommand });
      comp.ngOnInit();

      expect(statisticSubjectUserService.query).toHaveBeenCalled();
      expect(statisticSubjectUserService.addStatisticSubjectUserToCollectionIfMissing).toHaveBeenCalledWith(
        statisticSubjectUserCollection,
        ...additionalStatisticSubjectUsers.map(expect.objectContaining)
      );
      expect(comp.statisticSubjectUsersSharedCollection).toEqual(expectedCollection);
    });

    it('Should call StatisticSubjectQuestion query and add missing value', () => {
      const viewStatsCommand: IViewStatsCommand = { id: 456 };
      const question: IStatisticSubjectQuestion = { id: 33055 };
      viewStatsCommand.question = question;

      const statisticSubjectQuestionCollection: IStatisticSubjectQuestion[] = [{ id: 15252 }];
      jest
        .spyOn(statisticSubjectQuestionService, 'query')
        .mockReturnValue(of(new HttpResponse({ body: statisticSubjectQuestionCollection })));
      const additionalStatisticSubjectQuestions = [question];
      const expectedCollection: IStatisticSubjectQuestion[] = [
        ...additionalStatisticSubjectQuestions,
        ...statisticSubjectQuestionCollection,
      ];
      jest.spyOn(statisticSubjectQuestionService, 'addStatisticSubjectQuestionToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ viewStatsCommand });
      comp.ngOnInit();

      expect(statisticSubjectQuestionService.query).toHaveBeenCalled();
      expect(statisticSubjectQuestionService.addStatisticSubjectQuestionToCollectionIfMissing).toHaveBeenCalledWith(
        statisticSubjectQuestionCollection,
        ...additionalStatisticSubjectQuestions.map(expect.objectContaining)
      );
      expect(comp.statisticSubjectQuestionsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call StatisticSubjectTag query and add missing value', () => {
      const viewStatsCommand: IViewStatsCommand = { id: 456 };
      const tag: IStatisticSubjectTag = { id: 40651 };
      viewStatsCommand.tag = tag;

      const statisticSubjectTagCollection: IStatisticSubjectTag[] = [{ id: 94992 }];
      jest.spyOn(statisticSubjectTagService, 'query').mockReturnValue(of(new HttpResponse({ body: statisticSubjectTagCollection })));
      const additionalStatisticSubjectTags = [tag];
      const expectedCollection: IStatisticSubjectTag[] = [...additionalStatisticSubjectTags, ...statisticSubjectTagCollection];
      jest.spyOn(statisticSubjectTagService, 'addStatisticSubjectTagToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ viewStatsCommand });
      comp.ngOnInit();

      expect(statisticSubjectTagService.query).toHaveBeenCalled();
      expect(statisticSubjectTagService.addStatisticSubjectTagToCollectionIfMissing).toHaveBeenCalledWith(
        statisticSubjectTagCollection,
        ...additionalStatisticSubjectTags.map(expect.objectContaining)
      );
      expect(comp.statisticSubjectTagsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const viewStatsCommand: IViewStatsCommand = { id: 456 };
      const user: IStatisticSubjectUser = { id: 49414 };
      viewStatsCommand.user = user;
      const question: IStatisticSubjectQuestion = { id: 20685 };
      viewStatsCommand.question = question;
      const tag: IStatisticSubjectTag = { id: 73827 };
      viewStatsCommand.tag = tag;

      activatedRoute.data = of({ viewStatsCommand });
      comp.ngOnInit();

      expect(comp.statisticSubjectUsersSharedCollection).toContain(user);
      expect(comp.statisticSubjectQuestionsSharedCollection).toContain(question);
      expect(comp.statisticSubjectTagsSharedCollection).toContain(tag);
      expect(comp.viewStatsCommand).toEqual(viewStatsCommand);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IViewStatsCommand>>();
      const viewStatsCommand = { id: 123 };
      jest.spyOn(viewStatsCommandFormService, 'getViewStatsCommand').mockReturnValue(viewStatsCommand);
      jest.spyOn(viewStatsCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ viewStatsCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: viewStatsCommand }));
      saveSubject.complete();

      // THEN
      expect(viewStatsCommandFormService.getViewStatsCommand).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(viewStatsCommandService.update).toHaveBeenCalledWith(expect.objectContaining(viewStatsCommand));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IViewStatsCommand>>();
      const viewStatsCommand = { id: 123 };
      jest.spyOn(viewStatsCommandFormService, 'getViewStatsCommand').mockReturnValue({ id: null });
      jest.spyOn(viewStatsCommandService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ viewStatsCommand: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: viewStatsCommand }));
      saveSubject.complete();

      // THEN
      expect(viewStatsCommandFormService.getViewStatsCommand).toHaveBeenCalled();
      expect(viewStatsCommandService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IViewStatsCommand>>();
      const viewStatsCommand = { id: 123 };
      jest.spyOn(viewStatsCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ viewStatsCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(viewStatsCommandService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareStatisticSubjectUser', () => {
      it('Should forward to statisticSubjectUserService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(statisticSubjectUserService, 'compareStatisticSubjectUser');
        comp.compareStatisticSubjectUser(entity, entity2);
        expect(statisticSubjectUserService.compareStatisticSubjectUser).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareStatisticSubjectQuestion', () => {
      it('Should forward to statisticSubjectQuestionService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(statisticSubjectQuestionService, 'compareStatisticSubjectQuestion');
        comp.compareStatisticSubjectQuestion(entity, entity2);
        expect(statisticSubjectQuestionService.compareStatisticSubjectQuestion).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareStatisticSubjectTag', () => {
      it('Should forward to statisticSubjectTagService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(statisticSubjectTagService, 'compareStatisticSubjectTag');
        comp.compareStatisticSubjectTag(entity, entity2);
        expect(statisticSubjectTagService.compareStatisticSubjectTag).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
