public class MenuController extends Controller {

    // Chuc nang 1-5 la cac Controller con (chon bang chi so, goi chay() - da hinh)
    private final Controller[] manHinh;

    public MenuController(SieuThi st) {
        super(st);
        manHinh = new Controller[] {null, new SanPhamController(st), new NhanVienController(st),
                new KhachHangController(st), new BanHangController(st), new HoaDonController(st)};
    }

    @Override
    public void chay() {
        int chon;
        do {
            System.out.println("\n================ MENU CHINH ================");
            System.out.println("  1. Quan ly san pham (tim kiem, thong ke kho)");
            System.out.println("  2. Quan ly nhan vien (thong ke quy luong)");
            System.out.println("  3. Quan ly khach hang");
            System.out.println("  4. Ban hang (lap hoa don moi)");
            System.out.println("  5. Hoa don & Thong ke doanh thu");
            System.out.println("  6. Luu du lieu ra file");
            System.out.println("  0. Thoat");
            chon = ConsoleInput.nhapInt("Chon chuc nang (0-6): ", 0, 6);

            if (chon >= 1 && chon <= 5) {
                manHinh[chon].chay();
            } else if (chon == 6) {
                st.luuDuLieu();
            } else {
                st.luuDuLieu();
                System.out.println("Da luu du lieu an toan. Hen gap lai!");
            }
        } while (chon != 0);
    }
}
