import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IStatisticSubjectTag, NewStatisticSubjectTag } from '../statistic-subject-tag.model';

export type PartialUpdateStatisticSubjectTag = Partial<IStatisticSubjectTag> & Pick<IStatisticSubjectTag, 'id'>;

export type EntityResponseType = HttpResponse<IStatisticSubjectTag>;
export type EntityArrayResponseType = HttpResponse<IStatisticSubjectTag[]>;

@Injectable({ providedIn: 'root' })
export class StatisticSubjectTagService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/statistic-subject-tags');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IStatisticSubjectTag>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IStatisticSubjectTag[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getStatisticSubjectTagIdentifier(statisticSubjectTag: Pick<IStatisticSubjectTag, 'id'>): number {
    return statisticSubjectTag.id;
  }

  compareStatisticSubjectTag(o1: Pick<IStatisticSubjectTag, 'id'> | null, o2: Pick<IStatisticSubjectTag, 'id'> | null): boolean {
    return o1 && o2 ? this.getStatisticSubjectTagIdentifier(o1) === this.getStatisticSubjectTagIdentifier(o2) : o1 === o2;
  }

  addStatisticSubjectTagToCollectionIfMissing<Type extends Pick<IStatisticSubjectTag, 'id'>>(
    statisticSubjectTagCollection: Type[],
    ...statisticSubjectTagsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const statisticSubjectTags: Type[] = statisticSubjectTagsToCheck.filter(isPresent);
    if (statisticSubjectTags.length > 0) {
      const statisticSubjectTagCollectionIdentifiers = statisticSubjectTagCollection.map(
        statisticSubjectTagItem => this.getStatisticSubjectTagIdentifier(statisticSubjectTagItem)!
      );
      const statisticSubjectTagsToAdd = statisticSubjectTags.filter(statisticSubjectTagItem => {
        const statisticSubjectTagIdentifier = this.getStatisticSubjectTagIdentifier(statisticSubjectTagItem);
        if (statisticSubjectTagCollectionIdentifiers.includes(statisticSubjectTagIdentifier)) {
          return false;
        }
        statisticSubjectTagCollectionIdentifiers.push(statisticSubjectTagIdentifier);
        return true;
      });
      return [...statisticSubjectTagsToAdd, ...statisticSubjectTagCollection];
    }
    return statisticSubjectTagCollection;
  }
}
