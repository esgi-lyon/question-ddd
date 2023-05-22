import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IUserWithPreferencesId } from '../user-with-preferences-id.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../user-with-preferences-id.test-samples';

import { UserWithPreferencesIdService } from './user-with-preferences-id.service';

const requireRestSample: IUserWithPreferencesId = {
  ...sampleWithRequiredData,
};

describe('UserWithPreferencesId Service', () => {
  let service: UserWithPreferencesIdService;
  let httpMock: HttpTestingController;
  let expectedResult: IUserWithPreferencesId | IUserWithPreferencesId[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(UserWithPreferencesIdService);
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

    it('should return a list of UserWithPreferencesId', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addUserWithPreferencesIdToCollectionIfMissing', () => {
      it('should add a UserWithPreferencesId to an empty array', () => {
        const userWithPreferencesId: IUserWithPreferencesId = sampleWithRequiredData;
        expectedResult = service.addUserWithPreferencesIdToCollectionIfMissing([], userWithPreferencesId);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(userWithPreferencesId);
      });

      it('should not add a UserWithPreferencesId to an array that contains it', () => {
        const userWithPreferencesId: IUserWithPreferencesId = sampleWithRequiredData;
        const userWithPreferencesIdCollection: IUserWithPreferencesId[] = [
          {
            ...userWithPreferencesId,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addUserWithPreferencesIdToCollectionIfMissing(userWithPreferencesIdCollection, userWithPreferencesId);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a UserWithPreferencesId to an array that doesn't contain it", () => {
        const userWithPreferencesId: IUserWithPreferencesId = sampleWithRequiredData;
        const userWithPreferencesIdCollection: IUserWithPreferencesId[] = [sampleWithPartialData];
        expectedResult = service.addUserWithPreferencesIdToCollectionIfMissing(userWithPreferencesIdCollection, userWithPreferencesId);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(userWithPreferencesId);
      });

      it('should add only unique UserWithPreferencesId to an array', () => {
        const userWithPreferencesIdArray: IUserWithPreferencesId[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const userWithPreferencesIdCollection: IUserWithPreferencesId[] = [sampleWithRequiredData];
        expectedResult = service.addUserWithPreferencesIdToCollectionIfMissing(
          userWithPreferencesIdCollection,
          ...userWithPreferencesIdArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const userWithPreferencesId: IUserWithPreferencesId = sampleWithRequiredData;
        const userWithPreferencesId2: IUserWithPreferencesId = sampleWithPartialData;
        expectedResult = service.addUserWithPreferencesIdToCollectionIfMissing([], userWithPreferencesId, userWithPreferencesId2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(userWithPreferencesId);
        expect(expectedResult).toContain(userWithPreferencesId2);
      });

      it('should accept null and undefined values', () => {
        const userWithPreferencesId: IUserWithPreferencesId = sampleWithRequiredData;
        expectedResult = service.addUserWithPreferencesIdToCollectionIfMissing([], null, userWithPreferencesId, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(userWithPreferencesId);
      });

      it('should return initial array if no UserWithPreferencesId is added', () => {
        const userWithPreferencesIdCollection: IUserWithPreferencesId[] = [sampleWithRequiredData];
        expectedResult = service.addUserWithPreferencesIdToCollectionIfMissing(userWithPreferencesIdCollection, undefined, null);
        expect(expectedResult).toEqual(userWithPreferencesIdCollection);
      });
    });

    describe('compareUserWithPreferencesId', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareUserWithPreferencesId(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareUserWithPreferencesId(entity1, entity2);
        const compareResult2 = service.compareUserWithPreferencesId(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareUserWithPreferencesId(entity1, entity2);
        const compareResult2 = service.compareUserWithPreferencesId(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareUserWithPreferencesId(entity1, entity2);
        const compareResult2 = service.compareUserWithPreferencesId(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
