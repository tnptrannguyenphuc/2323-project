import model.HoaDon;
import model.InHoaDon;
import java.time.LocalDate;

public class DanhSachHoaDon extends DanhSachCoBan<HoaDon> {

    public DanhSachHoaDon() { super("hoa don"); }

    @Override protected String maCua(HoaDon h)   { return h.getMaHD(); }
    @Override protected void tieuDe()            { InHoaDon.tieuDeNgan(); }
    @Override protected void inMot(HoaDon h)    { InHoaDon.ngan(h); }
    @Override protected String ghiDong(HoaDon h) { return h.toFileString(); }

    private HoaDon hdDangDoc = null;

    @Override
    protected HoaDon docDong(String dong) {
        String[] t = dong.split(";", -1);
        if (t[0].equals("CT")) {
            if (hdDangDoc != null) {
                hdDangDoc.themHang(t[1], t[2], Double.parseDouble(t[3]), Integer.parseInt(t[4]));
            }
            return null;
        }
        hdDangDoc = HoaDon.tuChuoi(t);
        return hdDangDoc;
    }

    public String taoMaMoi() {
        int max = 0;
        for (int i = 0; i < getSoLuong(); i++) {
            String ma = lay(i).getMaHD();
            try {
                int so = Integer.parseInt(ma.substring(2));
                if (so > max) max = so;
            } catch (NumberFormatException e) {
            }
        }
        return "HD" + String.format("%03d", max + 1);
    }

    public void thongKeDoanhThu() {
        int n = getSoLuong(), donLe = 0, donHomNay = 0;
        if (n == 0) {
            System.out.println("  Chua co hoa don nao de thong ke doanh thu!");
            return;
        }
        double thu = 0, homNay = 0;
        double[] theoPT = new double[HoaDon.PHUONG_THUC.length];
        for (int i = 0; i < n; i++) {
            HoaDon hd = lay(i);
            thu += hd.tinhTongTienHang();
            if (hd.laKhachLe()) donLe++;
            for (int k = 0; k < theoPT.length; k++) {
                if (HoaDon.PHUONG_THUC[k].equals(hd.getPhuongThuc())) theoPT[k] += hd.tinhTongTienHang();
            }
            if (hd.getThoiGian().toLocalDate().equals(LocalDate.now())) {
                homNay += hd.tinhTongTienHang();
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
        System.out.printf("  TONG DOANH THU                : %,15.0f VND%n", thu);
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
