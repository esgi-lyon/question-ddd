import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEvaluationQuestion, NewEvaluationQuestion } from '../evaluation-question.model';

export type PartialUpdateEvaluationQuestion = Partial<IEvaluationQuestion> & Pick<IEvaluationQuestion, 'id'>;

export type EntityResponseType = HttpResponse<IEvaluationQuestion>;
export type EntityArrayResponseType = HttpResponse<IEvaluationQuestion[]>;

@Injectable({ providedIn: 'root' })
export class EvaluationQuestionService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/evaluation-questions');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEvaluationQuestion>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEvaluationQuestion[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getEvaluationQuestionIdentifier(evaluationQuestion: Pick<IEvaluationQuestion, 'id'>): number {
    return evaluationQuestion.id;
  }

  compareEvaluationQuestion(o1: Pick<IEvaluationQuestion, 'id'> | null, o2: Pick<IEvaluationQuestion, 'id'> | null): boolean {
    return o1 && o2 ? this.getEvaluationQuestionIdentifier(o1) === this.getEvaluationQuestionIdentifier(o2) : o1 === o2;
  }

  addEvaluationQuestionToCollectionIfMissing<Type extends Pick<IEvaluationQuestion, 'id'>>(
    evaluationQuestionCollection: Type[],
    ...evaluationQuestionsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const evaluationQuestions: Type[] = evaluationQuestionsToCheck.filter(isPresent);
    if (evaluationQuestions.length > 0) {
      const evaluationQuestionCollectionIdentifiers = evaluationQuestionCollection.map(
        evaluationQuestionItem => this.getEvaluationQuestionIdentifier(evaluationQuestionItem)!
      );
      const evaluationQuestionsToAdd = evaluationQuestions.filter(evaluationQuestionItem => {
        const evaluationQuestionIdentifier = this.getEvaluationQuestionIdentifier(evaluationQuestionItem);
        if (evaluationQuestionCollectionIdentifiers.includes(evaluationQuestionIdentifier)) {
          return false;
        }
        evaluationQuestionCollectionIdentifiers.push(evaluationQuestionIdentifier);
        return true;
      });
      return [...evaluationQuestionsToAdd, ...evaluationQuestionCollection];
    }
    return evaluationQuestionCollection;
  }
}
