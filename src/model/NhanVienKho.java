package model;

public class NhanVienKho extends NhanVien {

    public NhanVienKho(String maNV, String hoTen, String sdt,
                       double luongCoBan, String matKhau) {
        super("NV", maNV, hoTen, sdt, luongCoBan, matKhau, 0.10);
    }

    @Override
    public String getVaiTro() { return "Nhân viên kho"; }
}
