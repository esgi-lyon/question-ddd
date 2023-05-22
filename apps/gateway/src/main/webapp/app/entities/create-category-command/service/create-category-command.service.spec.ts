import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICreateCategoryCommand } from '../create-category-command.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../create-category-command.test-samples';

import { CreateCategoryCommandService } from './create-category-command.service';

const requireRestSample: ICreateCategoryCommand = {
  ...sampleWithRequiredData,
};

describe('CreateCategoryCommand Service', () => {
  let service: CreateCategoryCommandService;
  let httpMock: HttpTestingController;
  let expectedResult: ICreateCategoryCommand | ICreateCategoryCommand[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CreateCategoryCommandService);
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

    it('should create a CreateCategoryCommand', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const createCategoryCommand = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(createCategoryCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a CreateCategoryCommand', () => {
      const createCategoryCommand = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(createCategoryCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a CreateCategoryCommand', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of CreateCategoryCommand', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a CreateCategoryCommand', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCreateCategoryCommandToCollectionIfMissing', () => {
      it('should add a CreateCategoryCommand to an empty array', () => {
        const createCategoryCommand: ICreateCategoryCommand = sampleWithRequiredData;
        expectedResult = service.addCreateCategoryCommandToCollectionIfMissing([], createCategoryCommand);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(createCategoryCommand);
      });

      it('should not add a CreateCategoryCommand to an array that contains it', () => {
        const createCategoryCommand: ICreateCategoryCommand = sampleWithRequiredData;
        const createCategoryCommandCollection: ICreateCategoryCommand[] = [
          {
            ...createCategoryCommand,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCreateCategoryCommandToCollectionIfMissing(createCategoryCommandCollection, createCategoryCommand);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a CreateCategoryCommand to an array that doesn't contain it", () => {
        const createCategoryCommand: ICreateCategoryCommand = sampleWithRequiredData;
        const createCategoryCommandCollection: ICreateCategoryCommand[] = [sampleWithPartialData];
        expectedResult = service.addCreateCategoryCommandToCollectionIfMissing(createCategoryCommandCollection, createCategoryCommand);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(createCategoryCommand);
      });

      it('should add only unique CreateCategoryCommand to an array', () => {
        const createCategoryCommandArray: ICreateCategoryCommand[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const createCategoryCommandCollection: ICreateCategoryCommand[] = [sampleWithRequiredData];
        expectedResult = service.addCreateCategoryCommandToCollectionIfMissing(
          createCategoryCommandCollection,
          ...createCategoryCommandArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const createCategoryCommand: ICreateCategoryCommand = sampleWithRequiredData;
        const createCategoryCommand2: ICreateCategoryCommand = sampleWithPartialData;
        expectedResult = service.addCreateCategoryCommandToCollectionIfMissing([], createCategoryCommand, createCategoryCommand2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(createCategoryCommand);
        expect(expectedResult).toContain(createCategoryCommand2);
      });

      it('should accept null and undefined values', () => {
        const createCategoryCommand: ICreateCategoryCommand = sampleWithRequiredData;
        expectedResult = service.addCreateCategoryCommandToCollectionIfMissing([], null, createCategoryCommand, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(createCategoryCommand);
      });

      it('should return initial array if no CreateCategoryCommand is added', () => {
        const createCategoryCommandCollection: ICreateCategoryCommand[] = [sampleWithRequiredData];
        expectedResult = service.addCreateCategoryCommandToCollectionIfMissing(createCategoryCommandCollection, undefined, null);
        expect(expectedResult).toEqual(createCategoryCommandCollection);
      });
    });

    describe('compareCreateCategoryCommand', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCreateCategoryCommand(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCreateCategoryCommand(entity1, entity2);
        const compareResult2 = service.compareCreateCategoryCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCreateCategoryCommand(entity1, entity2);
        const compareResult2 = service.compareCreateCategoryCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCreateCategoryCommand(entity1, entity2);
        const compareResult2 = service.compareCreateCategoryCommand(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
