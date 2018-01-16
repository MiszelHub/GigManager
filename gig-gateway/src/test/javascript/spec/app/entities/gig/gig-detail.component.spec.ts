/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { GiggatewayTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
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
                imports: [GiggatewayTestModule],
                declarations: [GigDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    GigService,
                    JhiEventManager
                ]
            }).overrideTemplate(GigDetailComponent, '')
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

            spyOn(service, 'find').and.returnValue(Observable.of(new Gig(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.gig).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
