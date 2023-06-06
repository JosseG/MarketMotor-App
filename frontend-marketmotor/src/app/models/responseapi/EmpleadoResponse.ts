import { Empleado } from "../dtos/Empleado";

export class EmpleadoResponse{

    content: Empleado[];
    pageNo: number;
    pageSize: number;
    totalElements: number;
    totalPages: number;
    last: boolean;

    constructor(){
        this.content  = [new Empleado()]
        this.pageNo = 0;
        this.pageSize = 0;
        this.totalElements = 0;
        this.totalPages = 0;
        this.last = false;
    }

}
