import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GiggatewaySharedModule } from '../../shared';
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
        GiggatewaySharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
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
export class GiggatewayGigModule {}
