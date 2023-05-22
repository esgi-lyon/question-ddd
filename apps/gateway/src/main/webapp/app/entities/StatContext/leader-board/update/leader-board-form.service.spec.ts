import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../leader-board.test-samples';

import { LeaderBoardFormService } from './leader-board-form.service';

describe('LeaderBoard Form Service', () => {
  let service: LeaderBoardFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LeaderBoardFormService);
  });

  describe('Service methods', () => {
    describe('createLeaderBoardFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createLeaderBoardFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            difficultyLevel: expect.any(Object),
            tagId: expect.any(Object),
          })
        );
      });

      it('passing ILeaderBoard should create a new form with FormGroup', () => {
        const formGroup = service.createLeaderBoardFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            difficultyLevel: expect.any(Object),
            tagId: expect.any(Object),
          })
        );
      });
    });

    describe('getLeaderBoard', () => {
      it('should return NewLeaderBoard for default LeaderBoard initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createLeaderBoardFormGroup(sampleWithNewData);

        const leaderBoard = service.getLeaderBoard(formGroup) as any;

        expect(leaderBoard).toMatchObject(sampleWithNewData);
      });

      it('should return NewLeaderBoard for empty LeaderBoard initial value', () => {
        const formGroup = service.createLeaderBoardFormGroup();

        const leaderBoard = service.getLeaderBoard(formGroup) as any;

        expect(leaderBoard).toMatchObject({});
      });

      it('should return ILeaderBoard', () => {
        const formGroup = service.createLeaderBoardFormGroup(sampleWithRequiredData);

        const leaderBoard = service.getLeaderBoard(formGroup) as any;

        expect(leaderBoard).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ILeaderBoard should not enable id FormControl', () => {
        const formGroup = service.createLeaderBoardFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewLeaderBoard should disable id FormControl', () => {
        const formGroup = service.createLeaderBoardFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
