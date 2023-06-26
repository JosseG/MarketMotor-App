import { Component, EventEmitter, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, NgForm } from '@angular/forms';
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
import { genUniqueId } from 'src/app/_shared/serialid/GenerateSerial';
//import genUniqueId from 'src/app/_shared/serialid/GenerateSerial';

@Component({
  selector: 'app-generarordencompra',
  templateUrl: './generarordencompra.component.html',
  styleUrls: ['./generarordencompra.component.css'],
})
export class GenerarordencompraComponent implements OnInit {


  @ViewChild('formDirective')
  private formDirective!: NgForm;

  currentPage = 1;
  total = 0;
  itemsPerPage = 4;

  isSearching = false;
  isSearchingProveedor = false;

  productoToQuantity = new Producto();

  formSearchProduct: FormGroup = this.formbuilder.group({
    descripcion: [],
  });

  formAddingCartProduct: FormGroup = this.formbuilder.group({
    cantidad: [],
  });

  formSearchProveedor: FormGroup = this.formbuilder.group({
    id: [],
  });

  formAddingProveedor: FormGroup = this.formbuilder.group({
    nombreRuc: [],
  });

  formOrdenCompra: FormGroup = this.formbuilder.group({
    numero: [genUniqueId()],
    fecha: [new Date()],
    valorTotal: [0.0],
    idEmpleado: [1],
    idProveedor: [1],
  });

  formProveedor: FormGroup = this.formbuilder.group({
    numeroRuc: [],
    nombreComercial: [],
    razonSocial: [],
  });

  proveedores: Proveedor[] = [];
  myproveedor: Proveedor = new Proveedor();
  empleado: Empleado = new Empleado();

  proveedorSearched: Proveedor = new Proveedor();

  proveedorToForm = new Proveedor();

  constructor(
    private router: Router,
    private proveedorService: ProveedorService,
    private empleadoService: EmpleadoService,
    private carritoService: CarritoService,
    private ordenCompraService: OrdencompraService,
    private productoService: ProductoService,
    private formbuilder: FormBuilder,
    private detalleOrdenCompraService: DetalleordencompraService
  ) { }

  element$: Observable<[CarritoItem]> = new Observable();

  ngOnInit(): void {
    //this.serial = genUniqueId
    this.getProveedorForSearch();
    if (this.isActiveOrden()) {
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

  async addToCart(id: number) {
    const values = this.formAddingCartProduct.value.cantidad;
    console.log('captured');
    console.log(id);
    await this.carritoService.addToCarItemsOrden(String(id), parseInt(values));
    this.getCartProducts();
  }

  getPaginableProductos() {
    this.productoService
      .getAllByPaginable(this.currentPage, this.itemsPerPage)
      .subscribe({
        next: (data: any) => {
          this.productosPaginable = data;
          this.total = this.productosPaginable.totalElements;
          this.productos = this.productosPaginable.content;
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
          this.productos = this.productosPaginable.content;
          console.log('se llego a filtrar ');
          this.isSearching = true;
          console.log(data);
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

  addProveedorToForm(proveedor: Proveedor) {
    const values = this.formAddingProveedor.value.descripcion;
    console.log(proveedor.id);
    this.proveedorService.setProveedorToStorage(proveedor);
    this.proveedorSearched = this.getProveedorForSearch();
  }

  getCartProducts() {
    this.element$ = this.carritoService.getCarItems();
  }

  deleteItemProducto(id: number) {
    this.carritoService.borraritemOrden(id);
    this.getCartProducts();
  }

  clear() {
    this.isSearching = false;
    this.formSearchProduct.get('descripcion')?.reset();
    this.getPaginableProductos();
  }

  productoToSetQuantity(producto: Producto) {
    console.log(producto.id);
    this.productoToQuantity = producto;
  }

  clearProveedorSearched() {
    this.isSearchingProveedor = false;
    this.formSearchProveedor.get('id')?.reset();
  }

  isActiveOrden(): boolean {
    return this.ordenCompraService.isActiveOrden();
  }

  setActiveOrden(): void {
    this.getCartProducts();
    this.getEmpleadoFromSess();
    this.getProveedorForSearch();
    return this.ordenCompraService.setActiveOrden();
  }

  setInactiveOrden(): void {
    this.cleanOrdenCompra();
    this.ordenCompraService.setInactiveOrden();
  }

  getEmpleadoFromSess() {
    this.empleado = this.empleadoService.getEmpleadoFromSession();
  }



  getProveedor() {
    const values = this.formSearchProveedor.value.id;
    console.log(typeof values);

    this.proveedorService.getProveedorId(values).subscribe({
      next: (data: Proveedor) => {
        if (data.id != 0) {
          this.proveedores = [data];
        }

        this.isSearchingProveedor = true;

        console.log(typeof data);
      },
      error: (e) => console.log('Error ' + e),
    });
  }

  getProveedorForSearch(): Proveedor {
    var proveedorFinal = new Proveedor();
    var proveedorstorage = sessionStorage.getItem('proveedorTemporal');
    if (proveedorstorage != null) {
      proveedorFinal = JSON.parse(proveedorstorage!);
      this.formProveedor.patchValue(proveedorFinal);
    }
    return proveedorFinal;
  }

  proveedorToGetId(proveedor: Proveedor) {
    console.log(proveedor.id);
    this.proveedorToForm = proveedor;
  }

  registrarOrdenCompra() {
    this.element$.subscribe({
      next: (data: [CarritoItem]) => {
        if (data.length > 0) {
          var total = 0;

          for (let productoFromCart of data) {
            total +=
              productoFromCart.cantidad * productoFromCart.producto.precio;
          }

          var valores = this.formOrdenCompra.value;
          valores.valorTotal = total;

          if (total > 0) {
            this.ordenCompraService.guardarOrdenCompra(valores).subscribe({
              next: (ordenCompra: any) => {
                for (let productoFromCart of data) {
                  var newObject: any = new Object();
                  newObject.cantidad = productoFromCart.cantidad;
                  newObject.precioUnitario = productoFromCart.producto.precio;
                  newObject.idProducto = productoFromCart.producto.id;
                  newObject.idOrdenCompra = ordenCompra.id;

                  this.detalleOrdenCompraService
                    .guardarDetalleOrdenCompra(newObject)
                    .subscribe({
                      next: (detalle) => {
                        this.cleanOrdenCompra();
                        console.log(detalle);
                      },
                      error: (e) => {
                        console.log(e);
                      },
                    });
                }
              },
              error: (e) => {
                console.log(e);
              },
            });
          } else {
            alert('el total es 0');
          }
        } else {
          alert('No puedes realizar');
        }
      },
    });
  }

  cleanOrdenCompra() {
    this.carritoService.cleanCarritoOrden();
    this.proveedorService.cleanProveedorService();
    this.formAddingProveedor.reset();
    this.formDirective.resetForm();
    this.formProveedor.reset();
    this.getCartProducts();
  }
}
