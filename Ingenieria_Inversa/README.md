// Proyecto de ejemplo para ingeniería inversa
// Contiene: interfaces, herencia, agregación, composición
//Objetivo del proyecto
A menudo nos encontramos con código generados por terceros que tenemos que modificar, ya sea para agregar funcionalidad, o corregir errores. En estos casos es impresindible conocer las funcionalidad del código. Considero que lo mas adecuado es el analisis de las estructuras a nivel de clases e interfaces como componentes principales
// ------------------------------------------------------------------
// DESCRIPCIÓN DEL PROYECTO:
// Este proyecto en Java fue diseñado con fines educativos para practicar
// ingeniería inversa y modelado UML. Incluye distintos conceptos de la
// Programación Orientada a Objetos:
//
// - Interfaces: Conducible y Asegurable definen comportamientos que deben ser
// implementados por las clases derivadas (ej. Auto y Camion).
// - Herencia: VehiculoMotorizado es una clase abstracta que sirve de base para
// Auto y Camion, heredando atributos comunes como marca y modelo.
// - Composición: Auto y Camion poseen objetos Motor y Rueda que no existen
// fuera de estos vehículos, formando parte esencial de su estructura.
// - Agregación: Conductor y Garage se relacionan con los vehículos, pero existen
// independientemente de ellos.
//
// La idea es que practiquen leyendo el código e infiriendo las relaciones entre clases 

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
 
