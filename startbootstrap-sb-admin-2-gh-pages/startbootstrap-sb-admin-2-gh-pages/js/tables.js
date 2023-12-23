// Dùng hàm fetch để gửi yêu cầu lấy dữ liệu từ máy chủ
fetch('http://localhost:8081/api/listProduct')
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
    })
    .then(data => {
        displayDataOnPage(data);
    })
    .catch(error => {
        console.error('Error fetching data:', error);
    });

// Hàm để hiển thị dữ liệu trên trang
function displayDataOnPage(studentsData) {
    const tbody = document.querySelector('#student tbody');
    const rowsHTML = studentsData.map(student => `
        <tr>
            <td>${student.id}</td>
            <td>${student.nameSP}</td>
            <td>${student.price}</td>
            <td>${student.description}</td>
            <td><button type="button" class="btn btn-primary" onclick="deleteStudent(${student.id})">Delete</button>
            <button type="button" class="btn btn-danger" onclick="openUpdateProductModal(${student.id})">Chỉnh sửa</button>
            </td>
        </tr>
    `);
    tbody.innerHTML = rowsHTML.join('');
}

function deleteStudent(studentId) {
    fetch(`http://localhost:8081/api/productsDelete/${studentId}`, {
        method: 'DELETE',
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        // Không cần phải parse JSON ở đây vì response không chứa dữ liệu JSON
        return response.text(); // Đọc dữ liệu văn bản từ response
    })
    .then(deletedData => {
        console.log(`Student with ID ${studentId} deleted successfully.`);

        // Tự động load lại trang sau khi xóa
        window.location.reload();
    })
    .catch(error => {
        console.error('Error deleting student:', error);
    });
}

function addProducts() {
    // Get values from the form
    var productName = document.getElementById("nameSP").value;
    var price = document.getElementById("price").value;
    var description = document.getElementById("description").value;

    // Check if the price is a valid number
    if (isNaN(price) || price === "") {
        console.error('Invalid price. Please enter a valid number.');
        return;
    }

    // Prepare the data for the API request
    var data = {
        nameSP: productName,
        price: parseFloat(price),
        description: description
    };

    // Make the API request
    fetch('http://localhost:8081/api/productsAdd', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
    .then(response => response.json())
    .then(data => {
        // Handle the response from the API
        console.log('Success:', data);
        // Close the modal
        $('#addStudentModal').modal('hide');
        // Reload the page
        window.location.reload();
    })
    .catch((error) => {
        console.error('Error:', error);
        // Handle errors if needed
    });
}
//tìm kiếm theo ai
function openUpdateProductModal(productId) {
    // Gửi yêu cầu fetch đến API để lấy thông tin sản phẩm theo ID
    fetch(`http://localhost:8081/api/products/${productId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(product => {
            // Hiển thị thông tin sản phẩm trong modal cập nhật
            displayProductInfoInModal(product);
            
            // Mở modal cập nhật sản phẩm
            $('#updateProductModal').modal('show');
        })
        .catch(error => {
            console.error('Error fetching product data:', error);
        });
}

// Hàm để hiển thị thông tin sản phẩm trong modal cập nhật
function displayProductInfoInModal(product) {
    // Đổ dữ liệu sản phẩm vào các trường trong modal
    document.getElementById('updateProductId').value = product.id;
    document.getElementById('updateProductName').value = product.nameSP;
    document.getElementById('updatePrice').value = product.price;
    document.getElementById('updateDescription').value = product.description;
}

// Hàm để cập nhật sản phẩm
function updateProduct() {
    // Lấy thông tin sản phẩm từ các trường trong modal
    const productId = document.getElementById('updateProductId').value;
    const productName = document.getElementById('updateProductName').value;
    const price = document.getElementById('updatePrice').value;
    const description = document.getElementById('updateDescription').value;

    // Tạo đối tượng chứa thông tin sản phẩm để gửi lên server
    const updatedProduct = {
        id: productId,
        nameSP: productName,
        price: price,
        description: description
    };

    // Gửi yêu cầu fetch để cập nhật sản phẩm
    fetch(`http://localhost:8081/api/productsUp/${productId}`, {
    method: 'PUT',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(updatedProduct)
})
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        // Đóng modal sau khi cập nhật thành công
        $('#updateProductModal').modal('hide');
        window.location.reload();
    })
    .catch(error => {
        console.error('Error updating product:', error);
    });
}

