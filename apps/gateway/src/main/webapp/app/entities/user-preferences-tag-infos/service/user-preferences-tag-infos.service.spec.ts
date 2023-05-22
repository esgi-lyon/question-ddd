import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IUserPreferencesTagInfos } from '../user-preferences-tag-infos.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../user-preferences-tag-infos.test-samples';

import { UserPreferencesTagInfosService } from './user-preferences-tag-infos.service';

const requireRestSample: IUserPreferencesTagInfos = {
  ...sampleWithRequiredData,
};

describe('UserPreferencesTagInfos Service', () => {
  let service: UserPreferencesTagInfosService;
  let httpMock: HttpTestingController;
  let expectedResult: IUserPreferencesTagInfos | IUserPreferencesTagInfos[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(UserPreferencesTagInfosService);
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

    it('should return a list of UserPreferencesTagInfos', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addUserPreferencesTagInfosToCollectionIfMissing', () => {
      it('should add a UserPreferencesTagInfos to an empty array', () => {
        const userPreferencesTagInfos: IUserPreferencesTagInfos = sampleWithRequiredData;
        expectedResult = service.addUserPreferencesTagInfosToCollectionIfMissing([], userPreferencesTagInfos);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(userPreferencesTagInfos);
      });

      it('should not add a UserPreferencesTagInfos to an array that contains it', () => {
        const userPreferencesTagInfos: IUserPreferencesTagInfos = sampleWithRequiredData;
        const userPreferencesTagInfosCollection: IUserPreferencesTagInfos[] = [
          {
            ...userPreferencesTagInfos,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addUserPreferencesTagInfosToCollectionIfMissing(
          userPreferencesTagInfosCollection,
          userPreferencesTagInfos
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a UserPreferencesTagInfos to an array that doesn't contain it", () => {
        const userPreferencesTagInfos: IUserPreferencesTagInfos = sampleWithRequiredData;
        const userPreferencesTagInfosCollection: IUserPreferencesTagInfos[] = [sampleWithPartialData];
        expectedResult = service.addUserPreferencesTagInfosToCollectionIfMissing(
          userPreferencesTagInfosCollection,
          userPreferencesTagInfos
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(userPreferencesTagInfos);
      });

      it('should add only unique UserPreferencesTagInfos to an array', () => {
        const userPreferencesTagInfosArray: IUserPreferencesTagInfos[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const userPreferencesTagInfosCollection: IUserPreferencesTagInfos[] = [sampleWithRequiredData];
        expectedResult = service.addUserPreferencesTagInfosToCollectionIfMissing(
          userPreferencesTagInfosCollection,
          ...userPreferencesTagInfosArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const userPreferencesTagInfos: IUserPreferencesTagInfos = sampleWithRequiredData;
        const userPreferencesTagInfos2: IUserPreferencesTagInfos = sampleWithPartialData;
        expectedResult = service.addUserPreferencesTagInfosToCollectionIfMissing([], userPreferencesTagInfos, userPreferencesTagInfos2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(userPreferencesTagInfos);
        expect(expectedResult).toContain(userPreferencesTagInfos2);
      });

      it('should accept null and undefined values', () => {
        const userPreferencesTagInfos: IUserPreferencesTagInfos = sampleWithRequiredData;
        expectedResult = service.addUserPreferencesTagInfosToCollectionIfMissing([], null, userPreferencesTagInfos, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(userPreferencesTagInfos);
      });

      it('should return initial array if no UserPreferencesTagInfos is added', () => {
        const userPreferencesTagInfosCollection: IUserPreferencesTagInfos[] = [sampleWithRequiredData];
        expectedResult = service.addUserPreferencesTagInfosToCollectionIfMissing(userPreferencesTagInfosCollection, undefined, null);
        expect(expectedResult).toEqual(userPreferencesTagInfosCollection);
      });
    });

    describe('compareUserPreferencesTagInfos', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareUserPreferencesTagInfos(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareUserPreferencesTagInfos(entity1, entity2);
        const compareResult2 = service.compareUserPreferencesTagInfos(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareUserPreferencesTagInfos(entity1, entity2);
        const compareResult2 = service.compareUserPreferencesTagInfos(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareUserPreferencesTagInfos(entity1, entity2);
        const compareResult2 = service.compareUserPreferencesTagInfos(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
