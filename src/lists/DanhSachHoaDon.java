import model.HoaDon;
import model.InHoaDon;
import java.time.LocalDate;

/**
 * Danh sach hoa don: ke thua DanhSachCoBan<HoaDon>, them ma tu tang va THONG KE 2 (doanh thu).
 */
public class DanhSachHoaDon extends DanhSachCoBan<HoaDon> {

    public DanhSachHoaDon() { super("hoa don"); }

    @Override protected String maCua(HoaDon h)   { return h.getMaHD(); }
    @Override protected void tieuDe()            { InHoaDon.tieuDeNgan(); }
    @Override protected void inMot(HoaDon h)    { InHoaDon.ngan(h); }
    @Override protected String ghiDong(HoaDon h) { return h.toFileString(); }

    public String taoMaMoi() {
        return "HD" + String.format("%03d", getSoLuong() + 1);
    }

    // THONG KE 2: doanh thu, giam gia, theo phuong thuc thanh toan va trong ngay
    public void thongKeDoanhThu() {
        int n = getSoLuong(), donLe = 0, donHomNay = 0;
        if (n == 0) {
            System.out.println("  Chua co hoa don nao de thong ke doanh thu!");
            return;
        }
        double hang = 0, giam = 0, thu = 0, homNay = 0;
        double[] theoPT = new double[HoaDon.PHUONG_THUC.length];
        for (int i = 0; i < n; i++) {
            HoaDon hd = lay(i);
            hang += hd.tinhTongTienHang();
            giam += hd.getGiamThanhVien();
            thu  += hd.tinhTongThanhToan();
            if (hd.laKhachLe()) donLe++;
            for (int k = 0; k < theoPT.length; k++) {
                if (HoaDon.PHUONG_THUC[k].equals(hd.getPhuongThuc())) theoPT[k] += hd.tinhTongThanhToan();
            }
            if (hd.getThoiGian().toLocalDate().equals(LocalDate.now())) {
                homNay += hd.tinhTongThanhToan();
                donHomNay++;
            }
        }
        System.out.println("\n=======================================================");
        System.out.println("        THONG KE DOANH THU VA GIAO DICH BAN HANG       ");
        System.out.println("=======================================================");
        System.out.printf("  Tong so hoa don da lap        : %d don%n", n);
        System.out.printf("  - Don hang khach thanh vien   : %d don%n", n - donLe);
        System.out.printf("  - Don hang khach le           : %d don%n", donLe);
        System.out.println("-------------------------------------------------------");
        System.out.printf("  Tong tien hang truoc giam     : %,15.0f VND%n", hang);
        System.out.printf("  Tong tien uu dai thanh vien   : %,15.0f VND%n", giam);
        System.out.printf("  TONG DOANH THU THUC TE        : %,15.0f VND%n", thu);
        System.out.printf("  Gia tri trung binh moi don    : %,15.0f VND%n", thu / n);
        System.out.println("-------------------------------------------------------");
        System.out.println("  DOANH THU THEO PHUONG THUC THANH TOAN:");
        for (int k = 0; k < theoPT.length; k++) {
            System.out.printf("  - %-27s : %,15.0f VND%n", HoaDon.PHUONG_THUC[k], theoPT[k]);
        }
        System.out.println("-------------------------------------------------------");
        System.out.printf("  Doanh thu ngay hom nay (%s) : %,15.0f VND (%d don)%n",
                LocalDate.now(), homNay, donHomNay);
        System.out.println("=======================================================");
    }
}
