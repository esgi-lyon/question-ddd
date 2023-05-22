import { States } from 'app/entities/enumerations/states.model';
import { Types } from 'app/entities/enumerations/types.model';

import { IQuestionResource, NewQuestionResource } from './question-resource.model';

export const sampleWithRequiredData: IQuestionResource = {
  id: 30848,
};

export const sampleWithPartialData: IQuestionResource = {
  id: 59600,
  resourceType: Types['TEXT'],
};

export const sampleWithFullData: IQuestionResource = {
  id: 19192,
  questionContent: 'Soft',
  tag: 71619,
  questionState: States['ASSOCIATED'],
  resourceType: Types['IMG_URL'],
};

export const sampleWithNewData: NewQuestionResource = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
