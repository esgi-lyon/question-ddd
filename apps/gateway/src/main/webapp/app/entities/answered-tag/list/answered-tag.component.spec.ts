import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { AnsweredTagService } from '../service/answered-tag.service';

import { AnsweredTagComponent } from './answered-tag.component';

describe('AnsweredTag Management Component', () => {
  let comp: AnsweredTagComponent;
  let fixture: ComponentFixture<AnsweredTagComponent>;
  let service: AnsweredTagService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'answered-tag', component: AnsweredTagComponent }]), HttpClientTestingModule],
      declarations: [AnsweredTagComponent],
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
      .overrideTemplate(AnsweredTagComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AnsweredTagComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(AnsweredTagService);

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
    expect(comp.answeredTags?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to answeredTagService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getAnsweredTagIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getAnsweredTagIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
