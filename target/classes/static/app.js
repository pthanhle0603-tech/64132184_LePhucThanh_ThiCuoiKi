const API_BASE_URL = 'http://localhost:8080/api/products';
let currentEditId = null;

// Tải dữ liệu khi trang tải
window.addEventListener('load', () => {
    loadProducts();
    updateStats();
});

// Thêm/Cập nhật sản phẩm
function addProduct(event) {
    event.preventDefault();
    
    const product = {
        id: document.getElementById('productId').value,
        name: document.getElementById('productName').value,
        price: parseFloat(document.getElementById('productPrice').value),
        category: document.getElementById('productCategory').value,
        stock: parseInt(document.getElementById('productStock').value) || 0,
        rating: parseFloat(document.getElementById('productRating').value) || 5,
        description: document.getElementById('productDesc').value
    };

    const url = currentEditId ? `${API_BASE_URL}/${currentEditId}` : API_BASE_URL;
    const method = currentEditId ? 'PUT' : 'POST';

    fetch(url, {
        method: method,
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(product)
    })
    .then(response => response.json())
    .then(data => {
        if (data.status === 'success') {
            alert(currentEditId ? '✅ Cập nhật sản phẩm thành công!' : '✅ Thêm sản phẩm thành công!');
            resetForm();
            loadProducts();
            updateStats();
        } else {
            alert('❌ Lỗi: ' + data.message);
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('❌ Lỗi kết nối');
    });
}

// Tải danh sách sản phẩm
function loadProducts() {
    fetch(API_BASE_URL)
    .then(response => response.json())
    .then(data => {
        const productList = document.getElementById('productList');
        const productCount = document.getElementById('productCount');
        
        productCount.textContent = `📊 Tổng cộng: ${data.count} sản phẩm`;
        
        if (!data.products || data.products.length === 0) {
            productList.innerHTML = '<div class="empty-state"><p>📭 Chưa có sản phẩm nào. Hãy thêm sản phẩm!</p></div>';
            return;
        }

        productList.innerHTML = data.products.map(product => `
            <div class="product-card">
                <div class="product-header">
                    <div>
                        <h3>${product.name}</h3>
                        <div class="product-id">ID: ${product.id}</div>
                    </div>
                    <span class="product-badge">${product.category}</span>
                </div>
                
                <div class="product-info">
                    <div class="price">💰 ${product.price.toLocaleString('vi-VN')} VND</div>
                    
                    <div class="info-row">
                        <label>📦 Kho hàng:</label>
                        <span>${product.stock} sản phẩm</span>
                    </div>
                    
                    <div class="info-row">
                        <label>⭐ Đánh giá:</label>
                        <span class="rating">${product.rating}/5 ${'⭐'.repeat(Math.round(product.rating))}</span>
                    </div>
                    
                    ${product.description ? `<div class="description">📝 ${product.description}</div>` : ''}
                </div>
                
                <div class="product-actions">
                    <button class="btn-edit" onclick="editProduct('${product.id}')">✏️ Sửa</button>
                    <button class="btn-delete" onclick="deleteProduct('${product.id}')">🗑️ Xóa</button>
                </div>
            </div>
        `).join('');
    })
    .catch(error => console.error('Error loading products:', error));
}

// Chỉnh sửa sản phẩm
function editProduct(id) {
    fetch(`${API_BASE_URL}/${id}`)
    .then(response => response.json())
    .then(data => {
        if (data.status === 'success') {
            const product = data.product;
            document.getElementById('productId').value = product.id;
            document.getElementById('productName').value = product.name;
            document.getElementById('productPrice').value = product.price;
            document.getElementById('productCategory').value = product.category;
            document.getElementById('productStock').value = product.stock;
            document.getElementById('productRating').value = product.rating;
            document.getElementById('productDesc').value = product.description;
            
            currentEditId = id;
            document.querySelector('.btn-primary').textContent = '✏️ Cập nhật';
            document.querySelector('.form-section').scrollIntoView({ behavior: 'smooth' });
        }
    })
    .catch(error => console.error('Error:', error));
}

// Xóa sản phẩm
function deleteProduct(id) {
    if (confirm('🚨 Bạn chắc chắn muốn xóa sản phẩm này không?')) {
        fetch(`${API_BASE_URL}/${id}`, {method: 'DELETE'})
        .then(response => response.json())
        .then(data => {
            if (data.status === 'success') {
                alert('✅ Xóa sản phẩm thành công!');
                loadProducts();
                updateStats();
            } else {
                alert('❌ Lỗi: ' + data.message);
            }
        })
        .catch(error => console.error('Error:', error));
    }
}

// Tìm kiếm sản phẩm
function searchProducts() {
    const keyword = document.getElementById('searchInput').value;
    if (keyword.trim() === '') {
        loadProducts();
        return;
    }
    
    fetch(`${API_BASE_URL}/search?keyword=${encodeURIComponent(keyword)}`)
    .then(response => response.json())
    .then(data => {
        const productList = document.getElementById('productList');
        const productCount = document.getElementById('productCount');
        
        productCount.textContent = `🔍 Kết quả tìm kiếm: ${data.count} sản phẩm`;
        
        if (data.results.length === 0) {
            productList.innerHTML = '<div class="empty-state"><p>😞 Không tìm thấy sản phẩm nào</p></div>';
            return;
        }

        productList.innerHTML = data.results.map(product => `
            <div class="product-card">
                <div class="product-header">
                    <div>
                        <h3>${product.name}</h3>
                        <div class="product-id">ID: ${product.id}</div>
                    </div>
                    <span class="product-badge">${product.category}</span>
                </div>
                <div class="product-info">
                    <div class="price">💰 ${product.price.toLocaleString('vi-VN')} VND</div>
                    <div class="info-row">
                        <label>📦 Kho hàng:</label>
                        <span>${product.stock} sản phẩm</span>
                    </div>
                    <div class="info-row">
                        <label>⭐ Đánh giá:</label>
                        <span class="rating">${product.rating}/5 ${'⭐'.repeat(Math.round(product.rating))}</span>
                    </div>
                </div>
                <div class="product-actions">
                    <button class="btn-edit" onclick="editProduct('${product.id}')">✏️ Sửa</button>
                    <button class="btn-delete" onclick="deleteProduct('${product.id}')">🗑️ Xóa</button>
                </div>
            </div>
        `).join('');
    })
    .catch(error => console.error('Error:', error));
}

// Lọc theo danh mục
function filterByCategory() {
    const category = document.getElementById('categoryFilter').value;
    if (category === '') {
        loadProducts();
        return;
    }
    
    fetch(`${API_BASE_URL}/category/${category}`)
    .then(response => response.json())
    .then(data => {
        const productList = document.getElementById('productList');
        const productCount = document.getElementById('productCount');
        
        productCount.textContent = `📂 Loại "${category}": ${data.count} sản phẩm`;
        
        if (data.results.length === 0) {
            productList.innerHTML = `<div class="empty-state"><p>📭 Không có sản phẩm nào thuộc loại "${category}"</p></div>`;
            return;
        }

        productList.innerHTML = data.results.map(product => `
            <div class="product-card">
                <div class="product-header">
                    <div>
                        <h3>${product.name}</h3>
                        <div class="product-id">ID: ${product.id}</div>
                    </div>
                    <span class="product-badge">${product.category}</span>
                </div>
                <div class="product-info">
                    <div class="price">💰 ${product.price.toLocaleString('vi-VN')} VND</div>
                    <div class="info-row">
                        <label>📦 Kho hàng:</label>
                        <span>${product.stock} sản phẩm</span>
                    </div>
                </div>
                <div class="product-actions">
                    <button class="btn-edit" onclick="editProduct('${product.id}')">✏️ Sửa</button>
                    <button class="btn-delete" onclick="deleteProduct('${product.id}')">🗑️ Xóa</button>
                </div>
            </div>
        `).join('');
    })
    .catch(error => console.error('Error:', error));
}

// Cập nhật thống kê
function updateStats() {
    fetch(`${API_BASE_URL}/list/stats`)
    .then(response => response.json())
    .then(data => {
        const statsDiv = document.getElementById('stats');
        statsDiv.innerHTML = `
            <div class="stat-item">
                <h4>📦 Tổng sản phẩm</h4>
                <div class="value">${data.total}</div>
            </div>
            <div class="stat-item">
                <h4>💰 Giá trung bình</h4>
                <div class="value">${data.averagePrice.toLocaleString('vi-VN')} VND</div>
            </div>
        `;
    })
    .catch(error => console.error('Error:', error));
}

// Làm mới form
function resetForm() {
    document.getElementById('addProductForm').reset();
    currentEditId = null;
    document.querySelector('.btn-primary').textContent = '✅ Thêm/Cập nhật';
    document.getElementById('productRating').value = 5;
    document.getElementById('productCategory').value = 'Khác';
    document.getElementById('searchInput').value = '';
    document.getElementById('categoryFilter').value = '';
}
