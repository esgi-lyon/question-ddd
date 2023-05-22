import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LeaderBoardDetailComponent } from './leader-board-detail.component';

describe('LeaderBoard Management Detail Component', () => {
  let comp: LeaderBoardDetailComponent;
  let fixture: ComponentFixture<LeaderBoardDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LeaderBoardDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ leaderBoard: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(LeaderBoardDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(LeaderBoardDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load leaderBoard on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.leaderBoard).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
