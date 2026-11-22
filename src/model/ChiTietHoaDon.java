package model;

public class ChiTietHoaDon {

    private String maSP;
    private String tenSP;
    private double donGia;
    private int    soLuong;
    public ChiTietHoaDon(String maSP, String tenSP, double donGia, int soLuong) {
        this.maSP    = maSP;
        this.tenSP   = tenSP;
        this.donGia  = donGia;
        this.soLuong = soLuong;
    }

    public double tinhThanhTien() {
        return donGia * soLuong;
    }

    public String toFileString() {
        return "CT;" + maSP + ";" + tenSP + ";" + (long) donGia + ";" + soLuong;
    }

    public String getMaSP()   { return maSP; }
    public String getTenSP()  { return tenSP; }
    public double getDonGia() { return donGia; }
    public int getSoLuong()   { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
}
