import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITagChoicesListedEvent } from '../tag-choices-listed-event.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../tag-choices-listed-event.test-samples';

import { TagChoicesListedEventService } from './tag-choices-listed-event.service';

const requireRestSample: ITagChoicesListedEvent = {
  ...sampleWithRequiredData,
};

describe('TagChoicesListedEvent Service', () => {
  let service: TagChoicesListedEventService;
  let httpMock: HttpTestingController;
  let expectedResult: ITagChoicesListedEvent | ITagChoicesListedEvent[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(TagChoicesListedEventService);
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

    it('should return a list of TagChoicesListedEvent', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addTagChoicesListedEventToCollectionIfMissing', () => {
      it('should add a TagChoicesListedEvent to an empty array', () => {
        const tagChoicesListedEvent: ITagChoicesListedEvent = sampleWithRequiredData;
        expectedResult = service.addTagChoicesListedEventToCollectionIfMissing([], tagChoicesListedEvent);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tagChoicesListedEvent);
      });

      it('should not add a TagChoicesListedEvent to an array that contains it', () => {
        const tagChoicesListedEvent: ITagChoicesListedEvent = sampleWithRequiredData;
        const tagChoicesListedEventCollection: ITagChoicesListedEvent[] = [
          {
            ...tagChoicesListedEvent,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addTagChoicesListedEventToCollectionIfMissing(tagChoicesListedEventCollection, tagChoicesListedEvent);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a TagChoicesListedEvent to an array that doesn't contain it", () => {
        const tagChoicesListedEvent: ITagChoicesListedEvent = sampleWithRequiredData;
        const tagChoicesListedEventCollection: ITagChoicesListedEvent[] = [sampleWithPartialData];
        expectedResult = service.addTagChoicesListedEventToCollectionIfMissing(tagChoicesListedEventCollection, tagChoicesListedEvent);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tagChoicesListedEvent);
      });

      it('should add only unique TagChoicesListedEvent to an array', () => {
        const tagChoicesListedEventArray: ITagChoicesListedEvent[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const tagChoicesListedEventCollection: ITagChoicesListedEvent[] = [sampleWithRequiredData];
        expectedResult = service.addTagChoicesListedEventToCollectionIfMissing(
          tagChoicesListedEventCollection,
          ...tagChoicesListedEventArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const tagChoicesListedEvent: ITagChoicesListedEvent = sampleWithRequiredData;
        const tagChoicesListedEvent2: ITagChoicesListedEvent = sampleWithPartialData;
        expectedResult = service.addTagChoicesListedEventToCollectionIfMissing([], tagChoicesListedEvent, tagChoicesListedEvent2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tagChoicesListedEvent);
        expect(expectedResult).toContain(tagChoicesListedEvent2);
      });

      it('should accept null and undefined values', () => {
        const tagChoicesListedEvent: ITagChoicesListedEvent = sampleWithRequiredData;
        expectedResult = service.addTagChoicesListedEventToCollectionIfMissing([], null, tagChoicesListedEvent, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tagChoicesListedEvent);
      });

      it('should return initial array if no TagChoicesListedEvent is added', () => {
        const tagChoicesListedEventCollection: ITagChoicesListedEvent[] = [sampleWithRequiredData];
        expectedResult = service.addTagChoicesListedEventToCollectionIfMissing(tagChoicesListedEventCollection, undefined, null);
        expect(expectedResult).toEqual(tagChoicesListedEventCollection);
      });
    });

    describe('compareTagChoicesListedEvent', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareTagChoicesListedEvent(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareTagChoicesListedEvent(entity1, entity2);
        const compareResult2 = service.compareTagChoicesListedEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareTagChoicesListedEvent(entity1, entity2);
        const compareResult2 = service.compareTagChoicesListedEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareTagChoicesListedEvent(entity1, entity2);
        const compareResult2 = service.compareTagChoicesListedEvent(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
