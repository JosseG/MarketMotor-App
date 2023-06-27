import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baserUrl from '../globalurl/UrlApi';

@Injectable({
  providedIn: 'root'
})
export class DetalleventaService {

  private readonly apiUrl = baserUrl+"/detalleventa";

  constructor(private http: HttpClient) {  }

  getAllByPaginable(pageNo: number = 0,pageSize: number = 10, sortBy: string= "id",sortDir: string="asc"){
    return this.http.get(`${this.apiUrl}/pagination`,{
      params: new HttpParams().set('pageNo', pageNo).set('pageSize',pageSize)
    })
  }


  guardarDetalleVenta(detalleVenta: any){
    return this.http.post(this.apiUrl, detalleVenta);
  }


}
