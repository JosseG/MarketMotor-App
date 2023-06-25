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
import { Empleado } from 'src/app/models/dtos/Empleado';
import { EmpleadoService } from 'src/app/services/empleado/empleado.service';
import { DetalleventaService } from 'src/app/services/detalleVenta/detalleventa.service';
import { Venta } from 'src/app/models/dtos/Venta';

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
  empleado: Empleado = new Empleado()


  productoToQuantity = new Producto()

  clienteToForm = new Cliente()



  //producto form
  formSearchProduct: FormGroup = this.formbuilder.group({
    descripcion: [],
  })

  formAddingCartProduct: FormGroup = this.formbuilder.group({
    cantidad: [],
  })


  //Cliente Form
  formSearchCliente: FormGroup = this.formbuilder.group({
    id:[],
  })

  formAddingCliente: FormGroup = this.formbuilder.group({
    dni: [],
  })

  formCliente: FormGroup = this.formbuilder.group({

  })


  //GENERAR VENTA
  formVenta: FormGroup = this.formbuilder.group({
    preciototal: [0.0],
    idCliente: [1],
    idEmpleado: [1]
  })


    //GENERAR VENTA
    formDetalleVenta: FormGroup = this.formbuilder.group({
      unidades: [0],
      idProducto: [1],
      idVenta: [1]
    })


  constructor(private router:Router,private empleadoService: EmpleadoService,private carritoService: CarritoService, private clienteService: ClienteService,private ventaService: VentaService, private productoService: ProductoService, private formbuilder: FormBuilder, private detalleVentaService: DetalleventaService) {

  }
  ngOnInit(): void {


    this.getClienteForSearch();
    if(this.isActiveVenta()){
      this.getEmpleadoFromSess();
      this.getCartProductsVenta();
    }
    this.getPaginableProductos();

  }

  productos: Producto[] = [];
  clientes: Cliente[] = [];
  mycliente: Cliente = new Cliente();
  productosFromCartWith: [CarritoItem] = [new CarritoItem()];
  clienteSearched: Cliente = new Cliente();


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
        if(data.id!=0){
          this.clientes = [data]
        }

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
    this.getEmpleadoFromSess();
    this.getCartProductsVenta();
    this.getClienteForSearch();
    return this.ventaService.setActiveVenta();
  }

  setInactiveVenta(): void{
    this.carritoService.cleanCarritoVenta();
    this.clienteService.cleanClienteVenta();
    this.formAddingCliente.reset()
    this.ventaService.setInactiveVenta();
  }


  ///////




  clienteToGetDNI(cliente: Cliente) {
    console.log(cliente.id)
    this.clienteToForm = cliente
  }


  addClienteToForm(cliente: Cliente) {

    const values = this.formAddingCliente.value.descripcion
    console.log(cliente.id)
    this.clienteService.setClienteToStorage(cliente)
    this.clienteSearched = this.getClienteForSearch()
  }



  getClienteForSearch(): Cliente {

    var clienteFinal = new Cliente()
    var clientestorage = sessionStorage.getItem("clienteTemporal");
    if(clientestorage!=null){
      clienteFinal = JSON.parse(clientestorage!)
      this.mycliente = clienteFinal
    }
    return clienteFinal

  }


  getEmpleadoFromSess(){
    this.empleado = this.empleadoService.getEmpleadoFromSession()
  }


  



  //GUARDAR O GENERAR VENTA
  //VERIFICAR ANTES QUE EL PRECIO SEA MAYOR A 0
  //VERIFICAR LISTA DE CARRITO QUE NO ESTÉ VACÍA
  //LUEGO DE REALIZAR LA INSERCIÓN DE VENTA, GESTIONAR EL DETALLEVENTA


  registrarVenta(){

    if(this.productosFromCartWith.length>0){

      var total = 0;

      for(let productoFromCart of this.productosFromCartWith){
        total += productoFromCart.cantidad * productoFromCart.producto.precio
      }

      var valores = this.formVenta.value
      valores.preciototal=total

      if(total>0){
        this.ventaService.guardarVenta(valores).subscribe({
          next: (venta: any) => {
            console.log(venta);
            for(let productoFromCart of this.productosFromCartWith){

              var newObject: any = new Object()
              newObject.unidades = productoFromCart.cantidad;
              newObject.idProducto = productoFromCart.producto.id;
              newObject.idVenta = venta.id;

              this.detalleVentaService.guardarDetalleVenta(newObject).subscribe({
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
