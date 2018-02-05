import { BaseEntity } from './../../shared';

export class Gig implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public isCancelled?: boolean,
        public ticketPrice?: number,
        public startDate?: any,
        public startTime?: string,
    ) {
        this.isCancelled = false;
    }
}
