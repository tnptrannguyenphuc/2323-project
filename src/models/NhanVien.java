package model;

/**
 * Lớp TRỪU TƯỢNG NhanVien: lớp cha chung của QuanLy, ThuNgan, NhanVienKho.
 * Công thức lương và phần ghi file dùng chung ở đây (lớp con kế thừa lại),
 * lớp con chỉ khai báo phụ cấp + tên vai trò.
 */
public abstract class NhanVien {

    protected String maLoai;     // mã loại khi ghi file: QU / TH / NV
    protected String maNV;       // mã nhân viên (ví dụ: QL01, TN01)
    protected String hoTen;      // họ và tên đầy đủ
    protected String sdt;        // số điện thoại
    protected double luongCoBan; // lương cơ bản hàng tháng
    protected String matKhau;    // mật khẩu đăng nhập hệ thống
    protected double phuCap;     // phụ cấp theo vai trò (0.2 = 20%)

    public NhanVien(String maLoai, String maNV, String hoTen, String sdt,
                    double luongCoBan, String matKhau, double phuCap) {
        this.maLoai     = maLoai;
        this.maNV       = maNV;
        this.hoTen      = hoTen;
        this.sdt        = sdt;
        this.luongCoBan = luongCoBan;
        this.matKhau    = matKhau;
        this.phuCap     = phuCap;
    }

    // Tên vai trò: lớp con BẮT BUỘC cài đặt (đa hình)
    public abstract String getVaiTro();

    // Lương thực lĩnh = lương cơ bản * (1 + phụ cấp) - dùng chung cho mọi vai trò
    public double tinhLuong() {
        return luongCoBan * (1 + phuCap);
    }

    // Kiểm tra đăng nhập
    public boolean kiemTraDangNhap(String ma, String mk) {
        return maNV.equalsIgnoreCase(ma.trim()) && matKhau.equals(mk);
    }

    // In tiêu đề bảng danh sách nhân viên
    public static void inTieuDe() {
        System.out.printf("%-7s %-25s %-16s %-13s %16s%n",
                "Mã NV", "Họ và tên", "Vai trò", "Số ĐT", "Lương tháng");
        System.out.println("-".repeat(80));
    }

    // In 1 dòng (đa hình: getVaiTro() là của lớp con)
    public void xuat() {
        System.out.printf("%-7s %-25s %-16s %-13s %,16.0f đ%n",
                maNV, hoTen, getVaiTro(), sdt, tinhLuong());
    }

    // Chuyển thành chuỗi để ghi file
    public String toFileString() {
        return maLoai + ";" + maNV + ";" + hoTen + ";" + sdt
                + ";" + (long) luongCoBan + ";" + matKhau;
    }

    public String getMaLoai() { return maLoai; }
    public String getMaNV()  { return maNV; }
    public String getHoTen() { return hoTen; }
    public String getSdt()   { return sdt; }
    public double getLuong() { return luongCoBan; }
}
