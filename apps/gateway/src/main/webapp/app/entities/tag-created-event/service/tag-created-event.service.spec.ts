import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITagCreatedEvent } from '../tag-created-event.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../tag-created-event.test-samples';

import { TagCreatedEventService } from './tag-created-event.service';

const requireRestSample: ITagCreatedEvent = {
  ...sampleWithRequiredData,
};

describe('TagCreatedEvent Service', () => {
  let service: TagCreatedEventService;
  let httpMock: HttpTestingController;
  let expectedResult: ITagCreatedEvent | ITagCreatedEvent[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(TagCreatedEventService);
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

    it('should return a list of TagCreatedEvent', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addTagCreatedEventToCollectionIfMissing', () => {
      it('should add a TagCreatedEvent to an empty array', () => {
        const tagCreatedEvent: ITagCreatedEvent = sampleWithRequiredData;
        expectedResult = service.addTagCreatedEventToCollectionIfMissing([], tagCreatedEvent);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tagCreatedEvent);
      });

      it('should not add a TagCreatedEvent to an array that contains it', () => {
        const tagCreatedEvent: ITagCreatedEvent = sampleWithRequiredData;
        const tagCreatedEventCollection: ITagCreatedEvent[] = [
          {
            ...tagCreatedEvent,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addTagCreatedEventToCollectionIfMissing(tagCreatedEventCollection, tagCreatedEvent);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a TagCreatedEvent to an array that doesn't contain it", () => {
        const tagCreatedEvent: ITagCreatedEvent = sampleWithRequiredData;
        const tagCreatedEventCollection: ITagCreatedEvent[] = [sampleWithPartialData];
        expectedResult = service.addTagCreatedEventToCollectionIfMissing(tagCreatedEventCollection, tagCreatedEvent);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tagCreatedEvent);
      });

      it('should add only unique TagCreatedEvent to an array', () => {
        const tagCreatedEventArray: ITagCreatedEvent[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const tagCreatedEventCollection: ITagCreatedEvent[] = [sampleWithRequiredData];
        expectedResult = service.addTagCreatedEventToCollectionIfMissing(tagCreatedEventCollection, ...tagCreatedEventArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const tagCreatedEvent: ITagCreatedEvent = sampleWithRequiredData;
        const tagCreatedEvent2: ITagCreatedEvent = sampleWithPartialData;
        expectedResult = service.addTagCreatedEventToCollectionIfMissing([], tagCreatedEvent, tagCreatedEvent2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tagCreatedEvent);
        expect(expectedResult).toContain(tagCreatedEvent2);
      });

      it('should accept null and undefined values', () => {
        const tagCreatedEvent: ITagCreatedEvent = sampleWithRequiredData;
        expectedResult = service.addTagCreatedEventToCollectionIfMissing([], null, tagCreatedEvent, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tagCreatedEvent);
      });

      it('should return initial array if no TagCreatedEvent is added', () => {
        const tagCreatedEventCollection: ITagCreatedEvent[] = [sampleWithRequiredData];
        expectedResult = service.addTagCreatedEventToCollectionIfMissing(tagCreatedEventCollection, undefined, null);
        expect(expectedResult).toEqual(tagCreatedEventCollection);
      });
    });

    describe('compareTagCreatedEvent', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareTagCreatedEvent(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareTagCreatedEvent(entity1, entity2);
        const compareResult2 = service.compareTagCreatedEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareTagCreatedEvent(entity1, entity2);
        const compareResult2 = service.compareTagCreatedEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareTagCreatedEvent(entity1, entity2);
        const compareResult2 = service.compareTagCreatedEvent(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
