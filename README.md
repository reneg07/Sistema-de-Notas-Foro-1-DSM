# 📱 Sistema de Notas Académicas con Jetpack Compose

## 🧩 Descripción General

Este proyecto consiste en el desarrollo de una aplicación móvil Android creada con **Kotlin** y **Jetpack Compose**, cuyo objetivo principal es permitir el **inicio de sesión y registro de usuarios**, el **ingreso de notas de estudiantes**, el **cálculo automático del promedio** y la **determinación del estado final de aprobación o reprobación**.

El proyecto forma parte del **Foro 1 de la asignatura Desarrollo de Software para Móviles (DSM941)**, enfocado en la investigación y aplicación práctica de **Jetpack Compose** como herramienta moderna para el desarrollo de interfaces gráficas declarativas en Android. El flujo implementado contempla cuatro pantallas principales: Login/Registro, Bienvenida, Ingreso de Notas y Resultado, siguiendo una estructura progresiva y organizada .

---

## 🎯 Objetivos

### Objetivo General

Investigar y aplicar los fundamentos de **Jetpack Compose** en el desarrollo de una aplicación móvil en **Android Studio con Kotlin**, que permita el inicio de sesión, registro de usuarios, ingreso de notas, cálculo automático de promedios y visualización del resultado académico final del estudiante.

### Objetivos Específicos

1. **Analizar** las características, ventajas y funcionamiento de **Jetpack Compose** como herramienta moderna para el diseño de interfaces en Android.
2. **Diseñar e implementar** una aplicación móvil funcional con login, registro, bienvenida e ingreso de notas, utilizando **Jetpack Compose** y aplicando buenas prácticas de programación en Kotlin.
3. **Evaluar** las ventajas prácticas de Jetpack Compose frente al desarrollo tradicional basado en XML, destacando su eficiencia, reactividad y facilidad de mantenimiento.

---

## ⚙️ Requerimientos Funcionales

### 1. Pantalla de Login / Registro

* Campos de texto para correo y contraseña.
* Validación de campos vacíos.
* Validación básica de formato de correo.
* Registro de nuevos usuarios.
* Confirmación de contraseña.
* Navegación controlada hacia la pantalla de bienvenida.

### 2. Pantalla de Bienvenida

* Muestra el nombre del usuario autenticado.
* El nombre se obtiene automáticamente desde el correo electrónico registrado.
* Botón para acceder al módulo de ingreso de notas.

### 3. Pantalla de Ingreso de Notas

* Mínimo tres campos numéricos para ingresar calificaciones.
* Restricción para aceptar únicamente valores numéricos.
* Validación de rango permitido entre 0 y 10.
* Restricción de hasta 2 decimales por nota.
* Botón para calcular promedio.

### 4. Pantalla de Resultado

* Visualización del promedio final.
* Resultado limitado a 3 decimales.
* Determinación automática de:

  * **Aprobado** si el promedio es mayor o igual a 6
  * **Reprobado** si el promedio es menor a 6
* Opción para regresar al módulo de notas.

---

## 🖥️ Tecnologías Utilizadas

* **Lenguaje de programación:** Kotlin
* **Framework UI:** Jetpack Compose
* **IDE:** Android Studio
* **Arquitectura:** Declarativa con manejo de estado (`remember`, `mutableStateOf`)
* **Diseño visual:** Material Design 3
* **Control de versiones:** Git + GitHub

Jetpack Compose permite una UI reactiva donde la interfaz se actualiza automáticamente según el estado de los datos, reduciendo considerablemente el uso de XML y facilitando el mantenimiento del proyecto .

---

## 🔄 Flujo de Navegación

```text
Login / Registro
↓
Pantalla de Bienvenida
↓
Ingreso de Notas
↓
Resultado Final
```

La aplicación fue diseñada siguiendo el principio de **separación de responsabilidades**, donde cada pantalla cumple una función específica y bien delimitada, facilitando tanto la experiencia del usuario como la mantenibilidad del sistema .

Además, se implementaron validaciones desde el inicio para evitar errores de captura de datos y mejorar la confiabilidad del sistema.

---

## 🧠 Conclusiones Generales

Jetpack Compose representa una evolución importante en el desarrollo de aplicaciones Android. Su enfoque declarativo permite construir interfaces más limpias, modernas y fáciles de mantener.

Durante el desarrollo del proyecto se evidenció que:

* Se reduce significativamente la cantidad de código.
* La actualización automática del estado mejora la experiencia de usuario.
* La reutilización de componentes hace el proyecto más escalable.
* La integración con Kotlin mejora la legibilidad y productividad.
* El desarrollo resulta más rápido comparado con XML tradicional.

Aunque existe una curva de aprendizaje inicial, sus beneficios superan ampliamente las limitaciones iniciales, consolidándolo como una de las mejores opciones para el desarrollo móvil moderno .

---

## 📎 Entregables

### 📄 Documento de Investigación

https://drive.google.com/file/d/1H0bNJEO-Rxrs9Adb_q2RCpp66vwbcKrm/view?usp=sharing

### 🎥 Video de Defensa

https://youtu.be/n8LKoX3xkmE

---

## 👥 Integrantes

* Ronald Alexander Martínez Gutiérrez — MG223061
* Katherine Paola Pineda Rodríguez — PR232427
* René Francisco Guevara Alfaro — GA202826
* Karina Lisbeth Ángel Quezada — AQ161844



---

## 📚 Referencias Base

* Google. Documentación oficial de Jetpack Compose
* Android Developers
* Philipp Lackner
* MoureDev by Brais Moure
* Platzi
* AppMaster Blog

