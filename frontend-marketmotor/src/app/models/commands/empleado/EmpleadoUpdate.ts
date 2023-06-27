export class EmpleadoUpdate{
    id:number;
    nombre:string;
    apellidoPaterno:string;
    apellidoMaterno: string ;
    telefono: string ;
    correo: string;
    estado: boolean;

    constructor(){
        this.id=0;
        this.nombre="";
        this.apellidoPaterno = "";
        this.apellidoMaterno= "";
        this.telefono="";
        this.correo="";
        this.estado=true;
    }


}