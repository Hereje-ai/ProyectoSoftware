import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../domain/product';
import { Category } from '../domain/category';
import { Suplier } from '../domain/suplier';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {
  private urlGetProducts: string =`http://127.0.0.1:8085/inventoried-services/v1/product/getProductAll`
  private urlCreateProduct: string =`http://127.0.0.1:8085/inventoried-services/v1/product/save`
  private urlEditProduct: string =`http://127.0.0.1:8085/inventoried-services/v1/product/update`
  private urlAllCategory: string =`http://127.0.0.1:8085/inventoried-services/v1/product/category-all` 
  private urlSuplierAll: string =`http://127.0.0.1:8085/inventoried-services/v1/suplier/getSuplierAll` 

  constructor(private httpClient: HttpClient) {}

   /**
 * metodo get de httpclient que nos devuelve informacion por medio de la api
 */
   createProduct(product: Product): Observable<Product>{

    return this.httpClient.post<Product>(this.urlCreateProduct, product )
  }

  /**
 * metodo get de httpclient que nos devuelve informacion por medio de la api
 */
  editProduct(product: Product): Observable<Product>{

    return this.httpClient.put<Product>(this.urlEditProduct, product)
  }

  /**
 * metodo get de httpclient que nos devuelve informacion por medio de la api
 */
  getProductAll(): Observable<Product[]>{

    return this.httpClient.get<Product[]>(this.urlGetProducts )
  }

  /**
 * metodo get de httpclient que nos devuelve informacion por medio de la api
 */
  getCategoryAll(): Observable<Category[]>{

    return this.httpClient.get<Category[]>(this.urlAllCategory)
  }

   /**
 * metodo get de httpclient que nos devuelve informacion por medio de la api
 */
   getSuplierAll(): Observable<Suplier[]>{

    return this.httpClient.get<Suplier[]>(this.urlSuplierAll)
  }
}
