package model;

/**
 * Nhân viên kho: kế thừa NhanVien, chỉ khai báo mã loại, phụ cấp 10% và tên vai trò.
 */
public class NhanVienKho extends NhanVien {

    public NhanVienKho(String maNV, String hoTen, String sdt,
                       double luongCoBan, String matKhau) {
        super("NV", maNV, hoTen, sdt, luongCoBan, matKhau, 0.10);
    }

    @Override
    public String getVaiTro() { return "Nhân viên kho"; }
}
