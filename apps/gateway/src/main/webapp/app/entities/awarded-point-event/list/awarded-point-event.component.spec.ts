import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { AwardedPointEventService } from '../service/awarded-point-event.service';

import { AwardedPointEventComponent } from './awarded-point-event.component';

describe('AwardedPointEvent Management Component', () => {
  let comp: AwardedPointEventComponent;
  let fixture: ComponentFixture<AwardedPointEventComponent>;
  let service: AwardedPointEventService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'awarded-point-event', component: AwardedPointEventComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [AwardedPointEventComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              })
            ),
            snapshot: { queryParams: {} },
          },
        },
      ],
    })
      .overrideTemplate(AwardedPointEventComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AwardedPointEventComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(AwardedPointEventService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.awardedPointEvents?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to awardedPointEventService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getAwardedPointEventIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getAwardedPointEventIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
