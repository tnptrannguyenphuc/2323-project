package model;

import java.time.format.DateTimeFormatter;

/**
 * Lớp InHoaDon: chỉ lo việc hiển thị hóa đơn ra console (tách khỏi HoaDon để HoaDon gọn).
 */
public class InHoaDon {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final String FMT_TIEN = "  %-42s %,17.0f đ%n";

    // In hóa đơn hoàn chỉnh
    public static void chiTiet(HoaDon hd) {
        System.out.println("=".repeat(65));
        System.out.println("  HÓA ĐƠN BÁN HÀNG: " + hd.getMaHD() + "     Ngày: " + hd.getThoiGian().format(FMT));
        System.out.println("  Thu ngân: " + hd.getMaNV() + " | Khách hàng: "
                + (hd.laKhachLe() ? "Khách vãng lai" : hd.getMaKH()));
        System.out.println("-".repeat(65));
        System.out.printf("  %-25s %4s %14s %15s%n", "Tên mặt hàng", "SL", "Đơn giá", "Thành tiền");
        System.out.println("-".repeat(65));
        for (int i = 0; i < hd.getSoChiTiet(); i++) {
            ChiTietHoaDon ct = hd.getChiTiet(i);
            System.out.printf("  %-25s %4d %,14.0f %,15.0f%n",
                    ct.getTenSP(), ct.getSoLuong(), ct.getDonGia(), ct.tinhThanhTien());
        }
        System.out.println("-".repeat(65));
        System.out.printf(FMT_TIEN, "Tổng tiền hàng:", hd.tinhTongTienHang());
        if (hd.getGiamThanhVien() > 0) {
            System.out.printf(FMT_TIEN, "Ưu đãi hạng thành viên:", -hd.getGiamThanhVien());
        }
        System.out.printf(FMT_TIEN, "TỔNG CẦN THANH TOÁN (" + hd.getPhuongThuc() + "):", hd.tinhTongThanhToan());
        if (hd.getPhuongThuc().equals(HoaDon.PHUONG_THUC[0])) {
            System.out.printf(FMT_TIEN, "Tiền khách đưa:", hd.getTienKhachDua());
            System.out.printf(FMT_TIEN, "Tiền thừa trả lại:", hd.tinhTienThua());
        }
        System.out.println("=".repeat(65));
    }

    // Bảng tóm tắt: tiêu đề + 1 dòng cho mỗi hóa đơn
    public static void tieuDeNgan() {
        System.out.printf("%-8s %-18s %-8s %-12s %16s%n",
                "Mã HĐ", "Thời gian", "Thu ngân", "Mã KH", "Tổng thanh toán");
        System.out.println("-".repeat(65));
    }

    public static void ngan(HoaDon hd) {
        System.out.printf("%-8s %-18s %-8s %-12s %,16.0f đ%n",
                hd.getMaHD(), hd.getThoiGian().format(FMT), hd.getMaNV(),
                hd.laKhachLe() ? "Khách lẻ" : hd.getMaKH(), hd.tinhTongThanhToan());
    }
}
