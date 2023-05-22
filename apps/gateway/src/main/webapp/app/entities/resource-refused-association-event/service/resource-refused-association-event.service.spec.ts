import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IResourceRefusedAssociationEvent } from '../resource-refused-association-event.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../resource-refused-association-event.test-samples';

import { ResourceRefusedAssociationEventService } from './resource-refused-association-event.service';

const requireRestSample: IResourceRefusedAssociationEvent = {
  ...sampleWithRequiredData,
};

describe('ResourceRefusedAssociationEvent Service', () => {
  let service: ResourceRefusedAssociationEventService;
  let httpMock: HttpTestingController;
  let expectedResult: IResourceRefusedAssociationEvent | IResourceRefusedAssociationEvent[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ResourceRefusedAssociationEventService);
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

    it('should return a list of ResourceRefusedAssociationEvent', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addResourceRefusedAssociationEventToCollectionIfMissing', () => {
      it('should add a ResourceRefusedAssociationEvent to an empty array', () => {
        const resourceRefusedAssociationEvent: IResourceRefusedAssociationEvent = sampleWithRequiredData;
        expectedResult = service.addResourceRefusedAssociationEventToCollectionIfMissing([], resourceRefusedAssociationEvent);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(resourceRefusedAssociationEvent);
      });

      it('should not add a ResourceRefusedAssociationEvent to an array that contains it', () => {
        const resourceRefusedAssociationEvent: IResourceRefusedAssociationEvent = sampleWithRequiredData;
        const resourceRefusedAssociationEventCollection: IResourceRefusedAssociationEvent[] = [
          {
            ...resourceRefusedAssociationEvent,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addResourceRefusedAssociationEventToCollectionIfMissing(
          resourceRefusedAssociationEventCollection,
          resourceRefusedAssociationEvent
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ResourceRefusedAssociationEvent to an array that doesn't contain it", () => {
        const resourceRefusedAssociationEvent: IResourceRefusedAssociationEvent = sampleWithRequiredData;
        const resourceRefusedAssociationEventCollection: IResourceRefusedAssociationEvent[] = [sampleWithPartialData];
        expectedResult = service.addResourceRefusedAssociationEventToCollectionIfMissing(
          resourceRefusedAssociationEventCollection,
          resourceRefusedAssociationEvent
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(resourceRefusedAssociationEvent);
      });

      it('should add only unique ResourceRefusedAssociationEvent to an array', () => {
        const resourceRefusedAssociationEventArray: IResourceRefusedAssociationEvent[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const resourceRefusedAssociationEventCollection: IResourceRefusedAssociationEvent[] = [sampleWithRequiredData];
        expectedResult = service.addResourceRefusedAssociationEventToCollectionIfMissing(
          resourceRefusedAssociationEventCollection,
          ...resourceRefusedAssociationEventArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const resourceRefusedAssociationEvent: IResourceRefusedAssociationEvent = sampleWithRequiredData;
        const resourceRefusedAssociationEvent2: IResourceRefusedAssociationEvent = sampleWithPartialData;
        expectedResult = service.addResourceRefusedAssociationEventToCollectionIfMissing(
          [],
          resourceRefusedAssociationEvent,
          resourceRefusedAssociationEvent2
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(resourceRefusedAssociationEvent);
        expect(expectedResult).toContain(resourceRefusedAssociationEvent2);
      });

      it('should accept null and undefined values', () => {
        const resourceRefusedAssociationEvent: IResourceRefusedAssociationEvent = sampleWithRequiredData;
        expectedResult = service.addResourceRefusedAssociationEventToCollectionIfMissing(
          [],
          null,
          resourceRefusedAssociationEvent,
          undefined
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(resourceRefusedAssociationEvent);
      });

      it('should return initial array if no ResourceRefusedAssociationEvent is added', () => {
        const resourceRefusedAssociationEventCollection: IResourceRefusedAssociationEvent[] = [sampleWithRequiredData];
        expectedResult = service.addResourceRefusedAssociationEventToCollectionIfMissing(
          resourceRefusedAssociationEventCollection,
          undefined,
          null
        );
        expect(expectedResult).toEqual(resourceRefusedAssociationEventCollection);
      });
    });

    describe('compareResourceRefusedAssociationEvent', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareResourceRefusedAssociationEvent(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareResourceRefusedAssociationEvent(entity1, entity2);
        const compareResult2 = service.compareResourceRefusedAssociationEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareResourceRefusedAssociationEvent(entity1, entity2);
        const compareResult2 = service.compareResourceRefusedAssociationEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareResourceRefusedAssociationEvent(entity1, entity2);
        const compareResult2 = service.compareResourceRefusedAssociationEvent(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
