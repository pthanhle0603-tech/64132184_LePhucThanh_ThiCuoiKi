const apiUrl = '/api/products';

async function loadProducts() {
    const response = await fetch(apiUrl);
    const products = await response.json();
    const tbody = document.getElementById('productTable');
    tbody.innerHTML = '';

    products.forEach(product => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price.toFixed(0)}</td>
            <td><button class="delete-button" data-id="${product.id}">Xóa</button></td>
        `;
        tbody.appendChild(row);
    });

    document.querySelectorAll('.delete-button').forEach(button => {
        button.addEventListener('click', async () => {
            const id = button.dataset.id;
            await fetch(`${apiUrl}/${id}`, { method: 'DELETE' });
            loadProducts();
        });
    });
}

async function addProduct() {
    const id = document.getElementById('productId').value.trim();
    const name = document.getElementById('productName').value.trim();
    const price = Number(document.getElementById('productPrice').value);

    if (!id || !name || !price) {
        alert('Vui lòng điền đủ thông tin sản phẩm.');
        return;
    }

    await fetch(apiUrl, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id, name, price }),
    });

    document.getElementById('productId').value = '';
    document.getElementById('productName').value = '';
    document.getElementById('productPrice').value = '';

    loadProducts();
}

document.getElementById('addButton').addEventListener('click', addProduct);
window.addEventListener('load', loadProducts);
