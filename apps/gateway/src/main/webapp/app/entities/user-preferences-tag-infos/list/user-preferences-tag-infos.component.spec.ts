import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { UserPreferencesTagInfosService } from '../service/user-preferences-tag-infos.service';

import { UserPreferencesTagInfosComponent } from './user-preferences-tag-infos.component';

describe('UserPreferencesTagInfos Management Component', () => {
  let comp: UserPreferencesTagInfosComponent;
  let fixture: ComponentFixture<UserPreferencesTagInfosComponent>;
  let service: UserPreferencesTagInfosService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'user-preferences-tag-infos', component: UserPreferencesTagInfosComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [UserPreferencesTagInfosComponent],
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
      .overrideTemplate(UserPreferencesTagInfosComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(UserPreferencesTagInfosComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(UserPreferencesTagInfosService);

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
    expect(comp.userPreferencesTagInfos?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to userPreferencesTagInfosService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getUserPreferencesTagInfosIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getUserPreferencesTagInfosIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
