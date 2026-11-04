import model.NhanVien;

/**
 * Chuong trinh Quan Ly Sieu Thi Mini (Console).
 *
 * Cac yeu cau OOP:
 *   1. Dong goi     : thuoc tinh private/protected + getter.
 *   2. Ke thua      : SanPham -> ThucPham, DoUong; NhanVien -> QuanLy, ThuNgan, NhanVienKho;
 *                     DanhSachCoBan -> 4 danh sach; Controller -> cac controller.
 *   3. Da hinh      : SanPham[], NhanVien[] chua doi tuong con; Controller.chay().
 *   4. Truu tuong   : abstract class SanPham, NhanVien, DanhSachCoBan, Controller.
 *   5. Interface    : ICoHanSuDung (han su dung), IQuanLyDanhSach<T> (them/xoa/tim).
 *   6. Thong ke     : ThongKeKho, DanhSachHoaDon.thongKeDoanhThu, DanhSachNhanVien.thongKeLuong.
 *   7. Tim kiem NC  : DanhSachSanPham.timKiemNangCao, xuatHangCanDateVaHetHan.
 * Nhap lieu: chi qua ConsoleInput (khong truyen Scanner giua cac lop).
 */
public class Main {

    public static void main(String[] args) {
        SieuThi st = SieuThi.getInstance();
        System.out.println("==================================================");
        System.out.println("       CHAO MUNG DEN VOI " + SieuThi.TEN.toUpperCase());
        System.out.println("==================================================");

        // Doc du lieu tu file, neu chua co thi tao du lieu mau
        st.docDuLieu();
        if (st.getDsNV().getSoLuong() == 0) {
            System.out.println("Chua co file du lieu. He thong dang tao du lieu mau ban dau...");
            DuLieuMau.nap(st);
        }

        NhanVien nguoiDung = new LoginController(st).thucHienDangNhap();
        if (nguoiDung == null) {
            System.out.println("Dang nhap that bai qua 3 lan. Chuong trinh ket thuc!");
            return;
        }
        System.out.println("Dang nhap thanh cong! Xin chao " + nguoiDung.getHoTen()
                + " [" + nguoiDung.getVaiTro() + "]");

        new MenuController(st).chay();
    }
}
