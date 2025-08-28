```js
const productos = [
    { id: 1, nombre: "Laptop", precio: 1000 },
    { id: 2, nombre: "Mouse", precio: 25 },
    { id: 3, nombre: "Teclado", precio: 45 }
];

function buscarProductos(id, callback) {

    setTimeout(() => {
        const producto = productos.find(p => p.id === id);
        if (!producto) {
            return new Error('No se encontro el producto');
        }
        console.log("El producto encontrado es: ", producto.nombre);
        callback(null, producto);
    }, 1000);

}

function calcularImpuesto(producto, callback) {

    setTimeout(() => {

        const total = producto.precio * 1.21;
        console.log("El producto con impuesto es ", total);
        callback(null, total);

    }, 1000);
}

function aplicarDescuento(total, callback) {
    setTimeout(() => {
        const final = total * 0.9;
        console.log("el producto con descuento es: ", final);
        callback(null, final);
    }, 1000);
}


buscarProductos(3, (error,producto) =>{
    if(error) return console.log(error.message);
    calcularImpuesto(producto, (error, total)=>{
        if(error) return  console.log(error.message);
        aplicarDescuento(total, (error, final)=>{
            if(error)return console.log(error.message);
            console.log("Compra con exito ", final);
        });
    });
});

```







