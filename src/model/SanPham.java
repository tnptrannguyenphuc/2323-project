package model;

public abstract class SanPham {

    protected String maSP;
    protected String tenSP;
    protected String donVi;
    protected double giaNhap;
    protected double giaBan;
    protected int    soLuong;

    public SanPham(String maSP, String tenSP, String donVi,
                   double giaNhap, double giaBan, int soLuong) {
        this.maSP    = maSP;
        this.tenSP   = tenSP;
        this.donVi   = donVi;
        this.giaNhap = giaNhap;
        this.giaBan  = giaBan;
        this.soLuong = soLuong;
    }

    public abstract String getLoai();

    public boolean coTheBan() {
        return soLuong > 0;
    }

    public boolean xuatKho(int sl) {
        if (sl <= 0 || sl > soLuong) return false;
        soLuong -= sl;
        return true;
    }

    public void nhapKho(int sl) {
        if (sl > 0) soLuong += sl;
    }

    public static void inTieuDe() {
        System.out.printf("%-7s %-25s %-15s %-7s %14s %14s %6s%n",
                "Mã SP", "Tên sản phẩm", "Loại", "ĐVT",
                "Giá nhập", "Giá bán", "Tồn");
        System.out.println("-".repeat(92));
    }

    public void xuat() {
        System.out.printf("%-7s %-25s %-15s %-7s %,14.0f %,14.0f %6d%n",
                maSP, tenSP, getLoai(), donVi,
                giaNhap, giaBan, soLuong);
    }

    public String toFileString() {
        return maSP + ";" + tenSP + ";" + donVi + ";"
                + (long) giaNhap + ";" + (long) giaBan + ";" + soLuong;
    }

    @Override
    public String toString() {
        return maSP + " - " + tenSP
                + " (" + String.format("%,.0f", giaBan) + " đ/" + donVi + ")";
    }

    public String getMaSP()    { return maSP; }
    public String getTenSP()   { return tenSP; }
    public String getDonVi()   { return donVi; }
    public double getGiaNhap() { return giaNhap; }
    public double getGiaBan()  { return giaBan; }
    public int    getSoLuong() { return soLuong; }

    public void setTenSP(String tenSP)     { this.tenSP   = tenSP; }
    public void setGiaNhap(double giaNhap) { this.giaNhap = giaNhap; }
    public void setGiaBan(double giaBan)   { this.giaBan  = giaBan; }
    public void setSoLuong(int soLuong)    { this.soLuong = soLuong; }
}
