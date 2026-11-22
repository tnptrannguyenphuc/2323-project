package model;

public class ThuNgan extends NhanVien {

    public ThuNgan(String maNV, String hoTen, String sdt,
                   double luongCoBan, String matKhau) {
        super("TH", maNV, hoTen, sdt, luongCoBan, matKhau, 0.0);
    }

    @Override
    public String getVaiTro() { return "Thu ngân"; }
}
