import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEvaluationId, NewEvaluationId } from '../evaluation-id.model';

export type PartialUpdateEvaluationId = Partial<IEvaluationId> & Pick<IEvaluationId, 'id'>;

export type EntityResponseType = HttpResponse<IEvaluationId>;
export type EntityArrayResponseType = HttpResponse<IEvaluationId[]>;

@Injectable({ providedIn: 'root' })
export class EvaluationIdService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/evaluation-ids');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEvaluationId>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEvaluationId[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getEvaluationIdIdentifier(evaluationId: Pick<IEvaluationId, 'id'>): number {
    return evaluationId.id;
  }

  compareEvaluationId(o1: Pick<IEvaluationId, 'id'> | null, o2: Pick<IEvaluationId, 'id'> | null): boolean {
    return o1 && o2 ? this.getEvaluationIdIdentifier(o1) === this.getEvaluationIdIdentifier(o2) : o1 === o2;
  }

  addEvaluationIdToCollectionIfMissing<Type extends Pick<IEvaluationId, 'id'>>(
    evaluationIdCollection: Type[],
    ...evaluationIdsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const evaluationIds: Type[] = evaluationIdsToCheck.filter(isPresent);
    if (evaluationIds.length > 0) {
      const evaluationIdCollectionIdentifiers = evaluationIdCollection.map(
        evaluationIdItem => this.getEvaluationIdIdentifier(evaluationIdItem)!
      );
      const evaluationIdsToAdd = evaluationIds.filter(evaluationIdItem => {
        const evaluationIdIdentifier = this.getEvaluationIdIdentifier(evaluationIdItem);
        if (evaluationIdCollectionIdentifiers.includes(evaluationIdIdentifier)) {
          return false;
        }
        evaluationIdCollectionIdentifiers.push(evaluationIdIdentifier);
        return true;
      });
      return [...evaluationIdsToAdd, ...evaluationIdCollection];
    }
    return evaluationIdCollection;
  }
}
