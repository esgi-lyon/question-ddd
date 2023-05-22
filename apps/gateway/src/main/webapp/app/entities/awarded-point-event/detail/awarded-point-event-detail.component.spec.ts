import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AwardedPointEventDetailComponent } from './awarded-point-event-detail.component';

describe('AwardedPointEvent Management Detail Component', () => {
  let comp: AwardedPointEventDetailComponent;
  let fixture: ComponentFixture<AwardedPointEventDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AwardedPointEventDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ awardedPointEvent: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(AwardedPointEventDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(AwardedPointEventDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load awardedPointEvent on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.awardedPointEvent).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
