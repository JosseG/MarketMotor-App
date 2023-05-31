import { Empleado } from "./Empleado";
import { Usuario } from "./Usuario";

export class EmpleadoFull{

    empleado : Empleado;
    usuario : Usuario;

    constructor(){
        this.empleado = new Empleado();
        this.usuario = new Usuario();
    }


}