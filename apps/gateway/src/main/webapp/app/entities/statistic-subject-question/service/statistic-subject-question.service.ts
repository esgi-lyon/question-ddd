import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IStatisticSubjectQuestion, NewStatisticSubjectQuestion } from '../statistic-subject-question.model';

export type PartialUpdateStatisticSubjectQuestion = Partial<IStatisticSubjectQuestion> & Pick<IStatisticSubjectQuestion, 'id'>;

export type EntityResponseType = HttpResponse<IStatisticSubjectQuestion>;
export type EntityArrayResponseType = HttpResponse<IStatisticSubjectQuestion[]>;

@Injectable({ providedIn: 'root' })
export class StatisticSubjectQuestionService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/statistic-subject-questions');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IStatisticSubjectQuestion>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IStatisticSubjectQuestion[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getStatisticSubjectQuestionIdentifier(statisticSubjectQuestion: Pick<IStatisticSubjectQuestion, 'id'>): number {
    return statisticSubjectQuestion.id;
  }

  compareStatisticSubjectQuestion(
    o1: Pick<IStatisticSubjectQuestion, 'id'> | null,
    o2: Pick<IStatisticSubjectQuestion, 'id'> | null
  ): boolean {
    return o1 && o2 ? this.getStatisticSubjectQuestionIdentifier(o1) === this.getStatisticSubjectQuestionIdentifier(o2) : o1 === o2;
  }

  addStatisticSubjectQuestionToCollectionIfMissing<Type extends Pick<IStatisticSubjectQuestion, 'id'>>(
    statisticSubjectQuestionCollection: Type[],
    ...statisticSubjectQuestionsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const statisticSubjectQuestions: Type[] = statisticSubjectQuestionsToCheck.filter(isPresent);
    if (statisticSubjectQuestions.length > 0) {
      const statisticSubjectQuestionCollectionIdentifiers = statisticSubjectQuestionCollection.map(
        statisticSubjectQuestionItem => this.getStatisticSubjectQuestionIdentifier(statisticSubjectQuestionItem)!
      );
      const statisticSubjectQuestionsToAdd = statisticSubjectQuestions.filter(statisticSubjectQuestionItem => {
        const statisticSubjectQuestionIdentifier = this.getStatisticSubjectQuestionIdentifier(statisticSubjectQuestionItem);
        if (statisticSubjectQuestionCollectionIdentifiers.includes(statisticSubjectQuestionIdentifier)) {
          return false;
        }
        statisticSubjectQuestionCollectionIdentifiers.push(statisticSubjectQuestionIdentifier);
        return true;
      });
      return [...statisticSubjectQuestionsToAdd, ...statisticSubjectQuestionCollection];
    }
    return statisticSubjectQuestionCollection;
  }
}
