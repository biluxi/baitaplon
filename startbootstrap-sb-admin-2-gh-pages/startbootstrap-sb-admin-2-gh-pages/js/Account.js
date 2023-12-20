// Dùng hàm fetch để gửi yêu cầu lấy dữ liệu từ máy chủ
fetch('http://localhost:8081/api/listAccount')
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
function displayDataOnPage(AccData) {
    const tbody = document.querySelector('#student tbody');
    const rowsHTML = AccData.map(Acc => `
        <tr>
            <td>${Acc.account_ID}</td>
            <td>${Acc.username}</td>
            <td>${Acc.pasword}</td>
            <td>${Acc.privleges}</td>
            <td><button type="button" class="btn btn-primary" onclick="deleteStudent(${Acc.account_ID})">Delete</button>
            <button type="button" class="btn btn-danger" onclick="openUpdateAccountModal(${Acc.account_ID})">Chỉnh sửa</button>
            </td>
        </tr>
    `);
    tbody.innerHTML = rowsHTML.join('');
}
function deleteStudent(Accid) {
    fetch(`http://localhost:8081/api/accountDelete/${Accid}`, {
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
        console.log(`Student with ID ${Accid} deleted successfully.`);

        // Tự động load lại trang sau khi xóa
        window.location.reload();
    })
    .catch(error => {
        console.error('Error deleting student:', error);
    });
}
// Thêm sản phẩm : 
function addAcc() {
    // lấy giá trị trên form
    var userName = document.getElementById("userName").value;
    var password = document.getElementById("password").value;
    var role = document.getElementById("role").value;

    // Kiểm tra giá trị không được null
    if (!userName.trim()) {
        console.error('Không được để trống.');
        return;
    }
    if (!password.trim()) {
        console.error('Không được để trống.');
        return;
    }

    // tạo đối tượng dữ liệu cho API request
    var data = {
        username: userName,
        pasword: password,
        privleges: role  // sửa tên trường này
    };
    console.log('data trả ra:', data);

    // gọi đến API add acc
    fetch('http://localhost:8081/api/accountAdd', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
    .then(response => response.json())
    .then(data => {
        // khi API thành công
        console.log('Success:', data);
        // đóng lại form
        $('#addAccModal').modal('hide');
        // load lại trang để cập nhật dữ liệu
        window.location.reload();
    })
    .catch((error) => {
        // khi API lỗi sẽ trả ra dữ liệu lỗi
        console.error('Error:', error);
    });
}

// chỉnh sửa
//lấy 1 dữ liệu từ api 
function openUpdateAccountModal(accountId) {
    // Gửi yêu cầu fetch đến API để lấy thông tin sản phẩm theo ID
    fetch(`http://localhost:8081/api/account/${accountId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(ac => {
            // Hiển thị thông tin sản phẩm trong modal cập nhật
            displayAccountInfoInModal(ac);
            
            // Mở modal cập nhật sản phẩm
            $('#updateProductModal').modal('show');
        })
        .catch(error => {
            console.error('Error fetching product data:', error);
        });
}
// chỉnh sửa cập nhật 
// thành strat
// Hàm để hiển thị thông tin sản phẩm trong modal cập nhật
function displayAccountInfoInModal(Account) {
    // Đổ dữ liệu sản phẩm vào các trường trong modal
    document.getElementById('updateAccountId').value = Account.account_ID;
    document.getElementById('updatedAccountName').value = Account.pasword;   
    document.getElementById('updatePasword').value = Account.username;
    document.getElementById('updatePrivleges').value = Account.privleges;
}

// Hàm để cập nhật sản phẩm
function updatedAccount() {
    // Lấy thông tin sản phẩm từ các trường trong modal
    const AccountId = document.getElementById('updateAccountId').value;
    const UseName = document.getElementById('updatedAccountName').value;
    const Pasword = document.getElementById('updatePasword').value;
    const Privleges = document.getElementById('updatePrivleges').value;

    //Tạo đối tượng chứa thông tin để gửi lên server
    const updatedAccount = {
        id: AccountId,
        username: UseName,
        pasword: Pasword,
        privleges: Privleges
    };

    // Gửi yêu cầu fetch để cập nhật sản phẩm
    //code không call được api
    fetch(`http://localhost:8081/api/accountUp/${AccountId}`, {
    method: 'PUT',
    headers: {
        'Content-Type': 'application/json'
    },
   
    body: JSON.stringify(updatedAccount)
})
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        // Đóng modal sau khi cập nhật thành công
        $('#updatedAccountModal').modal('hide');
        window.location.reload();
    })
    .catch(error => {
        console.error('Error updating account:', error);
    });
}
// thành end