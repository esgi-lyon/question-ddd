import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IQuestionSentTagId, NewQuestionSentTagId } from '../question-sent-tag-id.model';

export type PartialUpdateQuestionSentTagId = Partial<IQuestionSentTagId> & Pick<IQuestionSentTagId, 'id'>;

export type EntityResponseType = HttpResponse<IQuestionSentTagId>;
export type EntityArrayResponseType = HttpResponse<IQuestionSentTagId[]>;

@Injectable({ providedIn: 'root' })
export class QuestionSentTagIdService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/question-sent-tag-ids');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IQuestionSentTagId>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IQuestionSentTagId[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getQuestionSentTagIdIdentifier(questionSentTagId: Pick<IQuestionSentTagId, 'id'>): number {
    return questionSentTagId.id;
  }

  compareQuestionSentTagId(o1: Pick<IQuestionSentTagId, 'id'> | null, o2: Pick<IQuestionSentTagId, 'id'> | null): boolean {
    return o1 && o2 ? this.getQuestionSentTagIdIdentifier(o1) === this.getQuestionSentTagIdIdentifier(o2) : o1 === o2;
  }

  addQuestionSentTagIdToCollectionIfMissing<Type extends Pick<IQuestionSentTagId, 'id'>>(
    questionSentTagIdCollection: Type[],
    ...questionSentTagIdsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const questionSentTagIds: Type[] = questionSentTagIdsToCheck.filter(isPresent);
    if (questionSentTagIds.length > 0) {
      const questionSentTagIdCollectionIdentifiers = questionSentTagIdCollection.map(
        questionSentTagIdItem => this.getQuestionSentTagIdIdentifier(questionSentTagIdItem)!
      );
      const questionSentTagIdsToAdd = questionSentTagIds.filter(questionSentTagIdItem => {
        const questionSentTagIdIdentifier = this.getQuestionSentTagIdIdentifier(questionSentTagIdItem);
        if (questionSentTagIdCollectionIdentifiers.includes(questionSentTagIdIdentifier)) {
          return false;
        }
        questionSentTagIdCollectionIdentifiers.push(questionSentTagIdIdentifier);
        return true;
      });
      return [...questionSentTagIdsToAdd, ...questionSentTagIdCollection];
    }
    return questionSentTagIdCollection;
  }
}
