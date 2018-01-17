import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaySharedModule } from '../../shared';
import {
    BandService,
    BandPopupService,
    BandComponent,
    BandDetailComponent,
    BandDialogComponent,
    BandPopupComponent,
    BandDeletePopupComponent,
    BandDeleteDialogComponent,
    bandRoute,
    bandPopupRoute,
    BandResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...bandRoute,
    ...bandPopupRoute,
];

@NgModule({
    imports: [
        GatewaySharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        BandComponent,
        BandDetailComponent,
        BandDialogComponent,
        BandDeleteDialogComponent,
        BandPopupComponent,
        BandDeletePopupComponent,
    ],
    entryComponents: [
        BandComponent,
        BandDialogComponent,
        BandPopupComponent,
        BandDeleteDialogComponent,
        BandDeletePopupComponent,
    ],
    providers: [
        BandService,
        BandPopupService,
        BandResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayBandModule {}
