# Proyecto_ECommerce

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
