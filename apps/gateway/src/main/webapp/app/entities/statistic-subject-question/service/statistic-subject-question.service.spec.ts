import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IStatisticSubjectQuestion } from '../statistic-subject-question.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../statistic-subject-question.test-samples';

import { StatisticSubjectQuestionService } from './statistic-subject-question.service';

const requireRestSample: IStatisticSubjectQuestion = {
  ...sampleWithRequiredData,
};

describe('StatisticSubjectQuestion Service', () => {
  let service: StatisticSubjectQuestionService;
  let httpMock: HttpTestingController;
  let expectedResult: IStatisticSubjectQuestion | IStatisticSubjectQuestion[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(StatisticSubjectQuestionService);
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

    it('should return a list of StatisticSubjectQuestion', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addStatisticSubjectQuestionToCollectionIfMissing', () => {
      it('should add a StatisticSubjectQuestion to an empty array', () => {
        const statisticSubjectQuestion: IStatisticSubjectQuestion = sampleWithRequiredData;
        expectedResult = service.addStatisticSubjectQuestionToCollectionIfMissing([], statisticSubjectQuestion);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(statisticSubjectQuestion);
      });

      it('should not add a StatisticSubjectQuestion to an array that contains it', () => {
        const statisticSubjectQuestion: IStatisticSubjectQuestion = sampleWithRequiredData;
        const statisticSubjectQuestionCollection: IStatisticSubjectQuestion[] = [
          {
            ...statisticSubjectQuestion,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addStatisticSubjectQuestionToCollectionIfMissing(
          statisticSubjectQuestionCollection,
          statisticSubjectQuestion
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a StatisticSubjectQuestion to an array that doesn't contain it", () => {
        const statisticSubjectQuestion: IStatisticSubjectQuestion = sampleWithRequiredData;
        const statisticSubjectQuestionCollection: IStatisticSubjectQuestion[] = [sampleWithPartialData];
        expectedResult = service.addStatisticSubjectQuestionToCollectionIfMissing(
          statisticSubjectQuestionCollection,
          statisticSubjectQuestion
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(statisticSubjectQuestion);
      });

      it('should add only unique StatisticSubjectQuestion to an array', () => {
        const statisticSubjectQuestionArray: IStatisticSubjectQuestion[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const statisticSubjectQuestionCollection: IStatisticSubjectQuestion[] = [sampleWithRequiredData];
        expectedResult = service.addStatisticSubjectQuestionToCollectionIfMissing(
          statisticSubjectQuestionCollection,
          ...statisticSubjectQuestionArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const statisticSubjectQuestion: IStatisticSubjectQuestion = sampleWithRequiredData;
        const statisticSubjectQuestion2: IStatisticSubjectQuestion = sampleWithPartialData;
        expectedResult = service.addStatisticSubjectQuestionToCollectionIfMissing([], statisticSubjectQuestion, statisticSubjectQuestion2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(statisticSubjectQuestion);
        expect(expectedResult).toContain(statisticSubjectQuestion2);
      });

      it('should accept null and undefined values', () => {
        const statisticSubjectQuestion: IStatisticSubjectQuestion = sampleWithRequiredData;
        expectedResult = service.addStatisticSubjectQuestionToCollectionIfMissing([], null, statisticSubjectQuestion, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(statisticSubjectQuestion);
      });

      it('should return initial array if no StatisticSubjectQuestion is added', () => {
        const statisticSubjectQuestionCollection: IStatisticSubjectQuestion[] = [sampleWithRequiredData];
        expectedResult = service.addStatisticSubjectQuestionToCollectionIfMissing(statisticSubjectQuestionCollection, undefined, null);
        expect(expectedResult).toEqual(statisticSubjectQuestionCollection);
      });
    });

    describe('compareStatisticSubjectQuestion', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareStatisticSubjectQuestion(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareStatisticSubjectQuestion(entity1, entity2);
        const compareResult2 = service.compareStatisticSubjectQuestion(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareStatisticSubjectQuestion(entity1, entity2);
        const compareResult2 = service.compareStatisticSubjectQuestion(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareStatisticSubjectQuestion(entity1, entity2);
        const compareResult2 = service.compareStatisticSubjectQuestion(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
