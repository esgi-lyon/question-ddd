import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IQuestionResourceTagInfos } from '../question-resource-tag-infos.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../question-resource-tag-infos.test-samples';

import { QuestionResourceTagInfosService } from './question-resource-tag-infos.service';

const requireRestSample: IQuestionResourceTagInfos = {
  ...sampleWithRequiredData,
};

describe('QuestionResourceTagInfos Service', () => {
  let service: QuestionResourceTagInfosService;
  let httpMock: HttpTestingController;
  let expectedResult: IQuestionResourceTagInfos | IQuestionResourceTagInfos[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(QuestionResourceTagInfosService);
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

    it('should return a list of QuestionResourceTagInfos', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addQuestionResourceTagInfosToCollectionIfMissing', () => {
      it('should add a QuestionResourceTagInfos to an empty array', () => {
        const questionResourceTagInfos: IQuestionResourceTagInfos = sampleWithRequiredData;
        expectedResult = service.addQuestionResourceTagInfosToCollectionIfMissing([], questionResourceTagInfos);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(questionResourceTagInfos);
      });

      it('should not add a QuestionResourceTagInfos to an array that contains it', () => {
        const questionResourceTagInfos: IQuestionResourceTagInfos = sampleWithRequiredData;
        const questionResourceTagInfosCollection: IQuestionResourceTagInfos[] = [
          {
            ...questionResourceTagInfos,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addQuestionResourceTagInfosToCollectionIfMissing(
          questionResourceTagInfosCollection,
          questionResourceTagInfos
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a QuestionResourceTagInfos to an array that doesn't contain it", () => {
        const questionResourceTagInfos: IQuestionResourceTagInfos = sampleWithRequiredData;
        const questionResourceTagInfosCollection: IQuestionResourceTagInfos[] = [sampleWithPartialData];
        expectedResult = service.addQuestionResourceTagInfosToCollectionIfMissing(
          questionResourceTagInfosCollection,
          questionResourceTagInfos
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(questionResourceTagInfos);
      });

      it('should add only unique QuestionResourceTagInfos to an array', () => {
        const questionResourceTagInfosArray: IQuestionResourceTagInfos[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const questionResourceTagInfosCollection: IQuestionResourceTagInfos[] = [sampleWithRequiredData];
        expectedResult = service.addQuestionResourceTagInfosToCollectionIfMissing(
          questionResourceTagInfosCollection,
          ...questionResourceTagInfosArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const questionResourceTagInfos: IQuestionResourceTagInfos = sampleWithRequiredData;
        const questionResourceTagInfos2: IQuestionResourceTagInfos = sampleWithPartialData;
        expectedResult = service.addQuestionResourceTagInfosToCollectionIfMissing([], questionResourceTagInfos, questionResourceTagInfos2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(questionResourceTagInfos);
        expect(expectedResult).toContain(questionResourceTagInfos2);
      });

      it('should accept null and undefined values', () => {
        const questionResourceTagInfos: IQuestionResourceTagInfos = sampleWithRequiredData;
        expectedResult = service.addQuestionResourceTagInfosToCollectionIfMissing([], null, questionResourceTagInfos, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(questionResourceTagInfos);
      });

      it('should return initial array if no QuestionResourceTagInfos is added', () => {
        const questionResourceTagInfosCollection: IQuestionResourceTagInfos[] = [sampleWithRequiredData];
        expectedResult = service.addQuestionResourceTagInfosToCollectionIfMissing(questionResourceTagInfosCollection, undefined, null);
        expect(expectedResult).toEqual(questionResourceTagInfosCollection);
      });
    });

    describe('compareQuestionResourceTagInfos', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareQuestionResourceTagInfos(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareQuestionResourceTagInfos(entity1, entity2);
        const compareResult2 = service.compareQuestionResourceTagInfos(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareQuestionResourceTagInfos(entity1, entity2);
        const compareResult2 = service.compareQuestionResourceTagInfos(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareQuestionResourceTagInfos(entity1, entity2);
        const compareResult2 = service.compareQuestionResourceTagInfos(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
