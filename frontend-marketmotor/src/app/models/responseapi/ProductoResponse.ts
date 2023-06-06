import { Producto } from "../dtos/Producto";

export class productoResponse{
    content: Producto[];
    pageNo: number;
    pageSize: number;
    totalElements: number;
    totalPages: number;
    last: boolean;

    constructor(){
        this.content  = []
        this.pageNo = 0;
        this.pageSize = 0;
        this.totalElements = 0;
        this.totalPages = 0;
        this.last = false;
    }
}