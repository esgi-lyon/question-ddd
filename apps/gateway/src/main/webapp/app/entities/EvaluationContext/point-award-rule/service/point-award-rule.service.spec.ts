import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IPointAwardRule } from '../point-award-rule.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../point-award-rule.test-samples';

import { PointAwardRuleService } from './point-award-rule.service';

const requireRestSample: IPointAwardRule = {
  ...sampleWithRequiredData,
};

describe('PointAwardRule Service', () => {
  let service: PointAwardRuleService;
  let httpMock: HttpTestingController;
  let expectedResult: IPointAwardRule | IPointAwardRule[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(PointAwardRuleService);
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

    it('should create a PointAwardRule', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const pointAwardRule = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(pointAwardRule).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a PointAwardRule', () => {
      const pointAwardRule = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(pointAwardRule).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a PointAwardRule', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of PointAwardRule', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a PointAwardRule', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addPointAwardRuleToCollectionIfMissing', () => {
      it('should add a PointAwardRule to an empty array', () => {
        const pointAwardRule: IPointAwardRule = sampleWithRequiredData;
        expectedResult = service.addPointAwardRuleToCollectionIfMissing([], pointAwardRule);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(pointAwardRule);
      });

      it('should not add a PointAwardRule to an array that contains it', () => {
        const pointAwardRule: IPointAwardRule = sampleWithRequiredData;
        const pointAwardRuleCollection: IPointAwardRule[] = [
          {
            ...pointAwardRule,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addPointAwardRuleToCollectionIfMissing(pointAwardRuleCollection, pointAwardRule);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a PointAwardRule to an array that doesn't contain it", () => {
        const pointAwardRule: IPointAwardRule = sampleWithRequiredData;
        const pointAwardRuleCollection: IPointAwardRule[] = [sampleWithPartialData];
        expectedResult = service.addPointAwardRuleToCollectionIfMissing(pointAwardRuleCollection, pointAwardRule);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(pointAwardRule);
      });

      it('should add only unique PointAwardRule to an array', () => {
        const pointAwardRuleArray: IPointAwardRule[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const pointAwardRuleCollection: IPointAwardRule[] = [sampleWithRequiredData];
        expectedResult = service.addPointAwardRuleToCollectionIfMissing(pointAwardRuleCollection, ...pointAwardRuleArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const pointAwardRule: IPointAwardRule = sampleWithRequiredData;
        const pointAwardRule2: IPointAwardRule = sampleWithPartialData;
        expectedResult = service.addPointAwardRuleToCollectionIfMissing([], pointAwardRule, pointAwardRule2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(pointAwardRule);
        expect(expectedResult).toContain(pointAwardRule2);
      });

      it('should accept null and undefined values', () => {
        const pointAwardRule: IPointAwardRule = sampleWithRequiredData;
        expectedResult = service.addPointAwardRuleToCollectionIfMissing([], null, pointAwardRule, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(pointAwardRule);
      });

      it('should return initial array if no PointAwardRule is added', () => {
        const pointAwardRuleCollection: IPointAwardRule[] = [sampleWithRequiredData];
        expectedResult = service.addPointAwardRuleToCollectionIfMissing(pointAwardRuleCollection, undefined, null);
        expect(expectedResult).toEqual(pointAwardRuleCollection);
      });
    });

    describe('comparePointAwardRule', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.comparePointAwardRule(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.comparePointAwardRule(entity1, entity2);
        const compareResult2 = service.comparePointAwardRule(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.comparePointAwardRule(entity1, entity2);
        const compareResult2 = service.comparePointAwardRule(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.comparePointAwardRule(entity1, entity2);
        const compareResult2 = service.comparePointAwardRule(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
