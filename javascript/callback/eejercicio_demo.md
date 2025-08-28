# Ejercicios demo callback
## - Entender el concepto de callbacks en JavaScript
## - Familiaridad con la funcion setTimeout para simular operaciones asincronas
## // Realizar operaciones matematicas (suma, multiplicacion, resta y division) usando callbacks y simulando un retardo con setTimeout. 
## // Cada operacion debe esperar el resultado de la anterior para continuar, demostrando el concepto de "callback hell".
```js
function sumar(a, b, callback){
  setTimeout(() => {
    const resultado = a + b;
    console.log(`la suma de ${a} + ${b} es: ${resultado}`);
    callback(resultado);
  }, 1000);
}

function multiplicar(a, b, callback){
  setTimeout(() => {
    const resultado = a * b;
    console.log(`la multiplicacion de ${a} * ${b} es: ${resultado}`);
    callback(resultado);
  }, 1000);
}

function restar(a, b, callback){
  setTimeout(() => {
    const resultado = a - b;
    console.log(`la resta de ${a} - ${b} es: ${resultado}`);
    callback(resultado);
  }, 1000);
}

function dividir(a, b, callback){
  setTimeout(() => {
    if(b === 0){
      console.log("Error: division por 0");
      return callback(null);
    }
    const resultado = a / b;
    console.log(`la division de ${a} / ${b} es: ${resultado}`);
    callback(resultado);
  }, 1000);
}


//definir el callback
// function mostrarResultado(resultado){
//   console.log("El resultado es:", resultado);
// }
//llamar a la funcion suma
// sumar(2,3, function(resultadoSuma){
//   console.log(resultadoSuma);
//   });


//llamando a la funcion multiplicar con el resultado de sumar
// sumar(2, 3, function(resultadoSuma){
//   multiplicar(resultadoSuma, 4, function(resultadoMultiplicar){
//     console.log(resultadoMultiplicar);
//   });
// });


//llamar a la funcion restar con el resultado de multiplicar y sumar
// sumar(5, 3, function(resultadoSuma){
//   multiplicar(resultadoSuma, 4, function(resultadoMultiplicar){
//     restar(resultadoMultiplicar, 2, function(resultadoRestar){
//       console.log(resultadoRestar);
//     });
//   });
// });

//llamar a la funcion dividir con el resultado de restar, multiplicar y sumar
sumar(5, 3, function(resultadoSuma){
  multiplicar(resultadoSuma, 4, function(resultadoMultiplicar){
    restar(resultadoMultiplicar, 2, function(resultadoRestar){
      dividir(resultadoRestar, 3, function(resultadoFinal){
        console.log("El resultado final es:", resultadoFinal);
      });
    });
  });
});
```
