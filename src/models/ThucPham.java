package model;

import interfaces.ICoHanSuDung;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Lớp ThucPham: kế thừa SanPham và cài đặt Interface ICoHanSuDung.
 * Chỉ bổ sung phần riêng (hạn sử dụng, khối lượng); phần còn lại dùng lại từ SanPham.
 */
public class ThucPham extends SanPham implements ICoHanSuDung {

    private LocalDate ngayHetHan; // ngày hết hạn sử dụng
    private double    khoiLuong;  // khối lượng

    public ThucPham(String maSP, String tenSP, String donVi,
                    double giaNhap, double giaBan, int soLuong,
                    LocalDate ngayHetHan, double khoiLuong) {
        super(maSP, tenSP, donVi, giaNhap, giaBan, soLuong);
        this.ngayHetHan = ngayHetHan;
        this.khoiLuong  = khoiLuong;
    }

    @Override
    public String getLoai() { return "Thực phẩm"; }

    // Chỉ được bán khi còn hàng và chưa hết hạn
    @Override
    public boolean coTheBan() {
        return super.coTheBan() && !daHetHan();
    }

    // ===== Cài đặt Interface ICoHanSuDung =====
    @Override
    public LocalDate getNgayHetHan() { return ngayHetHan; }

    @Override
    public long soNgayConLai() {
        return ChronoUnit.DAYS.between(LocalDate.now(), ngayHetHan);
    }

    @Override
    public boolean daHetHan() { return soNgayConLai() < 0; }

    @Override
    public boolean sapHetHan() {
        long con = soNgayConLai();
        return con >= 0 && con <= 2;
    }

    // Ghi đè xuất để in thêm thông tin hạn sử dụng và khối lượng
    @Override
    public void xuat() {
        super.xuat();
        String tt = daHetHan() ? "[ĐÃ HẾT HẠN]" : (sapHetHan() ? "[SẮP HẾT HẠN]" : "[CÒN HẠN]");
        String klStr = (khoiLuong == (long) khoiLuong) ? String.format("%d", (long) khoiLuong) : String.format("%.2f", khoiLuong);
        System.out.printf("          -> Khối lượng: %s | HSD: %s | Còn: %d ngày %s%n",
                klStr, ngayHetHan, soNgayConLai(), tt);
    }

    @Override
    public String toFileString() {
        return "TP;" + super.toFileString() + ";" + ngayHetHan + ";" + khoiLuong;
    }

    // Tạo đối tượng từ dòng file đã tách bằng ";"
    public static ThucPham tuChuoi(String[] t) {
        double kl = 0.0;
        if (t.length > 9) {
            try { kl = Double.parseDouble(t[9]); } catch (Exception ignored) {}
        } else if (t.length > 8) {
            try { kl = Double.parseDouble(t[8]); } catch (Exception ignored) {}
        }
        return new ThucPham(t[1], t[2], t[3],
                Double.parseDouble(t[4]), Double.parseDouble(t[5]),
                Integer.parseInt(t[6]), LocalDate.parse(t[7]), kl);
    }

    public double getKhoiLuong() { return khoiLuong; }
    public void setKhoiLuong(double khoiLuong) { this.khoiLuong = khoiLuong; }
}
