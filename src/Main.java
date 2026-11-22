import model.NhanVien;

public class Main {

    public static void main(String[] args) {
        SieuThi st = SieuThi.getInstance();
        System.out.println("==================================================");
        System.out.println("       CHAO MUNG DEN VOI " + SieuThi.TEN.toUpperCase());
        System.out.println("==================================================");

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
