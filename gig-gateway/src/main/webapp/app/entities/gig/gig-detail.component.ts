import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Gig } from './gig.model';
import { GigService } from './gig.service';

@Component({
    selector: 'jhi-gig-detail',
    templateUrl: './gig-detail.component.html'
})
export class GigDetailComponent implements OnInit, OnDestroy {

    gig: Gig;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private gigService: GigService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInGigs();
    }

    load(id) {
        this.gigService.find(id).subscribe((gig) => {
            this.gig = gig;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInGigs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'gigListModification',
            (response) => this.load(this.gig.id)
        );
    }
}
