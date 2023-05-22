import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITagChoicesListCommand, NewTagChoicesListCommand } from '../tag-choices-list-command.model';

export type PartialUpdateTagChoicesListCommand = Partial<ITagChoicesListCommand> & Pick<ITagChoicesListCommand, 'id'>;

export type EntityResponseType = HttpResponse<ITagChoicesListCommand>;
export type EntityArrayResponseType = HttpResponse<ITagChoicesListCommand[]>;

@Injectable({ providedIn: 'root' })
export class TagChoicesListCommandService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/tag-choices-list-commands');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(tagChoicesListCommand: NewTagChoicesListCommand): Observable<EntityResponseType> {
    return this.http.post<ITagChoicesListCommand>(this.resourceUrl, tagChoicesListCommand, { observe: 'response' });
  }

  update(tagChoicesListCommand: ITagChoicesListCommand): Observable<EntityResponseType> {
    return this.http.put<ITagChoicesListCommand>(
      `${this.resourceUrl}/${this.getTagChoicesListCommandIdentifier(tagChoicesListCommand)}`,
      tagChoicesListCommand,
      { observe: 'response' }
    );
  }

  partialUpdate(tagChoicesListCommand: PartialUpdateTagChoicesListCommand): Observable<EntityResponseType> {
    return this.http.patch<ITagChoicesListCommand>(
      `${this.resourceUrl}/${this.getTagChoicesListCommandIdentifier(tagChoicesListCommand)}`,
      tagChoicesListCommand,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITagChoicesListCommand>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITagChoicesListCommand[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getTagChoicesListCommandIdentifier(tagChoicesListCommand: Pick<ITagChoicesListCommand, 'id'>): number {
    return tagChoicesListCommand.id;
  }

  compareTagChoicesListCommand(o1: Pick<ITagChoicesListCommand, 'id'> | null, o2: Pick<ITagChoicesListCommand, 'id'> | null): boolean {
    return o1 && o2 ? this.getTagChoicesListCommandIdentifier(o1) === this.getTagChoicesListCommandIdentifier(o2) : o1 === o2;
  }

  addTagChoicesListCommandToCollectionIfMissing<Type extends Pick<ITagChoicesListCommand, 'id'>>(
    tagChoicesListCommandCollection: Type[],
    ...tagChoicesListCommandsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const tagChoicesListCommands: Type[] = tagChoicesListCommandsToCheck.filter(isPresent);
    if (tagChoicesListCommands.length > 0) {
      const tagChoicesListCommandCollectionIdentifiers = tagChoicesListCommandCollection.map(
        tagChoicesListCommandItem => this.getTagChoicesListCommandIdentifier(tagChoicesListCommandItem)!
      );
      const tagChoicesListCommandsToAdd = tagChoicesListCommands.filter(tagChoicesListCommandItem => {
        const tagChoicesListCommandIdentifier = this.getTagChoicesListCommandIdentifier(tagChoicesListCommandItem);
        if (tagChoicesListCommandCollectionIdentifiers.includes(tagChoicesListCommandIdentifier)) {
          return false;
        }
        tagChoicesListCommandCollectionIdentifiers.push(tagChoicesListCommandIdentifier);
        return true;
      });
      return [...tagChoicesListCommandsToAdd, ...tagChoicesListCommandCollection];
    }
    return tagChoicesListCommandCollection;
  }
}
