import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { GigComponent } from './gig.component';
import { GigDetailComponent } from './gig-detail.component';
import { GigPopupComponent } from './gig-dialog.component';
import { GigDeletePopupComponent } from './gig-delete-dialog.component';

@Injectable()
export class GigResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const gigRoute: Routes = [
    {
        path: 'gig',
        component: GigComponent,
        resolve: {
            'pagingParams': GigResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'giggatewayApp.gig.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'gig/:id',
        component: GigDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'giggatewayApp.gig.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const gigPopupRoute: Routes = [
    {
        path: 'gig-new',
        component: GigPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'giggatewayApp.gig.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'gig/:id/edit',
        component: GigPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'giggatewayApp.gig.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'gig/:id/delete',
        component: GigDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'giggatewayApp.gig.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
