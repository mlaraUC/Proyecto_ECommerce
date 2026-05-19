# Proyec## Funcionalidades principales

### Autenticación y gestión de usuarios
- **Inicio de sesión** - Validación de credenciales mediante clase `Auth`.
- **Registro de usuarios** - Formulario de registro con almacenamiento en archivo persistente.
- **Menú de usuario** - Interfaz con toggle para mostrar opciones de usuario.
- **Cierre de sesión** - Opción para cerrar sesión y volver a la pantalla de inicio.

### Panel de administración (Admin)
- **Dashboard** - Vista principal del panel administrativo (`AdminController` y `admin.fxml`).
- **Carga dinámica de vistas** - Sistema de carga de vistas FXML dentro del panel (`contentPane`).

### Tienda (Shop)
- **Catálogo de productos** - Visualización de productos en grid (`ShopController` y `shop.fxml`).
- **Tarjetas de producto** - Componentes interactivos que muestran imagen, categoría, nombre, precio y botón "Add to Cart".
- **Persistencia de productos** - Los productos se cargan/guardan en archivo (`ProductoFileManager`).
- **Datos de ejemplo** - Si no hay productos guardados, se crean 6 productos de ejemplo (audífonos, reloj, sofá, tenis, cámara, anillo).
- **Interactividad visual** - Efecto hover en tarjetas de productos con sombra y bordes dinámicos.

### Modelos y persistencia
- **Modelo Producto** - Clase `Producto` con atributos: nombre, categoría, precio e imagen.
- **Modelo Usuario** - Clase `Usuario` para representar datos de usuario.
- **Persistencia en archivo** - Clases `ProductoFileManager` y `UsuarioFileManager` para guardar/cargar datos en archivos `.dat`.

### Tecnología JavaFX
- Interfaz de usuario desarrollada completamente con **JavaFX** y archivos FXML.
- Componentes utilizados: `TilePane` para grillas de productos, `VBox/HBox` para layouts, `ImageView` para imágenes, `Label` y `Button` para controles.
- Estilos CSS inline para personalización visual.
- Carga de recursos desde classpath (`/images/products/`, `/proyecto_ecommerce/`).

Estas funcionalidades son la base para ampliar el proyecto con features como carrito de compras, búsqueda/filtrado de productos, integración con base de datos, pagos, etc.erce

Pequeña aplicación de ejemplo para un sistema de comercio electrónico, desarrollada en Java con FXML (NetBeans/JavaFX). Este repositorio contiene el código fuente, recursos FXML y clases compiladas en la carpeta `build`.

## Descripción

El proyecto implementa la estructura básica de un front-end de escritorio para un e-commerce: pantallas definidas en FXML, controladores Java para la lógica de interfaz y una clase de modelo simple para usuarios. Está pensado como un prototipo funcional que sigue el diseño visual definido en un prototipo de Figma.

## Funcionalidades principales

- Inicio de sesión / autenticación básica (clase `Auth`).
- Interfaz principal del proyecto (`ProyectoController` y `proyecto.fxml`).
- Panel de administración con vistas FXML (`admin.fxml`) y controlador (`AdminController`).
- Modelo de usuario simple (`model/Usuario.java`) para representar datos de usuario.

Estas funcionalidades son la base para ampliar el proyecto con features como gestión de productos, carrito, etc.

## Diseño y prototipo

El proyecto sigue el diseño de un prototipo creado en Figma.

Figma prototype: https://www.figma.com/design/faQjNM8rjgUz6IJk6c36dn/Zenvy-Shop--E-commerce-?node-id=0-1&t=8kkPvoXaQY611HcF-1

## Estructura del repositorio

- `src/` - código fuente Java y archivos FXML.
	- `controller/` - controladores Java (`AdminController.java`, `Auth.java`, `ProyectoController.java`).
	- `model/` - modelos de datos (`Usuario.java`).
	- `proyecto_ecommerce/` - recursos FXML empaquetados y clase principal (`Proyecto_ECommerce.java`).
- `build/` - artefactos compilados por NetBeans/Ant.
- `nbproject/` - configuración del proyecto para NetBeans.
- `build.xml` - script Ant para compilar/limpiar/ejecutar el proyecto.

## Cómo ejecutar

Opciones recomendadas:

- Abrir el proyecto en NetBeans y ejecutar desde el IDE (recomendado si usas NetBeans).
- Compilar y ejecutar con Ant (requiere tener Ant instalado):

```powershell
ant clean; ant jar
# o para ejecutar (según target disponible en build.xml):
ant run
```
