import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { UserInfosService } from '../service/user-infos.service';

import { UserInfosComponent } from './user-infos.component';

describe('UserInfos Management Component', () => {
  let comp: UserInfosComponent;
  let fixture: ComponentFixture<UserInfosComponent>;
  let service: UserInfosService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'user-infos', component: UserInfosComponent }]), HttpClientTestingModule],
      declarations: [UserInfosComponent],
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
      .overrideTemplate(UserInfosComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(UserInfosComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(UserInfosService);

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
    expect(comp.userInfos?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to userInfosService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getUserInfosIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getUserInfosIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
