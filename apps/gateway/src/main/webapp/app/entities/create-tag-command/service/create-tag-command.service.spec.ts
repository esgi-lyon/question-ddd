import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICreateTagCommand } from '../create-tag-command.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../create-tag-command.test-samples';

import { CreateTagCommandService } from './create-tag-command.service';

const requireRestSample: ICreateTagCommand = {
  ...sampleWithRequiredData,
};

describe('CreateTagCommand Service', () => {
  let service: CreateTagCommandService;
  let httpMock: HttpTestingController;
  let expectedResult: ICreateTagCommand | ICreateTagCommand[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CreateTagCommandService);
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

    it('should create a CreateTagCommand', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const createTagCommand = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(createTagCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a CreateTagCommand', () => {
      const createTagCommand = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(createTagCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a CreateTagCommand', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of CreateTagCommand', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a CreateTagCommand', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCreateTagCommandToCollectionIfMissing', () => {
      it('should add a CreateTagCommand to an empty array', () => {
        const createTagCommand: ICreateTagCommand = sampleWithRequiredData;
        expectedResult = service.addCreateTagCommandToCollectionIfMissing([], createTagCommand);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(createTagCommand);
      });

      it('should not add a CreateTagCommand to an array that contains it', () => {
        const createTagCommand: ICreateTagCommand = sampleWithRequiredData;
        const createTagCommandCollection: ICreateTagCommand[] = [
          {
            ...createTagCommand,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCreateTagCommandToCollectionIfMissing(createTagCommandCollection, createTagCommand);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a CreateTagCommand to an array that doesn't contain it", () => {
        const createTagCommand: ICreateTagCommand = sampleWithRequiredData;
        const createTagCommandCollection: ICreateTagCommand[] = [sampleWithPartialData];
        expectedResult = service.addCreateTagCommandToCollectionIfMissing(createTagCommandCollection, createTagCommand);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(createTagCommand);
      });

      it('should add only unique CreateTagCommand to an array', () => {
        const createTagCommandArray: ICreateTagCommand[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const createTagCommandCollection: ICreateTagCommand[] = [sampleWithRequiredData];
        expectedResult = service.addCreateTagCommandToCollectionIfMissing(createTagCommandCollection, ...createTagCommandArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const createTagCommand: ICreateTagCommand = sampleWithRequiredData;
        const createTagCommand2: ICreateTagCommand = sampleWithPartialData;
        expectedResult = service.addCreateTagCommandToCollectionIfMissing([], createTagCommand, createTagCommand2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(createTagCommand);
        expect(expectedResult).toContain(createTagCommand2);
      });

      it('should accept null and undefined values', () => {
        const createTagCommand: ICreateTagCommand = sampleWithRequiredData;
        expectedResult = service.addCreateTagCommandToCollectionIfMissing([], null, createTagCommand, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(createTagCommand);
      });

      it('should return initial array if no CreateTagCommand is added', () => {
        const createTagCommandCollection: ICreateTagCommand[] = [sampleWithRequiredData];
        expectedResult = service.addCreateTagCommandToCollectionIfMissing(createTagCommandCollection, undefined, null);
        expect(expectedResult).toEqual(createTagCommandCollection);
      });
    });

    describe('compareCreateTagCommand', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCreateTagCommand(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCreateTagCommand(entity1, entity2);
        const compareResult2 = service.compareCreateTagCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCreateTagCommand(entity1, entity2);
        const compareResult2 = service.compareCreateTagCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCreateTagCommand(entity1, entity2);
        const compareResult2 = service.compareCreateTagCommand(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
