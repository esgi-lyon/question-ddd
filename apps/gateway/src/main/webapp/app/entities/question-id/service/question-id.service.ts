import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IQuestionId, NewQuestionId } from '../question-id.model';

export type PartialUpdateQuestionId = Partial<IQuestionId> & Pick<IQuestionId, 'id'>;

export type EntityResponseType = HttpResponse<IQuestionId>;
export type EntityArrayResponseType = HttpResponse<IQuestionId[]>;

@Injectable({ providedIn: 'root' })
export class QuestionIdService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/question-ids');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IQuestionId>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IQuestionId[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getQuestionIdIdentifier(questionId: Pick<IQuestionId, 'id'>): number {
    return questionId.id;
  }

  compareQuestionId(o1: Pick<IQuestionId, 'id'> | null, o2: Pick<IQuestionId, 'id'> | null): boolean {
    return o1 && o2 ? this.getQuestionIdIdentifier(o1) === this.getQuestionIdIdentifier(o2) : o1 === o2;
  }

  addQuestionIdToCollectionIfMissing<Type extends Pick<IQuestionId, 'id'>>(
    questionIdCollection: Type[],
    ...questionIdsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const questionIds: Type[] = questionIdsToCheck.filter(isPresent);
    if (questionIds.length > 0) {
      const questionIdCollectionIdentifiers = questionIdCollection.map(questionIdItem => this.getQuestionIdIdentifier(questionIdItem)!);
      const questionIdsToAdd = questionIds.filter(questionIdItem => {
        const questionIdIdentifier = this.getQuestionIdIdentifier(questionIdItem);
        if (questionIdCollectionIdentifiers.includes(questionIdIdentifier)) {
          return false;
        }
        questionIdCollectionIdentifiers.push(questionIdIdentifier);
        return true;
      });
      return [...questionIdsToAdd, ...questionIdCollection];
    }
    return questionIdCollection;
  }
}
