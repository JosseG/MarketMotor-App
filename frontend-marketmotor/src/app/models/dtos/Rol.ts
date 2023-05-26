import { Permiso } from "./Permiso";

export class Rol{


    id:number;
    nombre:string;
    estado:boolean;
    permisos:Set<Permiso>;

 constructor(){
    this.id=0;
    this.nombre="";
    this.estado=true;
    this.permisos=new Set<Permiso>();
 }
}