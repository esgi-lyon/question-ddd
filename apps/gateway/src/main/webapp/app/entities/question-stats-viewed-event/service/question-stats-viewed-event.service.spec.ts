import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IQuestionStatsViewedEvent } from '../question-stats-viewed-event.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../question-stats-viewed-event.test-samples';

import { QuestionStatsViewedEventService } from './question-stats-viewed-event.service';

const requireRestSample: IQuestionStatsViewedEvent = {
  ...sampleWithRequiredData,
};

describe('QuestionStatsViewedEvent Service', () => {
  let service: QuestionStatsViewedEventService;
  let httpMock: HttpTestingController;
  let expectedResult: IQuestionStatsViewedEvent | IQuestionStatsViewedEvent[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(QuestionStatsViewedEventService);
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

    it('should return a list of QuestionStatsViewedEvent', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addQuestionStatsViewedEventToCollectionIfMissing', () => {
      it('should add a QuestionStatsViewedEvent to an empty array', () => {
        const questionStatsViewedEvent: IQuestionStatsViewedEvent = sampleWithRequiredData;
        expectedResult = service.addQuestionStatsViewedEventToCollectionIfMissing([], questionStatsViewedEvent);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(questionStatsViewedEvent);
      });

      it('should not add a QuestionStatsViewedEvent to an array that contains it', () => {
        const questionStatsViewedEvent: IQuestionStatsViewedEvent = sampleWithRequiredData;
        const questionStatsViewedEventCollection: IQuestionStatsViewedEvent[] = [
          {
            ...questionStatsViewedEvent,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addQuestionStatsViewedEventToCollectionIfMissing(
          questionStatsViewedEventCollection,
          questionStatsViewedEvent
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a QuestionStatsViewedEvent to an array that doesn't contain it", () => {
        const questionStatsViewedEvent: IQuestionStatsViewedEvent = sampleWithRequiredData;
        const questionStatsViewedEventCollection: IQuestionStatsViewedEvent[] = [sampleWithPartialData];
        expectedResult = service.addQuestionStatsViewedEventToCollectionIfMissing(
          questionStatsViewedEventCollection,
          questionStatsViewedEvent
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(questionStatsViewedEvent);
      });

      it('should add only unique QuestionStatsViewedEvent to an array', () => {
        const questionStatsViewedEventArray: IQuestionStatsViewedEvent[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const questionStatsViewedEventCollection: IQuestionStatsViewedEvent[] = [sampleWithRequiredData];
        expectedResult = service.addQuestionStatsViewedEventToCollectionIfMissing(
          questionStatsViewedEventCollection,
          ...questionStatsViewedEventArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const questionStatsViewedEvent: IQuestionStatsViewedEvent = sampleWithRequiredData;
        const questionStatsViewedEvent2: IQuestionStatsViewedEvent = sampleWithPartialData;
        expectedResult = service.addQuestionStatsViewedEventToCollectionIfMissing([], questionStatsViewedEvent, questionStatsViewedEvent2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(questionStatsViewedEvent);
        expect(expectedResult).toContain(questionStatsViewedEvent2);
      });

      it('should accept null and undefined values', () => {
        const questionStatsViewedEvent: IQuestionStatsViewedEvent = sampleWithRequiredData;
        expectedResult = service.addQuestionStatsViewedEventToCollectionIfMissing([], null, questionStatsViewedEvent, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(questionStatsViewedEvent);
      });

      it('should return initial array if no QuestionStatsViewedEvent is added', () => {
        const questionStatsViewedEventCollection: IQuestionStatsViewedEvent[] = [sampleWithRequiredData];
        expectedResult = service.addQuestionStatsViewedEventToCollectionIfMissing(questionStatsViewedEventCollection, undefined, null);
        expect(expectedResult).toEqual(questionStatsViewedEventCollection);
      });
    });

    describe('compareQuestionStatsViewedEvent', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareQuestionStatsViewedEvent(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareQuestionStatsViewedEvent(entity1, entity2);
        const compareResult2 = service.compareQuestionStatsViewedEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareQuestionStatsViewedEvent(entity1, entity2);
        const compareResult2 = service.compareQuestionStatsViewedEvent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareQuestionStatsViewedEvent(entity1, entity2);
        const compareResult2 = service.compareQuestionStatsViewedEvent(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
