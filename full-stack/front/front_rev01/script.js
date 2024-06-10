document.addEventListener("DOMContentLoaded", function() {
    const apiUrl = 'http://localhost:8080/api/v1/products';

    // Función para obtener todos los productos
    function fetchProducts() {
        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                const productTable = document.getElementById('product-table');
                productTable.innerHTML = '';
                data.forEach(product => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${product.id}</td>
                        <td>${product.name}</td>
                        <td>${product.price}</td>
                        <td>${product.quantity}</td>
                        <td>
                            <button class="btn btn-warning btn-sm edit-btn" data-id="${product.id}">Editar</button>
                            <button class="btn btn-danger btn-sm delete-btn" data-id="${product.id}">Eliminar</button>
                        </td>
                    `;
                    productTable.appendChild(row);
                });
            });
    }

    // Función para agregar un producto
    function addProduct(product) {
        fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(product)
        }).then(() => fetchProducts());
    }

    // Función para actualizar un producto
    function updateProduct(id, product) {
        fetch(`${apiUrl}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(product)
        }).then(() => fetchProducts());
    }

    // Función para eliminar un producto
    function deleteProduct(id) {
        fetch(`${apiUrl}/${id}`, {
            method: 'DELETE'
        }).then(() => fetchProducts());
    }

    // Manejar el envío del formulario
    document.getElementById('product-form').addEventListener('submit', function(event) {
        event.preventDefault();
        const id = document.getElementById('product-id').value;
        const product = {
            name: document.getElementById('name').value,
            price: document.getElementById('price').value,
            quantity: document.getElementById('quantity').value
        };

        if (id) {
            updateProduct(id, product);
        } else {
            addProduct(product);
        }

        document.getElementById('product-form').reset();
        document.getElementById('product-id').value = '';
    });

    // Manejar el clic en el botón de editar
    document.getElementById('product-table').addEventListener('click', function(event) {
        if (event.target.classList.contains('edit-btn')) {
            const id = event.target.dataset.id;
            fetch(`${apiUrl}/${id}`)
                .then(response => response.json())
                .then(product => {
                    document.getElementById('product-id').value = product.id;
                    document.getElementById('name').value = product.name;
                    document.getElementById('price').value = product.price;
                    document.getElementById('quantity').value = product.quantity;
                });
        }

        // Manejar el clic en el botón de eliminar
        if (event.target.classList.contains('delete-btn')) {
            const id = event.target.dataset.id;
            deleteProduct(id);
        }
    });

    // Cargar los productos inicialmente
    fetchProducts();
});
