import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IRejectResourceTagCommand } from '../reject-resource-tag-command.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../reject-resource-tag-command.test-samples';

import { RejectResourceTagCommandService } from './reject-resource-tag-command.service';

const requireRestSample: IRejectResourceTagCommand = {
  ...sampleWithRequiredData,
};

describe('RejectResourceTagCommand Service', () => {
  let service: RejectResourceTagCommandService;
  let httpMock: HttpTestingController;
  let expectedResult: IRejectResourceTagCommand | IRejectResourceTagCommand[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(RejectResourceTagCommandService);
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

    it('should create a RejectResourceTagCommand', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const rejectResourceTagCommand = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(rejectResourceTagCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a RejectResourceTagCommand', () => {
      const rejectResourceTagCommand = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(rejectResourceTagCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a RejectResourceTagCommand', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of RejectResourceTagCommand', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a RejectResourceTagCommand', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addRejectResourceTagCommandToCollectionIfMissing', () => {
      it('should add a RejectResourceTagCommand to an empty array', () => {
        const rejectResourceTagCommand: IRejectResourceTagCommand = sampleWithRequiredData;
        expectedResult = service.addRejectResourceTagCommandToCollectionIfMissing([], rejectResourceTagCommand);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(rejectResourceTagCommand);
      });

      it('should not add a RejectResourceTagCommand to an array that contains it', () => {
        const rejectResourceTagCommand: IRejectResourceTagCommand = sampleWithRequiredData;
        const rejectResourceTagCommandCollection: IRejectResourceTagCommand[] = [
          {
            ...rejectResourceTagCommand,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addRejectResourceTagCommandToCollectionIfMissing(
          rejectResourceTagCommandCollection,
          rejectResourceTagCommand
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a RejectResourceTagCommand to an array that doesn't contain it", () => {
        const rejectResourceTagCommand: IRejectResourceTagCommand = sampleWithRequiredData;
        const rejectResourceTagCommandCollection: IRejectResourceTagCommand[] = [sampleWithPartialData];
        expectedResult = service.addRejectResourceTagCommandToCollectionIfMissing(
          rejectResourceTagCommandCollection,
          rejectResourceTagCommand
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(rejectResourceTagCommand);
      });

      it('should add only unique RejectResourceTagCommand to an array', () => {
        const rejectResourceTagCommandArray: IRejectResourceTagCommand[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const rejectResourceTagCommandCollection: IRejectResourceTagCommand[] = [sampleWithRequiredData];
        expectedResult = service.addRejectResourceTagCommandToCollectionIfMissing(
          rejectResourceTagCommandCollection,
          ...rejectResourceTagCommandArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const rejectResourceTagCommand: IRejectResourceTagCommand = sampleWithRequiredData;
        const rejectResourceTagCommand2: IRejectResourceTagCommand = sampleWithPartialData;
        expectedResult = service.addRejectResourceTagCommandToCollectionIfMissing([], rejectResourceTagCommand, rejectResourceTagCommand2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(rejectResourceTagCommand);
        expect(expectedResult).toContain(rejectResourceTagCommand2);
      });

      it('should accept null and undefined values', () => {
        const rejectResourceTagCommand: IRejectResourceTagCommand = sampleWithRequiredData;
        expectedResult = service.addRejectResourceTagCommandToCollectionIfMissing([], null, rejectResourceTagCommand, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(rejectResourceTagCommand);
      });

      it('should return initial array if no RejectResourceTagCommand is added', () => {
        const rejectResourceTagCommandCollection: IRejectResourceTagCommand[] = [sampleWithRequiredData];
        expectedResult = service.addRejectResourceTagCommandToCollectionIfMissing(rejectResourceTagCommandCollection, undefined, null);
        expect(expectedResult).toEqual(rejectResourceTagCommandCollection);
      });
    });

    describe('compareRejectResourceTagCommand', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareRejectResourceTagCommand(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareRejectResourceTagCommand(entity1, entity2);
        const compareResult2 = service.compareRejectResourceTagCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareRejectResourceTagCommand(entity1, entity2);
        const compareResult2 = service.compareRejectResourceTagCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareRejectResourceTagCommand(entity1, entity2);
        const compareResult2 = service.compareRejectResourceTagCommand(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
