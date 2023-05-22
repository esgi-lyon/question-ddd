import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../user-infos.test-samples';

import { UserInfosFormService } from './user-infos-form.service';

describe('UserInfos Form Service', () => {
  let service: UserInfosFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserInfosFormService);
  });

  describe('Service methods', () => {
    describe('createUserInfosFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createUserInfosFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            firstname: expect.any(Object),
            lastname: expect.any(Object),
            role: expect.any(Object),
            status: expect.any(Object),
          })
        );
      });

      it('passing IUserInfos should create a new form with FormGroup', () => {
        const formGroup = service.createUserInfosFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            firstname: expect.any(Object),
            lastname: expect.any(Object),
            role: expect.any(Object),
            status: expect.any(Object),
          })
        );
      });
    });

    describe('getUserInfos', () => {
      it('should return NewUserInfos for default UserInfos initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createUserInfosFormGroup(sampleWithNewData);

        const userInfos = service.getUserInfos(formGroup) as any;

        expect(userInfos).toMatchObject(sampleWithNewData);
      });

      it('should return NewUserInfos for empty UserInfos initial value', () => {
        const formGroup = service.createUserInfosFormGroup();

        const userInfos = service.getUserInfos(formGroup) as any;

        expect(userInfos).toMatchObject({});
      });

      it('should return IUserInfos', () => {
        const formGroup = service.createUserInfosFormGroup(sampleWithRequiredData);

        const userInfos = service.getUserInfos(formGroup) as any;

        expect(userInfos).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IUserInfos should not enable id FormControl', () => {
        const formGroup = service.createUserInfosFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewUserInfos should disable id FormControl', () => {
        const formGroup = service.createUserInfosFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
