import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEvaluationId } from '../evaluation-id.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../evaluation-id.test-samples';

import { EvaluationIdService } from './evaluation-id.service';

const requireRestSample: IEvaluationId = {
  ...sampleWithRequiredData,
};

describe('EvaluationId Service', () => {
  let service: EvaluationIdService;
  let httpMock: HttpTestingController;
  let expectedResult: IEvaluationId | IEvaluationId[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(EvaluationIdService);
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

    it('should return a list of EvaluationId', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addEvaluationIdToCollectionIfMissing', () => {
      it('should add a EvaluationId to an empty array', () => {
        const evaluationId: IEvaluationId = sampleWithRequiredData;
        expectedResult = service.addEvaluationIdToCollectionIfMissing([], evaluationId);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(evaluationId);
      });

      it('should not add a EvaluationId to an array that contains it', () => {
        const evaluationId: IEvaluationId = sampleWithRequiredData;
        const evaluationIdCollection: IEvaluationId[] = [
          {
            ...evaluationId,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addEvaluationIdToCollectionIfMissing(evaluationIdCollection, evaluationId);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a EvaluationId to an array that doesn't contain it", () => {
        const evaluationId: IEvaluationId = sampleWithRequiredData;
        const evaluationIdCollection: IEvaluationId[] = [sampleWithPartialData];
        expectedResult = service.addEvaluationIdToCollectionIfMissing(evaluationIdCollection, evaluationId);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(evaluationId);
      });

      it('should add only unique EvaluationId to an array', () => {
        const evaluationIdArray: IEvaluationId[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const evaluationIdCollection: IEvaluationId[] = [sampleWithRequiredData];
        expectedResult = service.addEvaluationIdToCollectionIfMissing(evaluationIdCollection, ...evaluationIdArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const evaluationId: IEvaluationId = sampleWithRequiredData;
        const evaluationId2: IEvaluationId = sampleWithPartialData;
        expectedResult = service.addEvaluationIdToCollectionIfMissing([], evaluationId, evaluationId2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(evaluationId);
        expect(expectedResult).toContain(evaluationId2);
      });

      it('should accept null and undefined values', () => {
        const evaluationId: IEvaluationId = sampleWithRequiredData;
        expectedResult = service.addEvaluationIdToCollectionIfMissing([], null, evaluationId, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(evaluationId);
      });

      it('should return initial array if no EvaluationId is added', () => {
        const evaluationIdCollection: IEvaluationId[] = [sampleWithRequiredData];
        expectedResult = service.addEvaluationIdToCollectionIfMissing(evaluationIdCollection, undefined, null);
        expect(expectedResult).toEqual(evaluationIdCollection);
      });
    });

    describe('compareEvaluationId', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareEvaluationId(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareEvaluationId(entity1, entity2);
        const compareResult2 = service.compareEvaluationId(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareEvaluationId(entity1, entity2);
        const compareResult2 = service.compareEvaluationId(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareEvaluationId(entity1, entity2);
        const compareResult2 = service.compareEvaluationId(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
