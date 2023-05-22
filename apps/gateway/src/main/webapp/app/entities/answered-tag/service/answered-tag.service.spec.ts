import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAnsweredTag } from '../answered-tag.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../answered-tag.test-samples';

import { AnsweredTagService } from './answered-tag.service';

const requireRestSample: IAnsweredTag = {
  ...sampleWithRequiredData,
};

describe('AnsweredTag Service', () => {
  let service: AnsweredTagService;
  let httpMock: HttpTestingController;
  let expectedResult: IAnsweredTag | IAnsweredTag[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AnsweredTagService);
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

    it('should return a list of AnsweredTag', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addAnsweredTagToCollectionIfMissing', () => {
      it('should add a AnsweredTag to an empty array', () => {
        const answeredTag: IAnsweredTag = sampleWithRequiredData;
        expectedResult = service.addAnsweredTagToCollectionIfMissing([], answeredTag);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(answeredTag);
      });

      it('should not add a AnsweredTag to an array that contains it', () => {
        const answeredTag: IAnsweredTag = sampleWithRequiredData;
        const answeredTagCollection: IAnsweredTag[] = [
          {
            ...answeredTag,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAnsweredTagToCollectionIfMissing(answeredTagCollection, answeredTag);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a AnsweredTag to an array that doesn't contain it", () => {
        const answeredTag: IAnsweredTag = sampleWithRequiredData;
        const answeredTagCollection: IAnsweredTag[] = [sampleWithPartialData];
        expectedResult = service.addAnsweredTagToCollectionIfMissing(answeredTagCollection, answeredTag);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(answeredTag);
      });

      it('should add only unique AnsweredTag to an array', () => {
        const answeredTagArray: IAnsweredTag[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const answeredTagCollection: IAnsweredTag[] = [sampleWithRequiredData];
        expectedResult = service.addAnsweredTagToCollectionIfMissing(answeredTagCollection, ...answeredTagArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const answeredTag: IAnsweredTag = sampleWithRequiredData;
        const answeredTag2: IAnsweredTag = sampleWithPartialData;
        expectedResult = service.addAnsweredTagToCollectionIfMissing([], answeredTag, answeredTag2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(answeredTag);
        expect(expectedResult).toContain(answeredTag2);
      });

      it('should accept null and undefined values', () => {
        const answeredTag: IAnsweredTag = sampleWithRequiredData;
        expectedResult = service.addAnsweredTagToCollectionIfMissing([], null, answeredTag, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(answeredTag);
      });

      it('should return initial array if no AnsweredTag is added', () => {
        const answeredTagCollection: IAnsweredTag[] = [sampleWithRequiredData];
        expectedResult = service.addAnsweredTagToCollectionIfMissing(answeredTagCollection, undefined, null);
        expect(expectedResult).toEqual(answeredTagCollection);
      });
    });

    describe('compareAnsweredTag', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAnsweredTag(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAnsweredTag(entity1, entity2);
        const compareResult2 = service.compareAnsweredTag(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAnsweredTag(entity1, entity2);
        const compareResult2 = service.compareAnsweredTag(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAnsweredTag(entity1, entity2);
        const compareResult2 = service.compareAnsweredTag(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
