package model;

/**
 * Lớp cơ sở TRỪU TƯỢNG cho mọi sản phẩm (lớp con: ThucPham, DoUong).
 * Mọi thuộc tính + hành vi dùng chung (giá, kho, in, ghi file) nằm ở đây.
 */
public abstract class SanPham {

    protected String maSP;    // mã định danh sản phẩm (ví dụ: SP001)
    protected String tenSP;   // tên sản phẩm
    protected String donVi;   // đơn vị tính (kg, chai, hộp, cái...)
    protected double giaNhap; // giá mua vào từ nhà cung cấp
    protected double giaBan;  // giá bán ra
    protected int    soLuong; // số lượng còn trong kho

    public SanPham(String maSP, String tenSP, String donVi,
                   double giaNhap, double giaBan, int soLuong) {
        this.maSP    = maSP;
        this.tenSP   = tenSP;
        this.donVi   = donVi;
        this.giaNhap = giaNhap;
        this.giaBan  = giaBan;
        this.soLuong = soLuong;
    }

    // Hàm TRỪU TƯỢNG: lớp con BẮT BUỘC cài đặt
    public abstract String getLoai(); // "Thực phẩm", "Đồ uống"

    // Kiểm tra còn hàng để bán hay không
    public boolean coTheBan() {
        return soLuong > 0;
    }

    // Trừ kho khi bán, trả về false nếu không đủ hàng hoặc số lượng không hợp lệ
    public boolean xuatKho(int sl) {
        if (sl <= 0 || sl > soLuong) return false;
        soLuong -= sl;
        return true;
    }

    // Nhập thêm hàng vào kho
    public void nhapKho(int sl) {
        if (sl > 0) soLuong += sl;
    }

    // In tiêu đề bảng (gọi trước khi in danh sách sản phẩm)
    public static void inTieuDe() {
        System.out.printf("%-7s %-25s %-15s %-7s %14s %14s %6s%n",
                "Mã SP", "Tên sản phẩm", "Loại", "ĐVT",
                "Giá nhập", "Giá bán", "Tồn");
        System.out.println("-".repeat(92));
    }

    // In thông tin sản phẩm ra một dòng (đa hình: gọi getLoai() của lớp con)
    public void xuat() {
        System.out.printf("%-7s %-25s %-15s %-7s %,14.0f %,14.0f %6d%n",
                maSP, tenSP, getLoai(), donVi,
                giaNhap, giaBan, soLuong);
    }

    // Chuyển thành chuỗi để ghi file
    public String toFileString() {
        return maSP + ";" + tenSP + ";" + donVi + ";"
                + (long) giaNhap + ";" + (long) giaBan + ";" + soLuong;
    }

    // Hiển thị ngắn gọn (dùng khi tìm kiếm, chọn sản phẩm khi bán hàng)
    @Override
    public String toString() {
        return maSP + " - " + tenSP
                + " (" + String.format("%,.0f", giaBan) + " đ/" + donVi + ")";
    }

    // --- Getter và Setter ---
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
