import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEvaluatedAnswer } from '../evaluated-answer.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../evaluated-answer.test-samples';

import { EvaluatedAnswerService } from './evaluated-answer.service';

const requireRestSample: IEvaluatedAnswer = {
  ...sampleWithRequiredData,
};

describe('EvaluatedAnswer Service', () => {
  let service: EvaluatedAnswerService;
  let httpMock: HttpTestingController;
  let expectedResult: IEvaluatedAnswer | IEvaluatedAnswer[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(EvaluatedAnswerService);
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

    it('should return a list of EvaluatedAnswer', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addEvaluatedAnswerToCollectionIfMissing', () => {
      it('should add a EvaluatedAnswer to an empty array', () => {
        const evaluatedAnswer: IEvaluatedAnswer = sampleWithRequiredData;
        expectedResult = service.addEvaluatedAnswerToCollectionIfMissing([], evaluatedAnswer);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(evaluatedAnswer);
      });

      it('should not add a EvaluatedAnswer to an array that contains it', () => {
        const evaluatedAnswer: IEvaluatedAnswer = sampleWithRequiredData;
        const evaluatedAnswerCollection: IEvaluatedAnswer[] = [
          {
            ...evaluatedAnswer,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addEvaluatedAnswerToCollectionIfMissing(evaluatedAnswerCollection, evaluatedAnswer);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a EvaluatedAnswer to an array that doesn't contain it", () => {
        const evaluatedAnswer: IEvaluatedAnswer = sampleWithRequiredData;
        const evaluatedAnswerCollection: IEvaluatedAnswer[] = [sampleWithPartialData];
        expectedResult = service.addEvaluatedAnswerToCollectionIfMissing(evaluatedAnswerCollection, evaluatedAnswer);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(evaluatedAnswer);
      });

      it('should add only unique EvaluatedAnswer to an array', () => {
        const evaluatedAnswerArray: IEvaluatedAnswer[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const evaluatedAnswerCollection: IEvaluatedAnswer[] = [sampleWithRequiredData];
        expectedResult = service.addEvaluatedAnswerToCollectionIfMissing(evaluatedAnswerCollection, ...evaluatedAnswerArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const evaluatedAnswer: IEvaluatedAnswer = sampleWithRequiredData;
        const evaluatedAnswer2: IEvaluatedAnswer = sampleWithPartialData;
        expectedResult = service.addEvaluatedAnswerToCollectionIfMissing([], evaluatedAnswer, evaluatedAnswer2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(evaluatedAnswer);
        expect(expectedResult).toContain(evaluatedAnswer2);
      });

      it('should accept null and undefined values', () => {
        const evaluatedAnswer: IEvaluatedAnswer = sampleWithRequiredData;
        expectedResult = service.addEvaluatedAnswerToCollectionIfMissing([], null, evaluatedAnswer, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(evaluatedAnswer);
      });

      it('should return initial array if no EvaluatedAnswer is added', () => {
        const evaluatedAnswerCollection: IEvaluatedAnswer[] = [sampleWithRequiredData];
        expectedResult = service.addEvaluatedAnswerToCollectionIfMissing(evaluatedAnswerCollection, undefined, null);
        expect(expectedResult).toEqual(evaluatedAnswerCollection);
      });
    });

    describe('compareEvaluatedAnswer', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareEvaluatedAnswer(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareEvaluatedAnswer(entity1, entity2);
        const compareResult2 = service.compareEvaluatedAnswer(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareEvaluatedAnswer(entity1, entity2);
        const compareResult2 = service.compareEvaluatedAnswer(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareEvaluatedAnswer(entity1, entity2);
        const compareResult2 = service.compareEvaluatedAnswer(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
