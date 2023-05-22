import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICreatedQuestionEvent } from '../created-question-event.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../created-question-event.test-samples';

import { CreatedQuestionEventService } from './created-question-event.service';

const requireRestSample: ICreatedQuestionEvent = {
  ...sampleWithRequiredData,
};

describe('CreatedQuestionEvent Service', () => {
  let service: CreatedQuestionEventService;
  let httpMock: HttpTestingController;
  let expectedResult: ICreatedQuestionEvent | ICreatedQuestionEvent[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CreatedQuestionEventService);
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

    it('should return a list of CreatedQuestionEvent', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addCreatedQuestionEventToCollectionIfMissing', () => {
      it('should add a CreatedQuestionEvent to an empty array', () => {
        const createdQuestionEvent: ICreatedQuestionEvent = sampleWithRequiredData;
        expectedResult = service.addCreatedQuestionEventToCollectionIfMissing([], createdQuestionEvent);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(createdQuestionEvent);
      });

      it('should not add a CreatedQuestionEvent to an array that contains it', () => {
        const createdQuestionEvent: ICreatedQuestionEvent = sampleWithRequiredData;
        const createdQuestionEventCollection: ICreatedQuestionEvent[] = [
          {
            ...createdQuestionEvent,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCreatedQuestionEventToCollectionIfMissing(createdQuestionEventCollection, createdQuestionEvent);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a CreatedQuestionEvent to an array that doesn't contain it", () => {
        const createdQuestionEvent: ICreatedQuestionEvent = sampleWithRequiredData;
        const createdQuestionEventCollection: ICreatedQuestionEvent[] = [sampleWithPartialData];
        expectedResult = service.addCreatedQuestionEventToCollectionIfMissing(createdQuestionEventCollection, createdQuestionEvent);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(createdQuestionEvent);
      });

      it('should add only unique CreatedQuestionEvent to an array', () => {
        const createdQuestionEventArray: ICreatedQuestionEvent[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const createdQuestionEventCollection: ICreatedQuestionEvent[] = [sampleWithRequiredData];
        expectedResult = service.addCreatedQuestionEventToCollectionIfMissing(createdQuestionEventCollection, ...createdQuestionEventArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const createdQuestionEvent: ICreatedQuestionEvent = sampleWithRequiredData;
        const createdQuestionEvent2: ICreatedQuestionEvent = sampleWithPartialData;
        expectedResult = service.addCreatedQuestionEventToCollectionIfMissing([], createdQuestionEvent, createdQuestionEvent2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(createdQuestionEvent);
        expect(expectedResult).toContain(createdQuestionEvent2);
      });

      it('should accept null and undefined values', () => {
        const createdQuestionEvent: ICreatedQuestionEvent = sampleWithRequiredData;
        expectedResult = service.addCreatedQuestionEventToCollectionIfMissing([], null, createdQuestionEvent, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(createdQuestionEvent);
      });

      it('should return initial array if no CreatedQuestionEvent is added', () => {
        const createdQuestionEventCollection: ICreatedQuestionEvent[] = [sampleWithRequiredData];
        expectedResult = service.addCreatedQuestionEventToCollectionIfMissing(createdQuestionEventCollection, undefined, null);
        expect(expectedResult).toEqual(createdQuestionEventCollection);
      });
    });

    describe('compareCreatedQuestionEvent', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCreatedQuestionEvent(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCreatedQuestionEvent(entity1, entity2);
        const compareResult2 = service.compareCreatedQuestionEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCreatedQuestionEvent(entity1, entity2);
        const compareResult2 = service.compareCreatedQuestionEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCreatedQuestionEvent(entity1, entity2);
        const compareResult2 = service.compareCreatedQuestionEvent(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
