import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICreateResourceCommand, NewCreateResourceCommand } from '../create-resource-command.model';

export type PartialUpdateCreateResourceCommand = Partial<ICreateResourceCommand> & Pick<ICreateResourceCommand, 'id'>;

export type EntityResponseType = HttpResponse<ICreateResourceCommand>;
export type EntityArrayResponseType = HttpResponse<ICreateResourceCommand[]>;

@Injectable({ providedIn: 'root' })
export class CreateResourceCommandService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/create-resource-commands');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(createResourceCommand: NewCreateResourceCommand): Observable<EntityResponseType> {
    return this.http.post<ICreateResourceCommand>(this.resourceUrl, createResourceCommand, { observe: 'response' });
  }

  update(createResourceCommand: ICreateResourceCommand): Observable<EntityResponseType> {
    return this.http.put<ICreateResourceCommand>(
      `${this.resourceUrl}/${this.getCreateResourceCommandIdentifier(createResourceCommand)}`,
      createResourceCommand,
      { observe: 'response' }
    );
  }

  partialUpdate(createResourceCommand: PartialUpdateCreateResourceCommand): Observable<EntityResponseType> {
    return this.http.patch<ICreateResourceCommand>(
      `${this.resourceUrl}/${this.getCreateResourceCommandIdentifier(createResourceCommand)}`,
      createResourceCommand,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICreateResourceCommand>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICreateResourceCommand[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCreateResourceCommandIdentifier(createResourceCommand: Pick<ICreateResourceCommand, 'id'>): number {
    return createResourceCommand.id;
  }

  compareCreateResourceCommand(o1: Pick<ICreateResourceCommand, 'id'> | null, o2: Pick<ICreateResourceCommand, 'id'> | null): boolean {
    return o1 && o2 ? this.getCreateResourceCommandIdentifier(o1) === this.getCreateResourceCommandIdentifier(o2) : o1 === o2;
  }

  addCreateResourceCommandToCollectionIfMissing<Type extends Pick<ICreateResourceCommand, 'id'>>(
    createResourceCommandCollection: Type[],
    ...createResourceCommandsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const createResourceCommands: Type[] = createResourceCommandsToCheck.filter(isPresent);
    if (createResourceCommands.length > 0) {
      const createResourceCommandCollectionIdentifiers = createResourceCommandCollection.map(
        createResourceCommandItem => this.getCreateResourceCommandIdentifier(createResourceCommandItem)!
      );
      const createResourceCommandsToAdd = createResourceCommands.filter(createResourceCommandItem => {
        const createResourceCommandIdentifier = this.getCreateResourceCommandIdentifier(createResourceCommandItem);
        if (createResourceCommandCollectionIdentifiers.includes(createResourceCommandIdentifier)) {
          return false;
        }
        createResourceCommandCollectionIdentifiers.push(createResourceCommandIdentifier);
        return true;
      });
      return [...createResourceCommandsToAdd, ...createResourceCommandCollection];
    }
    return createResourceCommandCollection;
  }
}
