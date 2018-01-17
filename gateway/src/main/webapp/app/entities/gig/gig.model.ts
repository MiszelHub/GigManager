import { BaseEntity } from './../../shared';

export class Gig implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public startDate?: any,
        public isCancelled?: boolean,
        public ticketPrice?: number,
    ) {
        this.isCancelled = false;
    }
}
