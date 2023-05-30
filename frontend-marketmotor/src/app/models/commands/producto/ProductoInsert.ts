export class ProductoInsert{
    descripcion:string;
    tipo:string;
    serial:string;
    marca:string;
    precio:number;

   constructor(){
      this.descripcion="";
      this.tipo = "";
      this.serial="";
      this.marca="";
      this.precio=0;
   }
}