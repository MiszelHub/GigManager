/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';

import { GatewayTestModule } from '../../../test.module';
import { BandComponent } from '../../../../../../main/webapp/app/entities/band/band.component';
import { BandService } from '../../../../../../main/webapp/app/entities/band/band.service';
import { Band } from '../../../../../../main/webapp/app/entities/band/band.model';

describe('Component Tests', () => {

    describe('Band Management Component', () => {
        let comp: BandComponent;
        let fixture: ComponentFixture<BandComponent>;
        let service: BandService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [BandComponent],
                providers: [
                    BandService
                ]
            })
            .overrideTemplate(BandComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BandComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BandService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new Band(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.bands[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
