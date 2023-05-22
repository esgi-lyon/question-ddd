import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { CreateTagCommandService } from '../service/create-tag-command.service';

import { CreateTagCommandComponent } from './create-tag-command.component';

describe('CreateTagCommand Management Component', () => {
  let comp: CreateTagCommandComponent;
  let fixture: ComponentFixture<CreateTagCommandComponent>;
  let service: CreateTagCommandService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'create-tag-command', component: CreateTagCommandComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [CreateTagCommandComponent],
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
      .overrideTemplate(CreateTagCommandComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CreateTagCommandComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(CreateTagCommandService);

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
    expect(comp.createTagCommands?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to createTagCommandService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getCreateTagCommandIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getCreateTagCommandIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
