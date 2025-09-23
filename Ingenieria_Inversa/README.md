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
package org.example.reverseengineering;

public interface Asegurable {
    double calcularCostoSeguro();
}
package org.example.reverseengineering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Auto extends VehiculoMotorizado{

    // Composición: el motor es parte intrínseca del auto
    private final Motor motor;
    private final List<Rueda> ruedas = new ArrayList<>(); // composición

    // Agregación: conductor puede existir fuera del auto
    private Conductor conductor; // puede ser null

    public Auto(String marca, String modelo, Motor motor, int tamañoRueda) {
        super(marca, modelo);
        this.motor = motor;
        for (int i = 0; i < 4; i++) ruedas.add(new Rueda(tamañoRueda));
    }

    public void asignarConductor(Conductor c) { this.conductor = c; }
    public Conductor getConductor() { return conductor; }
    public Motor getMotor() { return motor; }
    public List<Rueda> getRuedas() { return Collections.unmodifiableList(ruedas); }
    @Override
    public void arrancar() {
        System.out.println(this + " arranca con motor " + motor);
    }


    @Override
    public void detener() {
        System.out.println(this + " se detiene.");
    }


    @Override
    public double calcularCostoSeguro() {
        return 200 + motor.getCaballosDeFuerza() * 1.5;
    }
}
package org.example.reverseengineering;

public class Camion extends VehiculoMotorizado {

    private final Motor motor; // composición
    private Conductor conductor; // agregación
    private final double cargaMaximaKg;


    public Camion(String marca, String modelo, Motor motor, double cargaMaximaKg) {
        super(marca, modelo);
        this.motor = motor;
        this.cargaMaximaKg = cargaMaximaKg;
    }


    public void asignarConductor(Conductor c) {
        this.conductor = c;
    }

    public Conductor getConductor() {
        return conductor;
    }


    @Override
    public void arrancar() {
        System.out.println(this + " (camión) arranca con " + motor);
    }


    @Override
    public void detener() {
        System.out.println(this + " (camión) se detiene.");
    }


    @Override
    public double calcularCostoSeguro() {
        return 400 + motor.getCaballosDeFuerza() * 2.0 + cargaMaximaKg * 0.01;
    }
}
package org.example.reverseengineering;

public interface Conducible {
    void arrancar();
    void detener();
}
package org.example.reverseengineering;

public class Conductor {
    private final String nombre;
    public Conductor(String nombre) { this.nombre = nombre; }
    public String getNombre() { return nombre; }
}
package org.example.reverseengineering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Garage {
    private final String nombre;
    private final List<VehiculoMotorizado> vehiculos = new ArrayList<>();


    public Garage(String nombre) { this.nombre = nombre; }


    public void aparcar(VehiculoMotorizado v) { vehiculos.add(v); }
    public void retirar(VehiculoMotorizado v) { vehiculos.remove(v); }
    public List<VehiculoMotorizado> listarVehiculos() { return Collections.unmodifiableList(vehiculos); }


    public String toString() { return "Garage " + nombre; }
}
package org.example.reverseengineering;

public class Motor {
    // Composición: Motor no tiene sentido fuera de un vehículo que lo creó
    private final String modelo;
    private final int caballosDeFuerza;

    public Motor(String modelo, int caballosDeFuerza) {
        this.modelo = modelo;
        this.caballosDeFuerza = caballosDeFuerza;
    }


    public String getModelo() { return modelo; }
    public int getCaballosDeFuerza() { return caballosDeFuerza; }


    public String toString() {
        return modelo + " (" + caballosDeFuerza + " HP)";
    }
}
package org.example.reverseengineering;

public class Rueda {
    // Composición: no tiene sentido fuera de un vehículo que la creó
    private final int tamañoEnPulgadas;

    public Rueda(int tamañoEnPulgadas) {
        this.tamañoEnPulgadas = tamañoEnPulgadas;
    }

    public int getTamaño() {
        return tamañoEnPulgadas;
    }
}
package org.example.reverseengineering;

public abstract class VehiculoMotorizado implements Conducible, Asegurable{
    protected final String marca;
    protected final String modelo;

    public VehiculoMotorizado(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }


    @Override
    public String toString() {
        return marca + " " + modelo;
    }

}
package org.example;

import org.example.reverseengineering.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
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
 
