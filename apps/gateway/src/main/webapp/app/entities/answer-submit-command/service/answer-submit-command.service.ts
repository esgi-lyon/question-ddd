import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAnswerSubmitCommand, NewAnswerSubmitCommand } from '../answer-submit-command.model';

export type PartialUpdateAnswerSubmitCommand = Partial<IAnswerSubmitCommand> & Pick<IAnswerSubmitCommand, 'id'>;

export type EntityResponseType = HttpResponse<IAnswerSubmitCommand>;
export type EntityArrayResponseType = HttpResponse<IAnswerSubmitCommand[]>;

@Injectable({ providedIn: 'root' })
export class AnswerSubmitCommandService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/answer-submit-commands');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(answerSubmitCommand: NewAnswerSubmitCommand): Observable<EntityResponseType> {
    return this.http.post<IAnswerSubmitCommand>(this.resourceUrl, answerSubmitCommand, { observe: 'response' });
  }

  update(answerSubmitCommand: IAnswerSubmitCommand): Observable<EntityResponseType> {
    return this.http.put<IAnswerSubmitCommand>(
      `${this.resourceUrl}/${this.getAnswerSubmitCommandIdentifier(answerSubmitCommand)}`,
      answerSubmitCommand,
      { observe: 'response' }
    );
  }

  partialUpdate(answerSubmitCommand: PartialUpdateAnswerSubmitCommand): Observable<EntityResponseType> {
    return this.http.patch<IAnswerSubmitCommand>(
      `${this.resourceUrl}/${this.getAnswerSubmitCommandIdentifier(answerSubmitCommand)}`,
      answerSubmitCommand,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAnswerSubmitCommand>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAnswerSubmitCommand[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAnswerSubmitCommandIdentifier(answerSubmitCommand: Pick<IAnswerSubmitCommand, 'id'>): number {
    return answerSubmitCommand.id;
  }

  compareAnswerSubmitCommand(o1: Pick<IAnswerSubmitCommand, 'id'> | null, o2: Pick<IAnswerSubmitCommand, 'id'> | null): boolean {
    return o1 && o2 ? this.getAnswerSubmitCommandIdentifier(o1) === this.getAnswerSubmitCommandIdentifier(o2) : o1 === o2;
  }

  addAnswerSubmitCommandToCollectionIfMissing<Type extends Pick<IAnswerSubmitCommand, 'id'>>(
    answerSubmitCommandCollection: Type[],
    ...answerSubmitCommandsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const answerSubmitCommands: Type[] = answerSubmitCommandsToCheck.filter(isPresent);
    if (answerSubmitCommands.length > 0) {
      const answerSubmitCommandCollectionIdentifiers = answerSubmitCommandCollection.map(
        answerSubmitCommandItem => this.getAnswerSubmitCommandIdentifier(answerSubmitCommandItem)!
      );
      const answerSubmitCommandsToAdd = answerSubmitCommands.filter(answerSubmitCommandItem => {
        const answerSubmitCommandIdentifier = this.getAnswerSubmitCommandIdentifier(answerSubmitCommandItem);
        if (answerSubmitCommandCollectionIdentifiers.includes(answerSubmitCommandIdentifier)) {
          return false;
        }
        answerSubmitCommandCollectionIdentifiers.push(answerSubmitCommandIdentifier);
        return true;
      });
      return [...answerSubmitCommandsToAdd, ...answerSubmitCommandCollection];
    }
    return answerSubmitCommandCollection;
  }
}
