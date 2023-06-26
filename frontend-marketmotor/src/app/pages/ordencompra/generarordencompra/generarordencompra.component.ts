import { Component, EventEmitter, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { Empleado } from 'src/app/models/dtos/Empleado';
import { Producto } from 'src/app/models/dtos/Producto';
import { Proveedor } from 'src/app/models/dtos/Proveedor';
import { productoResponse } from 'src/app/models/responseapi/ProductoResponse';
import { CarritoItem } from 'src/app/models/temporal/CarritoItem';
import { CarritoService } from 'src/app/services/carrito/carrito.service';
import { EmpleadoService } from 'src/app/services/empleado/empleado.service';
import { OrdencompraService } from 'src/app/services/ordencompra/ordencompra.service';
import { ProductoService } from 'src/app/services/producto/producto.service';
import { ProveedorService } from '../../../services/proveedor/proveedor.service';
import { DetalleordencompraService } from 'src/app/services/detalleordencompra/detalleordencompra.service';

@Component({
  selector: 'app-generarordencompra',
  templateUrl: './generarordencompra.component.html',
  styleUrls: ['./generarordencompra.component.css']
})
export class GenerarordencompraComponent implements OnInit {
  currentPage = 1;
  total = 0;
  itemsPerPage = 4;

  isSearching = false;
  isSearchingProveedor = false;

  productoToQuantity = new Producto()



  formSearchProduct: FormGroup = this.formbuilder.group({
    descripcion: [],
  })

  formAddingCartProduct: FormGroup = this.formbuilder.group({
    cantidad: [],
  })


  formSearchProveedor: FormGroup = this.formbuilder.group({
    id:[],
  })

  formAddingProveedor: FormGroup = this.formbuilder.group({
    nombreRuc: [],
  })


  formOrdenCompra: FormGroup = this.formbuilder.group({
    numero: [1],
    fecha: [new Date()],
    valorTotal: [0.0],
    idEmpleado: [1],
    idProveedor: [1]
  })

  proveedores: Proveedor[] = [];
  proveedor: Proveedor = new Proveedor();
  empleado: Empleado = new Empleado();

  proveedorSearched: Proveedor = new Proveedor();


  proveedorToForm = new Proveedor()



  constructor(private router:Router,private proveedorService:ProveedorService,private empleadoService:EmpleadoService, private carritoService: CarritoService, private ordenCompraService: OrdencompraService, private productoService: ProductoService, private formbuilder: FormBuilder, private detalleOrdenCompraService: DetalleordencompraService) {

  }
  ngOnInit(): void {
    this.getProveedorForSearch();
    if(this.isActiveOrden()){
      this.getCartProducts();
      this.getEmpleadoFromSess();
    }
    this.getPaginableProductos();

  }

  productos: Producto[] = [];
  productosFromCartWith: [CarritoItem] = [new CarritoItem()];

  //productosFromCart$ = new EventEmitter<[CarritoItem]>()

  productosPaginable: productoResponse = new productoResponse();

  /*onLoad(): void {
    this.carritoService.addToCarItems("1", 5)
    console.log("llego")
  }*/

  addToCart(id: number) {

    const values = this.formAddingCartProduct.value.cantidad
    console.log("captured")
    console.log(id)
    ;/*(await this.carritoService.addToCarItems(String(id), parseInt(values))).subscribe({
      next: (data) => {
        console.log(data)
        this.getCartProducts()
      }
    })*/
    this.carritoService.addToCarItemsOrden(String(id),parseInt(values))
    this.getCartProducts()
    
    this.router.navigate(["productos"])

   //this.pushingProductos()
  }


  getPaginableProductos() {
    this.productoService.getAllByPaginable(this.currentPage, this.itemsPerPage).subscribe({
      next: (data: any) => {
        this.productosPaginable = data;
        this.total = this.productosPaginable.totalElements
        this.productos = this.productosPaginable.content
        //console.log(data);
      },
      error: (e) =>
        console.log("Error " + e)

    });
  }

  getPaginableProductosFiltred() {

    const values = this.formSearchProduct.value.descripcion
    console.log(values)

    this.productoService.getAllFiltredPaginable(values, this.currentPage, this.itemsPerPage).subscribe({
      next: (data: any) => {
        this.productosPaginable = data;
        this.total = this.productosPaginable.totalElements
        this.productos = this.productosPaginable.content
        console.log("se llego a filtrar ")
        this.isSearching = true;
        console.log(data);
      },
      error: (e) =>
        console.log("Error " + e)
    });
  }

  pageChangeEvent(event: number) {
    this.currentPage = event;

    if (!this.isSearching) {
      this.getPaginableProductos();
    }

  }
  /*pushingProductos() {
    this.productosFromCart$.emit(this.carritoService.getCarItems())
    console.log("Obtiene del carro en genero")
    console.log(this.carritoService.getCarItems())
  }*/

  addProveedorToForm(proveedor: Proveedor) {

    const values = this.formAddingProveedor.value.descripcion;
    console.log(proveedor.id);
    this.proveedorService.setProveedorToStorage(proveedor);
    this.proveedorSearched = this.getProveedorForSearch();
  }

  getCartProducts() {
    //this.productosFromCart$ = of(this.carritoService.getCarItems())
    this.carritoService.getCarItems().subscribe({
      next: (data : any) => {
        this.productosFromCartWith = data
        console.log("implementando observables")
        console.log(data)
      }
    });
    //this.productosFromCartWith = this.carritoService.getCarItems()
  }

  deleteItemProducto(id: number){
    this.carritoService.borraritemOrden(id)
    this.getCartProducts()
  }


  clear() {
    this.isSearching = false;
    this.formSearchProduct.get("descripcion")?.reset()
    this.getPaginableProductos()
  }

  productoToSetQuantity(producto: Producto) {
    console.log(producto.id)
    this.productoToQuantity = producto
  }



  clearProveedorSearched(){
    this.isSearchingProveedor = false;
    this.formSearchProveedor.get("id")?.reset()
  }



  isActiveOrden() : boolean{
    return this.ordenCompraService.isActiveOrden();
  }

  setActiveOrden(): void{
    this.getCartProducts();
    this.getEmpleadoFromSess();
    return this.ordenCompraService.setActiveOrden();
  }

  setInactiveOrden(): void{
    this.carritoService.cleanCarritoOrden();
    return this.ordenCompraService.setInactiveOrden()
  }


  getEmpleadoFromSess(){
    this.empleado = this.empleadoService.getEmpleadoFromSession()
  }

  submit(){

  }

  getProveedor(){
    const values = this.formSearchProveedor.value.id
    console.log(typeof(values))

    this.proveedorService.getProveedorId(values).subscribe({
      next: (data: Proveedor) => {
        if(data.id!=0){
          this.proveedores = [data]
        }

        this.isSearchingProveedor = true;

        console.log(typeof(data));
      },
      error: (e) =>
        console.log("Error " + e)
    });
  }



  getProveedorForSearch(): Proveedor {

    var proveedorFinal = new Proveedor()
    var proveedorstorage = sessionStorage.getItem("proveedorTemporal");
    if(proveedorstorage!=null){
      proveedorFinal = JSON.parse(proveedorstorage!)
      this.proveedor = proveedorFinal
    }
    return proveedorFinal

  }


  proveedorToGetId(proveedor: Proveedor) {
    console.log(proveedor.id)
    this.proveedorToForm = proveedor
  }












  registrarOrdenCompra(){

    if(this.productosFromCartWith.length>0){

      var total = 0;

      for(let productoFromCart of this.productosFromCartWith){
        total += productoFromCart.cantidad * productoFromCart.producto.precio
      }

      var valores = this.formOrdenCompra.value
      valores.valorTotal=total

      if(total>0){
        this.ordenCompraService.guardarOrdenCompra(valores).subscribe({
          next: (ordenCompra: any) => {
            for(let productoFromCart of this.productosFromCartWith){

              var newObject: any = new Object()
              newObject.cantidad = productoFromCart.cantidad;
              newObject.precioUnitario = productoFromCart.producto.precio;
              newObject.idProducto = productoFromCart.producto.id;
              newObject.idOrdenCompra = ordenCompra.id;

              this.detalleOrdenCompraService.guardarDetalleOrdenCompra(newObject).subscribe({
                next: (detalle) => {
                  console.log(detalle)
                },
                error: (e) => {
                  console.log(e)
                }
              })
            }
  
          },
          error: (e) => {
            console.log(e);
          }
        })
      }else{
        alert("el total es 0")
      }
      
    }else{
      alert("No puedes realizar")
    }
    
  }





}
