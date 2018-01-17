import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { BandComponent } from './band.component';
import { BandDetailComponent } from './band-detail.component';
import { BandPopupComponent } from './band-dialog.component';
import { BandDeletePopupComponent } from './band-delete-dialog.component';

@Injectable()
export class BandResolvePagingParams implements Resolve<any> {

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

export const bandRoute: Routes = [
    {
        path: 'band',
        component: BandComponent,
        resolve: {
            'pagingParams': BandResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.band.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'band/:id',
        component: BandDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.band.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const bandPopupRoute: Routes = [
    {
        path: 'band-new',
        component: BandPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.band.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'band/:id/edit',
        component: BandPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.band.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'band/:id/delete',
        component: BandDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.band.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
