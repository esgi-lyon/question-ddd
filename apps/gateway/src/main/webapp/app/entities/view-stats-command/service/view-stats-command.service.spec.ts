import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IViewStatsCommand } from '../view-stats-command.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../view-stats-command.test-samples';

import { ViewStatsCommandService } from './view-stats-command.service';

const requireRestSample: IViewStatsCommand = {
  ...sampleWithRequiredData,
};

describe('ViewStatsCommand Service', () => {
  let service: ViewStatsCommandService;
  let httpMock: HttpTestingController;
  let expectedResult: IViewStatsCommand | IViewStatsCommand[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ViewStatsCommandService);
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

    it('should create a ViewStatsCommand', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const viewStatsCommand = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(viewStatsCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ViewStatsCommand', () => {
      const viewStatsCommand = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(viewStatsCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ViewStatsCommand', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ViewStatsCommand', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ViewStatsCommand', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addViewStatsCommandToCollectionIfMissing', () => {
      it('should add a ViewStatsCommand to an empty array', () => {
        const viewStatsCommand: IViewStatsCommand = sampleWithRequiredData;
        expectedResult = service.addViewStatsCommandToCollectionIfMissing([], viewStatsCommand);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(viewStatsCommand);
      });

      it('should not add a ViewStatsCommand to an array that contains it', () => {
        const viewStatsCommand: IViewStatsCommand = sampleWithRequiredData;
        const viewStatsCommandCollection: IViewStatsCommand[] = [
          {
            ...viewStatsCommand,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addViewStatsCommandToCollectionIfMissing(viewStatsCommandCollection, viewStatsCommand);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ViewStatsCommand to an array that doesn't contain it", () => {
        const viewStatsCommand: IViewStatsCommand = sampleWithRequiredData;
        const viewStatsCommandCollection: IViewStatsCommand[] = [sampleWithPartialData];
        expectedResult = service.addViewStatsCommandToCollectionIfMissing(viewStatsCommandCollection, viewStatsCommand);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(viewStatsCommand);
      });

      it('should add only unique ViewStatsCommand to an array', () => {
        const viewStatsCommandArray: IViewStatsCommand[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const viewStatsCommandCollection: IViewStatsCommand[] = [sampleWithRequiredData];
        expectedResult = service.addViewStatsCommandToCollectionIfMissing(viewStatsCommandCollection, ...viewStatsCommandArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const viewStatsCommand: IViewStatsCommand = sampleWithRequiredData;
        const viewStatsCommand2: IViewStatsCommand = sampleWithPartialData;
        expectedResult = service.addViewStatsCommandToCollectionIfMissing([], viewStatsCommand, viewStatsCommand2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(viewStatsCommand);
        expect(expectedResult).toContain(viewStatsCommand2);
      });

      it('should accept null and undefined values', () => {
        const viewStatsCommand: IViewStatsCommand = sampleWithRequiredData;
        expectedResult = service.addViewStatsCommandToCollectionIfMissing([], null, viewStatsCommand, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(viewStatsCommand);
      });

      it('should return initial array if no ViewStatsCommand is added', () => {
        const viewStatsCommandCollection: IViewStatsCommand[] = [sampleWithRequiredData];
        expectedResult = service.addViewStatsCommandToCollectionIfMissing(viewStatsCommandCollection, undefined, null);
        expect(expectedResult).toEqual(viewStatsCommandCollection);
      });
    });

    describe('compareViewStatsCommand', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareViewStatsCommand(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareViewStatsCommand(entity1, entity2);
        const compareResult2 = service.compareViewStatsCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareViewStatsCommand(entity1, entity2);
        const compareResult2 = service.compareViewStatsCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareViewStatsCommand(entity1, entity2);
        const compareResult2 = service.compareViewStatsCommand(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
