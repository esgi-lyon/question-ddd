import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { AnsweringUserService } from '../service/answering-user.service';

import { AnsweringUserComponent } from './answering-user.component';

describe('AnsweringUser Management Component', () => {
  let comp: AnsweringUserComponent;
  let fixture: ComponentFixture<AnsweringUserComponent>;
  let service: AnsweringUserService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'answering-user', component: AnsweringUserComponent }]), HttpClientTestingModule],
      declarations: [AnsweringUserComponent],
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
      .overrideTemplate(AnsweringUserComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AnsweringUserComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(AnsweringUserService);

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
    expect(comp.answeringUsers?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to answeringUserService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getAnsweringUserIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getAnsweringUserIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
