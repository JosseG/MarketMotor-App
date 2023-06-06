import { Producto } from "../dtos/Producto";

export class CarritoItem {
    producto : Producto
    cantidad : number

    constructor(){
        this.producto = new Producto()
        this.cantidad = 0;
    }
}