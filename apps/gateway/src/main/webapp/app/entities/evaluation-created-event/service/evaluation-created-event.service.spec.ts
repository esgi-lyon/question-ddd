import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEvaluationCreatedEvent } from '../evaluation-created-event.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../evaluation-created-event.test-samples';

import { EvaluationCreatedEventService } from './evaluation-created-event.service';

const requireRestSample: IEvaluationCreatedEvent = {
  ...sampleWithRequiredData,
};

describe('EvaluationCreatedEvent Service', () => {
  let service: EvaluationCreatedEventService;
  let httpMock: HttpTestingController;
  let expectedResult: IEvaluationCreatedEvent | IEvaluationCreatedEvent[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(EvaluationCreatedEventService);
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

    it('should return a list of EvaluationCreatedEvent', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addEvaluationCreatedEventToCollectionIfMissing', () => {
      it('should add a EvaluationCreatedEvent to an empty array', () => {
        const evaluationCreatedEvent: IEvaluationCreatedEvent = sampleWithRequiredData;
        expectedResult = service.addEvaluationCreatedEventToCollectionIfMissing([], evaluationCreatedEvent);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(evaluationCreatedEvent);
      });

      it('should not add a EvaluationCreatedEvent to an array that contains it', () => {
        const evaluationCreatedEvent: IEvaluationCreatedEvent = sampleWithRequiredData;
        const evaluationCreatedEventCollection: IEvaluationCreatedEvent[] = [
          {
            ...evaluationCreatedEvent,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addEvaluationCreatedEventToCollectionIfMissing(evaluationCreatedEventCollection, evaluationCreatedEvent);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a EvaluationCreatedEvent to an array that doesn't contain it", () => {
        const evaluationCreatedEvent: IEvaluationCreatedEvent = sampleWithRequiredData;
        const evaluationCreatedEventCollection: IEvaluationCreatedEvent[] = [sampleWithPartialData];
        expectedResult = service.addEvaluationCreatedEventToCollectionIfMissing(evaluationCreatedEventCollection, evaluationCreatedEvent);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(evaluationCreatedEvent);
      });

      it('should add only unique EvaluationCreatedEvent to an array', () => {
        const evaluationCreatedEventArray: IEvaluationCreatedEvent[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const evaluationCreatedEventCollection: IEvaluationCreatedEvent[] = [sampleWithRequiredData];
        expectedResult = service.addEvaluationCreatedEventToCollectionIfMissing(
          evaluationCreatedEventCollection,
          ...evaluationCreatedEventArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const evaluationCreatedEvent: IEvaluationCreatedEvent = sampleWithRequiredData;
        const evaluationCreatedEvent2: IEvaluationCreatedEvent = sampleWithPartialData;
        expectedResult = service.addEvaluationCreatedEventToCollectionIfMissing([], evaluationCreatedEvent, evaluationCreatedEvent2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(evaluationCreatedEvent);
        expect(expectedResult).toContain(evaluationCreatedEvent2);
      });

      it('should accept null and undefined values', () => {
        const evaluationCreatedEvent: IEvaluationCreatedEvent = sampleWithRequiredData;
        expectedResult = service.addEvaluationCreatedEventToCollectionIfMissing([], null, evaluationCreatedEvent, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(evaluationCreatedEvent);
      });

      it('should return initial array if no EvaluationCreatedEvent is added', () => {
        const evaluationCreatedEventCollection: IEvaluationCreatedEvent[] = [sampleWithRequiredData];
        expectedResult = service.addEvaluationCreatedEventToCollectionIfMissing(evaluationCreatedEventCollection, undefined, null);
        expect(expectedResult).toEqual(evaluationCreatedEventCollection);
      });
    });

    describe('compareEvaluationCreatedEvent', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareEvaluationCreatedEvent(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareEvaluationCreatedEvent(entity1, entity2);
        const compareResult2 = service.compareEvaluationCreatedEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareEvaluationCreatedEvent(entity1, entity2);
        const compareResult2 = service.compareEvaluationCreatedEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareEvaluationCreatedEvent(entity1, entity2);
        const compareResult2 = service.compareEvaluationCreatedEvent(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
