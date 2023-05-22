import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IQuestionSentTagInfos, NewQuestionSentTagInfos } from '../question-sent-tag-infos.model';

export type PartialUpdateQuestionSentTagInfos = Partial<IQuestionSentTagInfos> & Pick<IQuestionSentTagInfos, 'id'>;

export type EntityResponseType = HttpResponse<IQuestionSentTagInfos>;
export type EntityArrayResponseType = HttpResponse<IQuestionSentTagInfos[]>;

@Injectable({ providedIn: 'root' })
export class QuestionSentTagInfosService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/question-sent-tag-infos');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IQuestionSentTagInfos>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IQuestionSentTagInfos[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getQuestionSentTagInfosIdentifier(questionSentTagInfos: Pick<IQuestionSentTagInfos, 'id'>): number {
    return questionSentTagInfos.id;
  }

  compareQuestionSentTagInfos(o1: Pick<IQuestionSentTagInfos, 'id'> | null, o2: Pick<IQuestionSentTagInfos, 'id'> | null): boolean {
    return o1 && o2 ? this.getQuestionSentTagInfosIdentifier(o1) === this.getQuestionSentTagInfosIdentifier(o2) : o1 === o2;
  }

  addQuestionSentTagInfosToCollectionIfMissing<Type extends Pick<IQuestionSentTagInfos, 'id'>>(
    questionSentTagInfosCollection: Type[],
    ...questionSentTagInfosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const questionSentTagInfos: Type[] = questionSentTagInfosToCheck.filter(isPresent);
    if (questionSentTagInfos.length > 0) {
      const questionSentTagInfosCollectionIdentifiers = questionSentTagInfosCollection.map(
        questionSentTagInfosItem => this.getQuestionSentTagInfosIdentifier(questionSentTagInfosItem)!
      );
      const questionSentTagInfosToAdd = questionSentTagInfos.filter(questionSentTagInfosItem => {
        const questionSentTagInfosIdentifier = this.getQuestionSentTagInfosIdentifier(questionSentTagInfosItem);
        if (questionSentTagInfosCollectionIdentifiers.includes(questionSentTagInfosIdentifier)) {
          return false;
        }
        questionSentTagInfosCollectionIdentifiers.push(questionSentTagInfosIdentifier);
        return true;
      });
      return [...questionSentTagInfosToAdd, ...questionSentTagInfosCollection];
    }
    return questionSentTagInfosCollection;
  }
}
