import { OrdenCompra } from "../dtos/OrdenCompra";

export class OrdenCompraResponse{
    content: OrdenCompra[];
    pageNo: number;
    pageSize: number;
    totalElements: number;
    totalPages: number;
    last: boolean;

    constructor(){
        this.content  = [new OrdenCompra()]
        this.pageNo = 0;
        this.pageSize = 0;
        this.totalElements = 0;
        this.totalPages = 0;
        this.last = false;
    }
}