#Ejercicio 1 – Búsqueda y cuotas

Imagina que tienes una tienda online de productos electrónicos. Para cada producto, tienes su ID, su nombre y su precio.

Tu tarea es crear un proceso que simule el cálculo de un plan de pago. Este proceso debe hacer lo siguiente, paso a paso y de forma asíncrona:

Buscar un producto a partir de su ID.

Calcular el precio final, sumando el 21% de IVA.

Dividir ese precio total en 3 cuotas exactas.

Cada paso tiene que depender del resultado del anterior, usando callbacks (sí, esa estructura que parece una escalera al revés). Al final, si todo sale bien, muestra por consola el producto, el precio con IVA, el valor de cada cuota y un mensaje de éxito: "¡Plan de pago calculado con éxito!".

Usa setTimeout para que cada paso se demore un poco.

Usa funciones function, con o sin flechas.

Asegúrate de manejar los errores, por si alguien busca un producto que no existe.
