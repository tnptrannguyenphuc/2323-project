package model;

/**
 * Lớp ChiTietHoaDon: một dòng hàng trong hóa đơn.
 * Lưu đơn giá tại thời điểm mua để hóa đơn không đổi khi giá sản phẩm thay đổi.
 */
public class ChiTietHoaDon {

    private String maSP;     // mã sản phẩm
    private String tenSP;    // tên sản phẩm
    private double donGia;   // đơn giá tại thời điểm mua
    private int    soLuong;  // số lượng
    public ChiTietHoaDon(String maSP, String tenSP, double donGia, int soLuong) {
        this.maSP    = maSP;
        this.tenSP   = tenSP;
        this.donGia  = donGia;
        this.soLuong = soLuong;
    }

    public double tinhThanhTien() {
        return donGia * soLuong;
    }

    // Chuyển sang chuỗi để ghi file
    public String toFileString() {
        return "CT;" + maSP + ";" + tenSP + ";" + (long) donGia + ";" + soLuong;
    }

    // Getter / Setter
    public String getMaSP()   { return maSP; }
    public String getTenSP()  { return tenSP; }
    public double getDonGia() { return donGia; }
    public int getSoLuong()   { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
}
