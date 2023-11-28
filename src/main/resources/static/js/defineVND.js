            // Lấy phần tử HTML chứa số tiền
            var amountElement = document.getElementById("amount");
    
            // Lấy giá trị số tiền
            var amount = parseFloat(amountElement.textContent);
    
            // Định dạng số tiền theo định dạng VNĐ
            var formattedAmount = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(amount);
    
            // Hiển thị số tiền đã được định dạng
            amountElement.textContent = formattedAmount;