import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ValidateResourceTagLinkageCommandService } from '../service/validate-resource-tag-linkage-command.service';

import { ValidateResourceTagLinkageCommandComponent } from './validate-resource-tag-linkage-command.component';

describe('ValidateResourceTagLinkageCommand Management Component', () => {
  let comp: ValidateResourceTagLinkageCommandComponent;
  let fixture: ComponentFixture<ValidateResourceTagLinkageCommandComponent>;
  let service: ValidateResourceTagLinkageCommandService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([
          { path: 'validate-resource-tag-linkage-command', component: ValidateResourceTagLinkageCommandComponent },
        ]),
        HttpClientTestingModule,
      ],
      declarations: [ValidateResourceTagLinkageCommandComponent],
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
      .overrideTemplate(ValidateResourceTagLinkageCommandComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ValidateResourceTagLinkageCommandComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(ValidateResourceTagLinkageCommandService);

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
    expect(comp.validateResourceTagLinkageCommands?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to validateResourceTagLinkageCommandService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getValidateResourceTagLinkageCommandIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getValidateResourceTagLinkageCommandIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
