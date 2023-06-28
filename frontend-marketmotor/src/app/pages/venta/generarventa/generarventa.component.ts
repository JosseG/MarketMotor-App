import { Component, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
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
import { Observable } from 'rxjs';
import { DetalleVentaCommandInsert } from 'src/app/models/commands/detalleventa/DetalleVentaCommandInsert';

@Component({
  selector: 'app-generarventa',
  templateUrl: './generarventa.component.html',
  styleUrls: ['./generarventa.component.css'],
})
export class GenerarventaComponent {
  @ViewChild('formDirective')
  private formDirective!: NgForm;

  element$: Observable<[CarritoItem]> = new Observable();
  currentPage = 1;
  total = 0;
  itemsPerPage = 4;

  isSearching = false;
  isSearchingCliente = false;
  empleado: Empleado = new Empleado();

  productoToQuantity = new Producto();

  clienteToForm = new Cliente();

  //producto form
  formSearchProduct: FormGroup = this.formbuilder.group({
    descripcion: [],
  });

  formAddingCartProduct: FormGroup = this.formbuilder.group({
    cantidad: [0,Validators.compose([Validators.required, Validators.min(1)])]
  });

  //Cliente Form
  formSearchCliente: FormGroup = this.formbuilder.group({
    id: [],
  });

  formAddingCliente: FormGroup = this.formbuilder.group({
    dni: [],
  });

  formCliente: FormGroup = this.formbuilder.group({
    dni: ['',Validators.required],
    nombre: ['',Validators.required],
    apellido: ['',Validators.required],
  });

  //GENERAR VENTA
  formVenta: FormGroup = this.formbuilder.group({
    preciototal: [0.0],
    idCliente: [0],
    idEmpleado: [0],
  });

  //GENERAR VENTA
  formDetalleVenta: FormGroup = this.formbuilder.group({
    unidades: [0],
    idProducto: [0],
    idVenta: [0],
  });

  constructor(
    private router: Router,
    private empleadoService: EmpleadoService,
    private carritoService: CarritoService,
    private clienteService: ClienteService,
    private ventaService: VentaService,
    private productoService: ProductoService,
    private formbuilder: FormBuilder
  ) { }
  ngOnInit(): void {
    this.getClienteForSearch();
    if (this.isActiveVenta()) {
      this.getEmpleadoFromSess();
      this.getCartProductsVenta();
    }
    this.getPaginableProductos();
  }

  productos: Producto[] = [];
  clientes: Cliente[] = [];
  mycliente: Cliente = new Cliente();
  productosFromCartWith: CarritoItem[] = [new CarritoItem()];
  clienteSearched: Cliente = new Cliente();

  productosPaginable: productoResponse = new productoResponse();

  async addToCart(id: number) {
    if(this.formAddingCartProduct.valid && this.productoToQuantity.id>0){
      const values = this.formAddingCartProduct.value.cantidad;
      console.log('captured');
      console.log(id);
  
      await this.carritoService.addToCarItemsVenta(String(id), parseInt(values));
      this.getCartProductsVenta();
      this.formAddingCartProduct.reset();
      this.productoToQuantity = new Producto();
    }else{
      this.getCartProductsVenta();
    }

  }

  getPaginableProductos() {
    this.productoService
      .getAllByPaginable(this.currentPage, this.itemsPerPage)
      .subscribe({
        next: (data: any) => {
          this.productosPaginable = data;
          this.total = this.productosPaginable.totalElements;
          //this.productos = this.productosPaginable.content;
          this.productos = this.productosPaginable.content.filter(
            (e) => e.estado == true && e.stock > 10
          );
        },
        error: (e) => console.log('Error ' + e),
      });
  }

  getPaginableProductosFiltred() {
    const values = this.formSearchProduct.value.descripcion;
    console.log(values);

    this.productoService
      .getAllFiltredPaginable(values, this.currentPage, this.itemsPerPage)
      .subscribe({
        next: (data: any) => {
          this.productosPaginable = data;
          this.total = this.productosPaginable.totalElements;
          this.productos = this.productosPaginable.content.filter(
            (e) => e.estado == true && e.stock > 10
          );
          //this.productos.filter((e) => e.estado==true)
          console.log('se llego a filtrar ');
          this.isSearching = true;
          console.log(data);
        },
        error: (e) => console.log('Error ' + e),
      });
  }

  getCliente() {
    const values = this.formSearchCliente.value.id;
    console.log(typeof values);

    this.clienteService.getClienteId(values).subscribe({
      next: (data: Cliente) => {
        if (data.id != 0) {
          this.clientes = [data];
        }

        this.isSearchingCliente = true;

        console.log(typeof data);
      },
      error: (e) => console.log('Error ' + e),
    });
  }

  pageChangeEvent(event: number) {
    this.currentPage = event;

    if (!this.isSearching) {
      this.getPaginableProductos();
    }
  }

  getCartProductsVenta() {
    this.element$ = this.carritoService.getCarItemsVenta();
  }

  deleteItemProducto(id: number) {
    this.carritoService.borraritemVenta(id);
    this.getCartProductsVenta();
  }

  clear() {
    this.isSearching = false;
    this.formSearchProduct.get('descripcion')?.reset();
    this.getPaginableProductos();
  }

  clearClienteSearched() {
    this.isSearchingCliente = false;
    this.formSearchCliente.get('id')?.reset();
  }

  productoToSetQuantity(producto: Producto) {
    console.log(producto.id);
    this.productoToQuantity = producto;
  }

  isActiveVenta(): boolean {
    return this.ventaService.isActiveVenta();
  }

  setActiveVenta(): void {
    this.getEmpleadoFromSess();
    this.getCartProductsVenta();
    this.getClienteForSearch();
    return this.ventaService.setActiveVenta();
  }

  setInactiveVenta(): void {
    this.cleanVenta();
    this.ventaService.setInactiveVenta();
  }

  clienteToGetDNI(cliente: Cliente) {
    console.log(cliente.id);
    this.clienteToForm = cliente;
  }

  addClienteToForm(cliente: Cliente) {
    this.clienteService.setClienteToStorage(cliente);
    this.clienteSearched = this.getClienteForSearch();
  }

  getClienteForSearch(): Cliente {
    var clienteFinal = new Cliente();
    var clientestorage = sessionStorage.getItem('clienteTemporal');
    if (clientestorage != null) {
      clienteFinal = JSON.parse(clientestorage!);
      this.mycliente = clienteFinal;
      this.formCliente.patchValue(clienteFinal);
    }
    return clienteFinal;
  }

  getEmpleadoFromSess() {
    this.empleado = this.empleadoService.getEmpleadoFromSession();
  }

  //GUARDAR O GENERAR VENTA
  //VERIFICAR ANTES QUE EL PRECIO SEA MAYOR A 0
  //VERIFICAR LISTA DE CARRITO QUE NO ESTÉ VACÍA
  //LUEGO DE REALIZAR LA INSERCIÓN DE VENTA, GESTIONAR EL DETALLEVENTA

  async registrarVenta() {
    if(this.formCliente.valid){
      this.element$.subscribe({
        next: async (detalles: [CarritoItem]) => {
          if (detalles.length > 0) {
            var total = 0;
            var object : DetalleVentaCommandInsert[]= [];
            for (let productoFromCart of detalles) {
              
              total +=
                productoFromCart.cantidad * productoFromCart.producto.precio;
            }
            for(let i = 0; i < detalles.length; i++){
              var detalleVenta = new DetalleVentaCommandInsert();
              detalleVenta.unidades = detalles[i].cantidad
              detalleVenta.idProducto = detalles[i].producto.id
              object.push(detalleVenta);
            }
  
            var venta = this.formVenta.value;
            venta.preciototal = total;
            venta.idEmpleado = this.empleado.id;
            venta.idCliente = this.mycliente.id;
  
  
            if (total > 0) {
              await this.ventaService
                .realizarVentaTransaccion(venta, object)
                .subscribe({
                  next: (data:boolean) => {
                    if(data==true){
                      this.cleanVenta();
                      alert("Venta realizada con exito")
                    }else{
                      alert("Venta sin éxito, solucione la gestión de productos y su stock")
                    }
                  },
                  error: () => {
                    alert("Ocurrio un error inesperado")
                  }
                });
            } else {
              alert('El total es 0');
            }
          } else {
            alert('No puedes realizar la venta');
          }
        },
      });
    }

  }

  cleanVenta() {
    this.carritoService.cleanCarritoVenta();
    this.clienteService.cleanClienteVenta();
    this.mycliente = new Cliente();
    this.formAddingCliente.reset();
    this.formDirective.resetForm();
    this.formCliente.reset();
    this.getCartProductsVenta();
  }
}
