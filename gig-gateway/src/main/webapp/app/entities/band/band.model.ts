import { BaseEntity } from './../../shared';

export class Band implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public musicGenre?: any,
        public origin?: string,
        public siteUrl?: string,
        public dateOfFormation?: any,
    ) {
    }
}
