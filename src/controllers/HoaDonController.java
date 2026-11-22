import model.HoaDon;

public class HoaDonController extends Controller {

    public HoaDonController(SieuThi st) { super(st); }

    @Override
    public void chay() {
        DanhSachHoaDon ds = st.getDsHD();
        int chon;
        do {
            System.out.println("\n----- QUAN LY HOA DON & DOANH THU -----");
            System.out.println("  1. Xem danh sach tat ca hoa don");
            System.out.println("  2. Xem chi tiet mot hoa don theo ma");
            System.out.println("  3. THONG KE DOANH THU & GIAO DICH (Thong ke 2)");
            System.out.println("  0. Quay lai menu chinh");
            chon = ConsoleInput.nhapInt("Chon: ", 0, 3);

            if (chon == 1) {
                ds.xuatDanhSach();
            } else if (chon == 2) {
                String ma = ConsoleInput.nhapChuoi("Nhap ma hoa don (vi du HD001): ");
                HoaDon hd = ds.timTheoMa(ma);
                if (hd == null) System.out.println("Khong tim thay hoa don " + ma);
                else hd.xuat();
            } else if (chon == 3) {
                ds.thongKeDoanhThu();
            }
        } while (chon != 0);
    }
}
