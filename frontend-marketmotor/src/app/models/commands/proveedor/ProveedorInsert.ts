export class ProveedorInsert{

    razonSocial:string;
    nombreComercial:string;
    numeroRuc: string ;
    correo: string ;
    direccion: string;
    departamento: string;
    telefonoProveedor: string;
    idUsuario: number;

    constructor(){
        this.razonSocial="";
        this.nombreComercial = "";
        this.numeroRuc= "";
        this.correo="";
        this.direccion="";
        this.departamento= "";
        this.telefonoProveedor="";
        this.idUsuario=0;
    }


}