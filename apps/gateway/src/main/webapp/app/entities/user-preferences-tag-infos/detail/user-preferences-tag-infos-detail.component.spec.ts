import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { UserPreferencesTagInfosDetailComponent } from './user-preferences-tag-infos-detail.component';

describe('UserPreferencesTagInfos Management Detail Component', () => {
  let comp: UserPreferencesTagInfosDetailComponent;
  let fixture: ComponentFixture<UserPreferencesTagInfosDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserPreferencesTagInfosDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ userPreferencesTagInfos: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(UserPreferencesTagInfosDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(UserPreferencesTagInfosDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load userPreferencesTagInfos on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.userPreferencesTagInfos).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
