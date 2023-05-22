import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { UserInfosDetailComponent } from './user-infos-detail.component';

describe('UserInfos Management Detail Component', () => {
  let comp: UserInfosDetailComponent;
  let fixture: ComponentFixture<UserInfosDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserInfosDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ userInfos: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(UserInfosDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(UserInfosDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load userInfos on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.userInfos).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
