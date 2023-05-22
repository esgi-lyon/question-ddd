import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../point-award-rule.test-samples';

import { PointAwardRuleFormService } from './point-award-rule-form.service';

describe('PointAwardRule Form Service', () => {
  let service: PointAwardRuleFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PointAwardRuleFormService);
  });

  describe('Service methods', () => {
    describe('createPointAwardRuleFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createPointAwardRuleFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            scoreEvolution: expect.any(Object),
            difficultyLevel: expect.any(Object),
            userLevel: expect.any(Object),
          })
        );
      });

      it('passing IPointAwardRule should create a new form with FormGroup', () => {
        const formGroup = service.createPointAwardRuleFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            scoreEvolution: expect.any(Object),
            difficultyLevel: expect.any(Object),
            userLevel: expect.any(Object),
          })
        );
      });
    });

    describe('getPointAwardRule', () => {
      it('should return NewPointAwardRule for default PointAwardRule initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createPointAwardRuleFormGroup(sampleWithNewData);

        const pointAwardRule = service.getPointAwardRule(formGroup) as any;

        expect(pointAwardRule).toMatchObject(sampleWithNewData);
      });

      it('should return NewPointAwardRule for empty PointAwardRule initial value', () => {
        const formGroup = service.createPointAwardRuleFormGroup();

        const pointAwardRule = service.getPointAwardRule(formGroup) as any;

        expect(pointAwardRule).toMatchObject({});
      });

      it('should return IPointAwardRule', () => {
        const formGroup = service.createPointAwardRuleFormGroup(sampleWithRequiredData);

        const pointAwardRule = service.getPointAwardRule(formGroup) as any;

        expect(pointAwardRule).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IPointAwardRule should not enable id FormControl', () => {
        const formGroup = service.createPointAwardRuleFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewPointAwardRule should disable id FormControl', () => {
        const formGroup = service.createPointAwardRuleFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
