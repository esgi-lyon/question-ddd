import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAnswerCheckedEvent } from '../answer-checked-event.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../answer-checked-event.test-samples';

import { AnswerCheckedEventService } from './answer-checked-event.service';

const requireRestSample: IAnswerCheckedEvent = {
  ...sampleWithRequiredData,
};

describe('AnswerCheckedEvent Service', () => {
  let service: AnswerCheckedEventService;
  let httpMock: HttpTestingController;
  let expectedResult: IAnswerCheckedEvent | IAnswerCheckedEvent[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AnswerCheckedEventService);
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

    it('should return a list of AnswerCheckedEvent', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addAnswerCheckedEventToCollectionIfMissing', () => {
      it('should add a AnswerCheckedEvent to an empty array', () => {
        const answerCheckedEvent: IAnswerCheckedEvent = sampleWithRequiredData;
        expectedResult = service.addAnswerCheckedEventToCollectionIfMissing([], answerCheckedEvent);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(answerCheckedEvent);
      });

      it('should not add a AnswerCheckedEvent to an array that contains it', () => {
        const answerCheckedEvent: IAnswerCheckedEvent = sampleWithRequiredData;
        const answerCheckedEventCollection: IAnswerCheckedEvent[] = [
          {
            ...answerCheckedEvent,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAnswerCheckedEventToCollectionIfMissing(answerCheckedEventCollection, answerCheckedEvent);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a AnswerCheckedEvent to an array that doesn't contain it", () => {
        const answerCheckedEvent: IAnswerCheckedEvent = sampleWithRequiredData;
        const answerCheckedEventCollection: IAnswerCheckedEvent[] = [sampleWithPartialData];
        expectedResult = service.addAnswerCheckedEventToCollectionIfMissing(answerCheckedEventCollection, answerCheckedEvent);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(answerCheckedEvent);
      });

      it('should add only unique AnswerCheckedEvent to an array', () => {
        const answerCheckedEventArray: IAnswerCheckedEvent[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const answerCheckedEventCollection: IAnswerCheckedEvent[] = [sampleWithRequiredData];
        expectedResult = service.addAnswerCheckedEventToCollectionIfMissing(answerCheckedEventCollection, ...answerCheckedEventArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const answerCheckedEvent: IAnswerCheckedEvent = sampleWithRequiredData;
        const answerCheckedEvent2: IAnswerCheckedEvent = sampleWithPartialData;
        expectedResult = service.addAnswerCheckedEventToCollectionIfMissing([], answerCheckedEvent, answerCheckedEvent2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(answerCheckedEvent);
        expect(expectedResult).toContain(answerCheckedEvent2);
      });

      it('should accept null and undefined values', () => {
        const answerCheckedEvent: IAnswerCheckedEvent = sampleWithRequiredData;
        expectedResult = service.addAnswerCheckedEventToCollectionIfMissing([], null, answerCheckedEvent, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(answerCheckedEvent);
      });

      it('should return initial array if no AnswerCheckedEvent is added', () => {
        const answerCheckedEventCollection: IAnswerCheckedEvent[] = [sampleWithRequiredData];
        expectedResult = service.addAnswerCheckedEventToCollectionIfMissing(answerCheckedEventCollection, undefined, null);
        expect(expectedResult).toEqual(answerCheckedEventCollection);
      });
    });

    describe('compareAnswerCheckedEvent', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAnswerCheckedEvent(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAnswerCheckedEvent(entity1, entity2);
        const compareResult2 = service.compareAnswerCheckedEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAnswerCheckedEvent(entity1, entity2);
        const compareResult2 = service.compareAnswerCheckedEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAnswerCheckedEvent(entity1, entity2);
        const compareResult2 = service.compareAnswerCheckedEvent(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
