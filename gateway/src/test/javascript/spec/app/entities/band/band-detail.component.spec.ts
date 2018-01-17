/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';

import { GatewayTestModule } from '../../../test.module';
import { BandDetailComponent } from '../../../../../../main/webapp/app/entities/band/band-detail.component';
import { BandService } from '../../../../../../main/webapp/app/entities/band/band.service';
import { Band } from '../../../../../../main/webapp/app/entities/band/band.model';

describe('Component Tests', () => {

    describe('Band Management Detail Component', () => {
        let comp: BandDetailComponent;
        let fixture: ComponentFixture<BandDetailComponent>;
        let service: BandService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [BandDetailComponent],
                providers: [
                    BandService
                ]
            })
            .overrideTemplate(BandDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BandDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BandService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new Band(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.band).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
