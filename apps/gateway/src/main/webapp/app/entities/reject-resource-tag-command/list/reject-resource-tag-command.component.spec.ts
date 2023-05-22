import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { RejectResourceTagCommandService } from '../service/reject-resource-tag-command.service';

import { RejectResourceTagCommandComponent } from './reject-resource-tag-command.component';

describe('RejectResourceTagCommand Management Component', () => {
  let comp: RejectResourceTagCommandComponent;
  let fixture: ComponentFixture<RejectResourceTagCommandComponent>;
  let service: RejectResourceTagCommandService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'reject-resource-tag-command', component: RejectResourceTagCommandComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [RejectResourceTagCommandComponent],
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
      .overrideTemplate(RejectResourceTagCommandComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RejectResourceTagCommandComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(RejectResourceTagCommandService);

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
    expect(comp.rejectResourceTagCommands?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to rejectResourceTagCommandService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getRejectResourceTagCommandIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getRejectResourceTagCommandIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
