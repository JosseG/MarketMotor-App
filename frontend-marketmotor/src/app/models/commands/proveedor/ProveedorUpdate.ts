export class ProveedorUpdate{
    id:number;
    razonSocial:string;
    nombreComercial:string;
    numeroRuc: string ;
    correo: string ;
    direccion: string;
    departamento: string;
    telefonoProveedor: string;
  

    constructor(){
        this.id=0;
        this.razonSocial="";
        this.nombreComercial = "";
        this.numeroRuc= "";
        this.correo="";
        this.direccion="";
        this.departamento= "";
        this.telefonoProveedor="";
        
    }


}