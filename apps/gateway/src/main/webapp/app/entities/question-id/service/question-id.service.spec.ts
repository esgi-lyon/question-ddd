import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IQuestionId } from '../question-id.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../question-id.test-samples';

import { QuestionIdService } from './question-id.service';

const requireRestSample: IQuestionId = {
  ...sampleWithRequiredData,
};

describe('QuestionId Service', () => {
  let service: QuestionIdService;
  let httpMock: HttpTestingController;
  let expectedResult: IQuestionId | IQuestionId[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(QuestionIdService);
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

    it('should return a list of QuestionId', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addQuestionIdToCollectionIfMissing', () => {
      it('should add a QuestionId to an empty array', () => {
        const questionId: IQuestionId = sampleWithRequiredData;
        expectedResult = service.addQuestionIdToCollectionIfMissing([], questionId);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(questionId);
      });

      it('should not add a QuestionId to an array that contains it', () => {
        const questionId: IQuestionId = sampleWithRequiredData;
        const questionIdCollection: IQuestionId[] = [
          {
            ...questionId,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addQuestionIdToCollectionIfMissing(questionIdCollection, questionId);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a QuestionId to an array that doesn't contain it", () => {
        const questionId: IQuestionId = sampleWithRequiredData;
        const questionIdCollection: IQuestionId[] = [sampleWithPartialData];
        expectedResult = service.addQuestionIdToCollectionIfMissing(questionIdCollection, questionId);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(questionId);
      });

      it('should add only unique QuestionId to an array', () => {
        const questionIdArray: IQuestionId[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const questionIdCollection: IQuestionId[] = [sampleWithRequiredData];
        expectedResult = service.addQuestionIdToCollectionIfMissing(questionIdCollection, ...questionIdArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const questionId: IQuestionId = sampleWithRequiredData;
        const questionId2: IQuestionId = sampleWithPartialData;
        expectedResult = service.addQuestionIdToCollectionIfMissing([], questionId, questionId2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(questionId);
        expect(expectedResult).toContain(questionId2);
      });

      it('should accept null and undefined values', () => {
        const questionId: IQuestionId = sampleWithRequiredData;
        expectedResult = service.addQuestionIdToCollectionIfMissing([], null, questionId, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(questionId);
      });

      it('should return initial array if no QuestionId is added', () => {
        const questionIdCollection: IQuestionId[] = [sampleWithRequiredData];
        expectedResult = service.addQuestionIdToCollectionIfMissing(questionIdCollection, undefined, null);
        expect(expectedResult).toEqual(questionIdCollection);
      });
    });

    describe('compareQuestionId', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareQuestionId(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareQuestionId(entity1, entity2);
        const compareResult2 = service.compareQuestionId(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareQuestionId(entity1, entity2);
        const compareResult2 = service.compareQuestionId(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareQuestionId(entity1, entity2);
        const compareResult2 = service.compareQuestionId(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
