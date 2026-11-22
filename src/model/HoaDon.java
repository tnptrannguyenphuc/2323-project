package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HoaDon {

    public static final String[] PHUONG_THUC = {"Tiền mặt", "Chuyển khoản", "Thẻ ngân hàng"};

    private String maHD;
    private LocalDateTime thoiGian;
    private String maNV;
    private String maKH;

    private ChiTietHoaDon[] dsChiTiet = new ChiTietHoaDon[10];
    private int soChiTiet = 0;

    private String phuongThuc = PHUONG_THUC[0];
    private double tienKhachDua;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public HoaDon(String maHD, String maNV, String maKH) {
        this.maHD     = maHD;
        this.maNV     = maNV;
        this.maKH     = maKH;
        this.thoiGian = LocalDateTime.now();
    }

    public static HoaDon tuChuoi(String[] t) {
        HoaDon hd = new HoaDon(t[0], t[2], t[3]);
        hd.thoiGian     = LocalDateTime.parse(t[1], FMT);
        hd.phuongThuc   = t[4];
        hd.tienKhachDua = Double.parseDouble(t[5]);
        return hd;
    }

    public void themHang(String maSP, String tenSP, double donGia, int soLuong) {
        for (int i = 0; i < soChiTiet; i++) {
            if (dsChiTiet[i].getMaSP().equals(maSP)) {
                dsChiTiet[i].setSoLuong(dsChiTiet[i].getSoLuong() + soLuong);
                return;
            }
        }
        if (soChiTiet == dsChiTiet.length) {
            dsChiTiet = java.util.Arrays.copyOf(dsChiTiet, soChiTiet * 2);
        }
        dsChiTiet[soChiTiet++] = new ChiTietHoaDon(maSP, tenSP, donGia, soLuong);
    }

    public double tinhTongTienHang() {
        double tong = 0;
        for (int i = 0; i < soChiTiet; i++) tong += dsChiTiet[i].tinhThanhTien();
        return tong;
    }

    public double tinhTienThua() {
        return Math.max(0, tienKhachDua - tinhTongTienHang());
    }

    public boolean laKhachLe() {
        return maKH == null || maKH.isEmpty();
    }

    public void xuat() { InHoaDon.chiTiet(this); }

    public String toFileString() {
        String s = String.join(";", maHD, thoiGian.format(FMT), maNV, maKH,
                phuongThuc, String.valueOf((long) tienKhachDua));
        for (int i = 0; i < soChiTiet; i++) s += "\n" + dsChiTiet[i].toFileString();
        return s;
    }

    public String getMaHD()            { return maHD; }
    public String getMaNV()            { return maNV; }
    public String getMaKH()            { return maKH; }
    public double getTienKhachDua()    { return tienKhachDua; }
    public ChiTietHoaDon getChiTiet(int i) { return dsChiTiet[i]; }
    public LocalDateTime getThoiGian() { return thoiGian; }
    public int getSoChiTiet()          { return soChiTiet; }
    public String getPhuongThuc()      { return phuongThuc; }
    public void setPhuongThuc(String p){ this.phuongThuc = p; }
    public void setTienKhachDua(double t){ this.tienKhachDua = t; }
}
