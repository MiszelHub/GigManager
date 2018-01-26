import { BaseEntity } from './../../shared';

export class Band implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public origin?: string,
        public genre?: any,
        public dateOfFormation?: any,
        public bio?: string,
        public accountId?: string,
    ) {
    }
}
