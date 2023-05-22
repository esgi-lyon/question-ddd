import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { UserInfosFormService } from './user-infos-form.service';
import { UserInfosService } from '../service/user-infos.service';
import { IUserInfos } from '../user-infos.model';

import { UserInfosUpdateComponent } from './user-infos-update.component';

describe('UserInfos Management Update Component', () => {
  let comp: UserInfosUpdateComponent;
  let fixture: ComponentFixture<UserInfosUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let userInfosFormService: UserInfosFormService;
  let userInfosService: UserInfosService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [UserInfosUpdateComponent],
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
      .overrideTemplate(UserInfosUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(UserInfosUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    userInfosFormService = TestBed.inject(UserInfosFormService);
    userInfosService = TestBed.inject(UserInfosService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const userInfos: IUserInfos = { id: 456 };

      activatedRoute.data = of({ userInfos });
      comp.ngOnInit();

      expect(comp.userInfos).toEqual(userInfos);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUserInfos>>();
      const userInfos = { id: 123 };
      jest.spyOn(userInfosFormService, 'getUserInfos').mockReturnValue(userInfos);
      jest.spyOn(userInfosService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userInfos });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: userInfos }));
      saveSubject.complete();

      // THEN
      expect(userInfosFormService.getUserInfos).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(userInfosService.update).toHaveBeenCalledWith(expect.objectContaining(userInfos));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUserInfos>>();
      const userInfos = { id: 123 };
      jest.spyOn(userInfosFormService, 'getUserInfos').mockReturnValue({ id: null });
      jest.spyOn(userInfosService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userInfos: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: userInfos }));
      saveSubject.complete();

      // THEN
      expect(userInfosFormService.getUserInfos).toHaveBeenCalled();
      expect(userInfosService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUserInfos>>();
      const userInfos = { id: 123 };
      jest.spyOn(userInfosService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userInfos });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(userInfosService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
