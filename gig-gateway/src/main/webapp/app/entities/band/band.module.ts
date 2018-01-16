import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GiggatewaySharedModule } from '../../shared';
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
        GiggatewaySharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
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
export class GiggatewayBandModule {}
