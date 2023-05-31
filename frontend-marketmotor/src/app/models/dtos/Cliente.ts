export class Cliente{
    id:number;
    dni:string;
    nombre:string;
    apellido: string ;
    estado: boolean;
    actualizadoEn: Date;
    creadoEn: Date;

 constructor(){
    this.id=0;
    this.nombre="";
    this.dni = "";
    this.apellido= "";
    this.estado=true;
    this.actualizadoEn = new Date();
    this.creadoEn = new Date();
 }
}