import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Gig } from './gig.model';
import { GigPopupService } from './gig-popup.service';
import { GigService } from './gig.service';

@Component({
    selector: 'jhi-gig-dialog',
    templateUrl: './gig-dialog.component.html'
})
export class GigDialogComponent implements OnInit {

    gig: Gig;
    isSaving: boolean;
    startDateDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private gigService: GigService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.gig.id !== undefined) {
            this.subscribeToSaveResponse(
                this.gigService.update(this.gig));
        } else {
            this.subscribeToSaveResponse(
                this.gigService.create(this.gig));
        }
    }

    private subscribeToSaveResponse(result: Observable<Gig>) {
        result.subscribe((res: Gig) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Gig) {
        this.eventManager.broadcast({ name: 'gigListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-gig-popup',
    template: ''
})
export class GigPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private gigPopupService: GigPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.gigPopupService
                    .open(GigDialogComponent as Component, params['id']);
            } else {
                this.gigPopupService
                    .open(GigDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
