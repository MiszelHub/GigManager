import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Gig } from './gig.model';
import { GigPopupService } from './gig-popup.service';
import { GigService } from './gig.service';

@Component({
    selector: 'jhi-gig-delete-dialog',
    templateUrl: './gig-delete-dialog.component.html'
})
export class GigDeleteDialogComponent {

    gig: Gig;

    constructor(
        private gigService: GigService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.gigService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'gigListModification',
                content: 'Deleted an gig'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-gig-delete-popup',
    template: ''
})
export class GigDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private gigPopupService: GigPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.gigPopupService
                .open(GigDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
