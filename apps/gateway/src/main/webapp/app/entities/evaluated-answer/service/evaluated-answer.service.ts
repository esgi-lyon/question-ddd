import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEvaluatedAnswer, NewEvaluatedAnswer } from '../evaluated-answer.model';

export type PartialUpdateEvaluatedAnswer = Partial<IEvaluatedAnswer> & Pick<IEvaluatedAnswer, 'id'>;

export type EntityResponseType = HttpResponse<IEvaluatedAnswer>;
export type EntityArrayResponseType = HttpResponse<IEvaluatedAnswer[]>;

@Injectable({ providedIn: 'root' })
export class EvaluatedAnswerService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/evaluated-answers');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEvaluatedAnswer>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEvaluatedAnswer[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getEvaluatedAnswerIdentifier(evaluatedAnswer: Pick<IEvaluatedAnswer, 'id'>): number {
    return evaluatedAnswer.id;
  }

  compareEvaluatedAnswer(o1: Pick<IEvaluatedAnswer, 'id'> | null, o2: Pick<IEvaluatedAnswer, 'id'> | null): boolean {
    return o1 && o2 ? this.getEvaluatedAnswerIdentifier(o1) === this.getEvaluatedAnswerIdentifier(o2) : o1 === o2;
  }

  addEvaluatedAnswerToCollectionIfMissing<Type extends Pick<IEvaluatedAnswer, 'id'>>(
    evaluatedAnswerCollection: Type[],
    ...evaluatedAnswersToCheck: (Type | null | undefined)[]
  ): Type[] {
    const evaluatedAnswers: Type[] = evaluatedAnswersToCheck.filter(isPresent);
    if (evaluatedAnswers.length > 0) {
      const evaluatedAnswerCollectionIdentifiers = evaluatedAnswerCollection.map(
        evaluatedAnswerItem => this.getEvaluatedAnswerIdentifier(evaluatedAnswerItem)!
      );
      const evaluatedAnswersToAdd = evaluatedAnswers.filter(evaluatedAnswerItem => {
        const evaluatedAnswerIdentifier = this.getEvaluatedAnswerIdentifier(evaluatedAnswerItem);
        if (evaluatedAnswerCollectionIdentifiers.includes(evaluatedAnswerIdentifier)) {
          return false;
        }
        evaluatedAnswerCollectionIdentifiers.push(evaluatedAnswerIdentifier);
        return true;
      });
      return [...evaluatedAnswersToAdd, ...evaluatedAnswerCollection];
    }
    return evaluatedAnswerCollection;
  }
}
