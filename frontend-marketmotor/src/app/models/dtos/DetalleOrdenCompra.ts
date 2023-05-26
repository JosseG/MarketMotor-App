import { OrdenCompra } from "./OrdenCompra";
import { Producto } from "./Producto";

export class DetalleOrdenCompra{


    id:number;
    cantidad:number;
    precioUnitario:number;
    actualizadoEn: Date ;
    creadoEn: Date ;
    estado: boolean;
    producto: Producto;
    ordenCompra: OrdenCompra;



 constructor(){
    this.id=0;
    this.cantidad=0;
    this.precioUnitario = 0.0;
    this.actualizadoEn= new Date();
    this.creadoEn=new Date();
    this.estado=true;
    this.producto=new Producto();
    this.ordenCompra = new OrdenCompra();
 }
}