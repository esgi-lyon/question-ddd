import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IQuestionSentQuestionResourceTagId } from '../question-sent-question-resource-tag-id.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../question-sent-question-resource-tag-id.test-samples';

import { QuestionSentQuestionResourceTagIdService } from './question-sent-question-resource-tag-id.service';

const requireRestSample: IQuestionSentQuestionResourceTagId = {
  ...sampleWithRequiredData,
};

describe('QuestionSentQuestionResourceTagId Service', () => {
  let service: QuestionSentQuestionResourceTagIdService;
  let httpMock: HttpTestingController;
  let expectedResult: IQuestionSentQuestionResourceTagId | IQuestionSentQuestionResourceTagId[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(QuestionSentQuestionResourceTagIdService);
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

    it('should return a list of QuestionSentQuestionResourceTagId', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addQuestionSentQuestionResourceTagIdToCollectionIfMissing', () => {
      it('should add a QuestionSentQuestionResourceTagId to an empty array', () => {
        const questionSentQuestionResourceTagId: IQuestionSentQuestionResourceTagId = sampleWithRequiredData;
        expectedResult = service.addQuestionSentQuestionResourceTagIdToCollectionIfMissing([], questionSentQuestionResourceTagId);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(questionSentQuestionResourceTagId);
      });

      it('should not add a QuestionSentQuestionResourceTagId to an array that contains it', () => {
        const questionSentQuestionResourceTagId: IQuestionSentQuestionResourceTagId = sampleWithRequiredData;
        const questionSentQuestionResourceTagIdCollection: IQuestionSentQuestionResourceTagId[] = [
          {
            ...questionSentQuestionResourceTagId,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addQuestionSentQuestionResourceTagIdToCollectionIfMissing(
          questionSentQuestionResourceTagIdCollection,
          questionSentQuestionResourceTagId
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a QuestionSentQuestionResourceTagId to an array that doesn't contain it", () => {
        const questionSentQuestionResourceTagId: IQuestionSentQuestionResourceTagId = sampleWithRequiredData;
        const questionSentQuestionResourceTagIdCollection: IQuestionSentQuestionResourceTagId[] = [sampleWithPartialData];
        expectedResult = service.addQuestionSentQuestionResourceTagIdToCollectionIfMissing(
          questionSentQuestionResourceTagIdCollection,
          questionSentQuestionResourceTagId
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(questionSentQuestionResourceTagId);
      });

      it('should add only unique QuestionSentQuestionResourceTagId to an array', () => {
        const questionSentQuestionResourceTagIdArray: IQuestionSentQuestionResourceTagId[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const questionSentQuestionResourceTagIdCollection: IQuestionSentQuestionResourceTagId[] = [sampleWithRequiredData];
        expectedResult = service.addQuestionSentQuestionResourceTagIdToCollectionIfMissing(
          questionSentQuestionResourceTagIdCollection,
          ...questionSentQuestionResourceTagIdArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const questionSentQuestionResourceTagId: IQuestionSentQuestionResourceTagId = sampleWithRequiredData;
        const questionSentQuestionResourceTagId2: IQuestionSentQuestionResourceTagId = sampleWithPartialData;
        expectedResult = service.addQuestionSentQuestionResourceTagIdToCollectionIfMissing(
          [],
          questionSentQuestionResourceTagId,
          questionSentQuestionResourceTagId2
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(questionSentQuestionResourceTagId);
        expect(expectedResult).toContain(questionSentQuestionResourceTagId2);
      });

      it('should accept null and undefined values', () => {
        const questionSentQuestionResourceTagId: IQuestionSentQuestionResourceTagId = sampleWithRequiredData;
        expectedResult = service.addQuestionSentQuestionResourceTagIdToCollectionIfMissing(
          [],
          null,
          questionSentQuestionResourceTagId,
          undefined
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(questionSentQuestionResourceTagId);
      });

      it('should return initial array if no QuestionSentQuestionResourceTagId is added', () => {
        const questionSentQuestionResourceTagIdCollection: IQuestionSentQuestionResourceTagId[] = [sampleWithRequiredData];
        expectedResult = service.addQuestionSentQuestionResourceTagIdToCollectionIfMissing(
          questionSentQuestionResourceTagIdCollection,
          undefined,
          null
        );
        expect(expectedResult).toEqual(questionSentQuestionResourceTagIdCollection);
      });
    });

    describe('compareQuestionSentQuestionResourceTagId', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareQuestionSentQuestionResourceTagId(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareQuestionSentQuestionResourceTagId(entity1, entity2);
        const compareResult2 = service.compareQuestionSentQuestionResourceTagId(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareQuestionSentQuestionResourceTagId(entity1, entity2);
        const compareResult2 = service.compareQuestionSentQuestionResourceTagId(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareQuestionSentQuestionResourceTagId(entity1, entity2);
        const compareResult2 = service.compareQuestionSentQuestionResourceTagId(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
