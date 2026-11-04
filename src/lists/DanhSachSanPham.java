import interfaces.ICoHanSuDung;
import model.*;

/**
 * Danh sach san pham: ke thua DanhSachCoBan<SanPham>.
 * Mang chua ThucPham / DoUong (da hinh); them cac ham tim kiem nang cao.
 */
public class DanhSachSanPham extends DanhSachCoBan<SanPham> {

    public DanhSachSanPham() { super("san pham"); }

    @Override protected String maCua(SanPham s)   { return s.getMaSP(); }
    @Override protected void tieuDe()             { SanPham.inTieuDe(); }
    @Override protected void inMot(SanPham s)     { s.xuat(); } // da hinh
    @Override protected String ghiDong(SanPham s) { return s.toFileString(); }

    @Override
    protected SanPham docDong(String dong) {
        String[] t = dong.split(";", -1);
        if (t[0].equals("TP")) return ThucPham.tuChuoi(t);
        if (t[0].equals("DU")) return DoUong.tuChuoi(t);
        return null;
    }

    // Tim kiem co ban theo ten
    public SanPham[] timTheoTen(String tuKhoa) {
        return loc(s -> s.getTenSP().toLowerCase().contains(tuKhoa.toLowerCase()), SanPham[]::new);
    }

    /**
     * TIM KIEM NANG CAO: ket hop ten + loai + khoang gia ban.
     * @param maLoai 0: tat ca, 1: thuc pham, 2: do uong
     * @param giaTu  gia thap nhat (<= 0: khong gioi han)
     * @param giaDen gia cao nhat (<= 0: khong gioi han)
     */
    public SanPham[] timKiemNangCao(String tuKhoa, int maLoai, double giaTu, double giaDen) {
        String tk = tuKhoa == null ? "" : tuKhoa.trim().toLowerCase();
        return loc(sp -> sp.getTenSP().toLowerCase().contains(tk)
                && (maLoai != 1 || sp instanceof ThucPham)
                && (maLoai != 2 || sp instanceof DoUong)
                && (giaTu <= 0 || sp.getGiaBan() >= giaTu)
                && (giaDen <= 0 || sp.getGiaBan() <= giaDen), SanPham[]::new);
    }

    // Loc hang sap het han / da het han, nhan biet qua Interface ICoHanSuDung
    public void xuatHangCanDateVaHetHan() {
        System.out.println("\n  --- DANH SACH HANG SAP HET HAN / DA HET HAN ---");
        SanPham[] kq = loc(sp -> sp instanceof ICoHanSuDung
                && (((ICoHanSuDung) sp).daHetHan() || ((ICoHanSuDung) sp).sapHetHan()), SanPham[]::new);
        if (kq.length == 0) {
            System.out.println("  Khong co san pham nao sap het han hoac het han!");
            return;
        }
        SanPham.inTieuDe();
        for (SanPham sp : kq) sp.xuat();
    }
}
