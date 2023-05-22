import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IValidateResourceTagLinkageCommand } from '../validate-resource-tag-linkage-command.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../validate-resource-tag-linkage-command.test-samples';

import { ValidateResourceTagLinkageCommandService } from './validate-resource-tag-linkage-command.service';

const requireRestSample: IValidateResourceTagLinkageCommand = {
  ...sampleWithRequiredData,
};

describe('ValidateResourceTagLinkageCommand Service', () => {
  let service: ValidateResourceTagLinkageCommandService;
  let httpMock: HttpTestingController;
  let expectedResult: IValidateResourceTagLinkageCommand | IValidateResourceTagLinkageCommand[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ValidateResourceTagLinkageCommandService);
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

    it('should create a ValidateResourceTagLinkageCommand', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const validateResourceTagLinkageCommand = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(validateResourceTagLinkageCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ValidateResourceTagLinkageCommand', () => {
      const validateResourceTagLinkageCommand = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(validateResourceTagLinkageCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ValidateResourceTagLinkageCommand', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ValidateResourceTagLinkageCommand', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ValidateResourceTagLinkageCommand', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addValidateResourceTagLinkageCommandToCollectionIfMissing', () => {
      it('should add a ValidateResourceTagLinkageCommand to an empty array', () => {
        const validateResourceTagLinkageCommand: IValidateResourceTagLinkageCommand = sampleWithRequiredData;
        expectedResult = service.addValidateResourceTagLinkageCommandToCollectionIfMissing([], validateResourceTagLinkageCommand);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(validateResourceTagLinkageCommand);
      });

      it('should not add a ValidateResourceTagLinkageCommand to an array that contains it', () => {
        const validateResourceTagLinkageCommand: IValidateResourceTagLinkageCommand = sampleWithRequiredData;
        const validateResourceTagLinkageCommandCollection: IValidateResourceTagLinkageCommand[] = [
          {
            ...validateResourceTagLinkageCommand,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addValidateResourceTagLinkageCommandToCollectionIfMissing(
          validateResourceTagLinkageCommandCollection,
          validateResourceTagLinkageCommand
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ValidateResourceTagLinkageCommand to an array that doesn't contain it", () => {
        const validateResourceTagLinkageCommand: IValidateResourceTagLinkageCommand = sampleWithRequiredData;
        const validateResourceTagLinkageCommandCollection: IValidateResourceTagLinkageCommand[] = [sampleWithPartialData];
        expectedResult = service.addValidateResourceTagLinkageCommandToCollectionIfMissing(
          validateResourceTagLinkageCommandCollection,
          validateResourceTagLinkageCommand
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(validateResourceTagLinkageCommand);
      });

      it('should add only unique ValidateResourceTagLinkageCommand to an array', () => {
        const validateResourceTagLinkageCommandArray: IValidateResourceTagLinkageCommand[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const validateResourceTagLinkageCommandCollection: IValidateResourceTagLinkageCommand[] = [sampleWithRequiredData];
        expectedResult = service.addValidateResourceTagLinkageCommandToCollectionIfMissing(
          validateResourceTagLinkageCommandCollection,
          ...validateResourceTagLinkageCommandArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const validateResourceTagLinkageCommand: IValidateResourceTagLinkageCommand = sampleWithRequiredData;
        const validateResourceTagLinkageCommand2: IValidateResourceTagLinkageCommand = sampleWithPartialData;
        expectedResult = service.addValidateResourceTagLinkageCommandToCollectionIfMissing(
          [],
          validateResourceTagLinkageCommand,
          validateResourceTagLinkageCommand2
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(validateResourceTagLinkageCommand);
        expect(expectedResult).toContain(validateResourceTagLinkageCommand2);
      });

      it('should accept null and undefined values', () => {
        const validateResourceTagLinkageCommand: IValidateResourceTagLinkageCommand = sampleWithRequiredData;
        expectedResult = service.addValidateResourceTagLinkageCommandToCollectionIfMissing(
          [],
          null,
          validateResourceTagLinkageCommand,
          undefined
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(validateResourceTagLinkageCommand);
      });

      it('should return initial array if no ValidateResourceTagLinkageCommand is added', () => {
        const validateResourceTagLinkageCommandCollection: IValidateResourceTagLinkageCommand[] = [sampleWithRequiredData];
        expectedResult = service.addValidateResourceTagLinkageCommandToCollectionIfMissing(
          validateResourceTagLinkageCommandCollection,
          undefined,
          null
        );
        expect(expectedResult).toEqual(validateResourceTagLinkageCommandCollection);
      });
    });

    describe('compareValidateResourceTagLinkageCommand', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareValidateResourceTagLinkageCommand(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareValidateResourceTagLinkageCommand(entity1, entity2);
        const compareResult2 = service.compareValidateResourceTagLinkageCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareValidateResourceTagLinkageCommand(entity1, entity2);
        const compareResult2 = service.compareValidateResourceTagLinkageCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareValidateResourceTagLinkageCommand(entity1, entity2);
        const compareResult2 = service.compareValidateResourceTagLinkageCommand(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
