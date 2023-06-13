import { Injectable } from '@angular/core';
import { CarritoItem } from 'src/app/models/temporal/CarritoItem';
import { ProductoService } from '../producto/producto.service';
import { Observable, lastValueFrom, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarritoService {

  constructor(private element: ProductoService) { }

  getCarItems(): Observable<[CarritoItem]> {

    var carritoFinal = []
    var elementos = sessionStorage.getItem("carrito");
    if(elementos!=null){
      carritoFinal = JSON.parse(elementos!)

    }
    console.log("Es obtajdfksfdljskajf")
    console.log(carritoFinal)
    return of(carritoFinal);

  }


  addToCarItemsOrden(id: string, cantidad: number) {

    var elementos = sessionStorage.getItem("carrito")
    if (elementos == null) {
      console.log("Es nulo elemnetos")
      var listaEl: CarritoItem[] = []

      var carritoItem = new CarritoItem()

       this.element.getProductoId(parseInt(id)).subscribe({
        next: (data: any) => {
          carritoItem.producto = data
          carritoItem.cantidad = cantidad
          listaEl.push(carritoItem)
    
          var toJsonCarrito = JSON.stringify(listaEl)
          console.log("Hola " + toJsonCarrito)
          sessionStorage.setItem("carrito", toJsonCarrito)
        },
        error: (e) => console.log(e)


       });




      /*this.element.getProductoId(parseInt(id)).subscribe({
        next: (data: any) => {
          carritoItem.producto = data;
          carritoItem.cantidad = cantidad;
          console.log("Eta es la data")
          console.log(data);
          listaEl.push(carritoItem)

          var toJsonCarrito = JSON.stringify(listaEl)
          console.log("Hola " + toJsonCarrito)
          sessionStorage.setItem("carrito", toJsonCarrito)
        },
        error: (e) =>
          console.log("Error " + e)
      });*/

    } else {
      var listaTodoElementos: CarritoItem[] = JSON.parse(elementos)

      var indice = this.obtenerIndiceOrden(parseInt(id))

      if (indice == -1) {
        var carritoItem = new CarritoItem()

         this.element.getProductoId(parseInt(id)).subscribe({
          next: (data: any) => {
            carritoItem.producto = data;
            carritoItem.cantidad = cantidad;
            console.log("Lo que obtiene de la bd con el codigo " + id)
            console.log(data);
            listaTodoElementos.push(carritoItem);
            var toJsonCarrito = JSON.stringify(listaTodoElementos)
            sessionStorage.setItem("carrito", toJsonCarrito)
          },
          error: (e) =>
            console.log("Error " + e)
        });
      } else {
        listaTodoElementos[indice].cantidad += cantidad;
        var toJsonCarrito = JSON.stringify(listaTodoElementos)
        sessionStorage.setItem("carrito", toJsonCarrito)
      }
    }

    console.log("Termino de insertar")


  }


  borraritemOrden(id: number){
    var carritoobt = sessionStorage.getItem("carrito")

    var listaTodoElementos: CarritoItem[] = JSON.parse(carritoobt!)
    listaTodoElementos.splice(this.obtenerIndiceOrden(id),1)

    var toJsonCarrito = JSON.stringify(listaTodoElementos)
    sessionStorage.setItem("carrito", toJsonCarrito)

  }


  obtenerIndiceOrden(id: number): number {

    var carritoobt = sessionStorage.getItem("carrito")
    var listaTodoElementos: CarritoItem[] = JSON.parse(carritoobt!)

    for (var index in listaTodoElementos) {
      if (listaTodoElementos[index].producto.id == id) {
        console.log("Es es el indice que capturo " + index)
        return parseInt(index)
      }
    }



    return -1
  }



  ////////////////////////////

  getCarItemsVenta(): Observable<[CarritoItem]> {

    var carritoFinal = []
    var elementos = sessionStorage.getItem("carritoVenta");
    if(elementos!=null){
      carritoFinal = JSON.parse(elementos!)

    }
    return of(carritoFinal);

  }


  addToCarItemsVenta(id: string, cantidad: number) {

    var elementos = sessionStorage.getItem("carritoVenta")
    if (elementos == null) {
      console.log("Es nulo elemnetos")
      var listaEl: CarritoItem[] = []

      var carritoItem = new CarritoItem()

       this.element.getProductoId(parseInt(id)).subscribe({
        next: (data: any) => {
          carritoItem.producto = data
          carritoItem.cantidad = cantidad
          listaEl.push(carritoItem)
    
          var toJsonCarrito = JSON.stringify(listaEl)
          console.log("Hola " + toJsonCarrito)
          sessionStorage.setItem("carritoVenta", toJsonCarrito)
        },
        error: (e) => console.log(e)


       });




      /*this.element.getProductoId(parseInt(id)).subscribe({
        next: (data: any) => {
          carritoItem.producto = data;
          carritoItem.cantidad = cantidad;
          console.log("Eta es la data")
          console.log(data);
          listaEl.push(carritoItem)

          var toJsonCarrito = JSON.stringify(listaEl)
          console.log("Hola " + toJsonCarrito)
          sessionStorage.setItem("carrito", toJsonCarrito)
        },
        error: (e) =>
          console.log("Error " + e)
      });*/

    } else {
      var listaTodoElementos: CarritoItem[] = JSON.parse(elementos)

      var indice = this.obtenerIndiceVenta(parseInt(id))

      if (indice == -1) {
        var carritoItem = new CarritoItem()

         this.element.getProductoId(parseInt(id)).subscribe({
          next: (data: any) => {
            carritoItem.producto = data;
            carritoItem.cantidad = cantidad;
            console.log("Lo que obtiene de la bd con el codigo " + id)
            console.log(data);
            listaTodoElementos.push(carritoItem);
            var toJsonCarrito = JSON.stringify(listaTodoElementos)
            sessionStorage.setItem("carritoVenta", toJsonCarrito)
          },
          error: (e) =>
            console.log("Error " + e)
        });
      } else {
        listaTodoElementos[indice].cantidad += cantidad;
        var toJsonCarrito = JSON.stringify(listaTodoElementos)
        sessionStorage.setItem("carritoVenta", toJsonCarrito)
      }
    }

    console.log("Termino de insertar")


  }


  borraritemVenta(id: number){
    var carritoobt = sessionStorage.getItem("carritoVenta")

    var listaTodoElementos: CarritoItem[] = JSON.parse(carritoobt!)
    listaTodoElementos.splice(this.obtenerIndiceVenta(id),1)

    var toJsonCarrito = JSON.stringify(listaTodoElementos)
    sessionStorage.setItem("carritoVenta", toJsonCarrito)

  }


  obtenerIndiceVenta(id: number): number {

    var carritoobt = sessionStorage.getItem("carritoVenta")
    var listaTodoElementos: CarritoItem[] = JSON.parse(carritoobt!)

    for (var index in listaTodoElementos) {
      if (listaTodoElementos[index].producto.id == id) {
        console.log("Es es el indice que capturo " + index)
        return parseInt(index)
      }
    }



    return -1
  }


  cleanCarritoVenta(){
    sessionStorage.removeItem("carritoVenta");
  }
  cleanCarritoOrden(){
    sessionStorage.removeItem("carrito");
  }

}
