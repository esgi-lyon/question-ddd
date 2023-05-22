import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IQuestionSent } from '../question-sent.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../question-sent.test-samples';

import { QuestionSentService, RestQuestionSent } from './question-sent.service';

const requireRestSample: RestQuestionSent = {
  ...sampleWithRequiredData,
  sentDate: sampleWithRequiredData.sentDate?.format(DATE_FORMAT),
  viewedDate: sampleWithRequiredData.viewedDate?.format(DATE_FORMAT),
  answeredDate: sampleWithRequiredData.answeredDate?.format(DATE_FORMAT),
};

describe('QuestionSent Service', () => {
  let service: QuestionSentService;
  let httpMock: HttpTestingController;
  let expectedResult: IQuestionSent | IQuestionSent[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(QuestionSentService);
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

    it('should create a QuestionSent', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const questionSent = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(questionSent).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a QuestionSent', () => {
      const questionSent = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(questionSent).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a QuestionSent', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of QuestionSent', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a QuestionSent', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addQuestionSentToCollectionIfMissing', () => {
      it('should add a QuestionSent to an empty array', () => {
        const questionSent: IQuestionSent = sampleWithRequiredData;
        expectedResult = service.addQuestionSentToCollectionIfMissing([], questionSent);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(questionSent);
      });

      it('should not add a QuestionSent to an array that contains it', () => {
        const questionSent: IQuestionSent = sampleWithRequiredData;
        const questionSentCollection: IQuestionSent[] = [
          {
            ...questionSent,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addQuestionSentToCollectionIfMissing(questionSentCollection, questionSent);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a QuestionSent to an array that doesn't contain it", () => {
        const questionSent: IQuestionSent = sampleWithRequiredData;
        const questionSentCollection: IQuestionSent[] = [sampleWithPartialData];
        expectedResult = service.addQuestionSentToCollectionIfMissing(questionSentCollection, questionSent);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(questionSent);
      });

      it('should add only unique QuestionSent to an array', () => {
        const questionSentArray: IQuestionSent[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const questionSentCollection: IQuestionSent[] = [sampleWithRequiredData];
        expectedResult = service.addQuestionSentToCollectionIfMissing(questionSentCollection, ...questionSentArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const questionSent: IQuestionSent = sampleWithRequiredData;
        const questionSent2: IQuestionSent = sampleWithPartialData;
        expectedResult = service.addQuestionSentToCollectionIfMissing([], questionSent, questionSent2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(questionSent);
        expect(expectedResult).toContain(questionSent2);
      });

      it('should accept null and undefined values', () => {
        const questionSent: IQuestionSent = sampleWithRequiredData;
        expectedResult = service.addQuestionSentToCollectionIfMissing([], null, questionSent, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(questionSent);
      });

      it('should return initial array if no QuestionSent is added', () => {
        const questionSentCollection: IQuestionSent[] = [sampleWithRequiredData];
        expectedResult = service.addQuestionSentToCollectionIfMissing(questionSentCollection, undefined, null);
        expect(expectedResult).toEqual(questionSentCollection);
      });
    });

    describe('compareQuestionSent', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareQuestionSent(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareQuestionSent(entity1, entity2);
        const compareResult2 = service.compareQuestionSent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareQuestionSent(entity1, entity2);
        const compareResult2 = service.compareQuestionSent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareQuestionSent(entity1, entity2);
        const compareResult2 = service.compareQuestionSent(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
