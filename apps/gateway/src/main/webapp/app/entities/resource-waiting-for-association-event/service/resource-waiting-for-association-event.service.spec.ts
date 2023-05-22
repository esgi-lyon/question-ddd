import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IResourceWaitingForAssociationEvent } from '../resource-waiting-for-association-event.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../resource-waiting-for-association-event.test-samples';

import { ResourceWaitingForAssociationEventService } from './resource-waiting-for-association-event.service';

const requireRestSample: IResourceWaitingForAssociationEvent = {
  ...sampleWithRequiredData,
};

describe('ResourceWaitingForAssociationEvent Service', () => {
  let service: ResourceWaitingForAssociationEventService;
  let httpMock: HttpTestingController;
  let expectedResult: IResourceWaitingForAssociationEvent | IResourceWaitingForAssociationEvent[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ResourceWaitingForAssociationEventService);
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

    it('should return a list of ResourceWaitingForAssociationEvent', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addResourceWaitingForAssociationEventToCollectionIfMissing', () => {
      it('should add a ResourceWaitingForAssociationEvent to an empty array', () => {
        const resourceWaitingForAssociationEvent: IResourceWaitingForAssociationEvent = sampleWithRequiredData;
        expectedResult = service.addResourceWaitingForAssociationEventToCollectionIfMissing([], resourceWaitingForAssociationEvent);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(resourceWaitingForAssociationEvent);
      });

      it('should not add a ResourceWaitingForAssociationEvent to an array that contains it', () => {
        const resourceWaitingForAssociationEvent: IResourceWaitingForAssociationEvent = sampleWithRequiredData;
        const resourceWaitingForAssociationEventCollection: IResourceWaitingForAssociationEvent[] = [
          {
            ...resourceWaitingForAssociationEvent,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addResourceWaitingForAssociationEventToCollectionIfMissing(
          resourceWaitingForAssociationEventCollection,
          resourceWaitingForAssociationEvent
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ResourceWaitingForAssociationEvent to an array that doesn't contain it", () => {
        const resourceWaitingForAssociationEvent: IResourceWaitingForAssociationEvent = sampleWithRequiredData;
        const resourceWaitingForAssociationEventCollection: IResourceWaitingForAssociationEvent[] = [sampleWithPartialData];
        expectedResult = service.addResourceWaitingForAssociationEventToCollectionIfMissing(
          resourceWaitingForAssociationEventCollection,
          resourceWaitingForAssociationEvent
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(resourceWaitingForAssociationEvent);
      });

      it('should add only unique ResourceWaitingForAssociationEvent to an array', () => {
        const resourceWaitingForAssociationEventArray: IResourceWaitingForAssociationEvent[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const resourceWaitingForAssociationEventCollection: IResourceWaitingForAssociationEvent[] = [sampleWithRequiredData];
        expectedResult = service.addResourceWaitingForAssociationEventToCollectionIfMissing(
          resourceWaitingForAssociationEventCollection,
          ...resourceWaitingForAssociationEventArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const resourceWaitingForAssociationEvent: IResourceWaitingForAssociationEvent = sampleWithRequiredData;
        const resourceWaitingForAssociationEvent2: IResourceWaitingForAssociationEvent = sampleWithPartialData;
        expectedResult = service.addResourceWaitingForAssociationEventToCollectionIfMissing(
          [],
          resourceWaitingForAssociationEvent,
          resourceWaitingForAssociationEvent2
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(resourceWaitingForAssociationEvent);
        expect(expectedResult).toContain(resourceWaitingForAssociationEvent2);
      });

      it('should accept null and undefined values', () => {
        const resourceWaitingForAssociationEvent: IResourceWaitingForAssociationEvent = sampleWithRequiredData;
        expectedResult = service.addResourceWaitingForAssociationEventToCollectionIfMissing(
          [],
          null,
          resourceWaitingForAssociationEvent,
          undefined
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(resourceWaitingForAssociationEvent);
      });

      it('should return initial array if no ResourceWaitingForAssociationEvent is added', () => {
        const resourceWaitingForAssociationEventCollection: IResourceWaitingForAssociationEvent[] = [sampleWithRequiredData];
        expectedResult = service.addResourceWaitingForAssociationEventToCollectionIfMissing(
          resourceWaitingForAssociationEventCollection,
          undefined,
          null
        );
        expect(expectedResult).toEqual(resourceWaitingForAssociationEventCollection);
      });
    });

    describe('compareResourceWaitingForAssociationEvent', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareResourceWaitingForAssociationEvent(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareResourceWaitingForAssociationEvent(entity1, entity2);
        const compareResult2 = service.compareResourceWaitingForAssociationEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareResourceWaitingForAssociationEvent(entity1, entity2);
        const compareResult2 = service.compareResourceWaitingForAssociationEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareResourceWaitingForAssociationEvent(entity1, entity2);
        const compareResult2 = service.compareResourceWaitingForAssociationEvent(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
