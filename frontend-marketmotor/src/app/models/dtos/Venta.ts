
    import { Cliente } from "./Cliente";
    import { Empleado } from "./Empleado";
    export class Venta{
        id:number;
        preciototal:number;
        actualizadoEn:Date;
        creadoEn:Date;
        estado:boolean;
        cliente:Cliente;
        empleado:Empleado;

        constructor(){
            this.id=0;
            this.preciototal=0.0;
            this.actualizadoEn=new Date();
            this.creadoEn=new Date();
            this.estado=true;
            this.cliente= new Cliente();
            this.empleado= new Empleado();
        }
    }