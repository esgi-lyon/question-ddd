import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IQuestionSent, NewQuestionSent } from '../question-sent.model';

export type PartialUpdateQuestionSent = Partial<IQuestionSent> & Pick<IQuestionSent, 'id'>;

type RestOf<T extends IQuestionSent | NewQuestionSent> = Omit<T, 'sentDate' | 'viewedDate' | 'answeredDate'> & {
  sentDate?: string | null;
  viewedDate?: string | null;
  answeredDate?: string | null;
};

export type RestQuestionSent = RestOf<IQuestionSent>;

export type NewRestQuestionSent = RestOf<NewQuestionSent>;

export type PartialUpdateRestQuestionSent = RestOf<PartialUpdateQuestionSent>;

export type EntityResponseType = HttpResponse<IQuestionSent>;
export type EntityArrayResponseType = HttpResponse<IQuestionSent[]>;

@Injectable({ providedIn: 'root' })
export class QuestionSentService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/question-sents', 'sendquestioncontext');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(questionSent: NewQuestionSent): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(questionSent);
    return this.http
      .post<RestQuestionSent>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(questionSent: IQuestionSent): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(questionSent);
    return this.http
      .put<RestQuestionSent>(`${this.resourceUrl}/${this.getQuestionSentIdentifier(questionSent)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(questionSent: PartialUpdateQuestionSent): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(questionSent);
    return this.http
      .patch<RestQuestionSent>(`${this.resourceUrl}/${this.getQuestionSentIdentifier(questionSent)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestQuestionSent>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestQuestionSent[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getQuestionSentIdentifier(questionSent: Pick<IQuestionSent, 'id'>): number {
    return questionSent.id;
  }

  compareQuestionSent(o1: Pick<IQuestionSent, 'id'> | null, o2: Pick<IQuestionSent, 'id'> | null): boolean {
    return o1 && o2 ? this.getQuestionSentIdentifier(o1) === this.getQuestionSentIdentifier(o2) : o1 === o2;
  }

  addQuestionSentToCollectionIfMissing<Type extends Pick<IQuestionSent, 'id'>>(
    questionSentCollection: Type[],
    ...questionSentsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const questionSents: Type[] = questionSentsToCheck.filter(isPresent);
    if (questionSents.length > 0) {
      const questionSentCollectionIdentifiers = questionSentCollection.map(
        questionSentItem => this.getQuestionSentIdentifier(questionSentItem)!
      );
      const questionSentsToAdd = questionSents.filter(questionSentItem => {
        const questionSentIdentifier = this.getQuestionSentIdentifier(questionSentItem);
        if (questionSentCollectionIdentifiers.includes(questionSentIdentifier)) {
          return false;
        }
        questionSentCollectionIdentifiers.push(questionSentIdentifier);
        return true;
      });
      return [...questionSentsToAdd, ...questionSentCollection];
    }
    return questionSentCollection;
  }

  protected convertDateFromClient<T extends IQuestionSent | NewQuestionSent | PartialUpdateQuestionSent>(questionSent: T): RestOf<T> {
    return {
      ...questionSent,
      sentDate: questionSent.sentDate?.format(DATE_FORMAT) ?? null,
      viewedDate: questionSent.viewedDate?.format(DATE_FORMAT) ?? null,
      answeredDate: questionSent.answeredDate?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restQuestionSent: RestQuestionSent): IQuestionSent {
    return {
      ...restQuestionSent,
      sentDate: restQuestionSent.sentDate ? dayjs(restQuestionSent.sentDate) : undefined,
      viewedDate: restQuestionSent.viewedDate ? dayjs(restQuestionSent.viewedDate) : undefined,
      answeredDate: restQuestionSent.answeredDate ? dayjs(restQuestionSent.answeredDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestQuestionSent>): HttpResponse<IQuestionSent> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestQuestionSent[]>): HttpResponse<IQuestionSent[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
