package model;

public abstract class NhanVien {

    protected String maLoai;
    protected String maNV;
    protected String hoTen;
    protected String sdt;
    protected double luongCoBan;
    protected String matKhau;
    protected double phuCap;

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

    public abstract String getVaiTro();

    public double tinhLuong() {
        return luongCoBan * (1 + phuCap);
    }

    public boolean kiemTraDangNhap(String ma, String mk) {
        return maNV.equalsIgnoreCase(ma.trim()) && matKhau.equals(mk);
    }

    public static void inTieuDe() {
        System.out.printf("%-7s %-25s %-16s %-13s %16s%n",
                "Mã NV", "Họ và tên", "Vai trò", "Số ĐT", "Lương tháng");
        System.out.println("-".repeat(80));
    }

    public void xuat() {
        System.out.printf("%-7s %-25s %-16s %-13s %,16.0f đ%n",
                maNV, hoTen, getVaiTro(), sdt, tinhLuong());
    }

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
