import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICreateQuestionCommand } from '../create-question-command.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../create-question-command.test-samples';

import { CreateQuestionCommandService } from './create-question-command.service';

const requireRestSample: ICreateQuestionCommand = {
  ...sampleWithRequiredData,
};

describe('CreateQuestionCommand Service', () => {
  let service: CreateQuestionCommandService;
  let httpMock: HttpTestingController;
  let expectedResult: ICreateQuestionCommand | ICreateQuestionCommand[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CreateQuestionCommandService);
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

    it('should create a CreateQuestionCommand', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const createQuestionCommand = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(createQuestionCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a CreateQuestionCommand', () => {
      const createQuestionCommand = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(createQuestionCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a CreateQuestionCommand', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of CreateQuestionCommand', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a CreateQuestionCommand', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCreateQuestionCommandToCollectionIfMissing', () => {
      it('should add a CreateQuestionCommand to an empty array', () => {
        const createQuestionCommand: ICreateQuestionCommand = sampleWithRequiredData;
        expectedResult = service.addCreateQuestionCommandToCollectionIfMissing([], createQuestionCommand);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(createQuestionCommand);
      });

      it('should not add a CreateQuestionCommand to an array that contains it', () => {
        const createQuestionCommand: ICreateQuestionCommand = sampleWithRequiredData;
        const createQuestionCommandCollection: ICreateQuestionCommand[] = [
          {
            ...createQuestionCommand,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCreateQuestionCommandToCollectionIfMissing(createQuestionCommandCollection, createQuestionCommand);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a CreateQuestionCommand to an array that doesn't contain it", () => {
        const createQuestionCommand: ICreateQuestionCommand = sampleWithRequiredData;
        const createQuestionCommandCollection: ICreateQuestionCommand[] = [sampleWithPartialData];
        expectedResult = service.addCreateQuestionCommandToCollectionIfMissing(createQuestionCommandCollection, createQuestionCommand);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(createQuestionCommand);
      });

      it('should add only unique CreateQuestionCommand to an array', () => {
        const createQuestionCommandArray: ICreateQuestionCommand[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const createQuestionCommandCollection: ICreateQuestionCommand[] = [sampleWithRequiredData];
        expectedResult = service.addCreateQuestionCommandToCollectionIfMissing(
          createQuestionCommandCollection,
          ...createQuestionCommandArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const createQuestionCommand: ICreateQuestionCommand = sampleWithRequiredData;
        const createQuestionCommand2: ICreateQuestionCommand = sampleWithPartialData;
        expectedResult = service.addCreateQuestionCommandToCollectionIfMissing([], createQuestionCommand, createQuestionCommand2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(createQuestionCommand);
        expect(expectedResult).toContain(createQuestionCommand2);
      });

      it('should accept null and undefined values', () => {
        const createQuestionCommand: ICreateQuestionCommand = sampleWithRequiredData;
        expectedResult = service.addCreateQuestionCommandToCollectionIfMissing([], null, createQuestionCommand, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(createQuestionCommand);
      });

      it('should return initial array if no CreateQuestionCommand is added', () => {
        const createQuestionCommandCollection: ICreateQuestionCommand[] = [sampleWithRequiredData];
        expectedResult = service.addCreateQuestionCommandToCollectionIfMissing(createQuestionCommandCollection, undefined, null);
        expect(expectedResult).toEqual(createQuestionCommandCollection);
      });
    });

    describe('compareCreateQuestionCommand', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCreateQuestionCommand(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCreateQuestionCommand(entity1, entity2);
        const compareResult2 = service.compareCreateQuestionCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCreateQuestionCommand(entity1, entity2);
        const compareResult2 = service.compareCreateQuestionCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCreateQuestionCommand(entity1, entity2);
        const compareResult2 = service.compareCreateQuestionCommand(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
