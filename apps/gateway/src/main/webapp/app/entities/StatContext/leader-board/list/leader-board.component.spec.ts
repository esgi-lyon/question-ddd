import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { LeaderBoardService } from '../service/leader-board.service';

import { LeaderBoardComponent } from './leader-board.component';

describe('LeaderBoard Management Component', () => {
  let comp: LeaderBoardComponent;
  let fixture: ComponentFixture<LeaderBoardComponent>;
  let service: LeaderBoardService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'leader-board', component: LeaderBoardComponent }]), HttpClientTestingModule],
      declarations: [LeaderBoardComponent],
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
      .overrideTemplate(LeaderBoardComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(LeaderBoardComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(LeaderBoardService);

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
    expect(comp.leaderBoards?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to leaderBoardService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getLeaderBoardIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getLeaderBoardIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
