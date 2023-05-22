import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICheckAnswerCommand, NewCheckAnswerCommand } from '../check-answer-command.model';

export type PartialUpdateCheckAnswerCommand = Partial<ICheckAnswerCommand> & Pick<ICheckAnswerCommand, 'id'>;

export type EntityResponseType = HttpResponse<ICheckAnswerCommand>;
export type EntityArrayResponseType = HttpResponse<ICheckAnswerCommand[]>;

@Injectable({ providedIn: 'root' })
export class CheckAnswerCommandService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/check-answer-commands');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(checkAnswerCommand: NewCheckAnswerCommand): Observable<EntityResponseType> {
    return this.http.post<ICheckAnswerCommand>(this.resourceUrl, checkAnswerCommand, { observe: 'response' });
  }

  update(checkAnswerCommand: ICheckAnswerCommand): Observable<EntityResponseType> {
    return this.http.put<ICheckAnswerCommand>(
      `${this.resourceUrl}/${this.getCheckAnswerCommandIdentifier(checkAnswerCommand)}`,
      checkAnswerCommand,
      { observe: 'response' }
    );
  }

  partialUpdate(checkAnswerCommand: PartialUpdateCheckAnswerCommand): Observable<EntityResponseType> {
    return this.http.patch<ICheckAnswerCommand>(
      `${this.resourceUrl}/${this.getCheckAnswerCommandIdentifier(checkAnswerCommand)}`,
      checkAnswerCommand,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICheckAnswerCommand>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICheckAnswerCommand[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCheckAnswerCommandIdentifier(checkAnswerCommand: Pick<ICheckAnswerCommand, 'id'>): number {
    return checkAnswerCommand.id;
  }

  compareCheckAnswerCommand(o1: Pick<ICheckAnswerCommand, 'id'> | null, o2: Pick<ICheckAnswerCommand, 'id'> | null): boolean {
    return o1 && o2 ? this.getCheckAnswerCommandIdentifier(o1) === this.getCheckAnswerCommandIdentifier(o2) : o1 === o2;
  }

  addCheckAnswerCommandToCollectionIfMissing<Type extends Pick<ICheckAnswerCommand, 'id'>>(
    checkAnswerCommandCollection: Type[],
    ...checkAnswerCommandsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const checkAnswerCommands: Type[] = checkAnswerCommandsToCheck.filter(isPresent);
    if (checkAnswerCommands.length > 0) {
      const checkAnswerCommandCollectionIdentifiers = checkAnswerCommandCollection.map(
        checkAnswerCommandItem => this.getCheckAnswerCommandIdentifier(checkAnswerCommandItem)!
      );
      const checkAnswerCommandsToAdd = checkAnswerCommands.filter(checkAnswerCommandItem => {
        const checkAnswerCommandIdentifier = this.getCheckAnswerCommandIdentifier(checkAnswerCommandItem);
        if (checkAnswerCommandCollectionIdentifiers.includes(checkAnswerCommandIdentifier)) {
          return false;
        }
        checkAnswerCommandCollectionIdentifiers.push(checkAnswerCommandIdentifier);
        return true;
      });
      return [...checkAnswerCommandsToAdd, ...checkAnswerCommandCollection];
    }
    return checkAnswerCommandCollection;
  }
}
