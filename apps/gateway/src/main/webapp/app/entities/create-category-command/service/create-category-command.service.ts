import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICreateCategoryCommand, NewCreateCategoryCommand } from '../create-category-command.model';

export type PartialUpdateCreateCategoryCommand = Partial<ICreateCategoryCommand> & Pick<ICreateCategoryCommand, 'id'>;

export type EntityResponseType = HttpResponse<ICreateCategoryCommand>;
export type EntityArrayResponseType = HttpResponse<ICreateCategoryCommand[]>;

@Injectable({ providedIn: 'root' })
export class CreateCategoryCommandService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/create-category-commands');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(createCategoryCommand: NewCreateCategoryCommand): Observable<EntityResponseType> {
    return this.http.post<ICreateCategoryCommand>(this.resourceUrl, createCategoryCommand, { observe: 'response' });
  }

  update(createCategoryCommand: ICreateCategoryCommand): Observable<EntityResponseType> {
    return this.http.put<ICreateCategoryCommand>(
      `${this.resourceUrl}/${this.getCreateCategoryCommandIdentifier(createCategoryCommand)}`,
      createCategoryCommand,
      { observe: 'response' }
    );
  }

  partialUpdate(createCategoryCommand: PartialUpdateCreateCategoryCommand): Observable<EntityResponseType> {
    return this.http.patch<ICreateCategoryCommand>(
      `${this.resourceUrl}/${this.getCreateCategoryCommandIdentifier(createCategoryCommand)}`,
      createCategoryCommand,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICreateCategoryCommand>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICreateCategoryCommand[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCreateCategoryCommandIdentifier(createCategoryCommand: Pick<ICreateCategoryCommand, 'id'>): number {
    return createCategoryCommand.id;
  }

  compareCreateCategoryCommand(o1: Pick<ICreateCategoryCommand, 'id'> | null, o2: Pick<ICreateCategoryCommand, 'id'> | null): boolean {
    return o1 && o2 ? this.getCreateCategoryCommandIdentifier(o1) === this.getCreateCategoryCommandIdentifier(o2) : o1 === o2;
  }

  addCreateCategoryCommandToCollectionIfMissing<Type extends Pick<ICreateCategoryCommand, 'id'>>(
    createCategoryCommandCollection: Type[],
    ...createCategoryCommandsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const createCategoryCommands: Type[] = createCategoryCommandsToCheck.filter(isPresent);
    if (createCategoryCommands.length > 0) {
      const createCategoryCommandCollectionIdentifiers = createCategoryCommandCollection.map(
        createCategoryCommandItem => this.getCreateCategoryCommandIdentifier(createCategoryCommandItem)!
      );
      const createCategoryCommandsToAdd = createCategoryCommands.filter(createCategoryCommandItem => {
        const createCategoryCommandIdentifier = this.getCreateCategoryCommandIdentifier(createCategoryCommandItem);
        if (createCategoryCommandCollectionIdentifiers.includes(createCategoryCommandIdentifier)) {
          return false;
        }
        createCategoryCommandCollectionIdentifiers.push(createCategoryCommandIdentifier);
        return true;
      });
      return [...createCategoryCommandsToAdd, ...createCategoryCommandCollection];
    }
    return createCategoryCommandCollection;
  }
}
