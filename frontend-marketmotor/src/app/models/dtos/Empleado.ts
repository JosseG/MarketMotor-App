import { Usuario } from "./Usuario";

export class Empleado{


    id:number;
    nombre:string;
    apellidoPaterno:string;
    apellidoMaterno: string ;
    telefono: string ;
    correo: string;
    estado: boolean;
    actualizadoEn: Date;
    creadoEn: Date;
    usuarioDto: Usuario;

 constructor(){
    this.id=0;
    this.nombre="";
    this.apellidoPaterno = "";
    this.apellidoMaterno= "";
    this.telefono="";
    this.correo="";
    this.estado=true;
    this.actualizadoEn = new Date();
    this.creadoEn = new Date();
    this.usuarioDto = new Usuario();
 }
}