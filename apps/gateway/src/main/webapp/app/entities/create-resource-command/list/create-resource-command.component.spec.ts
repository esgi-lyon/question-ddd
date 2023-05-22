import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { CreateResourceCommandService } from '../service/create-resource-command.service';

import { CreateResourceCommandComponent } from './create-resource-command.component';

describe('CreateResourceCommand Management Component', () => {
  let comp: CreateResourceCommandComponent;
  let fixture: ComponentFixture<CreateResourceCommandComponent>;
  let service: CreateResourceCommandService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'create-resource-command', component: CreateResourceCommandComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [CreateResourceCommandComponent],
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
      .overrideTemplate(CreateResourceCommandComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CreateResourceCommandComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(CreateResourceCommandService);

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
    expect(comp.createResourceCommands?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to createResourceCommandService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getCreateResourceCommandIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getCreateResourceCommandIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
