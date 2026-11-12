package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Lớp HoaDon: hóa đơn bán hàng, chứa mảng ChiTietHoaDon (mảng thủ công),
 * và phương thức thanh toán.
 */
public class HoaDon {

    public static final String[] PHUONG_THUC = {"Tiền mặt", "Chuyển khoản", "Thẻ ngân hàng"};

    private String maHD;            // mã hóa đơn, ví dụ HD001
    private LocalDateTime thoiGian; // thời gian tạo hóa đơn
    private String maNV;            // mã nhân viên thu ngân
    private String maKH;            // mã khách hàng ("" nếu khách lẻ)

    private ChiTietHoaDon[] dsChiTiet = new ChiTietHoaDon[10];
    private int soChiTiet = 0;     // số dòng hàng hiện tại

    private String phuongThuc = PHUONG_THUC[0]; // phương thức thanh toán
    private double tienKhachDua;   // tiền khách đưa (nếu tiền mặt)

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public HoaDon(String maHD, String maNV, String maKH) {
        this.maHD     = maHD;
        this.maNV     = maNV;
        this.maKH     = maKH;
        this.thoiGian = LocalDateTime.now();
    }

    // Tạo hóa đơn từ dòng thông tin trong file đã tách bằng ";"
    // Định dạng: maHD;thoiGian;maNV;maKH;phuongThuc;tienKhachDua
    // (các món hàng nằm ở các dòng "CT" phía sau, do DanhSachHoaDon đọc và thêm vào)
    public static HoaDon tuChuoi(String[] t) {
        HoaDon hd = new HoaDon(t[0], t[2], t[3]);
        hd.thoiGian     = LocalDateTime.parse(t[1], FMT); // giữ đúng thời gian lập hóa đơn cũ
        hd.phuongThuc   = t[4];
        hd.tienKhachDua = Double.parseDouble(t[5]);
        return hd;
    }

    // Thêm một món hàng vào hóa đơn
    public void themHang(String maSP, String tenSP, double donGia, int soLuong) {
        // Đã có trong hóa đơn thì cộng dồn
        for (int i = 0; i < soChiTiet; i++) {
            if (dsChiTiet[i].getMaSP().equals(maSP)) {
                dsChiTiet[i].setSoLuong(dsChiTiet[i].getSoLuong() + soLuong);
                return;
            }
        }
        if (soChiTiet == dsChiTiet.length) { // mảng đầy thì mở rộng gấp đôi
            dsChiTiet = java.util.Arrays.copyOf(dsChiTiet, soChiTiet * 2);
        }
        dsChiTiet[soChiTiet++] = new ChiTietHoaDon(maSP, tenSP, donGia, soLuong);
    }

    // Tổng tiền hàng = tổng tiền khách phải thanh toán
    public double tinhTongTienHang() {
        double tong = 0;
        for (int i = 0; i < soChiTiet; i++) tong += dsChiTiet[i].tinhThanhTien();
        return tong;
    }

    // Tiền thừa trả lại cho khách
    public double tinhTienThua() {
        return Math.max(0, tienKhachDua - tinhTongTienHang());
    }

    public boolean laKhachLe() {
        return maKH == null || maKH.isEmpty();
    }

    public void xuat() { InHoaDon.chiTiet(this); }

    // Chuyển hóa đơn sang chuỗi (nhiều dòng) để ghi file
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
