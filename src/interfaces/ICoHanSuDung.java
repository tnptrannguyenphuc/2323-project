package interfaces;

import java.time.LocalDate;

/**
 * INTERFACE: ICoHanSuDung
 * 
 * Mục đích trong OOP:
 * - Định nghĩa hành vi cho các đối tượng có hạn sử dụng.
 * - Không phải sản phẩm nào cũng cần quản lý hạn sử dụng nghiêm ngặt,
 *   vì vậy ta dùng Interface thay vì đưa vào lớp cha SanPham.
 * - Lớp ThucPham sẽ cài đặt (implements) interface này.
 */
public interface ICoHanSuDung {

    // Lấy ngày hết hạn
    LocalDate getNgayHetHan();

    // Kiểm tra sản phẩm đã hết hạn hay chưa
    boolean daHetHan();

    // Kiểm tra sản phẩm sắp hết hạn (còn <= 2 ngày)
    boolean sapHetHan();

    // Số ngày còn lại trước khi hết hạn
    long soNgayConLai();
}
