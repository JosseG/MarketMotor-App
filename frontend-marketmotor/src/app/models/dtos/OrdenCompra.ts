import { Empleado } from "./Empleado";
import { Proveedor } from './Proveedor';

export class OrdenCompra{
    id:number;
    confirmado: boolean;
    numero:number;
    fecha:Date;
    valorTotal:number;
    actualizadoEn: Date ;
    creadoEn: Date ;
    estado: boolean;
    empleadoDto: Empleado;
    proveedorDto: Proveedor;

    constructor(){
        this.id=0;
        this.confirmado=false;
        this.numero = 0;
        this.fecha=new Date();
        this.valorTotal = 0.0;
        this.actualizadoEn= new Date();
        this.creadoEn=new Date();
        this.estado=true;
        this.empleadoDto=new Empleado();
        this.proveedorDto = new Proveedor();
     }
}