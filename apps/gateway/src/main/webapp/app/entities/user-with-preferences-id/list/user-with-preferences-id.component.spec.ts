import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { UserWithPreferencesIdService } from '../service/user-with-preferences-id.service';

import { UserWithPreferencesIdComponent } from './user-with-preferences-id.component';

describe('UserWithPreferencesId Management Component', () => {
  let comp: UserWithPreferencesIdComponent;
  let fixture: ComponentFixture<UserWithPreferencesIdComponent>;
  let service: UserWithPreferencesIdService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'user-with-preferences-id', component: UserWithPreferencesIdComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [UserWithPreferencesIdComponent],
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
      .overrideTemplate(UserWithPreferencesIdComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(UserWithPreferencesIdComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(UserWithPreferencesIdService);

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
    expect(comp.userWithPreferencesIds?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to userWithPreferencesIdService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getUserWithPreferencesIdIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getUserWithPreferencesIdIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
