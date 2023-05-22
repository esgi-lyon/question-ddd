import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { TagInfosService } from '../service/tag-infos.service';

import { TagInfosComponent } from './tag-infos.component';

describe('TagInfos Management Component', () => {
  let comp: TagInfosComponent;
  let fixture: ComponentFixture<TagInfosComponent>;
  let service: TagInfosService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'tag-infos', component: TagInfosComponent }]), HttpClientTestingModule],
      declarations: [TagInfosComponent],
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
      .overrideTemplate(TagInfosComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TagInfosComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(TagInfosService);

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
    expect(comp.tagInfos?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to tagInfosService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getTagInfosIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getTagInfosIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
