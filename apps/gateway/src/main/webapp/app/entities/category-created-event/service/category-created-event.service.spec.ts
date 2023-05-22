import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICategoryCreatedEvent } from '../category-created-event.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../category-created-event.test-samples';

import { CategoryCreatedEventService } from './category-created-event.service';

const requireRestSample: ICategoryCreatedEvent = {
  ...sampleWithRequiredData,
};

describe('CategoryCreatedEvent Service', () => {
  let service: CategoryCreatedEventService;
  let httpMock: HttpTestingController;
  let expectedResult: ICategoryCreatedEvent | ICategoryCreatedEvent[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CategoryCreatedEventService);
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

    it('should return a list of CategoryCreatedEvent', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addCategoryCreatedEventToCollectionIfMissing', () => {
      it('should add a CategoryCreatedEvent to an empty array', () => {
        const categoryCreatedEvent: ICategoryCreatedEvent = sampleWithRequiredData;
        expectedResult = service.addCategoryCreatedEventToCollectionIfMissing([], categoryCreatedEvent);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(categoryCreatedEvent);
      });

      it('should not add a CategoryCreatedEvent to an array that contains it', () => {
        const categoryCreatedEvent: ICategoryCreatedEvent = sampleWithRequiredData;
        const categoryCreatedEventCollection: ICategoryCreatedEvent[] = [
          {
            ...categoryCreatedEvent,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCategoryCreatedEventToCollectionIfMissing(categoryCreatedEventCollection, categoryCreatedEvent);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a CategoryCreatedEvent to an array that doesn't contain it", () => {
        const categoryCreatedEvent: ICategoryCreatedEvent = sampleWithRequiredData;
        const categoryCreatedEventCollection: ICategoryCreatedEvent[] = [sampleWithPartialData];
        expectedResult = service.addCategoryCreatedEventToCollectionIfMissing(categoryCreatedEventCollection, categoryCreatedEvent);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(categoryCreatedEvent);
      });

      it('should add only unique CategoryCreatedEvent to an array', () => {
        const categoryCreatedEventArray: ICategoryCreatedEvent[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const categoryCreatedEventCollection: ICategoryCreatedEvent[] = [sampleWithRequiredData];
        expectedResult = service.addCategoryCreatedEventToCollectionIfMissing(categoryCreatedEventCollection, ...categoryCreatedEventArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const categoryCreatedEvent: ICategoryCreatedEvent = sampleWithRequiredData;
        const categoryCreatedEvent2: ICategoryCreatedEvent = sampleWithPartialData;
        expectedResult = service.addCategoryCreatedEventToCollectionIfMissing([], categoryCreatedEvent, categoryCreatedEvent2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(categoryCreatedEvent);
        expect(expectedResult).toContain(categoryCreatedEvent2);
      });

      it('should accept null and undefined values', () => {
        const categoryCreatedEvent: ICategoryCreatedEvent = sampleWithRequiredData;
        expectedResult = service.addCategoryCreatedEventToCollectionIfMissing([], null, categoryCreatedEvent, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(categoryCreatedEvent);
      });

      it('should return initial array if no CategoryCreatedEvent is added', () => {
        const categoryCreatedEventCollection: ICategoryCreatedEvent[] = [sampleWithRequiredData];
        expectedResult = service.addCategoryCreatedEventToCollectionIfMissing(categoryCreatedEventCollection, undefined, null);
        expect(expectedResult).toEqual(categoryCreatedEventCollection);
      });
    });

    describe('compareCategoryCreatedEvent', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCategoryCreatedEvent(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCategoryCreatedEvent(entity1, entity2);
        const compareResult2 = service.compareCategoryCreatedEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCategoryCreatedEvent(entity1, entity2);
        const compareResult2 = service.compareCategoryCreatedEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCategoryCreatedEvent(entity1, entity2);
        const compareResult2 = service.compareCategoryCreatedEvent(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
