# Proyecto de Ejemplo para Ingeniería Inversa

## Contenido del Proyecto

Este proyecto contiene ejemplos de los siguientes conceptos de Programación Orientada a Objetos (POO):

- **Interfaces**
- **Herencia**
- **Agregación**
- **Composición**

---

## Objetivo del Proyecto

A menudo nos encontramos con código generado por terceros que debemos modificar, ya sea para agregar funcionalidad o corregir errores.  
En estos casos, es imprescindible comprender el funcionamiento del código.

Una estrategia adecuada es analizar las **estructuras a nivel de clases e interfaces**, ya que estas representan los componentes principales del sistema.

---

## Descripción del Proyecto

Este proyecto en **Java** fue diseñado con fines educativos para practicar:

- **Ingeniería inversa**
- **Modelado UML**

Incluye varios conceptos clave de la programación orientada a objetos:

### Interfaces

- `Conducible` y `Asegurable` definen comportamientos que deben ser implementados por las clases derivadas, como:
  - `Auto`
  - `Camion`

### Herencia

- `VehiculoMotorizado` es una **clase abstracta** que sirve de base para:
  - `Auto`
  - `Camion`

  Esta clase hereda atributos comunes como:
  - `marca`
  - `modelo`

### Composición

- `Auto` y `Camion` **poseen objetos** `Motor` y `Rueda`.
- Estos objetos **no existen independientemente** de los vehículos, ya que forman parte esencial de su estructura.

### Agregación

- `Conductor` y `Garage` **se relacionan** con los vehículos.
- Sin embargo, **pueden existir de forma independiente**.

---

## Actividad Recomendada

Se recomienda leer el código e **inferir las relaciones entre clases**, practicando así el análisis estructural y la comprensión del diseño del sistema desde una perspectiva de ingeniería inversa.


```java
package com.example.reverseengineering;
@Override
public double calcularCostoSeguro() {
return 400 + motor.getCaballosDeFuerza() * 2.0 + cargaMaximaKg * 0.01;
}
}


// --------------------- AGREGACIÓN ---------------------
class Garage {
private final String nombre;
private final List<VehiculoMotorizado> vehiculos = new ArrayList<>();


public Garage(String nombre) { this.nombre = nombre; }


public void aparcar(VehiculoMotorizado v) { vehiculos.add(v); }
public void retirar(VehiculoMotorizado v) { vehiculos.remove(v); }
public List<VehiculoMotorizado> listarVehiculos() { return Collections.unmodifiableList(vehiculos); }


public String toString() { return "Garage " + nombre; }
}


// --------------------- EJEMPLO DE USO ---------------------
class EjemploPrincipal {
public static void main(String[] args) {
Conductor alicia = new Conductor("Alicia");
Conductor roberto = new Conductor("Roberto");


Motor m1 = new Motor("V6-2000", 200);
Motor m2 = new Motor("V8-3500", 350);


Auto auto1 = new Auto("Toyota", "Corolla", m1, 15);
Camion camion1 = new Camion("Mercedes", "Actros", m2, 12000);


auto1.asignarConductor(alicia);
camion1.asignarConductor(roberto);


Garage g = new Garage("Central");
g.aparcar(auto1);
g.aparcar(camion1);


for (VehiculoMotorizado v : g.listarVehiculos()) {
v.arrancar();
System.out.println("Seguro: $" + v.calcularCostoSeguro());
v.detener();
System.out.println("---");
}


System.out.println("Motor de auto1: " + auto1.getMotor());
System.out.println("Conductor de auto1: " + auto1.getConductor().getNombre());
}
}
```
 
