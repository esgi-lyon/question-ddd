import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICheckAnswerCommand } from '../check-answer-command.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../check-answer-command.test-samples';

import { CheckAnswerCommandService } from './check-answer-command.service';

const requireRestSample: ICheckAnswerCommand = {
  ...sampleWithRequiredData,
};

describe('CheckAnswerCommand Service', () => {
  let service: CheckAnswerCommandService;
  let httpMock: HttpTestingController;
  let expectedResult: ICheckAnswerCommand | ICheckAnswerCommand[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CheckAnswerCommandService);
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

    it('should create a CheckAnswerCommand', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const checkAnswerCommand = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(checkAnswerCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a CheckAnswerCommand', () => {
      const checkAnswerCommand = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(checkAnswerCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a CheckAnswerCommand', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of CheckAnswerCommand', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a CheckAnswerCommand', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCheckAnswerCommandToCollectionIfMissing', () => {
      it('should add a CheckAnswerCommand to an empty array', () => {
        const checkAnswerCommand: ICheckAnswerCommand = sampleWithRequiredData;
        expectedResult = service.addCheckAnswerCommandToCollectionIfMissing([], checkAnswerCommand);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(checkAnswerCommand);
      });

      it('should not add a CheckAnswerCommand to an array that contains it', () => {
        const checkAnswerCommand: ICheckAnswerCommand = sampleWithRequiredData;
        const checkAnswerCommandCollection: ICheckAnswerCommand[] = [
          {
            ...checkAnswerCommand,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCheckAnswerCommandToCollectionIfMissing(checkAnswerCommandCollection, checkAnswerCommand);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a CheckAnswerCommand to an array that doesn't contain it", () => {
        const checkAnswerCommand: ICheckAnswerCommand = sampleWithRequiredData;
        const checkAnswerCommandCollection: ICheckAnswerCommand[] = [sampleWithPartialData];
        expectedResult = service.addCheckAnswerCommandToCollectionIfMissing(checkAnswerCommandCollection, checkAnswerCommand);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(checkAnswerCommand);
      });

      it('should add only unique CheckAnswerCommand to an array', () => {
        const checkAnswerCommandArray: ICheckAnswerCommand[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const checkAnswerCommandCollection: ICheckAnswerCommand[] = [sampleWithRequiredData];
        expectedResult = service.addCheckAnswerCommandToCollectionIfMissing(checkAnswerCommandCollection, ...checkAnswerCommandArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const checkAnswerCommand: ICheckAnswerCommand = sampleWithRequiredData;
        const checkAnswerCommand2: ICheckAnswerCommand = sampleWithPartialData;
        expectedResult = service.addCheckAnswerCommandToCollectionIfMissing([], checkAnswerCommand, checkAnswerCommand2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(checkAnswerCommand);
        expect(expectedResult).toContain(checkAnswerCommand2);
      });

      it('should accept null and undefined values', () => {
        const checkAnswerCommand: ICheckAnswerCommand = sampleWithRequiredData;
        expectedResult = service.addCheckAnswerCommandToCollectionIfMissing([], null, checkAnswerCommand, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(checkAnswerCommand);
      });

      it('should return initial array if no CheckAnswerCommand is added', () => {
        const checkAnswerCommandCollection: ICheckAnswerCommand[] = [sampleWithRequiredData];
        expectedResult = service.addCheckAnswerCommandToCollectionIfMissing(checkAnswerCommandCollection, undefined, null);
        expect(expectedResult).toEqual(checkAnswerCommandCollection);
      });
    });

    describe('compareCheckAnswerCommand', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCheckAnswerCommand(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCheckAnswerCommand(entity1, entity2);
        const compareResult2 = service.compareCheckAnswerCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCheckAnswerCommand(entity1, entity2);
        const compareResult2 = service.compareCheckAnswerCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCheckAnswerCommand(entity1, entity2);
        const compareResult2 = service.compareCheckAnswerCommand(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
