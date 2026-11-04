package interfaces;

/**
 * INTERFACE: IQuanLyDanhSach<T>
 * 
 * Mục đích trong OOP:
 * - Quy ước các phương thức chuẩn cho một danh sách quản lý đối tượng (Thêm, Xóa, Tìm, Xuất).
 * - Sử dụng Generic <T> giúp interface tái sử dụng được cho nhiều loại đối tượng khác nhau:
 *   SanPham, NhanVien, KhachHang...
 */
public interface IQuanLyDanhSach<T> {

    // Thêm một phần tử vào danh sách
    boolean them(T doiTuong);

    // Xóa phần tử theo mã định danh
    boolean xoa(String ma);

    // Tìm kiếm phần tử theo mã chính xác
    T timTheoMa(String ma);

    // Xuất toàn bộ danh sách ra màn hình console
    void xuatDanhSach();

    // Lấy số lượng phần tử hiện có trong danh sách
    int getSoLuong();
}
