import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Producto } from 'src/app/models/dtos/Producto';


@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  constructor(private http:HttpClient) { }
  url = 'http://localhost:8080/productos';

  getProductos(){
    return this.http.get<Producto[]>(this.url);
  }

  createProducto(producto: Producto){
    return this.http.post<Producto>(this.url,producto);
  }

  getProductoId(id: number){
    return this.http.get<Producto>(this.url+'/'+id);
  }

  updateProducto(id: number, producto: Producto){
    return this.http.put<Producto>(this.url,producto);
  }

  deleteProducto(producto: Producto){
    return this.http.delete<Producto>(this.url+"/"+producto.id);
  }
}
