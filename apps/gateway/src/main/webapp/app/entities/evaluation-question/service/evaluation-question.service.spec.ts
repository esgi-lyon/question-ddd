import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEvaluationQuestion } from '../evaluation-question.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../evaluation-question.test-samples';

import { EvaluationQuestionService } from './evaluation-question.service';

const requireRestSample: IEvaluationQuestion = {
  ...sampleWithRequiredData,
};

describe('EvaluationQuestion Service', () => {
  let service: EvaluationQuestionService;
  let httpMock: HttpTestingController;
  let expectedResult: IEvaluationQuestion | IEvaluationQuestion[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(EvaluationQuestionService);
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

    it('should return a list of EvaluationQuestion', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addEvaluationQuestionToCollectionIfMissing', () => {
      it('should add a EvaluationQuestion to an empty array', () => {
        const evaluationQuestion: IEvaluationQuestion = sampleWithRequiredData;
        expectedResult = service.addEvaluationQuestionToCollectionIfMissing([], evaluationQuestion);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(evaluationQuestion);
      });

      it('should not add a EvaluationQuestion to an array that contains it', () => {
        const evaluationQuestion: IEvaluationQuestion = sampleWithRequiredData;
        const evaluationQuestionCollection: IEvaluationQuestion[] = [
          {
            ...evaluationQuestion,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addEvaluationQuestionToCollectionIfMissing(evaluationQuestionCollection, evaluationQuestion);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a EvaluationQuestion to an array that doesn't contain it", () => {
        const evaluationQuestion: IEvaluationQuestion = sampleWithRequiredData;
        const evaluationQuestionCollection: IEvaluationQuestion[] = [sampleWithPartialData];
        expectedResult = service.addEvaluationQuestionToCollectionIfMissing(evaluationQuestionCollection, evaluationQuestion);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(evaluationQuestion);
      });

      it('should add only unique EvaluationQuestion to an array', () => {
        const evaluationQuestionArray: IEvaluationQuestion[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const evaluationQuestionCollection: IEvaluationQuestion[] = [sampleWithRequiredData];
        expectedResult = service.addEvaluationQuestionToCollectionIfMissing(evaluationQuestionCollection, ...evaluationQuestionArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const evaluationQuestion: IEvaluationQuestion = sampleWithRequiredData;
        const evaluationQuestion2: IEvaluationQuestion = sampleWithPartialData;
        expectedResult = service.addEvaluationQuestionToCollectionIfMissing([], evaluationQuestion, evaluationQuestion2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(evaluationQuestion);
        expect(expectedResult).toContain(evaluationQuestion2);
      });

      it('should accept null and undefined values', () => {
        const evaluationQuestion: IEvaluationQuestion = sampleWithRequiredData;
        expectedResult = service.addEvaluationQuestionToCollectionIfMissing([], null, evaluationQuestion, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(evaluationQuestion);
      });

      it('should return initial array if no EvaluationQuestion is added', () => {
        const evaluationQuestionCollection: IEvaluationQuestion[] = [sampleWithRequiredData];
        expectedResult = service.addEvaluationQuestionToCollectionIfMissing(evaluationQuestionCollection, undefined, null);
        expect(expectedResult).toEqual(evaluationQuestionCollection);
      });
    });

    describe('compareEvaluationQuestion', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareEvaluationQuestion(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareEvaluationQuestion(entity1, entity2);
        const compareResult2 = service.compareEvaluationQuestion(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareEvaluationQuestion(entity1, entity2);
        const compareResult2 = service.compareEvaluationQuestion(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareEvaluationQuestion(entity1, entity2);
        const compareResult2 = service.compareEvaluationQuestion(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
