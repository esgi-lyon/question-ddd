import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IUserStatsViewedEvent } from '../user-stats-viewed-event.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../user-stats-viewed-event.test-samples';

import { UserStatsViewedEventService } from './user-stats-viewed-event.service';

const requireRestSample: IUserStatsViewedEvent = {
  ...sampleWithRequiredData,
};

describe('UserStatsViewedEvent Service', () => {
  let service: UserStatsViewedEventService;
  let httpMock: HttpTestingController;
  let expectedResult: IUserStatsViewedEvent | IUserStatsViewedEvent[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(UserStatsViewedEventService);
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

    it('should return a list of UserStatsViewedEvent', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addUserStatsViewedEventToCollectionIfMissing', () => {
      it('should add a UserStatsViewedEvent to an empty array', () => {
        const userStatsViewedEvent: IUserStatsViewedEvent = sampleWithRequiredData;
        expectedResult = service.addUserStatsViewedEventToCollectionIfMissing([], userStatsViewedEvent);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(userStatsViewedEvent);
      });

      it('should not add a UserStatsViewedEvent to an array that contains it', () => {
        const userStatsViewedEvent: IUserStatsViewedEvent = sampleWithRequiredData;
        const userStatsViewedEventCollection: IUserStatsViewedEvent[] = [
          {
            ...userStatsViewedEvent,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addUserStatsViewedEventToCollectionIfMissing(userStatsViewedEventCollection, userStatsViewedEvent);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a UserStatsViewedEvent to an array that doesn't contain it", () => {
        const userStatsViewedEvent: IUserStatsViewedEvent = sampleWithRequiredData;
        const userStatsViewedEventCollection: IUserStatsViewedEvent[] = [sampleWithPartialData];
        expectedResult = service.addUserStatsViewedEventToCollectionIfMissing(userStatsViewedEventCollection, userStatsViewedEvent);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(userStatsViewedEvent);
      });

      it('should add only unique UserStatsViewedEvent to an array', () => {
        const userStatsViewedEventArray: IUserStatsViewedEvent[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const userStatsViewedEventCollection: IUserStatsViewedEvent[] = [sampleWithRequiredData];
        expectedResult = service.addUserStatsViewedEventToCollectionIfMissing(userStatsViewedEventCollection, ...userStatsViewedEventArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const userStatsViewedEvent: IUserStatsViewedEvent = sampleWithRequiredData;
        const userStatsViewedEvent2: IUserStatsViewedEvent = sampleWithPartialData;
        expectedResult = service.addUserStatsViewedEventToCollectionIfMissing([], userStatsViewedEvent, userStatsViewedEvent2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(userStatsViewedEvent);
        expect(expectedResult).toContain(userStatsViewedEvent2);
      });

      it('should accept null and undefined values', () => {
        const userStatsViewedEvent: IUserStatsViewedEvent = sampleWithRequiredData;
        expectedResult = service.addUserStatsViewedEventToCollectionIfMissing([], null, userStatsViewedEvent, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(userStatsViewedEvent);
      });

      it('should return initial array if no UserStatsViewedEvent is added', () => {
        const userStatsViewedEventCollection: IUserStatsViewedEvent[] = [sampleWithRequiredData];
        expectedResult = service.addUserStatsViewedEventToCollectionIfMissing(userStatsViewedEventCollection, undefined, null);
        expect(expectedResult).toEqual(userStatsViewedEventCollection);
      });
    });

    describe('compareUserStatsViewedEvent', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareUserStatsViewedEvent(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareUserStatsViewedEvent(entity1, entity2);
        const compareResult2 = service.compareUserStatsViewedEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareUserStatsViewedEvent(entity1, entity2);
        const compareResult2 = service.compareUserStatsViewedEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareUserStatsViewedEvent(entity1, entity2);
        const compareResult2 = service.compareUserStatsViewedEvent(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
