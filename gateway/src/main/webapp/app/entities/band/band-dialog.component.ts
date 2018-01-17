import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Band } from './band.model';
import { BandPopupService } from './band-popup.service';
import { BandService } from './band.service';

@Component({
    selector: 'jhi-band-dialog',
    templateUrl: './band-dialog.component.html'
})
export class BandDialogComponent implements OnInit {

    band: Band;
    isSaving: boolean;
    dateOfFormationDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private bandService: BandService,
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
        if (this.band.id !== undefined) {
            this.subscribeToSaveResponse(
                this.bandService.update(this.band));
        } else {
            this.subscribeToSaveResponse(
                this.bandService.create(this.band));
        }
    }

    private subscribeToSaveResponse(result: Observable<Band>) {
        result.subscribe((res: Band) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Band) {
        this.eventManager.broadcast({ name: 'bandListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-band-popup',
    template: ''
})
export class BandPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private bandPopupService: BandPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.bandPopupService
                    .open(BandDialogComponent as Component, params['id']);
            } else {
                this.bandPopupService
                    .open(BandDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
