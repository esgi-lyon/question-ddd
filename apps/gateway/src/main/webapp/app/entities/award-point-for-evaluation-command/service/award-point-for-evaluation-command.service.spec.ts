import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAwardPointForEvaluationCommand } from '../award-point-for-evaluation-command.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../award-point-for-evaluation-command.test-samples';

import { AwardPointForEvaluationCommandService } from './award-point-for-evaluation-command.service';

const requireRestSample: IAwardPointForEvaluationCommand = {
  ...sampleWithRequiredData,
};

describe('AwardPointForEvaluationCommand Service', () => {
  let service: AwardPointForEvaluationCommandService;
  let httpMock: HttpTestingController;
  let expectedResult: IAwardPointForEvaluationCommand | IAwardPointForEvaluationCommand[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AwardPointForEvaluationCommandService);
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

    it('should create a AwardPointForEvaluationCommand', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const awardPointForEvaluationCommand = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(awardPointForEvaluationCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a AwardPointForEvaluationCommand', () => {
      const awardPointForEvaluationCommand = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(awardPointForEvaluationCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a AwardPointForEvaluationCommand', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of AwardPointForEvaluationCommand', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a AwardPointForEvaluationCommand', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAwardPointForEvaluationCommandToCollectionIfMissing', () => {
      it('should add a AwardPointForEvaluationCommand to an empty array', () => {
        const awardPointForEvaluationCommand: IAwardPointForEvaluationCommand = sampleWithRequiredData;
        expectedResult = service.addAwardPointForEvaluationCommandToCollectionIfMissing([], awardPointForEvaluationCommand);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(awardPointForEvaluationCommand);
      });

      it('should not add a AwardPointForEvaluationCommand to an array that contains it', () => {
        const awardPointForEvaluationCommand: IAwardPointForEvaluationCommand = sampleWithRequiredData;
        const awardPointForEvaluationCommandCollection: IAwardPointForEvaluationCommand[] = [
          {
            ...awardPointForEvaluationCommand,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAwardPointForEvaluationCommandToCollectionIfMissing(
          awardPointForEvaluationCommandCollection,
          awardPointForEvaluationCommand
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a AwardPointForEvaluationCommand to an array that doesn't contain it", () => {
        const awardPointForEvaluationCommand: IAwardPointForEvaluationCommand = sampleWithRequiredData;
        const awardPointForEvaluationCommandCollection: IAwardPointForEvaluationCommand[] = [sampleWithPartialData];
        expectedResult = service.addAwardPointForEvaluationCommandToCollectionIfMissing(
          awardPointForEvaluationCommandCollection,
          awardPointForEvaluationCommand
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(awardPointForEvaluationCommand);
      });

      it('should add only unique AwardPointForEvaluationCommand to an array', () => {
        const awardPointForEvaluationCommandArray: IAwardPointForEvaluationCommand[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const awardPointForEvaluationCommandCollection: IAwardPointForEvaluationCommand[] = [sampleWithRequiredData];
        expectedResult = service.addAwardPointForEvaluationCommandToCollectionIfMissing(
          awardPointForEvaluationCommandCollection,
          ...awardPointForEvaluationCommandArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const awardPointForEvaluationCommand: IAwardPointForEvaluationCommand = sampleWithRequiredData;
        const awardPointForEvaluationCommand2: IAwardPointForEvaluationCommand = sampleWithPartialData;
        expectedResult = service.addAwardPointForEvaluationCommandToCollectionIfMissing(
          [],
          awardPointForEvaluationCommand,
          awardPointForEvaluationCommand2
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(awardPointForEvaluationCommand);
        expect(expectedResult).toContain(awardPointForEvaluationCommand2);
      });

      it('should accept null and undefined values', () => {
        const awardPointForEvaluationCommand: IAwardPointForEvaluationCommand = sampleWithRequiredData;
        expectedResult = service.addAwardPointForEvaluationCommandToCollectionIfMissing(
          [],
          null,
          awardPointForEvaluationCommand,
          undefined
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(awardPointForEvaluationCommand);
      });

      it('should return initial array if no AwardPointForEvaluationCommand is added', () => {
        const awardPointForEvaluationCommandCollection: IAwardPointForEvaluationCommand[] = [sampleWithRequiredData];
        expectedResult = service.addAwardPointForEvaluationCommandToCollectionIfMissing(
          awardPointForEvaluationCommandCollection,
          undefined,
          null
        );
        expect(expectedResult).toEqual(awardPointForEvaluationCommandCollection);
      });
    });

    describe('compareAwardPointForEvaluationCommand', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAwardPointForEvaluationCommand(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAwardPointForEvaluationCommand(entity1, entity2);
        const compareResult2 = service.compareAwardPointForEvaluationCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAwardPointForEvaluationCommand(entity1, entity2);
        const compareResult2 = service.compareAwardPointForEvaluationCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAwardPointForEvaluationCommand(entity1, entity2);
        const compareResult2 = service.compareAwardPointForEvaluationCommand(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
