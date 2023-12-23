function login() {
    // Lấy giá trị từ form
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    // Gọi API login

    fetch('http://localhost:8081/api/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            username: username,
            pasword: password
        }),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(response.statusText);
            }
            return response.json();  // Chuyển đổi phản hồi sang đối tượng JSON
        })
        .then(data => {
            // Xử lý dữ liệu thành công
            console.log('Login successful:', data);
            window.location.href = 'index.html';
        })
        .catch(error => {
            // Xử lý lỗi
            alert('Sai tên đăng nhập và mật khẩu .');
        });
}