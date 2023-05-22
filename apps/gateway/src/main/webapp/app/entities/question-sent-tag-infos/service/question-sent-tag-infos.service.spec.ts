import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IQuestionSentTagInfos } from '../question-sent-tag-infos.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../question-sent-tag-infos.test-samples';

import { QuestionSentTagInfosService } from './question-sent-tag-infos.service';

const requireRestSample: IQuestionSentTagInfos = {
  ...sampleWithRequiredData,
};

describe('QuestionSentTagInfos Service', () => {
  let service: QuestionSentTagInfosService;
  let httpMock: HttpTestingController;
  let expectedResult: IQuestionSentTagInfos | IQuestionSentTagInfos[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(QuestionSentTagInfosService);
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

    it('should return a list of QuestionSentTagInfos', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addQuestionSentTagInfosToCollectionIfMissing', () => {
      it('should add a QuestionSentTagInfos to an empty array', () => {
        const questionSentTagInfos: IQuestionSentTagInfos = sampleWithRequiredData;
        expectedResult = service.addQuestionSentTagInfosToCollectionIfMissing([], questionSentTagInfos);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(questionSentTagInfos);
      });

      it('should not add a QuestionSentTagInfos to an array that contains it', () => {
        const questionSentTagInfos: IQuestionSentTagInfos = sampleWithRequiredData;
        const questionSentTagInfosCollection: IQuestionSentTagInfos[] = [
          {
            ...questionSentTagInfos,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addQuestionSentTagInfosToCollectionIfMissing(questionSentTagInfosCollection, questionSentTagInfos);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a QuestionSentTagInfos to an array that doesn't contain it", () => {
        const questionSentTagInfos: IQuestionSentTagInfos = sampleWithRequiredData;
        const questionSentTagInfosCollection: IQuestionSentTagInfos[] = [sampleWithPartialData];
        expectedResult = service.addQuestionSentTagInfosToCollectionIfMissing(questionSentTagInfosCollection, questionSentTagInfos);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(questionSentTagInfos);
      });

      it('should add only unique QuestionSentTagInfos to an array', () => {
        const questionSentTagInfosArray: IQuestionSentTagInfos[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const questionSentTagInfosCollection: IQuestionSentTagInfos[] = [sampleWithRequiredData];
        expectedResult = service.addQuestionSentTagInfosToCollectionIfMissing(questionSentTagInfosCollection, ...questionSentTagInfosArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const questionSentTagInfos: IQuestionSentTagInfos = sampleWithRequiredData;
        const questionSentTagInfos2: IQuestionSentTagInfos = sampleWithPartialData;
        expectedResult = service.addQuestionSentTagInfosToCollectionIfMissing([], questionSentTagInfos, questionSentTagInfos2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(questionSentTagInfos);
        expect(expectedResult).toContain(questionSentTagInfos2);
      });

      it('should accept null and undefined values', () => {
        const questionSentTagInfos: IQuestionSentTagInfos = sampleWithRequiredData;
        expectedResult = service.addQuestionSentTagInfosToCollectionIfMissing([], null, questionSentTagInfos, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(questionSentTagInfos);
      });

      it('should return initial array if no QuestionSentTagInfos is added', () => {
        const questionSentTagInfosCollection: IQuestionSentTagInfos[] = [sampleWithRequiredData];
        expectedResult = service.addQuestionSentTagInfosToCollectionIfMissing(questionSentTagInfosCollection, undefined, null);
        expect(expectedResult).toEqual(questionSentTagInfosCollection);
      });
    });

    describe('compareQuestionSentTagInfos', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareQuestionSentTagInfos(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareQuestionSentTagInfos(entity1, entity2);
        const compareResult2 = service.compareQuestionSentTagInfos(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareQuestionSentTagInfos(entity1, entity2);
        const compareResult2 = service.compareQuestionSentTagInfos(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareQuestionSentTagInfos(entity1, entity2);
        const compareResult2 = service.compareQuestionSentTagInfos(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
