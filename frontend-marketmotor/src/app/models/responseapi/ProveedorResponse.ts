import { Proveedor } from "../dtos/Proveedor";

export class ProveedorResponse{

    content: Proveedor[];
    pageNo: number;
    pageSize: number;
    totalElements: number;
    totalPages: number;
    last: boolean;

    constructor(){
        this.content  = [new Proveedor()]
        this.pageNo = 0;
        this.pageSize = 0;
        this.totalElements = 0;
        this.totalPages = 0;
        this.last = false;
    }

}