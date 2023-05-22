import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { UserPreferencesFormService } from './user-preferences-form.service';
import { UserPreferencesService } from '../service/user-preferences.service';
import { IUserPreferences } from '../user-preferences.model';
import { IUserWithPreferencesId } from 'app/entities/user-with-preferences-id/user-with-preferences-id.model';
import { UserWithPreferencesIdService } from 'app/entities/user-with-preferences-id/service/user-with-preferences-id.service';

import { UserPreferencesUpdateComponent } from './user-preferences-update.component';

describe('UserPreferences Management Update Component', () => {
  let comp: UserPreferencesUpdateComponent;
  let fixture: ComponentFixture<UserPreferencesUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let userPreferencesFormService: UserPreferencesFormService;
  let userPreferencesService: UserPreferencesService;
  let userWithPreferencesIdService: UserWithPreferencesIdService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [UserPreferencesUpdateComponent],
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
      .overrideTemplate(UserPreferencesUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(UserPreferencesUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    userPreferencesFormService = TestBed.inject(UserPreferencesFormService);
    userPreferencesService = TestBed.inject(UserPreferencesService);
    userWithPreferencesIdService = TestBed.inject(UserWithPreferencesIdService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call UserWithPreferencesId query and add missing value', () => {
      const userPreferences: IUserPreferences = { id: 456 };
      const user: IUserWithPreferencesId = { id: 72258 };
      userPreferences.user = user;

      const userWithPreferencesIdCollection: IUserWithPreferencesId[] = [{ id: 2012 }];
      jest.spyOn(userWithPreferencesIdService, 'query').mockReturnValue(of(new HttpResponse({ body: userWithPreferencesIdCollection })));
      const additionalUserWithPreferencesIds = [user];
      const expectedCollection: IUserWithPreferencesId[] = [...additionalUserWithPreferencesIds, ...userWithPreferencesIdCollection];
      jest.spyOn(userWithPreferencesIdService, 'addUserWithPreferencesIdToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ userPreferences });
      comp.ngOnInit();

      expect(userWithPreferencesIdService.query).toHaveBeenCalled();
      expect(userWithPreferencesIdService.addUserWithPreferencesIdToCollectionIfMissing).toHaveBeenCalledWith(
        userWithPreferencesIdCollection,
        ...additionalUserWithPreferencesIds.map(expect.objectContaining)
      );
      expect(comp.userWithPreferencesIdsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const userPreferences: IUserPreferences = { id: 456 };
      const user: IUserWithPreferencesId = { id: 26727 };
      userPreferences.user = user;

      activatedRoute.data = of({ userPreferences });
      comp.ngOnInit();

      expect(comp.userWithPreferencesIdsSharedCollection).toContain(user);
      expect(comp.userPreferences).toEqual(userPreferences);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUserPreferences>>();
      const userPreferences = { id: 123 };
      jest.spyOn(userPreferencesFormService, 'getUserPreferences').mockReturnValue(userPreferences);
      jest.spyOn(userPreferencesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userPreferences });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: userPreferences }));
      saveSubject.complete();

      // THEN
      expect(userPreferencesFormService.getUserPreferences).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(userPreferencesService.update).toHaveBeenCalledWith(expect.objectContaining(userPreferences));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUserPreferences>>();
      const userPreferences = { id: 123 };
      jest.spyOn(userPreferencesFormService, 'getUserPreferences').mockReturnValue({ id: null });
      jest.spyOn(userPreferencesService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userPreferences: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: userPreferences }));
      saveSubject.complete();

      // THEN
      expect(userPreferencesFormService.getUserPreferences).toHaveBeenCalled();
      expect(userPreferencesService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUserPreferences>>();
      const userPreferences = { id: 123 };
      jest.spyOn(userPreferencesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userPreferences });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(userPreferencesService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareUserWithPreferencesId', () => {
      it('Should forward to userWithPreferencesIdService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(userWithPreferencesIdService, 'compareUserWithPreferencesId');
        comp.compareUserWithPreferencesId(entity, entity2);
        expect(userWithPreferencesIdService.compareUserWithPreferencesId).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
