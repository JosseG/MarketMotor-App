
    import { Cliente } from "./Cliente";
    import { Empleado } from "./Empleado";
    export class Venta{
        id:number;
        precioTotal:number;
        actualizadoEn:Date;
        creadoEn:Date;
        estado:boolean;
        clienteDto:Cliente;
        empleadoDto:Empleado;

        constructor(){
            this.id=0;
            this.precioTotal=0.0;
            this.actualizadoEn=new Date();
            this.creadoEn=new Date();
            this.estado=true;
            this.clienteDto= new Cliente();
            this.empleadoDto= new Empleado();
        }
    }