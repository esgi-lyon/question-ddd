import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../view-stats-command.test-samples';

import { ViewStatsCommandFormService } from './view-stats-command-form.service';

describe('ViewStatsCommand Form Service', () => {
  let service: ViewStatsCommandFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ViewStatsCommandFormService);
  });

  describe('Service methods', () => {
    describe('createViewStatsCommandFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createViewStatsCommandFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            user: expect.any(Object),
            question: expect.any(Object),
            tag: expect.any(Object),
          })
        );
      });

      it('passing IViewStatsCommand should create a new form with FormGroup', () => {
        const formGroup = service.createViewStatsCommandFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            user: expect.any(Object),
            question: expect.any(Object),
            tag: expect.any(Object),
          })
        );
      });
    });

    describe('getViewStatsCommand', () => {
      it('should return NewViewStatsCommand for default ViewStatsCommand initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createViewStatsCommandFormGroup(sampleWithNewData);

        const viewStatsCommand = service.getViewStatsCommand(formGroup) as any;

        expect(viewStatsCommand).toMatchObject(sampleWithNewData);
      });

      it('should return NewViewStatsCommand for empty ViewStatsCommand initial value', () => {
        const formGroup = service.createViewStatsCommandFormGroup();

        const viewStatsCommand = service.getViewStatsCommand(formGroup) as any;

        expect(viewStatsCommand).toMatchObject({});
      });

      it('should return IViewStatsCommand', () => {
        const formGroup = service.createViewStatsCommandFormGroup(sampleWithRequiredData);

        const viewStatsCommand = service.getViewStatsCommand(formGroup) as any;

        expect(viewStatsCommand).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IViewStatsCommand should not enable id FormControl', () => {
        const formGroup = service.createViewStatsCommandFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewViewStatsCommand should disable id FormControl', () => {
        const formGroup = service.createViewStatsCommandFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
