import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICreateEvaluationCommand, NewCreateEvaluationCommand } from '../create-evaluation-command.model';

export type PartialUpdateCreateEvaluationCommand = Partial<ICreateEvaluationCommand> & Pick<ICreateEvaluationCommand, 'id'>;

export type EntityResponseType = HttpResponse<ICreateEvaluationCommand>;
export type EntityArrayResponseType = HttpResponse<ICreateEvaluationCommand[]>;

@Injectable({ providedIn: 'root' })
export class CreateEvaluationCommandService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/create-evaluation-commands');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(createEvaluationCommand: NewCreateEvaluationCommand): Observable<EntityResponseType> {
    return this.http.post<ICreateEvaluationCommand>(this.resourceUrl, createEvaluationCommand, { observe: 'response' });
  }

  update(createEvaluationCommand: ICreateEvaluationCommand): Observable<EntityResponseType> {
    return this.http.put<ICreateEvaluationCommand>(
      `${this.resourceUrl}/${this.getCreateEvaluationCommandIdentifier(createEvaluationCommand)}`,
      createEvaluationCommand,
      { observe: 'response' }
    );
  }

  partialUpdate(createEvaluationCommand: PartialUpdateCreateEvaluationCommand): Observable<EntityResponseType> {
    return this.http.patch<ICreateEvaluationCommand>(
      `${this.resourceUrl}/${this.getCreateEvaluationCommandIdentifier(createEvaluationCommand)}`,
      createEvaluationCommand,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICreateEvaluationCommand>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICreateEvaluationCommand[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCreateEvaluationCommandIdentifier(createEvaluationCommand: Pick<ICreateEvaluationCommand, 'id'>): number {
    return createEvaluationCommand.id;
  }

  compareCreateEvaluationCommand(
    o1: Pick<ICreateEvaluationCommand, 'id'> | null,
    o2: Pick<ICreateEvaluationCommand, 'id'> | null
  ): boolean {
    return o1 && o2 ? this.getCreateEvaluationCommandIdentifier(o1) === this.getCreateEvaluationCommandIdentifier(o2) : o1 === o2;
  }

  addCreateEvaluationCommandToCollectionIfMissing<Type extends Pick<ICreateEvaluationCommand, 'id'>>(
    createEvaluationCommandCollection: Type[],
    ...createEvaluationCommandsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const createEvaluationCommands: Type[] = createEvaluationCommandsToCheck.filter(isPresent);
    if (createEvaluationCommands.length > 0) {
      const createEvaluationCommandCollectionIdentifiers = createEvaluationCommandCollection.map(
        createEvaluationCommandItem => this.getCreateEvaluationCommandIdentifier(createEvaluationCommandItem)!
      );
      const createEvaluationCommandsToAdd = createEvaluationCommands.filter(createEvaluationCommandItem => {
        const createEvaluationCommandIdentifier = this.getCreateEvaluationCommandIdentifier(createEvaluationCommandItem);
        if (createEvaluationCommandCollectionIdentifiers.includes(createEvaluationCommandIdentifier)) {
          return false;
        }
        createEvaluationCommandCollectionIdentifiers.push(createEvaluationCommandIdentifier);
        return true;
      });
      return [...createEvaluationCommandsToAdd, ...createEvaluationCommandCollection];
    }
    return createEvaluationCommandCollection;
  }
}
