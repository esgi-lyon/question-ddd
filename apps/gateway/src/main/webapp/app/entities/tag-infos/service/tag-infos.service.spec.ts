import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITagInfos } from '../tag-infos.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../tag-infos.test-samples';

import { TagInfosService } from './tag-infos.service';

const requireRestSample: ITagInfos = {
  ...sampleWithRequiredData,
};

describe('TagInfos Service', () => {
  let service: TagInfosService;
  let httpMock: HttpTestingController;
  let expectedResult: ITagInfos | ITagInfos[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(TagInfosService);
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

    it('should return a list of TagInfos', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addTagInfosToCollectionIfMissing', () => {
      it('should add a TagInfos to an empty array', () => {
        const tagInfos: ITagInfos = sampleWithRequiredData;
        expectedResult = service.addTagInfosToCollectionIfMissing([], tagInfos);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tagInfos);
      });

      it('should not add a TagInfos to an array that contains it', () => {
        const tagInfos: ITagInfos = sampleWithRequiredData;
        const tagInfosCollection: ITagInfos[] = [
          {
            ...tagInfos,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addTagInfosToCollectionIfMissing(tagInfosCollection, tagInfos);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a TagInfos to an array that doesn't contain it", () => {
        const tagInfos: ITagInfos = sampleWithRequiredData;
        const tagInfosCollection: ITagInfos[] = [sampleWithPartialData];
        expectedResult = service.addTagInfosToCollectionIfMissing(tagInfosCollection, tagInfos);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tagInfos);
      });

      it('should add only unique TagInfos to an array', () => {
        const tagInfosArray: ITagInfos[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const tagInfosCollection: ITagInfos[] = [sampleWithRequiredData];
        expectedResult = service.addTagInfosToCollectionIfMissing(tagInfosCollection, ...tagInfosArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const tagInfos: ITagInfos = sampleWithRequiredData;
        const tagInfos2: ITagInfos = sampleWithPartialData;
        expectedResult = service.addTagInfosToCollectionIfMissing([], tagInfos, tagInfos2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tagInfos);
        expect(expectedResult).toContain(tagInfos2);
      });

      it('should accept null and undefined values', () => {
        const tagInfos: ITagInfos = sampleWithRequiredData;
        expectedResult = service.addTagInfosToCollectionIfMissing([], null, tagInfos, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tagInfos);
      });

      it('should return initial array if no TagInfos is added', () => {
        const tagInfosCollection: ITagInfos[] = [sampleWithRequiredData];
        expectedResult = service.addTagInfosToCollectionIfMissing(tagInfosCollection, undefined, null);
        expect(expectedResult).toEqual(tagInfosCollection);
      });
    });

    describe('compareTagInfos', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareTagInfos(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareTagInfos(entity1, entity2);
        const compareResult2 = service.compareTagInfos(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareTagInfos(entity1, entity2);
        const compareResult2 = service.compareTagInfos(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareTagInfos(entity1, entity2);
        const compareResult2 = service.compareTagInfos(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
