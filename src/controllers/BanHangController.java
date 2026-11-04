import model.*;

public class BanHangController extends Controller {

    public BanHangController(SieuThi st) { super(st); }

    @Override
    public void chay() {
        System.out.println("\n----- LAP HOA DON BAN HANG -----");
        String sdt = ConsoleInput.nhapChuoi("Nhap SDT khach hang thanh vien (Bam Enter neu la khach le): ");
        KhachHang kh = sdt.isEmpty() ? null : st.getDsKH().timTheoSDT(sdt);
        if (!sdt.isEmpty() && kh == null) {
            System.out.println("So dien thoai chua dang ky thanh vien, tinh la khach le.");
        }
        if (kh != null) {
            System.out.printf("Khach hang: %s | Hang: %s | Uu dai giam: %.0f%%%n",
                    kh.getHoTen(), kh.getHangTV(), kh.getPhanTramGiam() * 100);
        }

        HoaDon hd = new HoaDon(st.getDsHD().taoMaMoi(), st.getNguoiDangNhap().getMaNV(),
                kh != null ? kh.getMaKH() : "");
        chonHang(hd);
        if (hd.getSoChiTiet() == 0) {
            System.out.println("Hoa don chua co hang. Huy hoa don.");
            return;
        }
        thanhToan(hd, kh);
    }

    // Them hang vao hoa don cho den khi nhan Enter
    private void chonHang(HoaDon hd) {
        while (true) {
            String nhap = ConsoleInput.nhapChuoi("\nNhap ma san pham hoac ten (Bam Enter de hoan tat chon hang): ");
            if (nhap.isEmpty()) return;
            SanPham sp = timSanPham(nhap);
            if (sp == null) continue;

            int sl = ConsoleInput.nhapInt("Nhap so luong can mua: ", 1, Integer.MAX_VALUE);
            String loi = st.themVaoGio(hd, sp, sl);
            if (loi != null) System.out.println("Loi: " + loi);
            else System.out.printf("-> Da them: %s (x%d) = %.0f VND%n", sp.getTenSP(), sl, sp.getGiaBan() * sl);
        }
    }

    // Tim theo ma, neu khong co thi tim theo ten va cho chon; null neu khong chon duoc
    private SanPham timSanPham(String nhap) {
        SanPham sp = st.getDsSP().timTheoMa(nhap);
        if (sp != null) return sp;
        SanPham[] kq = st.getDsSP().timTheoTen(nhap);
        if (kq.length == 0) {
            System.out.println("Khong tim thay san pham nao!");
            return null;
        }
        int soHienThi = Math.min(5, kq.length);
        System.out.println("Ket qua tim kiem:");
        for (int i = 0; i < soHienThi; i++) System.out.println("  " + (i + 1) + ". " + kq[i]);
        int chon = ConsoleInput.nhapInt("Chon so thu tu (0 de bo qua): ", 0, soHienThi);
        return chon == 0 ? null : kq[chon - 1];
    }

    private void thanhToan(HoaDon hd, KhachHang kh) {
        System.out.println("\nChon phuong thuc thanh toan:");
        for (int i = 0; i < HoaDon.PHUONG_THUC.length; i++) {
            System.out.println("  " + (i + 1) + ". " + HoaDon.PHUONG_THUC[i]);
        }
        int pt = ConsoleInput.nhapInt("Chon: ", 1, HoaDon.PHUONG_THUC.length);
        hd.setPhuongThuc(HoaDon.PHUONG_THUC[pt - 1]);

        double canTra = hd.tinhTongTienHang() * (1 - (kh != null ? kh.getPhanTramGiam() : 0));
        System.out.printf("Tong tien can thanh toan: %.0f VND%n", canTra);
        hd.setTienKhachDua(pt == 1 ? ConsoleInput.nhapDouble("Nhap so tien khach dua: ", canTra) : canTra);

        st.thanhToan(hd, kh);
        System.out.println("\n----- KET QUA GIAO DICH -----");
        hd.xuat();
        System.out.println("Giao dich thanh cong! Ma hoa don: " + hd.getMaHD());
    }
}
