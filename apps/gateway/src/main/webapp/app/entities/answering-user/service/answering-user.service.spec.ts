import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAnsweringUser } from '../answering-user.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../answering-user.test-samples';

import { AnsweringUserService } from './answering-user.service';

const requireRestSample: IAnsweringUser = {
  ...sampleWithRequiredData,
};

describe('AnsweringUser Service', () => {
  let service: AnsweringUserService;
  let httpMock: HttpTestingController;
  let expectedResult: IAnsweringUser | IAnsweringUser[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AnsweringUserService);
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

    it('should return a list of AnsweringUser', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addAnsweringUserToCollectionIfMissing', () => {
      it('should add a AnsweringUser to an empty array', () => {
        const answeringUser: IAnsweringUser = sampleWithRequiredData;
        expectedResult = service.addAnsweringUserToCollectionIfMissing([], answeringUser);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(answeringUser);
      });

      it('should not add a AnsweringUser to an array that contains it', () => {
        const answeringUser: IAnsweringUser = sampleWithRequiredData;
        const answeringUserCollection: IAnsweringUser[] = [
          {
            ...answeringUser,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAnsweringUserToCollectionIfMissing(answeringUserCollection, answeringUser);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a AnsweringUser to an array that doesn't contain it", () => {
        const answeringUser: IAnsweringUser = sampleWithRequiredData;
        const answeringUserCollection: IAnsweringUser[] = [sampleWithPartialData];
        expectedResult = service.addAnsweringUserToCollectionIfMissing(answeringUserCollection, answeringUser);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(answeringUser);
      });

      it('should add only unique AnsweringUser to an array', () => {
        const answeringUserArray: IAnsweringUser[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const answeringUserCollection: IAnsweringUser[] = [sampleWithRequiredData];
        expectedResult = service.addAnsweringUserToCollectionIfMissing(answeringUserCollection, ...answeringUserArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const answeringUser: IAnsweringUser = sampleWithRequiredData;
        const answeringUser2: IAnsweringUser = sampleWithPartialData;
        expectedResult = service.addAnsweringUserToCollectionIfMissing([], answeringUser, answeringUser2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(answeringUser);
        expect(expectedResult).toContain(answeringUser2);
      });

      it('should accept null and undefined values', () => {
        const answeringUser: IAnsweringUser = sampleWithRequiredData;
        expectedResult = service.addAnsweringUserToCollectionIfMissing([], null, answeringUser, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(answeringUser);
      });

      it('should return initial array if no AnsweringUser is added', () => {
        const answeringUserCollection: IAnsweringUser[] = [sampleWithRequiredData];
        expectedResult = service.addAnsweringUserToCollectionIfMissing(answeringUserCollection, undefined, null);
        expect(expectedResult).toEqual(answeringUserCollection);
      });
    });

    describe('compareAnsweringUser', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAnsweringUser(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAnsweringUser(entity1, entity2);
        const compareResult2 = service.compareAnsweringUser(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAnsweringUser(entity1, entity2);
        const compareResult2 = service.compareAnsweringUser(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAnsweringUser(entity1, entity2);
        const compareResult2 = service.compareAnsweringUser(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
