import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Suplier } from '../domain/suplier';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SuplierService {

  private urlCreateSuplier: string =`http://127.0.0.1:8085/inventoried-services/v1/suplier/save`
  private urlEditSuplier: string =`http://127.0.0.1:8085/inventoried-services/v1/suplier/update`
  private urlSuplierAll: string =`http://127.0.0.1:8085/inventoried-services/v1/suplier/getSuplierAll` 

  constructor(private httpClient: HttpClient) {}

   /**
 * metodo get de httpclient que nos devuelve informacion por medio de la api
 */
   createSuplier(suplier: Suplier): Observable<Suplier>{

    return this.httpClient.post<Suplier>(this.urlCreateSuplier, suplier )
  }

  /**
 * metodo get de httpclient que nos devuelve informacion por medio de la api
 */
  editSuplier(suplier: Suplier): Observable<Suplier>{

    return this.httpClient.put<Suplier>(this.urlEditSuplier, suplier)
  }

  /**
 * metodo get de httpclient que nos devuelve informacion por medio de la api
 */
  getSuplierAll(): Observable<Suplier[]>{

    return this.httpClient.get<Suplier[]>(this.urlSuplierAll )
  }
}
