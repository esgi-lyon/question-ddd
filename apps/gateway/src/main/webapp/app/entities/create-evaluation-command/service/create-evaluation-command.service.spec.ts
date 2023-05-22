import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICreateEvaluationCommand } from '../create-evaluation-command.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../create-evaluation-command.test-samples';

import { CreateEvaluationCommandService } from './create-evaluation-command.service';

const requireRestSample: ICreateEvaluationCommand = {
  ...sampleWithRequiredData,
};

describe('CreateEvaluationCommand Service', () => {
  let service: CreateEvaluationCommandService;
  let httpMock: HttpTestingController;
  let expectedResult: ICreateEvaluationCommand | ICreateEvaluationCommand[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CreateEvaluationCommandService);
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

    it('should create a CreateEvaluationCommand', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const createEvaluationCommand = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(createEvaluationCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a CreateEvaluationCommand', () => {
      const createEvaluationCommand = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(createEvaluationCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a CreateEvaluationCommand', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of CreateEvaluationCommand', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a CreateEvaluationCommand', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCreateEvaluationCommandToCollectionIfMissing', () => {
      it('should add a CreateEvaluationCommand to an empty array', () => {
        const createEvaluationCommand: ICreateEvaluationCommand = sampleWithRequiredData;
        expectedResult = service.addCreateEvaluationCommandToCollectionIfMissing([], createEvaluationCommand);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(createEvaluationCommand);
      });

      it('should not add a CreateEvaluationCommand to an array that contains it', () => {
        const createEvaluationCommand: ICreateEvaluationCommand = sampleWithRequiredData;
        const createEvaluationCommandCollection: ICreateEvaluationCommand[] = [
          {
            ...createEvaluationCommand,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCreateEvaluationCommandToCollectionIfMissing(
          createEvaluationCommandCollection,
          createEvaluationCommand
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a CreateEvaluationCommand to an array that doesn't contain it", () => {
        const createEvaluationCommand: ICreateEvaluationCommand = sampleWithRequiredData;
        const createEvaluationCommandCollection: ICreateEvaluationCommand[] = [sampleWithPartialData];
        expectedResult = service.addCreateEvaluationCommandToCollectionIfMissing(
          createEvaluationCommandCollection,
          createEvaluationCommand
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(createEvaluationCommand);
      });

      it('should add only unique CreateEvaluationCommand to an array', () => {
        const createEvaluationCommandArray: ICreateEvaluationCommand[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const createEvaluationCommandCollection: ICreateEvaluationCommand[] = [sampleWithRequiredData];
        expectedResult = service.addCreateEvaluationCommandToCollectionIfMissing(
          createEvaluationCommandCollection,
          ...createEvaluationCommandArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const createEvaluationCommand: ICreateEvaluationCommand = sampleWithRequiredData;
        const createEvaluationCommand2: ICreateEvaluationCommand = sampleWithPartialData;
        expectedResult = service.addCreateEvaluationCommandToCollectionIfMissing([], createEvaluationCommand, createEvaluationCommand2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(createEvaluationCommand);
        expect(expectedResult).toContain(createEvaluationCommand2);
      });

      it('should accept null and undefined values', () => {
        const createEvaluationCommand: ICreateEvaluationCommand = sampleWithRequiredData;
        expectedResult = service.addCreateEvaluationCommandToCollectionIfMissing([], null, createEvaluationCommand, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(createEvaluationCommand);
      });

      it('should return initial array if no CreateEvaluationCommand is added', () => {
        const createEvaluationCommandCollection: ICreateEvaluationCommand[] = [sampleWithRequiredData];
        expectedResult = service.addCreateEvaluationCommandToCollectionIfMissing(createEvaluationCommandCollection, undefined, null);
        expect(expectedResult).toEqual(createEvaluationCommandCollection);
      });
    });

    describe('compareCreateEvaluationCommand', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCreateEvaluationCommand(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCreateEvaluationCommand(entity1, entity2);
        const compareResult2 = service.compareCreateEvaluationCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCreateEvaluationCommand(entity1, entity2);
        const compareResult2 = service.compareCreateEvaluationCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCreateEvaluationCommand(entity1, entity2);
        const compareResult2 = service.compareCreateEvaluationCommand(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
