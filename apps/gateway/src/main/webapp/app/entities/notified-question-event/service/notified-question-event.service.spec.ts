import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { INotifiedQuestionEvent } from '../notified-question-event.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../notified-question-event.test-samples';

import { NotifiedQuestionEventService } from './notified-question-event.service';

const requireRestSample: INotifiedQuestionEvent = {
  ...sampleWithRequiredData,
};

describe('NotifiedQuestionEvent Service', () => {
  let service: NotifiedQuestionEventService;
  let httpMock: HttpTestingController;
  let expectedResult: INotifiedQuestionEvent | INotifiedQuestionEvent[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(NotifiedQuestionEventService);
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

    it('should return a list of NotifiedQuestionEvent', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addNotifiedQuestionEventToCollectionIfMissing', () => {
      it('should add a NotifiedQuestionEvent to an empty array', () => {
        const notifiedQuestionEvent: INotifiedQuestionEvent = sampleWithRequiredData;
        expectedResult = service.addNotifiedQuestionEventToCollectionIfMissing([], notifiedQuestionEvent);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(notifiedQuestionEvent);
      });

      it('should not add a NotifiedQuestionEvent to an array that contains it', () => {
        const notifiedQuestionEvent: INotifiedQuestionEvent = sampleWithRequiredData;
        const notifiedQuestionEventCollection: INotifiedQuestionEvent[] = [
          {
            ...notifiedQuestionEvent,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addNotifiedQuestionEventToCollectionIfMissing(notifiedQuestionEventCollection, notifiedQuestionEvent);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a NotifiedQuestionEvent to an array that doesn't contain it", () => {
        const notifiedQuestionEvent: INotifiedQuestionEvent = sampleWithRequiredData;
        const notifiedQuestionEventCollection: INotifiedQuestionEvent[] = [sampleWithPartialData];
        expectedResult = service.addNotifiedQuestionEventToCollectionIfMissing(notifiedQuestionEventCollection, notifiedQuestionEvent);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(notifiedQuestionEvent);
      });

      it('should add only unique NotifiedQuestionEvent to an array', () => {
        const notifiedQuestionEventArray: INotifiedQuestionEvent[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const notifiedQuestionEventCollection: INotifiedQuestionEvent[] = [sampleWithRequiredData];
        expectedResult = service.addNotifiedQuestionEventToCollectionIfMissing(
          notifiedQuestionEventCollection,
          ...notifiedQuestionEventArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const notifiedQuestionEvent: INotifiedQuestionEvent = sampleWithRequiredData;
        const notifiedQuestionEvent2: INotifiedQuestionEvent = sampleWithPartialData;
        expectedResult = service.addNotifiedQuestionEventToCollectionIfMissing([], notifiedQuestionEvent, notifiedQuestionEvent2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(notifiedQuestionEvent);
        expect(expectedResult).toContain(notifiedQuestionEvent2);
      });

      it('should accept null and undefined values', () => {
        const notifiedQuestionEvent: INotifiedQuestionEvent = sampleWithRequiredData;
        expectedResult = service.addNotifiedQuestionEventToCollectionIfMissing([], null, notifiedQuestionEvent, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(notifiedQuestionEvent);
      });

      it('should return initial array if no NotifiedQuestionEvent is added', () => {
        const notifiedQuestionEventCollection: INotifiedQuestionEvent[] = [sampleWithRequiredData];
        expectedResult = service.addNotifiedQuestionEventToCollectionIfMissing(notifiedQuestionEventCollection, undefined, null);
        expect(expectedResult).toEqual(notifiedQuestionEventCollection);
      });
    });

    describe('compareNotifiedQuestionEvent', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareNotifiedQuestionEvent(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareNotifiedQuestionEvent(entity1, entity2);
        const compareResult2 = service.compareNotifiedQuestionEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareNotifiedQuestionEvent(entity1, entity2);
        const compareResult2 = service.compareNotifiedQuestionEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareNotifiedQuestionEvent(entity1, entity2);
        const compareResult2 = service.compareNotifiedQuestionEvent(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
