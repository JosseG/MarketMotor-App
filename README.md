# MarketMotor-App

Con este proyecto se busca agilizar los procesos de venta, gestión de inventario, compra, entre otros; del centro de venta de autopartes MarketMotor.
Fue presentado como proyecto de ciclo del curso de Desarrollo de Servicios Web 2 en el Instituto CIBERTEC 2023

## Requisitos
- Java 11 +
- MySQL
- Angular 12 + 
- Node JS
- Postman


## Pasos para ejecución.
* Clonar el proyecto.
    > git clone ***url***
### Backend

1. Crear la base de datos **marketmotordb**.
2. Abrir el proyecto **product-service** (***Se encuentra dentro de marketmotormicroservices***) utilizando el editor de código de su preferencia y configurar su enlace de conexión con la base de datos en el archivo properties de ***product-service***.

3. Ejecutar el proyecto.
4. **IMPORTAR** y ejecutar en el siguiente orden cada request de la colección de Postman adherida a este proyecto. (***SOLO LA PRIMERA VEZ QUE SE EJECUTA EL PROYECTO***)
    <br>
    En orden :
    >4.1. **Roles en bloque** en la carpeta ***Roles***
    
    >4.2. **Usuarios en bloque** en la carpeta ***Usuarios***
    
    >4.3. **Empleados en bloque** en la carpeta ***Empleados***
    
    >4.4. **Productos en bloque** en la carpeta ***Empleados***
    
    >4.5. **Cliente** en la carpeta ***Clientes***
    
    >4.6. **Proveedores** en la carpeta ***Proveedores***

### Frontend
1. Abrir la carpeta **frontend-marketmotor** en su editor de código favorito.
2. Ubicarse la carpeta frontend en la terminal y cargar las dependencias con el siguiente script.
    > npm install
3. Ejecutar el proyecto.
    > ng serve --open
4. Interactuar.
    > url: **localhost:4200**
    > Usuario: **johns87** <br>
    > Contraseña: **admin**