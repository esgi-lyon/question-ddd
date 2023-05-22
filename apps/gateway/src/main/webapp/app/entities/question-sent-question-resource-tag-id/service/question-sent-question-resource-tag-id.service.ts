import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IQuestionSentQuestionResourceTagId, NewQuestionSentQuestionResourceTagId } from '../question-sent-question-resource-tag-id.model';

export type PartialUpdateQuestionSentQuestionResourceTagId = Partial<IQuestionSentQuestionResourceTagId> &
  Pick<IQuestionSentQuestionResourceTagId, 'id'>;

export type EntityResponseType = HttpResponse<IQuestionSentQuestionResourceTagId>;
export type EntityArrayResponseType = HttpResponse<IQuestionSentQuestionResourceTagId[]>;

@Injectable({ providedIn: 'root' })
export class QuestionSentQuestionResourceTagIdService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/question-sent-question-resource-tag-ids');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IQuestionSentQuestionResourceTagId>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IQuestionSentQuestionResourceTagId[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getQuestionSentQuestionResourceTagIdIdentifier(
    questionSentQuestionResourceTagId: Pick<IQuestionSentQuestionResourceTagId, 'id'>
  ): number {
    return questionSentQuestionResourceTagId.id;
  }

  compareQuestionSentQuestionResourceTagId(
    o1: Pick<IQuestionSentQuestionResourceTagId, 'id'> | null,
    o2: Pick<IQuestionSentQuestionResourceTagId, 'id'> | null
  ): boolean {
    return o1 && o2
      ? this.getQuestionSentQuestionResourceTagIdIdentifier(o1) === this.getQuestionSentQuestionResourceTagIdIdentifier(o2)
      : o1 === o2;
  }

  addQuestionSentQuestionResourceTagIdToCollectionIfMissing<Type extends Pick<IQuestionSentQuestionResourceTagId, 'id'>>(
    questionSentQuestionResourceTagIdCollection: Type[],
    ...questionSentQuestionResourceTagIdsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const questionSentQuestionResourceTagIds: Type[] = questionSentQuestionResourceTagIdsToCheck.filter(isPresent);
    if (questionSentQuestionResourceTagIds.length > 0) {
      const questionSentQuestionResourceTagIdCollectionIdentifiers = questionSentQuestionResourceTagIdCollection.map(
        questionSentQuestionResourceTagIdItem => this.getQuestionSentQuestionResourceTagIdIdentifier(questionSentQuestionResourceTagIdItem)!
      );
      const questionSentQuestionResourceTagIdsToAdd = questionSentQuestionResourceTagIds.filter(questionSentQuestionResourceTagIdItem => {
        const questionSentQuestionResourceTagIdIdentifier = this.getQuestionSentQuestionResourceTagIdIdentifier(
          questionSentQuestionResourceTagIdItem
        );
        if (questionSentQuestionResourceTagIdCollectionIdentifiers.includes(questionSentQuestionResourceTagIdIdentifier)) {
          return false;
        }
        questionSentQuestionResourceTagIdCollectionIdentifiers.push(questionSentQuestionResourceTagIdIdentifier);
        return true;
      });
      return [...questionSentQuestionResourceTagIdsToAdd, ...questionSentQuestionResourceTagIdCollection];
    }
    return questionSentQuestionResourceTagIdCollection;
  }
}
