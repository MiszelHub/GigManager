import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Gig } from './gig.model';
import { GigService } from './gig.service';

@Injectable()
export class GigPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private gigService: GigService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.gigService.find(id).subscribe((gig) => {
                    if (gig.startDate) {
                        gig.startDate = {
                            year: gig.startDate.getFullYear(),
                            month: gig.startDate.getMonth() + 1,
                            day: gig.startDate.getDate()
                        };
                    }
                    this.ngbModalRef = this.gigModalRef(component, gig);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.gigModalRef(component, new Gig());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    gigModalRef(component: Component, gig: Gig): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.gig = gig;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
