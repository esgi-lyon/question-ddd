import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IQuestionResource } from '../question-resource.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../question-resource.test-samples';

import { QuestionResourceService } from './question-resource.service';

const requireRestSample: IQuestionResource = {
  ...sampleWithRequiredData,
};

describe('QuestionResource Service', () => {
  let service: QuestionResourceService;
  let httpMock: HttpTestingController;
  let expectedResult: IQuestionResource | IQuestionResource[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(QuestionResourceService);
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

    it('should create a QuestionResource', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const questionResource = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(questionResource).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a QuestionResource', () => {
      const questionResource = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(questionResource).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a QuestionResource', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of QuestionResource', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a QuestionResource', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addQuestionResourceToCollectionIfMissing', () => {
      it('should add a QuestionResource to an empty array', () => {
        const questionResource: IQuestionResource = sampleWithRequiredData;
        expectedResult = service.addQuestionResourceToCollectionIfMissing([], questionResource);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(questionResource);
      });

      it('should not add a QuestionResource to an array that contains it', () => {
        const questionResource: IQuestionResource = sampleWithRequiredData;
        const questionResourceCollection: IQuestionResource[] = [
          {
            ...questionResource,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addQuestionResourceToCollectionIfMissing(questionResourceCollection, questionResource);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a QuestionResource to an array that doesn't contain it", () => {
        const questionResource: IQuestionResource = sampleWithRequiredData;
        const questionResourceCollection: IQuestionResource[] = [sampleWithPartialData];
        expectedResult = service.addQuestionResourceToCollectionIfMissing(questionResourceCollection, questionResource);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(questionResource);
      });

      it('should add only unique QuestionResource to an array', () => {
        const questionResourceArray: IQuestionResource[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const questionResourceCollection: IQuestionResource[] = [sampleWithRequiredData];
        expectedResult = service.addQuestionResourceToCollectionIfMissing(questionResourceCollection, ...questionResourceArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const questionResource: IQuestionResource = sampleWithRequiredData;
        const questionResource2: IQuestionResource = sampleWithPartialData;
        expectedResult = service.addQuestionResourceToCollectionIfMissing([], questionResource, questionResource2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(questionResource);
        expect(expectedResult).toContain(questionResource2);
      });

      it('should accept null and undefined values', () => {
        const questionResource: IQuestionResource = sampleWithRequiredData;
        expectedResult = service.addQuestionResourceToCollectionIfMissing([], null, questionResource, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(questionResource);
      });

      it('should return initial array if no QuestionResource is added', () => {
        const questionResourceCollection: IQuestionResource[] = [sampleWithRequiredData];
        expectedResult = service.addQuestionResourceToCollectionIfMissing(questionResourceCollection, undefined, null);
        expect(expectedResult).toEqual(questionResourceCollection);
      });
    });

    describe('compareQuestionResource', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareQuestionResource(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareQuestionResource(entity1, entity2);
        const compareResult2 = service.compareQuestionResource(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareQuestionResource(entity1, entity2);
        const compareResult2 = service.compareQuestionResource(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareQuestionResource(entity1, entity2);
        const compareResult2 = service.compareQuestionResource(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
