import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { GiggatewayGigModule } from './gig/gig.module';
import { GiggatewayBandModule } from './band/band.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        GiggatewayGigModule,
        GiggatewayBandModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GiggatewayEntityModule {}
