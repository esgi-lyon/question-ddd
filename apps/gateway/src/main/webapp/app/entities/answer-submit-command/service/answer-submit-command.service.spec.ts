import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAnswerSubmitCommand } from '../answer-submit-command.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../answer-submit-command.test-samples';

import { AnswerSubmitCommandService } from './answer-submit-command.service';

const requireRestSample: IAnswerSubmitCommand = {
  ...sampleWithRequiredData,
};

describe('AnswerSubmitCommand Service', () => {
  let service: AnswerSubmitCommandService;
  let httpMock: HttpTestingController;
  let expectedResult: IAnswerSubmitCommand | IAnswerSubmitCommand[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AnswerSubmitCommandService);
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

    it('should create a AnswerSubmitCommand', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const answerSubmitCommand = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(answerSubmitCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a AnswerSubmitCommand', () => {
      const answerSubmitCommand = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(answerSubmitCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a AnswerSubmitCommand', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of AnswerSubmitCommand', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a AnswerSubmitCommand', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAnswerSubmitCommandToCollectionIfMissing', () => {
      it('should add a AnswerSubmitCommand to an empty array', () => {
        const answerSubmitCommand: IAnswerSubmitCommand = sampleWithRequiredData;
        expectedResult = service.addAnswerSubmitCommandToCollectionIfMissing([], answerSubmitCommand);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(answerSubmitCommand);
      });

      it('should not add a AnswerSubmitCommand to an array that contains it', () => {
        const answerSubmitCommand: IAnswerSubmitCommand = sampleWithRequiredData;
        const answerSubmitCommandCollection: IAnswerSubmitCommand[] = [
          {
            ...answerSubmitCommand,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAnswerSubmitCommandToCollectionIfMissing(answerSubmitCommandCollection, answerSubmitCommand);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a AnswerSubmitCommand to an array that doesn't contain it", () => {
        const answerSubmitCommand: IAnswerSubmitCommand = sampleWithRequiredData;
        const answerSubmitCommandCollection: IAnswerSubmitCommand[] = [sampleWithPartialData];
        expectedResult = service.addAnswerSubmitCommandToCollectionIfMissing(answerSubmitCommandCollection, answerSubmitCommand);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(answerSubmitCommand);
      });

      it('should add only unique AnswerSubmitCommand to an array', () => {
        const answerSubmitCommandArray: IAnswerSubmitCommand[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const answerSubmitCommandCollection: IAnswerSubmitCommand[] = [sampleWithRequiredData];
        expectedResult = service.addAnswerSubmitCommandToCollectionIfMissing(answerSubmitCommandCollection, ...answerSubmitCommandArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const answerSubmitCommand: IAnswerSubmitCommand = sampleWithRequiredData;
        const answerSubmitCommand2: IAnswerSubmitCommand = sampleWithPartialData;
        expectedResult = service.addAnswerSubmitCommandToCollectionIfMissing([], answerSubmitCommand, answerSubmitCommand2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(answerSubmitCommand);
        expect(expectedResult).toContain(answerSubmitCommand2);
      });

      it('should accept null and undefined values', () => {
        const answerSubmitCommand: IAnswerSubmitCommand = sampleWithRequiredData;
        expectedResult = service.addAnswerSubmitCommandToCollectionIfMissing([], null, answerSubmitCommand, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(answerSubmitCommand);
      });

      it('should return initial array if no AnswerSubmitCommand is added', () => {
        const answerSubmitCommandCollection: IAnswerSubmitCommand[] = [sampleWithRequiredData];
        expectedResult = service.addAnswerSubmitCommandToCollectionIfMissing(answerSubmitCommandCollection, undefined, null);
        expect(expectedResult).toEqual(answerSubmitCommandCollection);
      });
    });

    describe('compareAnswerSubmitCommand', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAnswerSubmitCommand(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAnswerSubmitCommand(entity1, entity2);
        const compareResult2 = service.compareAnswerSubmitCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAnswerSubmitCommand(entity1, entity2);
        const compareResult2 = service.compareAnswerSubmitCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAnswerSubmitCommand(entity1, entity2);
        const compareResult2 = service.compareAnswerSubmitCommand(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
