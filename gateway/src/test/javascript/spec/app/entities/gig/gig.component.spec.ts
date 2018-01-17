/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';

import { GatewayTestModule } from '../../../test.module';
import { GigComponent } from '../../../../../../main/webapp/app/entities/gig/gig.component';
import { GigService } from '../../../../../../main/webapp/app/entities/gig/gig.service';
import { Gig } from '../../../../../../main/webapp/app/entities/gig/gig.model';

describe('Component Tests', () => {

    describe('Gig Management Component', () => {
        let comp: GigComponent;
        let fixture: ComponentFixture<GigComponent>;
        let service: GigService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [GigComponent],
                providers: [
                    GigService
                ]
            })
            .overrideTemplate(GigComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(GigComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GigService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new Gig(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.gigs[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
