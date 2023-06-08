import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Cliente } from 'src/app/models/dtos/Cliente';
import { Producto } from 'src/app/models/dtos/Producto';
import { productoResponse } from 'src/app/models/responseapi/ProductoResponse';
import { CarritoItem } from 'src/app/models/temporal/CarritoItem';
import { CarritoService } from 'src/app/services/carrito/carrito.service';
import { ProductoService } from 'src/app/services/producto/producto.service';
import { VentaService } from 'src/app/services/venta/venta.service';
import { ClienteService } from '../../../services/cliente/cliente.service';

@Component({
  selector: 'app-generarventa',
  templateUrl: './generarventa.component.html',
  styleUrls: ['./generarventa.component.css']
})
export class GenerarventaComponent {
  currentPage = 1;
  total = 0;
  itemsPerPage = 4;

  isSearching = false;
  isSearchingCliente = false;

  productoToQuantity = new Producto()

  clienteToForm = new Cliente()



  formSearchProduct: FormGroup = this.formbuilder.group({
    descripcion: [],
  })

  formAddingCartProduct: FormGroup = this.formbuilder.group({
    cantidad: [],
  })



  formSearchCliente: FormGroup = this.formbuilder.group({
    id:[],
  })


  formAddingCliente: FormGroup = this.formbuilder.group({
    dni: [],
  })

  constructor(private router:Router,private carritoService: CarritoService, private clienteService: ClienteService,private ventaService: VentaService, private productoService: ProductoService, private formbuilder: FormBuilder) {

  }
  ngOnInit(): void {

    this.getCartProductsVenta();
    this.getPaginableProductos();

  }

  productos: Producto[] = [];
  clientes: Cliente[] = [];
  mycliente: Cliente = new Cliente();
  productosFromCartWith: [CarritoItem] = [new CarritoItem()];
  clienteForSearch: Cliente = new Cliente();


  productosPaginable: productoResponse = new productoResponse();
  


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
    this.carritoService.addToCarItemsVenta(String(id),parseInt(values))
    this.getCartProductsVenta()
    
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


  getCliente(){
    const values = this.formSearchCliente.value.id
    console.log(typeof(values))

    this.clienteService.getClienteId(values).subscribe({
      next: (data: Cliente) => {
        this.clientes.push(data)
        this.isSearchingCliente = true;

        console.log(typeof(data));
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

  getCartProductsVenta() {
    this.carritoService.getCarItemsVenta().subscribe({
      next: (data : any) => {
        this.productosFromCartWith = data
        console.log("implementando observables")
        console.log(data)
      }
    });
  }

  deleteItemProducto(id: number){
    this.carritoService.borraritemVenta(id)
    this.getCartProductsVenta()
  }


  clear() {
    this.isSearching = false;
    this.formSearchProduct.get("descripcion")?.reset()
    this.getPaginableProductos()
  }

  clearClienteSearched(){
    this.isSearchingCliente = false;
    this.formSearchCliente.get("id")?.reset()
  }

  productoToSetQuantity(producto: Producto) {
    console.log(producto.id)
    this.productoToQuantity = producto
  }

  


  isActiveVenta() : boolean{
    return this.ventaService.isActiveVenta();
  }

  setActiveVenta(): void{
    
    this.getCartProductsVenta();
    return this.ventaService.setActiveVenta();
  }

  setInactiveVenta(): void{
    this.carritoService.cleanCarritoVenta();
    return this.ventaService.setInactiveVenta()
  }


  ///////




  clienteToGetDNI(cliente: Cliente) {
    console.log(cliente.id)
    this.clienteToForm = cliente
  }


  addToForm(id: number) {

    const values = this.formAddingCliente.value.descripcion
    console.log(id)
    this.ventaService.setCliente(id)
    this.clienteForSearch = this.getClienteForSearch()
  }



  getClienteForSearch(): Cliente {

    var clienteFinal = new Cliente()
    var clientestorage = sessionStorage.getItem("clienteTemporal");
    if(clientestorage!=null){
      clienteFinal = JSON.parse(clientestorage!)

    }
    return clienteFinal

  }


}
