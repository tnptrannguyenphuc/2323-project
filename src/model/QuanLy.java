package model;

/**
 * Quản lý: kế thừa NhanVien, chỉ khai báo mã loại, phụ cấp 20% và tên vai trò.
 */
public class QuanLy extends NhanVien {

    public QuanLy(String maNV, String hoTen, String sdt,
                  double luongCoBan, String matKhau) {
        super("QU", maNV, hoTen, sdt, luongCoBan, matKhau, 0.20);
    }

    @Override
    public String getVaiTro() { return "Quản lý"; }
}
