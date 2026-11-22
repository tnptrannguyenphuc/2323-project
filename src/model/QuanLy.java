package model;

public class QuanLy extends NhanVien {

    public QuanLy(String maNV, String hoTen, String sdt,
                  double luongCoBan, String matKhau) {
        super("QU", maNV, hoTen, sdt, luongCoBan, matKhau, 0.20);
    }

    @Override
    public String getVaiTro() { return "Quản lý"; }
}
