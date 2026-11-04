import model.KhachHang;

/**
 * Danh sach khach hang: ke thua DanhSachCoBan<KhachHang>, chi viet phan rieng cua khach hang.
 */
public class DanhSachKhachHang extends DanhSachCoBan<KhachHang> {

    public DanhSachKhachHang() { super("khach hang"); }

    @Override protected String maCua(KhachHang k)     { return k.getMaKH(); }
    @Override protected void tieuDe()                 { KhachHang.inTieuDe(); }
    @Override protected void inMot(KhachHang k)       { k.xuat(); }
    @Override protected String ghiDong(KhachHang k)   { return k.toFileString(); }
    @Override protected KhachHang docDong(String d)   { return KhachHang.tuChuoi(d); }

    public KhachHang timTheoSDT(String sdt) {
        for (int i = 0; i < getSoLuong(); i++) {
            if (lay(i).getSdt().equals(sdt)) return lay(i);
        }
        return null;
    }

    public KhachHang[] timTheoTen(String tuKhoa) {
        return loc(k -> k.getHoTen().toLowerCase().contains(tuKhoa.toLowerCase()), KhachHang[]::new);
    }

    // Tao khach hang moi voi ma tu tang
    public KhachHang taoMoi(String hoTen, String sdt) {
        return new KhachHang("KH" + String.format("%03d", getSoLuong() + 1), hoTen, sdt, 0);
    }
}
