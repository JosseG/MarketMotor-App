import { Producto } from "./Producto";
import { Venta } from "./Venta";

export class DetalleVenta{

    id:number;
    unidades:number;
    actualizadoEn:Date;
    creadoEn:Date;
    producto:Producto;
    venta:Venta;
    estado:boolean;

    constructor(){
        this.id=0;
        this.unidades=0;
        this.actualizadoEn= new Date();
        this.creadoEn= new Date();
        this.producto= new Producto();
        this.venta= new Venta();
        this.estado = true;
    }
}

