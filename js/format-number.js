// Định dạng số tiền có dấu chấm phân cách hàng nghìn, hàng triệu, hàng tỷ
function formatNumberWithDots(val) {
    if (!val) return '';
    // Chỉ giữ lại các chữ số
    var digits = val.toString().replace(/\D/g, '');
    if (!digits) return '';
    // Thêm dấu chấm sau mỗi 3 chữ số từ phải sang trái
    return digits.replace(/\B(?=(\d{3})+(?!\d))/g, '.');
}

// Lắng nghe sự kiện gõ trên toàn bộ trang
document.addEventListener('input', function (e) {
    var target = e.target;
    if (!target || target.tagName !== 'INPUT') return;

    var name = (target.name || '').toLowerCase();
    var id = (target.id || '').toLowerCase();
    var isNumberField = target.classList.contains('format-number') ||
                        name.indexOf('price') !== -1 ||
                        name.indexOf('cost') !== -1 ||
                        name.indexOf('amount') !== -1 ||
                        id.indexOf('price') !== -1 ||
                        id.indexOf('cost') !== -1 ||
                        id.indexOf('amount') !== -1;

    if (isNumberField) {
        // Chuyển input sang type='text' nếu đang là number để hiển thị được dấu chấm
        if (target.type === 'number') {
            target.type = 'text';
            target.inputMode = 'numeric';
        }
        var cursor = target.selectionStart;
        var oldLen = target.value.length;
        var formatted = formatNumberWithDots(target.value);
        target.value = formatted;
        var newLen = formatted.length;
        if (cursor !== null) {
            target.setSelectionRange(cursor + (newLen - oldLen), cursor + (newLen - oldLen));
        }
    }
});

