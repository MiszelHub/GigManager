import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaySharedModule } from '../../shared';
import {
    GigService,
    GigPopupService,
    GigComponent,
    GigDetailComponent,
    GigDialogComponent,
    GigPopupComponent,
    GigDeletePopupComponent,
    GigDeleteDialogComponent,
    gigRoute,
    gigPopupRoute,
    GigResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...gigRoute,
    ...gigPopupRoute,
];

@NgModule({
    imports: [
        GatewaySharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        GigComponent,
        GigDetailComponent,
        GigDialogComponent,
        GigDeleteDialogComponent,
        GigPopupComponent,
        GigDeletePopupComponent,
    ],
    entryComponents: [
        GigComponent,
        GigDialogComponent,
        GigPopupComponent,
        GigDeleteDialogComponent,
        GigDeletePopupComponent,
    ],
    providers: [
        GigService,
        GigPopupService,
        GigResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayGigModule {}
