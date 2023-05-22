import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITagStatsViewedEvent } from '../tag-stats-viewed-event.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../tag-stats-viewed-event.test-samples';

import { TagStatsViewedEventService } from './tag-stats-viewed-event.service';

const requireRestSample: ITagStatsViewedEvent = {
  ...sampleWithRequiredData,
};

describe('TagStatsViewedEvent Service', () => {
  let service: TagStatsViewedEventService;
  let httpMock: HttpTestingController;
  let expectedResult: ITagStatsViewedEvent | ITagStatsViewedEvent[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(TagStatsViewedEventService);
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

    it('should return a list of TagStatsViewedEvent', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addTagStatsViewedEventToCollectionIfMissing', () => {
      it('should add a TagStatsViewedEvent to an empty array', () => {
        const tagStatsViewedEvent: ITagStatsViewedEvent = sampleWithRequiredData;
        expectedResult = service.addTagStatsViewedEventToCollectionIfMissing([], tagStatsViewedEvent);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tagStatsViewedEvent);
      });

      it('should not add a TagStatsViewedEvent to an array that contains it', () => {
        const tagStatsViewedEvent: ITagStatsViewedEvent = sampleWithRequiredData;
        const tagStatsViewedEventCollection: ITagStatsViewedEvent[] = [
          {
            ...tagStatsViewedEvent,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addTagStatsViewedEventToCollectionIfMissing(tagStatsViewedEventCollection, tagStatsViewedEvent);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a TagStatsViewedEvent to an array that doesn't contain it", () => {
        const tagStatsViewedEvent: ITagStatsViewedEvent = sampleWithRequiredData;
        const tagStatsViewedEventCollection: ITagStatsViewedEvent[] = [sampleWithPartialData];
        expectedResult = service.addTagStatsViewedEventToCollectionIfMissing(tagStatsViewedEventCollection, tagStatsViewedEvent);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tagStatsViewedEvent);
      });

      it('should add only unique TagStatsViewedEvent to an array', () => {
        const tagStatsViewedEventArray: ITagStatsViewedEvent[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const tagStatsViewedEventCollection: ITagStatsViewedEvent[] = [sampleWithRequiredData];
        expectedResult = service.addTagStatsViewedEventToCollectionIfMissing(tagStatsViewedEventCollection, ...tagStatsViewedEventArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const tagStatsViewedEvent: ITagStatsViewedEvent = sampleWithRequiredData;
        const tagStatsViewedEvent2: ITagStatsViewedEvent = sampleWithPartialData;
        expectedResult = service.addTagStatsViewedEventToCollectionIfMissing([], tagStatsViewedEvent, tagStatsViewedEvent2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tagStatsViewedEvent);
        expect(expectedResult).toContain(tagStatsViewedEvent2);
      });

      it('should accept null and undefined values', () => {
        const tagStatsViewedEvent: ITagStatsViewedEvent = sampleWithRequiredData;
        expectedResult = service.addTagStatsViewedEventToCollectionIfMissing([], null, tagStatsViewedEvent, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tagStatsViewedEvent);
      });

      it('should return initial array if no TagStatsViewedEvent is added', () => {
        const tagStatsViewedEventCollection: ITagStatsViewedEvent[] = [sampleWithRequiredData];
        expectedResult = service.addTagStatsViewedEventToCollectionIfMissing(tagStatsViewedEventCollection, undefined, null);
        expect(expectedResult).toEqual(tagStatsViewedEventCollection);
      });
    });

    describe('compareTagStatsViewedEvent', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareTagStatsViewedEvent(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareTagStatsViewedEvent(entity1, entity2);
        const compareResult2 = service.compareTagStatsViewedEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareTagStatsViewedEvent(entity1, entity2);
        const compareResult2 = service.compareTagStatsViewedEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareTagStatsViewedEvent(entity1, entity2);
        const compareResult2 = service.compareTagStatsViewedEvent(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
