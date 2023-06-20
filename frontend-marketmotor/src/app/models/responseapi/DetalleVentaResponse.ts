import { DetalleOrdenCompra } from "../dtos/DetalleOrdenCompra";
import { DetalleVenta } from "../dtos/DetalleVenta";

export class DetalleVentaResponse{

    content: DetalleVenta[];
    pageNo: number;
    pageSize: number;
    totalElements: number;
    totalPages: number;
    last: boolean;

    constructor(){
        this.content  = [new DetalleVenta()]
        this.pageNo = 0;
        this.pageSize = 0;
        this.totalElements = 0;
        this.totalPages = 0;
        this.last = false;
    }

}