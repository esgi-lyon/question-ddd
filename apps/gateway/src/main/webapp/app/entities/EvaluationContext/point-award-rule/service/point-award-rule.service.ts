import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IPointAwardRule, NewPointAwardRule } from '../point-award-rule.model';

export type PartialUpdatePointAwardRule = Partial<IPointAwardRule> & Pick<IPointAwardRule, 'id'>;

export type EntityResponseType = HttpResponse<IPointAwardRule>;
export type EntityArrayResponseType = HttpResponse<IPointAwardRule[]>;

@Injectable({ providedIn: 'root' })
export class PointAwardRuleService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/point-award-rules', 'evaluationcontext');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(pointAwardRule: NewPointAwardRule): Observable<EntityResponseType> {
    return this.http.post<IPointAwardRule>(this.resourceUrl, pointAwardRule, { observe: 'response' });
  }

  update(pointAwardRule: IPointAwardRule): Observable<EntityResponseType> {
    return this.http.put<IPointAwardRule>(`${this.resourceUrl}/${this.getPointAwardRuleIdentifier(pointAwardRule)}`, pointAwardRule, {
      observe: 'response',
    });
  }

  partialUpdate(pointAwardRule: PartialUpdatePointAwardRule): Observable<EntityResponseType> {
    return this.http.patch<IPointAwardRule>(`${this.resourceUrl}/${this.getPointAwardRuleIdentifier(pointAwardRule)}`, pointAwardRule, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPointAwardRule>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPointAwardRule[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getPointAwardRuleIdentifier(pointAwardRule: Pick<IPointAwardRule, 'id'>): number {
    return pointAwardRule.id;
  }

  comparePointAwardRule(o1: Pick<IPointAwardRule, 'id'> | null, o2: Pick<IPointAwardRule, 'id'> | null): boolean {
    return o1 && o2 ? this.getPointAwardRuleIdentifier(o1) === this.getPointAwardRuleIdentifier(o2) : o1 === o2;
  }

  addPointAwardRuleToCollectionIfMissing<Type extends Pick<IPointAwardRule, 'id'>>(
    pointAwardRuleCollection: Type[],
    ...pointAwardRulesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const pointAwardRules: Type[] = pointAwardRulesToCheck.filter(isPresent);
    if (pointAwardRules.length > 0) {
      const pointAwardRuleCollectionIdentifiers = pointAwardRuleCollection.map(
        pointAwardRuleItem => this.getPointAwardRuleIdentifier(pointAwardRuleItem)!
      );
      const pointAwardRulesToAdd = pointAwardRules.filter(pointAwardRuleItem => {
        const pointAwardRuleIdentifier = this.getPointAwardRuleIdentifier(pointAwardRuleItem);
        if (pointAwardRuleCollectionIdentifiers.includes(pointAwardRuleIdentifier)) {
          return false;
        }
        pointAwardRuleCollectionIdentifiers.push(pointAwardRuleIdentifier);
        return true;
      });
      return [...pointAwardRulesToAdd, ...pointAwardRuleCollection];
    }
    return pointAwardRuleCollection;
  }
}
