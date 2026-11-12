import model.KhachHang;

public class KhachHangController extends Controller {

    public KhachHangController(SieuThi st) { super(st); }

    @Override
    public void chay() {
        DanhSachKhachHang ds = st.getDsKH();
        int chon;
        do {
            System.out.println("\n----- QUAN LY KHACH HANG -----");
            System.out.println("  1. Xem danh sach khach hang");
            System.out.println("  2. Them khach hang thanh vien");
            System.out.println("  3. Xoa khach hang");
            System.out.println("  4. Tim kiem khach hang theo ten");
            System.out.println("  0. Quay lai menu chinh");
            chon = ConsoleInput.nhapInt("Chon: ", 0, 4);

            if (chon == 1) {
                ds.xuatDanhSach();
            } else if (chon == 2) {
                String ma  = ConsoleInput.nhapMaMoi("  Ma KH (vd: KH004): ", ds);
                String ten = ConsoleInput.nhapChuoi("  Ho ten: ");
                String sdt = ConsoleInput.nhapChuoi("  So dien thoai: ");
                KhachHang kh = ds.taoMoi(ma, ten, sdt);
                if (ds.them(kh)) System.out.println("Them khach hang thanh cong! Ma KH: " + kh.getMaKH());
            } else if (chon == 3) {
                String ma = ConsoleInput.nhapChuoi("Nhap ma KH can xoa: ");
                System.out.println(ds.xoa(ma) ? "Da xoa khach hang " + ma : "Khong tim thay khach hang nay.");
            } else if (chon == 4) {
                KhachHang[] kq = ds.timTheoTen(ConsoleInput.nhapChuoi("Nhap ten can tim: "));
                if (kq.length == 0) System.out.println("Khong tim thay khach hang phu hop.");
                else {
                    KhachHang.inTieuDe();
                    for (KhachHang kh : kq) kh.xuat();
                }
            }
        } while (chon != 0);
    }
}
