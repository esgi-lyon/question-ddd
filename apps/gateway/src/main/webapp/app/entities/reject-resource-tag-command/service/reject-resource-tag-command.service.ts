import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IRejectResourceTagCommand, NewRejectResourceTagCommand } from '../reject-resource-tag-command.model';

export type PartialUpdateRejectResourceTagCommand = Partial<IRejectResourceTagCommand> & Pick<IRejectResourceTagCommand, 'id'>;

export type EntityResponseType = HttpResponse<IRejectResourceTagCommand>;
export type EntityArrayResponseType = HttpResponse<IRejectResourceTagCommand[]>;

@Injectable({ providedIn: 'root' })
export class RejectResourceTagCommandService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/reject-resource-tag-commands');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(rejectResourceTagCommand: NewRejectResourceTagCommand): Observable<EntityResponseType> {
    return this.http.post<IRejectResourceTagCommand>(this.resourceUrl, rejectResourceTagCommand, { observe: 'response' });
  }

  update(rejectResourceTagCommand: IRejectResourceTagCommand): Observable<EntityResponseType> {
    return this.http.put<IRejectResourceTagCommand>(
      `${this.resourceUrl}/${this.getRejectResourceTagCommandIdentifier(rejectResourceTagCommand)}`,
      rejectResourceTagCommand,
      { observe: 'response' }
    );
  }

  partialUpdate(rejectResourceTagCommand: PartialUpdateRejectResourceTagCommand): Observable<EntityResponseType> {
    return this.http.patch<IRejectResourceTagCommand>(
      `${this.resourceUrl}/${this.getRejectResourceTagCommandIdentifier(rejectResourceTagCommand)}`,
      rejectResourceTagCommand,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRejectResourceTagCommand>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRejectResourceTagCommand[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getRejectResourceTagCommandIdentifier(rejectResourceTagCommand: Pick<IRejectResourceTagCommand, 'id'>): number {
    return rejectResourceTagCommand.id;
  }

  compareRejectResourceTagCommand(
    o1: Pick<IRejectResourceTagCommand, 'id'> | null,
    o2: Pick<IRejectResourceTagCommand, 'id'> | null
  ): boolean {
    return o1 && o2 ? this.getRejectResourceTagCommandIdentifier(o1) === this.getRejectResourceTagCommandIdentifier(o2) : o1 === o2;
  }

  addRejectResourceTagCommandToCollectionIfMissing<Type extends Pick<IRejectResourceTagCommand, 'id'>>(
    rejectResourceTagCommandCollection: Type[],
    ...rejectResourceTagCommandsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const rejectResourceTagCommands: Type[] = rejectResourceTagCommandsToCheck.filter(isPresent);
    if (rejectResourceTagCommands.length > 0) {
      const rejectResourceTagCommandCollectionIdentifiers = rejectResourceTagCommandCollection.map(
        rejectResourceTagCommandItem => this.getRejectResourceTagCommandIdentifier(rejectResourceTagCommandItem)!
      );
      const rejectResourceTagCommandsToAdd = rejectResourceTagCommands.filter(rejectResourceTagCommandItem => {
        const rejectResourceTagCommandIdentifier = this.getRejectResourceTagCommandIdentifier(rejectResourceTagCommandItem);
        if (rejectResourceTagCommandCollectionIdentifiers.includes(rejectResourceTagCommandIdentifier)) {
          return false;
        }
        rejectResourceTagCommandCollectionIdentifiers.push(rejectResourceTagCommandIdentifier);
        return true;
      });
      return [...rejectResourceTagCommandsToAdd, ...rejectResourceTagCommandCollection];
    }
    return rejectResourceTagCommandCollection;
  }
}
