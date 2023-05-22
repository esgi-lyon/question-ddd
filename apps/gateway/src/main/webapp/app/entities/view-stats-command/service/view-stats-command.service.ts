import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IViewStatsCommand, NewViewStatsCommand } from '../view-stats-command.model';

export type PartialUpdateViewStatsCommand = Partial<IViewStatsCommand> & Pick<IViewStatsCommand, 'id'>;

export type EntityResponseType = HttpResponse<IViewStatsCommand>;
export type EntityArrayResponseType = HttpResponse<IViewStatsCommand[]>;

@Injectable({ providedIn: 'root' })
export class ViewStatsCommandService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/view-stats-commands');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(viewStatsCommand: NewViewStatsCommand): Observable<EntityResponseType> {
    return this.http.post<IViewStatsCommand>(this.resourceUrl, viewStatsCommand, { observe: 'response' });
  }

  update(viewStatsCommand: IViewStatsCommand): Observable<EntityResponseType> {
    return this.http.put<IViewStatsCommand>(
      `${this.resourceUrl}/${this.getViewStatsCommandIdentifier(viewStatsCommand)}`,
      viewStatsCommand,
      { observe: 'response' }
    );
  }

  partialUpdate(viewStatsCommand: PartialUpdateViewStatsCommand): Observable<EntityResponseType> {
    return this.http.patch<IViewStatsCommand>(
      `${this.resourceUrl}/${this.getViewStatsCommandIdentifier(viewStatsCommand)}`,
      viewStatsCommand,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IViewStatsCommand>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IViewStatsCommand[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getViewStatsCommandIdentifier(viewStatsCommand: Pick<IViewStatsCommand, 'id'>): number {
    return viewStatsCommand.id;
  }

  compareViewStatsCommand(o1: Pick<IViewStatsCommand, 'id'> | null, o2: Pick<IViewStatsCommand, 'id'> | null): boolean {
    return o1 && o2 ? this.getViewStatsCommandIdentifier(o1) === this.getViewStatsCommandIdentifier(o2) : o1 === o2;
  }

  addViewStatsCommandToCollectionIfMissing<Type extends Pick<IViewStatsCommand, 'id'>>(
    viewStatsCommandCollection: Type[],
    ...viewStatsCommandsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const viewStatsCommands: Type[] = viewStatsCommandsToCheck.filter(isPresent);
    if (viewStatsCommands.length > 0) {
      const viewStatsCommandCollectionIdentifiers = viewStatsCommandCollection.map(
        viewStatsCommandItem => this.getViewStatsCommandIdentifier(viewStatsCommandItem)!
      );
      const viewStatsCommandsToAdd = viewStatsCommands.filter(viewStatsCommandItem => {
        const viewStatsCommandIdentifier = this.getViewStatsCommandIdentifier(viewStatsCommandItem);
        if (viewStatsCommandCollectionIdentifiers.includes(viewStatsCommandIdentifier)) {
          return false;
        }
        viewStatsCommandCollectionIdentifiers.push(viewStatsCommandIdentifier);
        return true;
      });
      return [...viewStatsCommandsToAdd, ...viewStatsCommandCollection];
    }
    return viewStatsCommandCollection;
  }
}
