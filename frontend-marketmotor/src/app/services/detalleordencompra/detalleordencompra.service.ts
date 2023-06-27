import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baserUrl from '../globalurl/UrlApi';

@Injectable({
  providedIn: 'root'
})
export class DetalleordencompraService {


  private readonly apiUrl = baserUrl+"/detalleordencompra";

  constructor(private http: HttpClient) {  }

  getAllByPaginable(pageNo: number = 0,pageSize: number = 10, sortBy: string= "id",sortDir: string="asc"){
    return this.http.get(`${this.apiUrl}/pagination`,{
      params: new HttpParams().set('pageNo', pageNo).set('pageSize',pageSize)
    })
  }


  getAllByOrdeCompraIdPaginable(id : number,pageNo: number = 0,pageSize: number = 10, sortBy: string= "id",sortDir: string="asc"){
    return this.http.get(`${this.apiUrl}/ordencompra/pagination/${id}`,{
      params: new HttpParams().set('pageNo', pageNo).set('pageSize',pageSize)
    })
  }


  getAllByEmpleadoIdPaginable(id : number,pageNo: number = 0,pageSize: number = 10, sortBy: string= "id",sortDir: string="asc"){
    return this.http.get(`${this.apiUrl}/empleado/pagination/${id}`,{
      params: new HttpParams().set('pageNo', pageNo).set('pageSize',pageSize)
    })
  }


  getAllByProductoIdPaginable(id : number,pageNo: number = 0,pageSize: number = 10, sortBy: string= "id",sortDir: string="asc"){
    return this.http.get(`${this.apiUrl}/producto/pagination/${id}`,{
      params: new HttpParams().set('pageNo', pageNo).set('pageSize',pageSize)
    })
  }


  guardarDetalleOrdenCompra(detalleOrdenCompra: any){
    return this.http.post(this.apiUrl, detalleOrdenCompra);
  }
}
