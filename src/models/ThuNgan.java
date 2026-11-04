package model;

/**
 * Thu ngân: kế thừa NhanVien, chỉ khai báo mã loại, phụ cấp 0% và tên vai trò.
 */
public class ThuNgan extends NhanVien {

    public ThuNgan(String maNV, String hoTen, String sdt,
                   double luongCoBan, String matKhau) {
        super("TH", maNV, hoTen, sdt, luongCoBan, matKhau, 0.0);
    }

    @Override
    public String getVaiTro() { return "Thu ngân"; }
}
