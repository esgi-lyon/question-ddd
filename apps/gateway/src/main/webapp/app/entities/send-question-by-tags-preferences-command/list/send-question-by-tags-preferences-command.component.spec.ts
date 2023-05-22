import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { SendQuestionByTagsPreferencesCommandService } from '../service/send-question-by-tags-preferences-command.service';

import { SendQuestionByTagsPreferencesCommandComponent } from './send-question-by-tags-preferences-command.component';

describe('SendQuestionByTagsPreferencesCommand Management Component', () => {
  let comp: SendQuestionByTagsPreferencesCommandComponent;
  let fixture: ComponentFixture<SendQuestionByTagsPreferencesCommandComponent>;
  let service: SendQuestionByTagsPreferencesCommandService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([
          { path: 'send-question-by-tags-preferences-command', component: SendQuestionByTagsPreferencesCommandComponent },
        ]),
        HttpClientTestingModule,
      ],
      declarations: [SendQuestionByTagsPreferencesCommandComponent],
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
      .overrideTemplate(SendQuestionByTagsPreferencesCommandComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SendQuestionByTagsPreferencesCommandComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(SendQuestionByTagsPreferencesCommandService);

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
    expect(comp.sendQuestionByTagsPreferencesCommands?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to sendQuestionByTagsPreferencesCommandService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getSendQuestionByTagsPreferencesCommandIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getSendQuestionByTagsPreferencesCommandIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
