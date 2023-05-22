import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IStatisticSubjectTag } from '../statistic-subject-tag.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../statistic-subject-tag.test-samples';

import { StatisticSubjectTagService } from './statistic-subject-tag.service';

const requireRestSample: IStatisticSubjectTag = {
  ...sampleWithRequiredData,
};

describe('StatisticSubjectTag Service', () => {
  let service: StatisticSubjectTagService;
  let httpMock: HttpTestingController;
  let expectedResult: IStatisticSubjectTag | IStatisticSubjectTag[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(StatisticSubjectTagService);
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

    it('should return a list of StatisticSubjectTag', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addStatisticSubjectTagToCollectionIfMissing', () => {
      it('should add a StatisticSubjectTag to an empty array', () => {
        const statisticSubjectTag: IStatisticSubjectTag = sampleWithRequiredData;
        expectedResult = service.addStatisticSubjectTagToCollectionIfMissing([], statisticSubjectTag);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(statisticSubjectTag);
      });

      it('should not add a StatisticSubjectTag to an array that contains it', () => {
        const statisticSubjectTag: IStatisticSubjectTag = sampleWithRequiredData;
        const statisticSubjectTagCollection: IStatisticSubjectTag[] = [
          {
            ...statisticSubjectTag,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addStatisticSubjectTagToCollectionIfMissing(statisticSubjectTagCollection, statisticSubjectTag);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a StatisticSubjectTag to an array that doesn't contain it", () => {
        const statisticSubjectTag: IStatisticSubjectTag = sampleWithRequiredData;
        const statisticSubjectTagCollection: IStatisticSubjectTag[] = [sampleWithPartialData];
        expectedResult = service.addStatisticSubjectTagToCollectionIfMissing(statisticSubjectTagCollection, statisticSubjectTag);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(statisticSubjectTag);
      });

      it('should add only unique StatisticSubjectTag to an array', () => {
        const statisticSubjectTagArray: IStatisticSubjectTag[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const statisticSubjectTagCollection: IStatisticSubjectTag[] = [sampleWithRequiredData];
        expectedResult = service.addStatisticSubjectTagToCollectionIfMissing(statisticSubjectTagCollection, ...statisticSubjectTagArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const statisticSubjectTag: IStatisticSubjectTag = sampleWithRequiredData;
        const statisticSubjectTag2: IStatisticSubjectTag = sampleWithPartialData;
        expectedResult = service.addStatisticSubjectTagToCollectionIfMissing([], statisticSubjectTag, statisticSubjectTag2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(statisticSubjectTag);
        expect(expectedResult).toContain(statisticSubjectTag2);
      });

      it('should accept null and undefined values', () => {
        const statisticSubjectTag: IStatisticSubjectTag = sampleWithRequiredData;
        expectedResult = service.addStatisticSubjectTagToCollectionIfMissing([], null, statisticSubjectTag, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(statisticSubjectTag);
      });

      it('should return initial array if no StatisticSubjectTag is added', () => {
        const statisticSubjectTagCollection: IStatisticSubjectTag[] = [sampleWithRequiredData];
        expectedResult = service.addStatisticSubjectTagToCollectionIfMissing(statisticSubjectTagCollection, undefined, null);
        expect(expectedResult).toEqual(statisticSubjectTagCollection);
      });
    });

    describe('compareStatisticSubjectTag', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareStatisticSubjectTag(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareStatisticSubjectTag(entity1, entity2);
        const compareResult2 = service.compareStatisticSubjectTag(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareStatisticSubjectTag(entity1, entity2);
        const compareResult2 = service.compareStatisticSubjectTag(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareStatisticSubjectTag(entity1, entity2);
        const compareResult2 = service.compareStatisticSubjectTag(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
