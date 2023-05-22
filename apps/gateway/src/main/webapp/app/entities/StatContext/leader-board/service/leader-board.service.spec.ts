import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ILeaderBoard } from '../leader-board.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../leader-board.test-samples';

import { LeaderBoardService } from './leader-board.service';

const requireRestSample: ILeaderBoard = {
  ...sampleWithRequiredData,
};

describe('LeaderBoard Service', () => {
  let service: LeaderBoardService;
  let httpMock: HttpTestingController;
  let expectedResult: ILeaderBoard | ILeaderBoard[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(LeaderBoardService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a LeaderBoard', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const leaderBoard = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(leaderBoard).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a LeaderBoard', () => {
      const leaderBoard = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(leaderBoard).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a LeaderBoard', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of LeaderBoard', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a LeaderBoard', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addLeaderBoardToCollectionIfMissing', () => {
      it('should add a LeaderBoard to an empty array', () => {
        const leaderBoard: ILeaderBoard = sampleWithRequiredData;
        expectedResult = service.addLeaderBoardToCollectionIfMissing([], leaderBoard);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(leaderBoard);
      });

      it('should not add a LeaderBoard to an array that contains it', () => {
        const leaderBoard: ILeaderBoard = sampleWithRequiredData;
        const leaderBoardCollection: ILeaderBoard[] = [
          {
            ...leaderBoard,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addLeaderBoardToCollectionIfMissing(leaderBoardCollection, leaderBoard);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a LeaderBoard to an array that doesn't contain it", () => {
        const leaderBoard: ILeaderBoard = sampleWithRequiredData;
        const leaderBoardCollection: ILeaderBoard[] = [sampleWithPartialData];
        expectedResult = service.addLeaderBoardToCollectionIfMissing(leaderBoardCollection, leaderBoard);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(leaderBoard);
      });

      it('should add only unique LeaderBoard to an array', () => {
        const leaderBoardArray: ILeaderBoard[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const leaderBoardCollection: ILeaderBoard[] = [sampleWithRequiredData];
        expectedResult = service.addLeaderBoardToCollectionIfMissing(leaderBoardCollection, ...leaderBoardArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const leaderBoard: ILeaderBoard = sampleWithRequiredData;
        const leaderBoard2: ILeaderBoard = sampleWithPartialData;
        expectedResult = service.addLeaderBoardToCollectionIfMissing([], leaderBoard, leaderBoard2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(leaderBoard);
        expect(expectedResult).toContain(leaderBoard2);
      });

      it('should accept null and undefined values', () => {
        const leaderBoard: ILeaderBoard = sampleWithRequiredData;
        expectedResult = service.addLeaderBoardToCollectionIfMissing([], null, leaderBoard, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(leaderBoard);
      });

      it('should return initial array if no LeaderBoard is added', () => {
        const leaderBoardCollection: ILeaderBoard[] = [sampleWithRequiredData];
        expectedResult = service.addLeaderBoardToCollectionIfMissing(leaderBoardCollection, undefined, null);
        expect(expectedResult).toEqual(leaderBoardCollection);
      });
    });

    describe('compareLeaderBoard', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareLeaderBoard(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareLeaderBoard(entity1, entity2);
        const compareResult2 = service.compareLeaderBoard(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareLeaderBoard(entity1, entity2);
        const compareResult2 = service.compareLeaderBoard(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareLeaderBoard(entity1, entity2);
        const compareResult2 = service.compareLeaderBoard(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
