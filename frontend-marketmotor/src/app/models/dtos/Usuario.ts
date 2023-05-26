import { Rol } from "./Rol";

export class Usuario{


    id:number;
    alias:string;
    contrasena:string;
    actualizadoEn: Date ;
    creadoEn: Date ;
    estado: boolean;
    rol:Rol;

 constructor(){
    this.id=0;
    this.alias="";
    this.contrasena = "";
    this.actualizadoEn= new Date();
    this.creadoEn=new Date();
    this.estado=true;
    this.rol=new Rol();
 }
}