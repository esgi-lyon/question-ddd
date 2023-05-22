import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICreateQuestionCommand, NewCreateQuestionCommand } from '../create-question-command.model';

export type PartialUpdateCreateQuestionCommand = Partial<ICreateQuestionCommand> & Pick<ICreateQuestionCommand, 'id'>;

export type EntityResponseType = HttpResponse<ICreateQuestionCommand>;
export type EntityArrayResponseType = HttpResponse<ICreateQuestionCommand[]>;

@Injectable({ providedIn: 'root' })
export class CreateQuestionCommandService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/create-question-commands');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(createQuestionCommand: NewCreateQuestionCommand): Observable<EntityResponseType> {
    return this.http.post<ICreateQuestionCommand>(this.resourceUrl, createQuestionCommand, { observe: 'response' });
  }

  update(createQuestionCommand: ICreateQuestionCommand): Observable<EntityResponseType> {
    return this.http.put<ICreateQuestionCommand>(
      `${this.resourceUrl}/${this.getCreateQuestionCommandIdentifier(createQuestionCommand)}`,
      createQuestionCommand,
      { observe: 'response' }
    );
  }

  partialUpdate(createQuestionCommand: PartialUpdateCreateQuestionCommand): Observable<EntityResponseType> {
    return this.http.patch<ICreateQuestionCommand>(
      `${this.resourceUrl}/${this.getCreateQuestionCommandIdentifier(createQuestionCommand)}`,
      createQuestionCommand,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICreateQuestionCommand>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICreateQuestionCommand[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCreateQuestionCommandIdentifier(createQuestionCommand: Pick<ICreateQuestionCommand, 'id'>): number {
    return createQuestionCommand.id;
  }

  compareCreateQuestionCommand(o1: Pick<ICreateQuestionCommand, 'id'> | null, o2: Pick<ICreateQuestionCommand, 'id'> | null): boolean {
    return o1 && o2 ? this.getCreateQuestionCommandIdentifier(o1) === this.getCreateQuestionCommandIdentifier(o2) : o1 === o2;
  }

  addCreateQuestionCommandToCollectionIfMissing<Type extends Pick<ICreateQuestionCommand, 'id'>>(
    createQuestionCommandCollection: Type[],
    ...createQuestionCommandsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const createQuestionCommands: Type[] = createQuestionCommandsToCheck.filter(isPresent);
    if (createQuestionCommands.length > 0) {
      const createQuestionCommandCollectionIdentifiers = createQuestionCommandCollection.map(
        createQuestionCommandItem => this.getCreateQuestionCommandIdentifier(createQuestionCommandItem)!
      );
      const createQuestionCommandsToAdd = createQuestionCommands.filter(createQuestionCommandItem => {
        const createQuestionCommandIdentifier = this.getCreateQuestionCommandIdentifier(createQuestionCommandItem);
        if (createQuestionCommandCollectionIdentifiers.includes(createQuestionCommandIdentifier)) {
          return false;
        }
        createQuestionCommandCollectionIdentifiers.push(createQuestionCommandIdentifier);
        return true;
      });
      return [...createQuestionCommandsToAdd, ...createQuestionCommandCollection];
    }
    return createQuestionCommandCollection;
  }
}
