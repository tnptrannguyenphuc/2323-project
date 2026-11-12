// Lop trung tam quan ly sieu thi: luu cac danh sach va xu ly nghiep vu chinh.
// Su dung Singleton Pattern: chi ton tai duy nhat 1 doi tuong SieuThi trong suot chuong trinh.

import model.*;
import java.io.File;

public class SieuThi {

    public static final String TEN = "Sieu Thi Mini ABC";
    // Duong dan thu muc luu file du lieu
    // Tu dong tim thu muc data o goc du an, du chay tu goc hay tu thu muc src/
    private static final String DATA_DIR = timThuMucData();

    private static String timThuMucData() {
        if (new File("data").isDirectory()) return "data/";
        if (new File("../data").isDirectory()) return "../data/";
        return "data/";
    }

    // === Singleton Pattern ===
    private static SieuThi instance = null;

    // Ham tao private: khong cho phep ben ngoai khoi tao truc tiep
    private SieuThi() {}

    // Ham lay the hien duy nhat cua SieuThi
    public static SieuThi getInstance() {
        if (instance == null) {
            instance = new SieuThi();
        }
        return instance;
    }
    // ========================

    // Cac danh sach quan ly thanh phan
    private DanhSachSanPham   dsSP  = new DanhSachSanPham();
    private DanhSachNhanVien  dsNV  = new DanhSachNhanVien();
    private DanhSachKhachHang dsKH  = new DanhSachKhachHang();
    private DanhSachHoaDon    dsHD  = new DanhSachHoaDon();

    // Nhan vien hien tai dang dang nhap he thong
    private NhanVien nguoiDangNhap = null;

    // Xu ly dang nhap
    public NhanVien dangNhap(String maNV, String matKhau) {
        NhanVien nv = dsNV.dangNhap(maNV, matKhau);
        if (nv != null) {
            nguoiDangNhap = nv;
        }
        return nv;
    }

    // Xu ly them hang vao hoa don
    public String themVaoGio(HoaDon hd, SanPham sp, int soLuong) {
        if (!sp.coTheBan()) {
            return "San pham het hang hoac da het han su dung.";
        }
        if (soLuong > sp.getSoLuong()) {
            return "Kho chi con lai " + sp.getSoLuong() + " " + sp.getDonVi() + ".";
        }
        // Them hang vao hoa don voi don gia ban
        hd.themHang(sp.getMaSP(), sp.getTenSP(), sp.getGiaBan(), soLuong);
        sp.xuatKho(soLuong); // Giam ton kho
        return null;         // Tra ve null la thanh cong
    }

    // Hoan tat thanh toan hoa don
    public void thanhToan(HoaDon hd, KhachHang kh) {
        if (kh != null) {
            // Cong diem tich luy cho khach hang thanh vien dua tren tong tien hoa don
            kh.congDiem(hd.tinhTongTienHang());
        }
        dsHD.them(hd);
    }

    // Luu toan bo du lieu ra file text
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

    // Doc du lieu tu file text vao chuong trinh
    public void docDuLieu() {
        dsSP.docFile(DATA_DIR + "sanpham.txt");
        dsNV.docFile(DATA_DIR + "nhanvien.txt");
        dsKH.docFile(DATA_DIR + "khachhang.txt");
        dsHD.docFile(DATA_DIR + "hoadon.txt");
    }

    // Cac ham getter lay danh sach
    public DanhSachSanPham getDsSP() { return dsSP; }
    public DanhSachNhanVien getDsNV() { return dsNV; }
    public DanhSachKhachHang getDsKH() { return dsKH; }
    public DanhSachHoaDon getDsHD() { return dsHD; }
    public NhanVien getNguoiDangNhap() { return nguoiDangNhap; }
}

