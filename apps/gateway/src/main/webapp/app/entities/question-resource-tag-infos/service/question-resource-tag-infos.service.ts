import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IQuestionResourceTagInfos, NewQuestionResourceTagInfos } from '../question-resource-tag-infos.model';

export type PartialUpdateQuestionResourceTagInfos = Partial<IQuestionResourceTagInfos> & Pick<IQuestionResourceTagInfos, 'id'>;

export type EntityResponseType = HttpResponse<IQuestionResourceTagInfos>;
export type EntityArrayResponseType = HttpResponse<IQuestionResourceTagInfos[]>;

@Injectable({ providedIn: 'root' })
export class QuestionResourceTagInfosService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/question-resource-tag-infos');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IQuestionResourceTagInfos>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IQuestionResourceTagInfos[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getQuestionResourceTagInfosIdentifier(questionResourceTagInfos: Pick<IQuestionResourceTagInfos, 'id'>): number {
    return questionResourceTagInfos.id;
  }

  compareQuestionResourceTagInfos(
    o1: Pick<IQuestionResourceTagInfos, 'id'> | null,
    o2: Pick<IQuestionResourceTagInfos, 'id'> | null
  ): boolean {
    return o1 && o2 ? this.getQuestionResourceTagInfosIdentifier(o1) === this.getQuestionResourceTagInfosIdentifier(o2) : o1 === o2;
  }

  addQuestionResourceTagInfosToCollectionIfMissing<Type extends Pick<IQuestionResourceTagInfos, 'id'>>(
    questionResourceTagInfosCollection: Type[],
    ...questionResourceTagInfosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const questionResourceTagInfos: Type[] = questionResourceTagInfosToCheck.filter(isPresent);
    if (questionResourceTagInfos.length > 0) {
      const questionResourceTagInfosCollectionIdentifiers = questionResourceTagInfosCollection.map(
        questionResourceTagInfosItem => this.getQuestionResourceTagInfosIdentifier(questionResourceTagInfosItem)!
      );
      const questionResourceTagInfosToAdd = questionResourceTagInfos.filter(questionResourceTagInfosItem => {
        const questionResourceTagInfosIdentifier = this.getQuestionResourceTagInfosIdentifier(questionResourceTagInfosItem);
        if (questionResourceTagInfosCollectionIdentifiers.includes(questionResourceTagInfosIdentifier)) {
          return false;
        }
        questionResourceTagInfosCollectionIdentifiers.push(questionResourceTagInfosIdentifier);
        return true;
      });
      return [...questionResourceTagInfosToAdd, ...questionResourceTagInfosCollection];
    }
    return questionResourceTagInfosCollection;
  }
}
