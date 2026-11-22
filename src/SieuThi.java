import model.*;
import java.io.File;

public class SieuThi {

    public static final String TEN = "Sieu Thi Mini ABC";
    private static final String DATA_DIR = timThuMucData();

    private static String timThuMucData() {
        if (new File("data").isDirectory()) return "data/";
        if (new File("../data").isDirectory()) return "../data/";
        return "data/";
    }

    private static SieuThi instance = null;

    private SieuThi() {}

    public static SieuThi getInstance() {
        if (instance == null) {
            instance = new SieuThi();
        }
        return instance;
    }

    private DanhSachSanPham   dsSP  = new DanhSachSanPham();
    private DanhSachNhanVien  dsNV  = new DanhSachNhanVien();
    private DanhSachKhachHang dsKH  = new DanhSachKhachHang();
    private DanhSachHoaDon    dsHD  = new DanhSachHoaDon();

    private NhanVien nguoiDangNhap = null;

    public NhanVien dangNhap(String maNV, String matKhau) {
        NhanVien nv = dsNV.dangNhap(maNV, matKhau);
        if (nv != null) {
            nguoiDangNhap = nv;
        }
        return nv;
    }

    public String themVaoGio(HoaDon hd, SanPham sp, int soLuong) {
        if (!sp.coTheBan()) {
            return "San pham het hang hoac da het han su dung.";
        }
        int daCoTrongGio = 0;
        for (int i = 0; i < hd.getSoChiTiet(); i++) {
            if (hd.getChiTiet(i).getMaSP().equals(sp.getMaSP())) {
                daCoTrongGio = hd.getChiTiet(i).getSoLuong();
                break;
            }
        }
        if (daCoTrongGio + soLuong > sp.getSoLuong()) {
            return "Kho chi con lai " + sp.getSoLuong() + " " + sp.getDonVi()
                    + " (da co " + daCoTrongGio + " trong gio).";
        }
        hd.themHang(sp.getMaSP(), sp.getTenSP(), sp.getGiaBan(), soLuong);
        return null;
    }

    public void thanhToan(HoaDon hd, KhachHang kh) {
        for (int i = 0; i < hd.getSoChiTiet(); i++) {
            SanPham sp = dsSP.timTheoMa(hd.getChiTiet(i).getMaSP());
            if (sp != null) {
                sp.xuatKho(hd.getChiTiet(i).getSoLuong());
            }
        }
        if (kh != null) {
            kh.congDiem(hd.tinhTongTienHang());
        }
        dsHD.them(hd);
    }

    public void luuDuLieu() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        dsSP.luuFile(DATA_DIR + "sanpham.txt");
        dsNV.luuFile(DATA_DIR + "nhanvien.txt");
        dsKH.luuFile(DATA_DIR + "khachhang.txt");
        dsHD.luuFile(DATA_DIR + "hoadon.txt");
    }

    public void docDuLieu() {
        dsSP.docFile(DATA_DIR + "sanpham.txt");
        dsNV.docFile(DATA_DIR + "nhanvien.txt");
        dsKH.docFile(DATA_DIR + "khachhang.txt");
        dsHD.docFile(DATA_DIR + "hoadon.txt");
    }

    public DanhSachSanPham getDsSP() { return dsSP; }
    public DanhSachNhanVien getDsNV() { return dsNV; }
    public DanhSachKhachHang getDsKH() { return dsKH; }
    public DanhSachHoaDon getDsHD() { return dsHD; }
    public NhanVien getNguoiDangNhap() { return nguoiDangNhap; }
}
