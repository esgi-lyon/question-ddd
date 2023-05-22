import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICreateTagCommand, NewCreateTagCommand } from '../create-tag-command.model';

export type PartialUpdateCreateTagCommand = Partial<ICreateTagCommand> & Pick<ICreateTagCommand, 'id'>;

export type EntityResponseType = HttpResponse<ICreateTagCommand>;
export type EntityArrayResponseType = HttpResponse<ICreateTagCommand[]>;

@Injectable({ providedIn: 'root' })
export class CreateTagCommandService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/create-tag-commands');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(createTagCommand: NewCreateTagCommand): Observable<EntityResponseType> {
    return this.http.post<ICreateTagCommand>(this.resourceUrl, createTagCommand, { observe: 'response' });
  }

  update(createTagCommand: ICreateTagCommand): Observable<EntityResponseType> {
    return this.http.put<ICreateTagCommand>(
      `${this.resourceUrl}/${this.getCreateTagCommandIdentifier(createTagCommand)}`,
      createTagCommand,
      { observe: 'response' }
    );
  }

  partialUpdate(createTagCommand: PartialUpdateCreateTagCommand): Observable<EntityResponseType> {
    return this.http.patch<ICreateTagCommand>(
      `${this.resourceUrl}/${this.getCreateTagCommandIdentifier(createTagCommand)}`,
      createTagCommand,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICreateTagCommand>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICreateTagCommand[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCreateTagCommandIdentifier(createTagCommand: Pick<ICreateTagCommand, 'id'>): number {
    return createTagCommand.id;
  }

  compareCreateTagCommand(o1: Pick<ICreateTagCommand, 'id'> | null, o2: Pick<ICreateTagCommand, 'id'> | null): boolean {
    return o1 && o2 ? this.getCreateTagCommandIdentifier(o1) === this.getCreateTagCommandIdentifier(o2) : o1 === o2;
  }

  addCreateTagCommandToCollectionIfMissing<Type extends Pick<ICreateTagCommand, 'id'>>(
    createTagCommandCollection: Type[],
    ...createTagCommandsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const createTagCommands: Type[] = createTagCommandsToCheck.filter(isPresent);
    if (createTagCommands.length > 0) {
      const createTagCommandCollectionIdentifiers = createTagCommandCollection.map(
        createTagCommandItem => this.getCreateTagCommandIdentifier(createTagCommandItem)!
      );
      const createTagCommandsToAdd = createTagCommands.filter(createTagCommandItem => {
        const createTagCommandIdentifier = this.getCreateTagCommandIdentifier(createTagCommandItem);
        if (createTagCommandCollectionIdentifiers.includes(createTagCommandIdentifier)) {
          return false;
        }
        createTagCommandCollectionIdentifiers.push(createTagCommandIdentifier);
        return true;
      });
      return [...createTagCommandsToAdd, ...createTagCommandCollection];
    }
    return createTagCommandCollection;
  }
}
