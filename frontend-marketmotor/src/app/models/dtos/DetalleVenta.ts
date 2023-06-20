import { Producto } from "./Producto";
import { Venta } from "./Venta";

export class DetalleVenta{

    id:number;
    unidades:number;
    actualizadoEn:Date;
    creadoEn:Date;
    productoDto:Producto;
    ventaDto:Venta;
    estado:boolean;

    constructor(){
        this.id=0;
        this.unidades=0;
        this.actualizadoEn= new Date();
        this.creadoEn= new Date();
        this.productoDto= new Producto();
        this.ventaDto= new Venta();
        this.estado = true;
    }
}

