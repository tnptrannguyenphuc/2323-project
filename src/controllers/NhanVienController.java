import model.NhanVien;

public class NhanVienController extends Controller {

    public NhanVienController(SieuThi st) { super(st); }

    @Override
    public void chay() {
        DanhSachNhanVien ds = st.getDsNV();
        int chon;
        do {
            System.out.println("\n----- QUAN LY NHAN VIEN -----");
            System.out.println("  1. Xem danh sach nhan vien");
            System.out.println("  2. Them nhan vien moi");
            System.out.println("  3. Xoa nhan vien");
            System.out.println("  4. THONG KE QUY LUONG VA NHAN SU (Thong ke 3)");
            System.out.println("  0. Quay lai menu chinh");
            chon = ConsoleInput.nhapInt("Chon: ", 0, 4);

            if (chon == 1) {
                ds.xuatDanhSach();
            } else if (chon == 2) {
                themNhanVien(ds);
            } else if (chon == 3) {
                xoaNhanVien(ds);
            } else if (chon == 4) {
                ds.thongKeLuong();
            }
        } while (chon != 0);
    }

    private void themNhanVien(DanhSachNhanVien ds) {
        System.out.println("  Chon vai tro: 1-Quan ly  2-Thu ngan  3-Nhan vien kho");
        int loai     = ConsoleInput.nhapInt("  Chon (1-3): ", 1, 3);
        String ten   = ConsoleInput.nhapChuoi("  Ho ten: ");
        String sdt   = ConsoleInput.nhapChuoi("  So dien thoai: ");
        double luong = ConsoleInput.nhapDouble("  Luong co ban: ", 0);
        String mk    = ConsoleInput.nhapChuoi("  Mat khau dang nhap: ");
        NhanVien nv = ds.taoMoi(loai, ten, sdt, luong, mk);
        if (ds.them(nv)) System.out.println("Da them nhan vien thanh cong! Ma NV: " + nv.getMaNV());
    }

    private void xoaNhanVien(DanhSachNhanVien ds) {
        String ma = ConsoleInput.nhapChuoi("Nhap ma NV can xoa: ");
        if (st.getNguoiDangNhap() != null && ma.equalsIgnoreCase(st.getNguoiDangNhap().getMaNV())) {
            System.out.println("Loi: Khong the xoa tai khoan dang dang nhap!");
        } else if (ds.xoa(ma)) {
            System.out.println("Da xoa nhan vien " + ma);
        } else {
            System.out.println("Khong tim thay ma nhan vien nay.");
        }
    }
}
