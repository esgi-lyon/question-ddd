import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEvaluationTag, NewEvaluationTag } from '../evaluation-tag.model';

export type PartialUpdateEvaluationTag = Partial<IEvaluationTag> & Pick<IEvaluationTag, 'id'>;

export type EntityResponseType = HttpResponse<IEvaluationTag>;
export type EntityArrayResponseType = HttpResponse<IEvaluationTag[]>;

@Injectable({ providedIn: 'root' })
export class EvaluationTagService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/evaluation-tags');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEvaluationTag>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEvaluationTag[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getEvaluationTagIdentifier(evaluationTag: Pick<IEvaluationTag, 'id'>): number {
    return evaluationTag.id;
  }

  compareEvaluationTag(o1: Pick<IEvaluationTag, 'id'> | null, o2: Pick<IEvaluationTag, 'id'> | null): boolean {
    return o1 && o2 ? this.getEvaluationTagIdentifier(o1) === this.getEvaluationTagIdentifier(o2) : o1 === o2;
  }

  addEvaluationTagToCollectionIfMissing<Type extends Pick<IEvaluationTag, 'id'>>(
    evaluationTagCollection: Type[],
    ...evaluationTagsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const evaluationTags: Type[] = evaluationTagsToCheck.filter(isPresent);
    if (evaluationTags.length > 0) {
      const evaluationTagCollectionIdentifiers = evaluationTagCollection.map(
        evaluationTagItem => this.getEvaluationTagIdentifier(evaluationTagItem)!
      );
      const evaluationTagsToAdd = evaluationTags.filter(evaluationTagItem => {
        const evaluationTagIdentifier = this.getEvaluationTagIdentifier(evaluationTagItem);
        if (evaluationTagCollectionIdentifiers.includes(evaluationTagIdentifier)) {
          return false;
        }
        evaluationTagCollectionIdentifiers.push(evaluationTagIdentifier);
        return true;
      });
      return [...evaluationTagsToAdd, ...evaluationTagCollection];
    }
    return evaluationTagCollection;
  }
}
