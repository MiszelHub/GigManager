import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Gig } from './gig.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class GigService {

    private resourceUrl =  SERVER_API_URL + '/gigservice/api/gigs';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(gig: Gig): Observable<Gig> {
        const copy = this.convert(gig);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(gig: Gig): Observable<Gig> {
        const copy = this.convert(gig);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Gig> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to Gig.
     */
    private convertItemFromServer(json: any): Gig {
        const entity: Gig = Object.assign(new Gig(), json);
        entity.startDate = this.dateUtils
            .convertDateTimeFromServer(json.startDate);
        return entity;
    }

    /**
     * Convert a Gig to a JSON which can be sent to the server.
     */
    private convert(gig: Gig): Gig {
        const copy: Gig = Object.assign({}, gig);

        copy.startDate = this.dateUtils.toDate(gig.startDate);
        return copy;
    }
}
