import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IStatisticSubjectUser } from '../statistic-subject-user.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../statistic-subject-user.test-samples';

import { StatisticSubjectUserService } from './statistic-subject-user.service';

const requireRestSample: IStatisticSubjectUser = {
  ...sampleWithRequiredData,
};

describe('StatisticSubjectUser Service', () => {
  let service: StatisticSubjectUserService;
  let httpMock: HttpTestingController;
  let expectedResult: IStatisticSubjectUser | IStatisticSubjectUser[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(StatisticSubjectUserService);
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

    it('should return a list of StatisticSubjectUser', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addStatisticSubjectUserToCollectionIfMissing', () => {
      it('should add a StatisticSubjectUser to an empty array', () => {
        const statisticSubjectUser: IStatisticSubjectUser = sampleWithRequiredData;
        expectedResult = service.addStatisticSubjectUserToCollectionIfMissing([], statisticSubjectUser);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(statisticSubjectUser);
      });

      it('should not add a StatisticSubjectUser to an array that contains it', () => {
        const statisticSubjectUser: IStatisticSubjectUser = sampleWithRequiredData;
        const statisticSubjectUserCollection: IStatisticSubjectUser[] = [
          {
            ...statisticSubjectUser,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addStatisticSubjectUserToCollectionIfMissing(statisticSubjectUserCollection, statisticSubjectUser);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a StatisticSubjectUser to an array that doesn't contain it", () => {
        const statisticSubjectUser: IStatisticSubjectUser = sampleWithRequiredData;
        const statisticSubjectUserCollection: IStatisticSubjectUser[] = [sampleWithPartialData];
        expectedResult = service.addStatisticSubjectUserToCollectionIfMissing(statisticSubjectUserCollection, statisticSubjectUser);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(statisticSubjectUser);
      });

      it('should add only unique StatisticSubjectUser to an array', () => {
        const statisticSubjectUserArray: IStatisticSubjectUser[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const statisticSubjectUserCollection: IStatisticSubjectUser[] = [sampleWithRequiredData];
        expectedResult = service.addStatisticSubjectUserToCollectionIfMissing(statisticSubjectUserCollection, ...statisticSubjectUserArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const statisticSubjectUser: IStatisticSubjectUser = sampleWithRequiredData;
        const statisticSubjectUser2: IStatisticSubjectUser = sampleWithPartialData;
        expectedResult = service.addStatisticSubjectUserToCollectionIfMissing([], statisticSubjectUser, statisticSubjectUser2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(statisticSubjectUser);
        expect(expectedResult).toContain(statisticSubjectUser2);
      });

      it('should accept null and undefined values', () => {
        const statisticSubjectUser: IStatisticSubjectUser = sampleWithRequiredData;
        expectedResult = service.addStatisticSubjectUserToCollectionIfMissing([], null, statisticSubjectUser, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(statisticSubjectUser);
      });

      it('should return initial array if no StatisticSubjectUser is added', () => {
        const statisticSubjectUserCollection: IStatisticSubjectUser[] = [sampleWithRequiredData];
        expectedResult = service.addStatisticSubjectUserToCollectionIfMissing(statisticSubjectUserCollection, undefined, null);
        expect(expectedResult).toEqual(statisticSubjectUserCollection);
      });
    });

    describe('compareStatisticSubjectUser', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareStatisticSubjectUser(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareStatisticSubjectUser(entity1, entity2);
        const compareResult2 = service.compareStatisticSubjectUser(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareStatisticSubjectUser(entity1, entity2);
        const compareResult2 = service.compareStatisticSubjectUser(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareStatisticSubjectUser(entity1, entity2);
        const compareResult2 = service.compareStatisticSubjectUser(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
