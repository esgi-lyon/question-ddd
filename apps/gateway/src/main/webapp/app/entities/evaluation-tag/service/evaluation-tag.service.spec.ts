import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEvaluationTag } from '../evaluation-tag.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../evaluation-tag.test-samples';

import { EvaluationTagService } from './evaluation-tag.service';

const requireRestSample: IEvaluationTag = {
  ...sampleWithRequiredData,
};

describe('EvaluationTag Service', () => {
  let service: EvaluationTagService;
  let httpMock: HttpTestingController;
  let expectedResult: IEvaluationTag | IEvaluationTag[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(EvaluationTagService);
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

    it('should return a list of EvaluationTag', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addEvaluationTagToCollectionIfMissing', () => {
      it('should add a EvaluationTag to an empty array', () => {
        const evaluationTag: IEvaluationTag = sampleWithRequiredData;
        expectedResult = service.addEvaluationTagToCollectionIfMissing([], evaluationTag);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(evaluationTag);
      });

      it('should not add a EvaluationTag to an array that contains it', () => {
        const evaluationTag: IEvaluationTag = sampleWithRequiredData;
        const evaluationTagCollection: IEvaluationTag[] = [
          {
            ...evaluationTag,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addEvaluationTagToCollectionIfMissing(evaluationTagCollection, evaluationTag);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a EvaluationTag to an array that doesn't contain it", () => {
        const evaluationTag: IEvaluationTag = sampleWithRequiredData;
        const evaluationTagCollection: IEvaluationTag[] = [sampleWithPartialData];
        expectedResult = service.addEvaluationTagToCollectionIfMissing(evaluationTagCollection, evaluationTag);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(evaluationTag);
      });

      it('should add only unique EvaluationTag to an array', () => {
        const evaluationTagArray: IEvaluationTag[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const evaluationTagCollection: IEvaluationTag[] = [sampleWithRequiredData];
        expectedResult = service.addEvaluationTagToCollectionIfMissing(evaluationTagCollection, ...evaluationTagArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const evaluationTag: IEvaluationTag = sampleWithRequiredData;
        const evaluationTag2: IEvaluationTag = sampleWithPartialData;
        expectedResult = service.addEvaluationTagToCollectionIfMissing([], evaluationTag, evaluationTag2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(evaluationTag);
        expect(expectedResult).toContain(evaluationTag2);
      });

      it('should accept null and undefined values', () => {
        const evaluationTag: IEvaluationTag = sampleWithRequiredData;
        expectedResult = service.addEvaluationTagToCollectionIfMissing([], null, evaluationTag, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(evaluationTag);
      });

      it('should return initial array if no EvaluationTag is added', () => {
        const evaluationTagCollection: IEvaluationTag[] = [sampleWithRequiredData];
        expectedResult = service.addEvaluationTagToCollectionIfMissing(evaluationTagCollection, undefined, null);
        expect(expectedResult).toEqual(evaluationTagCollection);
      });
    });

    describe('compareEvaluationTag', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareEvaluationTag(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareEvaluationTag(entity1, entity2);
        const compareResult2 = service.compareEvaluationTag(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareEvaluationTag(entity1, entity2);
        const compareResult2 = service.compareEvaluationTag(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareEvaluationTag(entity1, entity2);
        const compareResult2 = service.compareEvaluationTag(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
