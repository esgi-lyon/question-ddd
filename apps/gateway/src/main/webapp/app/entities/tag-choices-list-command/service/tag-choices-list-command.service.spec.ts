import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITagChoicesListCommand } from '../tag-choices-list-command.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../tag-choices-list-command.test-samples';

import { TagChoicesListCommandService } from './tag-choices-list-command.service';

const requireRestSample: ITagChoicesListCommand = {
  ...sampleWithRequiredData,
};

describe('TagChoicesListCommand Service', () => {
  let service: TagChoicesListCommandService;
  let httpMock: HttpTestingController;
  let expectedResult: ITagChoicesListCommand | ITagChoicesListCommand[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(TagChoicesListCommandService);
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

    it('should create a TagChoicesListCommand', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const tagChoicesListCommand = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(tagChoicesListCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a TagChoicesListCommand', () => {
      const tagChoicesListCommand = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(tagChoicesListCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a TagChoicesListCommand', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of TagChoicesListCommand', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a TagChoicesListCommand', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addTagChoicesListCommandToCollectionIfMissing', () => {
      it('should add a TagChoicesListCommand to an empty array', () => {
        const tagChoicesListCommand: ITagChoicesListCommand = sampleWithRequiredData;
        expectedResult = service.addTagChoicesListCommandToCollectionIfMissing([], tagChoicesListCommand);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tagChoicesListCommand);
      });

      it('should not add a TagChoicesListCommand to an array that contains it', () => {
        const tagChoicesListCommand: ITagChoicesListCommand = sampleWithRequiredData;
        const tagChoicesListCommandCollection: ITagChoicesListCommand[] = [
          {
            ...tagChoicesListCommand,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addTagChoicesListCommandToCollectionIfMissing(tagChoicesListCommandCollection, tagChoicesListCommand);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a TagChoicesListCommand to an array that doesn't contain it", () => {
        const tagChoicesListCommand: ITagChoicesListCommand = sampleWithRequiredData;
        const tagChoicesListCommandCollection: ITagChoicesListCommand[] = [sampleWithPartialData];
        expectedResult = service.addTagChoicesListCommandToCollectionIfMissing(tagChoicesListCommandCollection, tagChoicesListCommand);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tagChoicesListCommand);
      });

      it('should add only unique TagChoicesListCommand to an array', () => {
        const tagChoicesListCommandArray: ITagChoicesListCommand[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const tagChoicesListCommandCollection: ITagChoicesListCommand[] = [sampleWithRequiredData];
        expectedResult = service.addTagChoicesListCommandToCollectionIfMissing(
          tagChoicesListCommandCollection,
          ...tagChoicesListCommandArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const tagChoicesListCommand: ITagChoicesListCommand = sampleWithRequiredData;
        const tagChoicesListCommand2: ITagChoicesListCommand = sampleWithPartialData;
        expectedResult = service.addTagChoicesListCommandToCollectionIfMissing([], tagChoicesListCommand, tagChoicesListCommand2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tagChoicesListCommand);
        expect(expectedResult).toContain(tagChoicesListCommand2);
      });

      it('should accept null and undefined values', () => {
        const tagChoicesListCommand: ITagChoicesListCommand = sampleWithRequiredData;
        expectedResult = service.addTagChoicesListCommandToCollectionIfMissing([], null, tagChoicesListCommand, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tagChoicesListCommand);
      });

      it('should return initial array if no TagChoicesListCommand is added', () => {
        const tagChoicesListCommandCollection: ITagChoicesListCommand[] = [sampleWithRequiredData];
        expectedResult = service.addTagChoicesListCommandToCollectionIfMissing(tagChoicesListCommandCollection, undefined, null);
        expect(expectedResult).toEqual(tagChoicesListCommandCollection);
      });
    });

    describe('compareTagChoicesListCommand', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareTagChoicesListCommand(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareTagChoicesListCommand(entity1, entity2);
        const compareResult2 = service.compareTagChoicesListCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareTagChoicesListCommand(entity1, entity2);
        const compareResult2 = service.compareTagChoicesListCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareTagChoicesListCommand(entity1, entity2);
        const compareResult2 = service.compareTagChoicesListCommand(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
