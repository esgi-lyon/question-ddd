import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ISendQuestionByTagsPreferencesCommand } from '../send-question-by-tags-preferences-command.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../send-question-by-tags-preferences-command.test-samples';

import { SendQuestionByTagsPreferencesCommandService } from './send-question-by-tags-preferences-command.service';

const requireRestSample: ISendQuestionByTagsPreferencesCommand = {
  ...sampleWithRequiredData,
};

describe('SendQuestionByTagsPreferencesCommand Service', () => {
  let service: SendQuestionByTagsPreferencesCommandService;
  let httpMock: HttpTestingController;
  let expectedResult: ISendQuestionByTagsPreferencesCommand | ISendQuestionByTagsPreferencesCommand[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SendQuestionByTagsPreferencesCommandService);
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

    it('should create a SendQuestionByTagsPreferencesCommand', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const sendQuestionByTagsPreferencesCommand = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(sendQuestionByTagsPreferencesCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a SendQuestionByTagsPreferencesCommand', () => {
      const sendQuestionByTagsPreferencesCommand = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(sendQuestionByTagsPreferencesCommand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a SendQuestionByTagsPreferencesCommand', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of SendQuestionByTagsPreferencesCommand', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a SendQuestionByTagsPreferencesCommand', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addSendQuestionByTagsPreferencesCommandToCollectionIfMissing', () => {
      it('should add a SendQuestionByTagsPreferencesCommand to an empty array', () => {
        const sendQuestionByTagsPreferencesCommand: ISendQuestionByTagsPreferencesCommand = sampleWithRequiredData;
        expectedResult = service.addSendQuestionByTagsPreferencesCommandToCollectionIfMissing([], sendQuestionByTagsPreferencesCommand);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sendQuestionByTagsPreferencesCommand);
      });

      it('should not add a SendQuestionByTagsPreferencesCommand to an array that contains it', () => {
        const sendQuestionByTagsPreferencesCommand: ISendQuestionByTagsPreferencesCommand = sampleWithRequiredData;
        const sendQuestionByTagsPreferencesCommandCollection: ISendQuestionByTagsPreferencesCommand[] = [
          {
            ...sendQuestionByTagsPreferencesCommand,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSendQuestionByTagsPreferencesCommandToCollectionIfMissing(
          sendQuestionByTagsPreferencesCommandCollection,
          sendQuestionByTagsPreferencesCommand
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a SendQuestionByTagsPreferencesCommand to an array that doesn't contain it", () => {
        const sendQuestionByTagsPreferencesCommand: ISendQuestionByTagsPreferencesCommand = sampleWithRequiredData;
        const sendQuestionByTagsPreferencesCommandCollection: ISendQuestionByTagsPreferencesCommand[] = [sampleWithPartialData];
        expectedResult = service.addSendQuestionByTagsPreferencesCommandToCollectionIfMissing(
          sendQuestionByTagsPreferencesCommandCollection,
          sendQuestionByTagsPreferencesCommand
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sendQuestionByTagsPreferencesCommand);
      });

      it('should add only unique SendQuestionByTagsPreferencesCommand to an array', () => {
        const sendQuestionByTagsPreferencesCommandArray: ISendQuestionByTagsPreferencesCommand[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const sendQuestionByTagsPreferencesCommandCollection: ISendQuestionByTagsPreferencesCommand[] = [sampleWithRequiredData];
        expectedResult = service.addSendQuestionByTagsPreferencesCommandToCollectionIfMissing(
          sendQuestionByTagsPreferencesCommandCollection,
          ...sendQuestionByTagsPreferencesCommandArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const sendQuestionByTagsPreferencesCommand: ISendQuestionByTagsPreferencesCommand = sampleWithRequiredData;
        const sendQuestionByTagsPreferencesCommand2: ISendQuestionByTagsPreferencesCommand = sampleWithPartialData;
        expectedResult = service.addSendQuestionByTagsPreferencesCommandToCollectionIfMissing(
          [],
          sendQuestionByTagsPreferencesCommand,
          sendQuestionByTagsPreferencesCommand2
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(sendQuestionByTagsPreferencesCommand);
        expect(expectedResult).toContain(sendQuestionByTagsPreferencesCommand2);
      });

      it('should accept null and undefined values', () => {
        const sendQuestionByTagsPreferencesCommand: ISendQuestionByTagsPreferencesCommand = sampleWithRequiredData;
        expectedResult = service.addSendQuestionByTagsPreferencesCommandToCollectionIfMissing(
          [],
          null,
          sendQuestionByTagsPreferencesCommand,
          undefined
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(sendQuestionByTagsPreferencesCommand);
      });

      it('should return initial array if no SendQuestionByTagsPreferencesCommand is added', () => {
        const sendQuestionByTagsPreferencesCommandCollection: ISendQuestionByTagsPreferencesCommand[] = [sampleWithRequiredData];
        expectedResult = service.addSendQuestionByTagsPreferencesCommandToCollectionIfMissing(
          sendQuestionByTagsPreferencesCommandCollection,
          undefined,
          null
        );
        expect(expectedResult).toEqual(sendQuestionByTagsPreferencesCommandCollection);
      });
    });

    describe('compareSendQuestionByTagsPreferencesCommand', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSendQuestionByTagsPreferencesCommand(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareSendQuestionByTagsPreferencesCommand(entity1, entity2);
        const compareResult2 = service.compareSendQuestionByTagsPreferencesCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareSendQuestionByTagsPreferencesCommand(entity1, entity2);
        const compareResult2 = service.compareSendQuestionByTagsPreferencesCommand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareSendQuestionByTagsPreferencesCommand(entity1, entity2);
        const compareResult2 = service.compareSendQuestionByTagsPreferencesCommand(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
