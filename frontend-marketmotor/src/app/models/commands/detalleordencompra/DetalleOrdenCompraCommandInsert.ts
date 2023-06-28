export class DetalleOrdenCompraCommandInsert{


    cantidad:number;
    precioUnitario:number;
    idProducto: number;
    idOrdenCompra: number;

    constructor(){
        this.cantidad = 0;
        this.precioUnitario = 0.0;
        this.idProducto = 0;
        this.idOrdenCompra = 0;
    }
    
}