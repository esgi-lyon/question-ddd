import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { LeaderBoardFormService } from './leader-board-form.service';
import { LeaderBoardService } from '../service/leader-board.service';
import { ILeaderBoard } from '../leader-board.model';
import { IStatisticSubjectTag } from 'app/entities/statistic-subject-tag/statistic-subject-tag.model';
import { StatisticSubjectTagService } from 'app/entities/statistic-subject-tag/service/statistic-subject-tag.service';

import { LeaderBoardUpdateComponent } from './leader-board-update.component';

describe('LeaderBoard Management Update Component', () => {
  let comp: LeaderBoardUpdateComponent;
  let fixture: ComponentFixture<LeaderBoardUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let leaderBoardFormService: LeaderBoardFormService;
  let leaderBoardService: LeaderBoardService;
  let statisticSubjectTagService: StatisticSubjectTagService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [LeaderBoardUpdateComponent],
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
      .overrideTemplate(LeaderBoardUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(LeaderBoardUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    leaderBoardFormService = TestBed.inject(LeaderBoardFormService);
    leaderBoardService = TestBed.inject(LeaderBoardService);
    statisticSubjectTagService = TestBed.inject(StatisticSubjectTagService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call StatisticSubjectTag query and add missing value', () => {
      const leaderBoard: ILeaderBoard = { id: 456 };
      const tagId: IStatisticSubjectTag = { id: 33420 };
      leaderBoard.tagId = tagId;

      const statisticSubjectTagCollection: IStatisticSubjectTag[] = [{ id: 88352 }];
      jest.spyOn(statisticSubjectTagService, 'query').mockReturnValue(of(new HttpResponse({ body: statisticSubjectTagCollection })));
      const additionalStatisticSubjectTags = [tagId];
      const expectedCollection: IStatisticSubjectTag[] = [...additionalStatisticSubjectTags, ...statisticSubjectTagCollection];
      jest.spyOn(statisticSubjectTagService, 'addStatisticSubjectTagToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ leaderBoard });
      comp.ngOnInit();

      expect(statisticSubjectTagService.query).toHaveBeenCalled();
      expect(statisticSubjectTagService.addStatisticSubjectTagToCollectionIfMissing).toHaveBeenCalledWith(
        statisticSubjectTagCollection,
        ...additionalStatisticSubjectTags.map(expect.objectContaining)
      );
      expect(comp.statisticSubjectTagsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const leaderBoard: ILeaderBoard = { id: 456 };
      const tagId: IStatisticSubjectTag = { id: 17522 };
      leaderBoard.tagId = tagId;

      activatedRoute.data = of({ leaderBoard });
      comp.ngOnInit();

      expect(comp.statisticSubjectTagsSharedCollection).toContain(tagId);
      expect(comp.leaderBoard).toEqual(leaderBoard);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILeaderBoard>>();
      const leaderBoard = { id: 123 };
      jest.spyOn(leaderBoardFormService, 'getLeaderBoard').mockReturnValue(leaderBoard);
      jest.spyOn(leaderBoardService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ leaderBoard });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: leaderBoard }));
      saveSubject.complete();

      // THEN
      expect(leaderBoardFormService.getLeaderBoard).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(leaderBoardService.update).toHaveBeenCalledWith(expect.objectContaining(leaderBoard));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILeaderBoard>>();
      const leaderBoard = { id: 123 };
      jest.spyOn(leaderBoardFormService, 'getLeaderBoard').mockReturnValue({ id: null });
      jest.spyOn(leaderBoardService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ leaderBoard: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: leaderBoard }));
      saveSubject.complete();

      // THEN
      expect(leaderBoardFormService.getLeaderBoard).toHaveBeenCalled();
      expect(leaderBoardService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILeaderBoard>>();
      const leaderBoard = { id: 123 };
      jest.spyOn(leaderBoardService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ leaderBoard });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(leaderBoardService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
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
