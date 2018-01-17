/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';

import { GatewayTestModule } from '../../../test.module';
import { GigDetailComponent } from '../../../../../../main/webapp/app/entities/gig/gig-detail.component';
import { GigService } from '../../../../../../main/webapp/app/entities/gig/gig.service';
import { Gig } from '../../../../../../main/webapp/app/entities/gig/gig.model';

describe('Component Tests', () => {

    describe('Gig Management Detail Component', () => {
        let comp: GigDetailComponent;
        let fixture: ComponentFixture<GigDetailComponent>;
        let service: GigService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [GigDetailComponent],
                providers: [
                    GigService
                ]
            })
            .overrideTemplate(GigDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(GigDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GigService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new Gig(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.gig).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
