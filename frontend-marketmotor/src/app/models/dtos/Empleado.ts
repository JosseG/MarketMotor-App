import { Usuario } from "./Usuario";

export class Empleado{


    id:number;
    nombre:string;
    apellidoPat:string;
    apellidoMat: string ;
    telefono: string ;
    correo: string;
    estado: boolean;
    actualizadoEn: Date;
    creadoEn: Date;
    usuarioDto: Usuario;

 constructor(){
    this.id=0;
    this.nombre="";
    this.apellidoPat = "";
    this.apellidoMat= "";
    this.telefono="";
    this.correo="";
    this.estado=true;
    this.actualizadoEn = new Date();
    this.creadoEn = new Date();
    this.usuarioDto = new Usuario();
 }
}