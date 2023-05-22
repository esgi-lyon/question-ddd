import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAnswerSubmittedEvent } from '../answer-submitted-event.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../answer-submitted-event.test-samples';

import { AnswerSubmittedEventService } from './answer-submitted-event.service';

const requireRestSample: IAnswerSubmittedEvent = {
  ...sampleWithRequiredData,
};

describe('AnswerSubmittedEvent Service', () => {
  let service: AnswerSubmittedEventService;
  let httpMock: HttpTestingController;
  let expectedResult: IAnswerSubmittedEvent | IAnswerSubmittedEvent[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AnswerSubmittedEventService);
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

    it('should return a list of AnswerSubmittedEvent', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addAnswerSubmittedEventToCollectionIfMissing', () => {
      it('should add a AnswerSubmittedEvent to an empty array', () => {
        const answerSubmittedEvent: IAnswerSubmittedEvent = sampleWithRequiredData;
        expectedResult = service.addAnswerSubmittedEventToCollectionIfMissing([], answerSubmittedEvent);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(answerSubmittedEvent);
      });

      it('should not add a AnswerSubmittedEvent to an array that contains it', () => {
        const answerSubmittedEvent: IAnswerSubmittedEvent = sampleWithRequiredData;
        const answerSubmittedEventCollection: IAnswerSubmittedEvent[] = [
          {
            ...answerSubmittedEvent,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAnswerSubmittedEventToCollectionIfMissing(answerSubmittedEventCollection, answerSubmittedEvent);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a AnswerSubmittedEvent to an array that doesn't contain it", () => {
        const answerSubmittedEvent: IAnswerSubmittedEvent = sampleWithRequiredData;
        const answerSubmittedEventCollection: IAnswerSubmittedEvent[] = [sampleWithPartialData];
        expectedResult = service.addAnswerSubmittedEventToCollectionIfMissing(answerSubmittedEventCollection, answerSubmittedEvent);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(answerSubmittedEvent);
      });

      it('should add only unique AnswerSubmittedEvent to an array', () => {
        const answerSubmittedEventArray: IAnswerSubmittedEvent[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const answerSubmittedEventCollection: IAnswerSubmittedEvent[] = [sampleWithRequiredData];
        expectedResult = service.addAnswerSubmittedEventToCollectionIfMissing(answerSubmittedEventCollection, ...answerSubmittedEventArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const answerSubmittedEvent: IAnswerSubmittedEvent = sampleWithRequiredData;
        const answerSubmittedEvent2: IAnswerSubmittedEvent = sampleWithPartialData;
        expectedResult = service.addAnswerSubmittedEventToCollectionIfMissing([], answerSubmittedEvent, answerSubmittedEvent2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(answerSubmittedEvent);
        expect(expectedResult).toContain(answerSubmittedEvent2);
      });

      it('should accept null and undefined values', () => {
        const answerSubmittedEvent: IAnswerSubmittedEvent = sampleWithRequiredData;
        expectedResult = service.addAnswerSubmittedEventToCollectionIfMissing([], null, answerSubmittedEvent, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(answerSubmittedEvent);
      });

      it('should return initial array if no AnswerSubmittedEvent is added', () => {
        const answerSubmittedEventCollection: IAnswerSubmittedEvent[] = [sampleWithRequiredData];
        expectedResult = service.addAnswerSubmittedEventToCollectionIfMissing(answerSubmittedEventCollection, undefined, null);
        expect(expectedResult).toEqual(answerSubmittedEventCollection);
      });
    });

    describe('compareAnswerSubmittedEvent', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAnswerSubmittedEvent(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAnswerSubmittedEvent(entity1, entity2);
        const compareResult2 = service.compareAnswerSubmittedEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAnswerSubmittedEvent(entity1, entity2);
        const compareResult2 = service.compareAnswerSubmittedEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAnswerSubmittedEvent(entity1, entity2);
        const compareResult2 = service.compareAnswerSubmittedEvent(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
