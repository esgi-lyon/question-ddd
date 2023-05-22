import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ValidateResourceTagLinkageCommandDetailComponent } from './validate-resource-tag-linkage-command-detail.component';

describe('ValidateResourceTagLinkageCommand Management Detail Component', () => {
  let comp: ValidateResourceTagLinkageCommandDetailComponent;
  let fixture: ComponentFixture<ValidateResourceTagLinkageCommandDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ValidateResourceTagLinkageCommandDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ validateResourceTagLinkageCommand: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(ValidateResourceTagLinkageCommandDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ValidateResourceTagLinkageCommandDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load validateResourceTagLinkageCommand on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.validateResourceTagLinkageCommand).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
