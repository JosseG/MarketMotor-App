import { Component, EventEmitter, OnInit, ViewChild } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, NgForm, ValidationErrors, Validator, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, of, Subscription } from 'rxjs';
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
import { DetalleOrdenCompraCommandInsert } from 'src/app/models/commands/detalleordencompra/DetalleOrdenCompraCommandInsert';
import { EmailService } from 'src/app/services/email/email.service';

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
    cantidad: [0,Validators.compose([Validators.required, Validators.min(1)])]
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
    idEmpleado: [0],
    idProveedor: [0],
  });


  
  formProveedor: FormGroup = this.formbuilder.group({
    numeroRuc: ['',Validators.required],
    nombreComercial: ['',Validators.required],
    razonSocial: ['',Validators.required],
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
    private detalleOrdenCompraService: DetalleordencompraService,
    private emailService: EmailService
  ) { }

  element$: Observable<[CarritoItem]> = new Observable();

  ngOnInit(): void {
    this.getProveedorForSearch();
    if (this.isActiveOrden()) {
      this.getCartProducts();
      this.getEmpleadoFromSess();
    }
    this.getPaginableProductos();
  }

  productos: Producto[] = [];

  productosFromCartWith: CarritoItem[] = [new CarritoItem()];

  productosPaginable: productoResponse = new productoResponse();


  async addToCart(id: number) {
    if(this.formAddingCartProduct.valid && this.productoToQuantity.id>0){
      const values = this.formAddingCartProduct.value.cantidad;
      console.log('captured');
      console.log(id);
      await this.carritoService.addToCarItemsOrden(String(id), parseInt(values));
      this.getCartProducts();
      this.formAddingCartProduct.reset();
      this.productoToQuantity = new Producto()
    }else{

      this.getCartProducts();
    }
  }

  getPaginableProductos() {
    this.productoService
      .getAllByPaginable(this.currentPage, this.itemsPerPage)
      .subscribe({
        next: (data: any) => {
          this.productosPaginable = data;
          this.total = this.productosPaginable.totalElements;
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
      this.myproveedor = proveedorFinal;
      this.formProveedor.patchValue(proveedorFinal);
    }
    return proveedorFinal;
  }

  proveedorToGetId(proveedor: Proveedor) {
    console.log(proveedor.id);
    this.proveedorToForm = proveedor;
  }

  async registrarOrdenCompra() {

    if(this.formProveedor.valid){
      await this.element$.subscribe({
        next: async (data: CarritoItem[]) => {
          if (data.length > 0) {
            var total = 0;
            var object : DetalleOrdenCompraCommandInsert[]= [];
            for (let productoFromCart of data) {
              total +=
                productoFromCart.cantidad * productoFromCart.producto.precio;
            }
  
            for(let i = 0; i < data.length; i++){
              var detalleOrdenCompra = new DetalleOrdenCompraCommandInsert();
              detalleOrdenCompra.cantidad = data[i].cantidad
              detalleOrdenCompra.idProducto = data[i].producto.id
              detalleOrdenCompra.precioUnitario = data[i].producto.precio
              object.push(detalleOrdenCompra);
            }
  
            var ordencompra = this.formOrdenCompra.value;
            ordencompra.valorTotal = total;
            ordencompra.idEmpleado = this.empleado.id
            ordencompra.idProveedor = this.myproveedor.id
  
            if (total > 0) {
              this.ordenCompraService.realizarOrdenCompraTransaccion(ordencompra, object).subscribe({
                next: async (data) =>{
                  if(data==true){
                    console.log(this.myproveedor.id)
                    //await this.emailService.enviarAviso(this.myproveedor.correo,"TIENES UNA ORDEN DE COMPRA PENDIENTE","VALIDAR ORDEN COMPRA")
                    /*await this.emailService.enviarAviso(this.myproveedor.id).subscribe({
                      next:()=>{
                        
                      }
                    })*/
                    this.cleanOrdenCompra();
                    alert("Orden Compra realizada con exito")
                  }else{
                    alert("Orden Compra sin éxito, solucione la gestión de productos y su stock")
                  }
                },
                error: () => {
                  alert("Ocurrio un error inesperado")
                }
              })
  
            } else {
              alert('El total es 0');
            }
          } else {
            alert('No puedes realizar la orden de compra');
          }
        },
      });
    }


  }

  cleanOrdenCompra() {
    this.carritoService.cleanCarritoOrden();
    this.proveedorService.cleanProveedorService();
    this.formAddingProveedor.reset();
    this.myproveedor = new Proveedor();
    this.formProveedor.reset();
    this.getCartProducts();
  }
}

