import { Usuario } from "./Usuario";

export class Proveedor{


    id:number;
    razonSocial:string;
    nombreComercial:string;
    numeroRuc:string;
    correo:string;
    direccion:string;
    departamento:string;
    telefonoProveedor:string;
    actualizadoEn: Date ;
    creadoEn: Date ;
    estado: boolean;
    usuario: Usuario;

 constructor(){
    this.id=0;
    this.razonSocial="";
    this.nombreComercial = "";
    this.numeroRuc="";
    this.correo="";
    this.direccion = "";
    this.departamento="";
    this.telefonoProveedor="";
    this.actualizadoEn= new Date();
    this.creadoEn=new Date();
    this.estado=true;
    this.usuario = new Usuario();
 }
}