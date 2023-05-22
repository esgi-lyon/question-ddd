import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICategoryId } from '../category-id.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../category-id.test-samples';

import { CategoryIdService } from './category-id.service';

const requireRestSample: ICategoryId = {
  ...sampleWithRequiredData,
};

describe('CategoryId Service', () => {
  let service: CategoryIdService;
  let httpMock: HttpTestingController;
  let expectedResult: ICategoryId | ICategoryId[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CategoryIdService);
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

    it('should return a list of CategoryId', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addCategoryIdToCollectionIfMissing', () => {
      it('should add a CategoryId to an empty array', () => {
        const categoryId: ICategoryId = sampleWithRequiredData;
        expectedResult = service.addCategoryIdToCollectionIfMissing([], categoryId);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(categoryId);
      });

      it('should not add a CategoryId to an array that contains it', () => {
        const categoryId: ICategoryId = sampleWithRequiredData;
        const categoryIdCollection: ICategoryId[] = [
          {
            ...categoryId,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCategoryIdToCollectionIfMissing(categoryIdCollection, categoryId);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a CategoryId to an array that doesn't contain it", () => {
        const categoryId: ICategoryId = sampleWithRequiredData;
        const categoryIdCollection: ICategoryId[] = [sampleWithPartialData];
        expectedResult = service.addCategoryIdToCollectionIfMissing(categoryIdCollection, categoryId);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(categoryId);
      });

      it('should add only unique CategoryId to an array', () => {
        const categoryIdArray: ICategoryId[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const categoryIdCollection: ICategoryId[] = [sampleWithRequiredData];
        expectedResult = service.addCategoryIdToCollectionIfMissing(categoryIdCollection, ...categoryIdArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const categoryId: ICategoryId = sampleWithRequiredData;
        const categoryId2: ICategoryId = sampleWithPartialData;
        expectedResult = service.addCategoryIdToCollectionIfMissing([], categoryId, categoryId2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(categoryId);
        expect(expectedResult).toContain(categoryId2);
      });

      it('should accept null and undefined values', () => {
        const categoryId: ICategoryId = sampleWithRequiredData;
        expectedResult = service.addCategoryIdToCollectionIfMissing([], null, categoryId, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(categoryId);
      });

      it('should return initial array if no CategoryId is added', () => {
        const categoryIdCollection: ICategoryId[] = [sampleWithRequiredData];
        expectedResult = service.addCategoryIdToCollectionIfMissing(categoryIdCollection, undefined, null);
        expect(expectedResult).toEqual(categoryIdCollection);
      });
    });

    describe('compareCategoryId', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCategoryId(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCategoryId(entity1, entity2);
        const compareResult2 = service.compareCategoryId(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCategoryId(entity1, entity2);
        const compareResult2 = service.compareCategoryId(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCategoryId(entity1, entity2);
        const compareResult2 = service.compareCategoryId(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
