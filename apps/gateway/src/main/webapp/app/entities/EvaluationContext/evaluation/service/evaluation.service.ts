import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEvaluation, NewEvaluation } from '../evaluation.model';

export type PartialUpdateEvaluation = Partial<IEvaluation> & Pick<IEvaluation, 'id'>;

export type EntityResponseType = HttpResponse<IEvaluation>;
export type EntityArrayResponseType = HttpResponse<IEvaluation[]>;

@Injectable({ providedIn: 'root' })
export class EvaluationService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/evaluations', 'evaluationcontext');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(evaluation: NewEvaluation): Observable<EntityResponseType> {
    return this.http.post<IEvaluation>(this.resourceUrl, evaluation, { observe: 'response' });
  }

  update(evaluation: IEvaluation): Observable<EntityResponseType> {
    return this.http.put<IEvaluation>(`${this.resourceUrl}/${this.getEvaluationIdentifier(evaluation)}`, evaluation, {
      observe: 'response',
    });
  }

  partialUpdate(evaluation: PartialUpdateEvaluation): Observable<EntityResponseType> {
    return this.http.patch<IEvaluation>(`${this.resourceUrl}/${this.getEvaluationIdentifier(evaluation)}`, evaluation, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEvaluation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEvaluation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getEvaluationIdentifier(evaluation: Pick<IEvaluation, 'id'>): number {
    return evaluation.id;
  }

  compareEvaluation(o1: Pick<IEvaluation, 'id'> | null, o2: Pick<IEvaluation, 'id'> | null): boolean {
    return o1 && o2 ? this.getEvaluationIdentifier(o1) === this.getEvaluationIdentifier(o2) : o1 === o2;
  }

  addEvaluationToCollectionIfMissing<Type extends Pick<IEvaluation, 'id'>>(
    evaluationCollection: Type[],
    ...evaluationsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const evaluations: Type[] = evaluationsToCheck.filter(isPresent);
    if (evaluations.length > 0) {
      const evaluationCollectionIdentifiers = evaluationCollection.map(evaluationItem => this.getEvaluationIdentifier(evaluationItem)!);
      const evaluationsToAdd = evaluations.filter(evaluationItem => {
        const evaluationIdentifier = this.getEvaluationIdentifier(evaluationItem);
        if (evaluationCollectionIdentifiers.includes(evaluationIdentifier)) {
          return false;
        }
        evaluationCollectionIdentifiers.push(evaluationIdentifier);
        return true;
      });
      return [...evaluationsToAdd, ...evaluationCollection];
    }
    return evaluationCollection;
  }
}
