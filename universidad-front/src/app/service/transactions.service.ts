import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Transaction } from '../domain/transaction';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransactionsService {

  private urlCreateTransaction: string =`http://127.0.0.1:8085/inventoried-services/v1/transaction/save`
  private urlEditTransaction: string =`http://127.0.0.1:8085/inventoried-services/v1/transaction/update`
  private urlTransactionAll: string =`http://127.0.0.1:8085/inventoried-services/v1/transaction/getTransactiontAll` 

  constructor(private httpClient: HttpClient) {}

   /**
 * metodo get de httpclient que nos devuelve informacion por medio de la api
 */
   createTransaction(transaction: Transaction): Observable<Transaction>{

    return this.httpClient.post<Transaction>(this.urlCreateTransaction, transaction )
  }

  /**
 * metodo get de httpclient que nos devuelve informacion por medio de la api
 */
  editTransaction(transaction: Transaction): Observable<Transaction>{

    return this.httpClient.put<Transaction>(this.urlEditTransaction, transaction)
  }

  /**
 * metodo get de httpclient que nos devuelve informacion por medio de la api
 */
  getTransactionAll(): Observable<Transaction[]>{

    return this.httpClient.get<Transaction[]>(this.urlTransactionAll )
  }
}
