import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { UserWithPreferencesIdDetailComponent } from './user-with-preferences-id-detail.component';

describe('UserWithPreferencesId Management Detail Component', () => {
  let comp: UserWithPreferencesIdDetailComponent;
  let fixture: ComponentFixture<UserWithPreferencesIdDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserWithPreferencesIdDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ userWithPreferencesId: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(UserWithPreferencesIdDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(UserWithPreferencesIdDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load userWithPreferencesId on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.userWithPreferencesId).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
