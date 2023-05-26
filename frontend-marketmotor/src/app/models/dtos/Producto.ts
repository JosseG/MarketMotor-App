export class Producto{


    id:number;
    descripcion:string;
    tipo:string;
    serial:string;
    marca:string;
    actualizadoEn: Date ;
    creadoEn: Date ;
    estado: boolean;

 constructor(){
    this.id=0;
    this.descripcion="";
    this.tipo = "";
    this.serial="";
    this.marca="";
    this.actualizadoEn= new Date();
    this.creadoEn=new Date();
    this.estado=true;
 }
}