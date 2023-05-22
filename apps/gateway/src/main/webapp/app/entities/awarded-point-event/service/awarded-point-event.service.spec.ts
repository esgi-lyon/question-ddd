import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAwardedPointEvent } from '../awarded-point-event.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../awarded-point-event.test-samples';

import { AwardedPointEventService } from './awarded-point-event.service';

const requireRestSample: IAwardedPointEvent = {
  ...sampleWithRequiredData,
};

describe('AwardedPointEvent Service', () => {
  let service: AwardedPointEventService;
  let httpMock: HttpTestingController;
  let expectedResult: IAwardedPointEvent | IAwardedPointEvent[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AwardedPointEventService);
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

    it('should return a list of AwardedPointEvent', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addAwardedPointEventToCollectionIfMissing', () => {
      it('should add a AwardedPointEvent to an empty array', () => {
        const awardedPointEvent: IAwardedPointEvent = sampleWithRequiredData;
        expectedResult = service.addAwardedPointEventToCollectionIfMissing([], awardedPointEvent);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(awardedPointEvent);
      });

      it('should not add a AwardedPointEvent to an array that contains it', () => {
        const awardedPointEvent: IAwardedPointEvent = sampleWithRequiredData;
        const awardedPointEventCollection: IAwardedPointEvent[] = [
          {
            ...awardedPointEvent,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAwardedPointEventToCollectionIfMissing(awardedPointEventCollection, awardedPointEvent);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a AwardedPointEvent to an array that doesn't contain it", () => {
        const awardedPointEvent: IAwardedPointEvent = sampleWithRequiredData;
        const awardedPointEventCollection: IAwardedPointEvent[] = [sampleWithPartialData];
        expectedResult = service.addAwardedPointEventToCollectionIfMissing(awardedPointEventCollection, awardedPointEvent);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(awardedPointEvent);
      });

      it('should add only unique AwardedPointEvent to an array', () => {
        const awardedPointEventArray: IAwardedPointEvent[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const awardedPointEventCollection: IAwardedPointEvent[] = [sampleWithRequiredData];
        expectedResult = service.addAwardedPointEventToCollectionIfMissing(awardedPointEventCollection, ...awardedPointEventArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const awardedPointEvent: IAwardedPointEvent = sampleWithRequiredData;
        const awardedPointEvent2: IAwardedPointEvent = sampleWithPartialData;
        expectedResult = service.addAwardedPointEventToCollectionIfMissing([], awardedPointEvent, awardedPointEvent2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(awardedPointEvent);
        expect(expectedResult).toContain(awardedPointEvent2);
      });

      it('should accept null and undefined values', () => {
        const awardedPointEvent: IAwardedPointEvent = sampleWithRequiredData;
        expectedResult = service.addAwardedPointEventToCollectionIfMissing([], null, awardedPointEvent, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(awardedPointEvent);
      });

      it('should return initial array if no AwardedPointEvent is added', () => {
        const awardedPointEventCollection: IAwardedPointEvent[] = [sampleWithRequiredData];
        expectedResult = service.addAwardedPointEventToCollectionIfMissing(awardedPointEventCollection, undefined, null);
        expect(expectedResult).toEqual(awardedPointEventCollection);
      });
    });

    describe('compareAwardedPointEvent', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAwardedPointEvent(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAwardedPointEvent(entity1, entity2);
        const compareResult2 = service.compareAwardedPointEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAwardedPointEvent(entity1, entity2);
        const compareResult2 = service.compareAwardedPointEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAwardedPointEvent(entity1, entity2);
        const compareResult2 = service.compareAwardedPointEvent(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
