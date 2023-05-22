import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PointAwardRuleDetailComponent } from './point-award-rule-detail.component';

describe('PointAwardRule Management Detail Component', () => {
  let comp: PointAwardRuleDetailComponent;
  let fixture: ComponentFixture<PointAwardRuleDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PointAwardRuleDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ pointAwardRule: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(PointAwardRuleDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(PointAwardRuleDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load pointAwardRule on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.pointAwardRule).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
