import { BaseEntity } from './../../shared';

export class Gig implements BaseEntity {
    constructor(
        public id?: number,
        public startDate?: any,
        public name?: string,
        public isCancelled?: boolean,
        public description?: string,
        public ticketPrice?: number,
        public stageName?: string,
    ) {
        this.isCancelled = false;
    }
}
