import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IQuestionResource, NewQuestionResource } from '../question-resource.model';

export type PartialUpdateQuestionResource = Partial<IQuestionResource> & Pick<IQuestionResource, 'id'>;

export type EntityResponseType = HttpResponse<IQuestionResource>;
export type EntityArrayResponseType = HttpResponse<IQuestionResource[]>;

@Injectable({ providedIn: 'root' })
export class QuestionResourceService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/question-resources', 'questioncontext');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(questionResource: NewQuestionResource): Observable<EntityResponseType> {
    return this.http.post<IQuestionResource>(this.resourceUrl, questionResource, { observe: 'response' });
  }

  update(questionResource: IQuestionResource): Observable<EntityResponseType> {
    return this.http.put<IQuestionResource>(
      `${this.resourceUrl}/${this.getQuestionResourceIdentifier(questionResource)}`,
      questionResource,
      { observe: 'response' }
    );
  }

  partialUpdate(questionResource: PartialUpdateQuestionResource): Observable<EntityResponseType> {
    return this.http.patch<IQuestionResource>(
      `${this.resourceUrl}/${this.getQuestionResourceIdentifier(questionResource)}`,
      questionResource,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IQuestionResource>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IQuestionResource[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getQuestionResourceIdentifier(questionResource: Pick<IQuestionResource, 'id'>): number {
    return questionResource.id;
  }

  compareQuestionResource(o1: Pick<IQuestionResource, 'id'> | null, o2: Pick<IQuestionResource, 'id'> | null): boolean {
    return o1 && o2 ? this.getQuestionResourceIdentifier(o1) === this.getQuestionResourceIdentifier(o2) : o1 === o2;
  }

  addQuestionResourceToCollectionIfMissing<Type extends Pick<IQuestionResource, 'id'>>(
    questionResourceCollection: Type[],
    ...questionResourcesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const questionResources: Type[] = questionResourcesToCheck.filter(isPresent);
    if (questionResources.length > 0) {
      const questionResourceCollectionIdentifiers = questionResourceCollection.map(
        questionResourceItem => this.getQuestionResourceIdentifier(questionResourceItem)!
      );
      const questionResourcesToAdd = questionResources.filter(questionResourceItem => {
        const questionResourceIdentifier = this.getQuestionResourceIdentifier(questionResourceItem);
        if (questionResourceCollectionIdentifiers.includes(questionResourceIdentifier)) {
          return false;
        }
        questionResourceCollectionIdentifiers.push(questionResourceIdentifier);
        return true;
      });
      return [...questionResourcesToAdd, ...questionResourceCollection];
    }
    return questionResourceCollection;
  }
}
