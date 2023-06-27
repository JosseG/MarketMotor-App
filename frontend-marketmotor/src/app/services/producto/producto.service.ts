import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProductoInsert } from 'src/app/models/commands/producto/ProductoInsert';
import { Producto } from 'src/app/models/dtos/Producto';
import baserUrl from '../globalurl/UrlApi';


@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  constructor(private http:HttpClient) { }
  url = baserUrl+"/productos";

  getProductos(){
    return this.http.get<Producto[]>(this.url);
  }

  createProducto(producto: ProductoInsert){
    return this.http.post<ProductoInsert>(this.url,producto);
  }

  getProductoId(id: number){
    return this.http.get<Producto>(this.url+'/'+id);
  }

  updateProducto(producto: Producto){
    return this.http.put<Producto>(this.url,producto);
  }

  deleteProducto(producto: Producto){
    return this.http.delete<Producto>(this.url+"/"+producto.id);
  }

  getAllByPaginable(pageNo: number = 0,pageSize: number = 10, sortBy: string= "id",sortDir: string="asc"){
    return this.http.get(`${this.url}/pagination`,{
      params: new HttpParams().set('pageNo', pageNo).set('pageSize',pageSize)
    })
  }

  getAllFiltredPaginable(descripcion: string,pageNo: number = 0,pageSize: number = 10, sortBy: string= "id",sortDir: string="asc"){
    return this.http.get(`${this.url}/pagination/`+descripcion,{
      params: new HttpParams().set('pageNo', pageNo).set('pageSize',pageSize)
    })
  }


}
