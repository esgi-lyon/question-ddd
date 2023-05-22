import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IResourceAcceptedAssociationEvent } from '../resource-accepted-association-event.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../resource-accepted-association-event.test-samples';

import { ResourceAcceptedAssociationEventService } from './resource-accepted-association-event.service';

const requireRestSample: IResourceAcceptedAssociationEvent = {
  ...sampleWithRequiredData,
};

describe('ResourceAcceptedAssociationEvent Service', () => {
  let service: ResourceAcceptedAssociationEventService;
  let httpMock: HttpTestingController;
  let expectedResult: IResourceAcceptedAssociationEvent | IResourceAcceptedAssociationEvent[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ResourceAcceptedAssociationEventService);
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

    it('should return a list of ResourceAcceptedAssociationEvent', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addResourceAcceptedAssociationEventToCollectionIfMissing', () => {
      it('should add a ResourceAcceptedAssociationEvent to an empty array', () => {
        const resourceAcceptedAssociationEvent: IResourceAcceptedAssociationEvent = sampleWithRequiredData;
        expectedResult = service.addResourceAcceptedAssociationEventToCollectionIfMissing([], resourceAcceptedAssociationEvent);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(resourceAcceptedAssociationEvent);
      });

      it('should not add a ResourceAcceptedAssociationEvent to an array that contains it', () => {
        const resourceAcceptedAssociationEvent: IResourceAcceptedAssociationEvent = sampleWithRequiredData;
        const resourceAcceptedAssociationEventCollection: IResourceAcceptedAssociationEvent[] = [
          {
            ...resourceAcceptedAssociationEvent,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addResourceAcceptedAssociationEventToCollectionIfMissing(
          resourceAcceptedAssociationEventCollection,
          resourceAcceptedAssociationEvent
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ResourceAcceptedAssociationEvent to an array that doesn't contain it", () => {
        const resourceAcceptedAssociationEvent: IResourceAcceptedAssociationEvent = sampleWithRequiredData;
        const resourceAcceptedAssociationEventCollection: IResourceAcceptedAssociationEvent[] = [sampleWithPartialData];
        expectedResult = service.addResourceAcceptedAssociationEventToCollectionIfMissing(
          resourceAcceptedAssociationEventCollection,
          resourceAcceptedAssociationEvent
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(resourceAcceptedAssociationEvent);
      });

      it('should add only unique ResourceAcceptedAssociationEvent to an array', () => {
        const resourceAcceptedAssociationEventArray: IResourceAcceptedAssociationEvent[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const resourceAcceptedAssociationEventCollection: IResourceAcceptedAssociationEvent[] = [sampleWithRequiredData];
        expectedResult = service.addResourceAcceptedAssociationEventToCollectionIfMissing(
          resourceAcceptedAssociationEventCollection,
          ...resourceAcceptedAssociationEventArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const resourceAcceptedAssociationEvent: IResourceAcceptedAssociationEvent = sampleWithRequiredData;
        const resourceAcceptedAssociationEvent2: IResourceAcceptedAssociationEvent = sampleWithPartialData;
        expectedResult = service.addResourceAcceptedAssociationEventToCollectionIfMissing(
          [],
          resourceAcceptedAssociationEvent,
          resourceAcceptedAssociationEvent2
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(resourceAcceptedAssociationEvent);
        expect(expectedResult).toContain(resourceAcceptedAssociationEvent2);
      });

      it('should accept null and undefined values', () => {
        const resourceAcceptedAssociationEvent: IResourceAcceptedAssociationEvent = sampleWithRequiredData;
        expectedResult = service.addResourceAcceptedAssociationEventToCollectionIfMissing(
          [],
          null,
          resourceAcceptedAssociationEvent,
          undefined
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(resourceAcceptedAssociationEvent);
      });

      it('should return initial array if no ResourceAcceptedAssociationEvent is added', () => {
        const resourceAcceptedAssociationEventCollection: IResourceAcceptedAssociationEvent[] = [sampleWithRequiredData];
        expectedResult = service.addResourceAcceptedAssociationEventToCollectionIfMissing(
          resourceAcceptedAssociationEventCollection,
          undefined,
          null
        );
        expect(expectedResult).toEqual(resourceAcceptedAssociationEventCollection);
      });
    });

    describe('compareResourceAcceptedAssociationEvent', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareResourceAcceptedAssociationEvent(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareResourceAcceptedAssociationEvent(entity1, entity2);
        const compareResult2 = service.compareResourceAcceptedAssociationEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareResourceAcceptedAssociationEvent(entity1, entity2);
        const compareResult2 = service.compareResourceAcceptedAssociationEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareResourceAcceptedAssociationEvent(entity1, entity2);
        const compareResult2 = service.compareResourceAcceptedAssociationEvent(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
