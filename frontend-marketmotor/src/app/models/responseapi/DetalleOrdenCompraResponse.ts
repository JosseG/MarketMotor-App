import { DetalleOrdenCompra } from "../dtos/DetalleOrdenCompra";

export class DetalleOrdenCompraResponse{

    content: DetalleOrdenCompra[];
    pageNo: number;
    pageSize: number;
    totalElements: number;
    totalPages: number;
    last: boolean;

    constructor(){
        this.content  = [new DetalleOrdenCompra()]
        this.pageNo = 0;
        this.pageSize = 0;
        this.totalElements = 0;
        this.totalPages = 0;
        this.last = false;
    }

}