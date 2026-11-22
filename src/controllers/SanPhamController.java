import model.SanPham;

public class SanPhamController extends Controller {

    public SanPhamController(SieuThi st) { super(st); }

    @Override
    public void chay() {
        DanhSachSanPham ds = st.getDsSP();
        int chon;
        do {
            System.out.println("\n----- QUAN LY SAN PHAM -----");
            System.out.println("  1. Xem danh sach san pham");
            System.out.println("  2. Them san pham moi");
            System.out.println("  3. Sua thong tin san pham");
            System.out.println("  4. Xoa san pham");
            System.out.println("  5. Tim kiem co ban (theo ten)");
            System.out.println("  6. TIM KIEM NANG CAO (ket hop: ten + loai + khoang gia)");
            System.out.println("  7. Loc san pham sap het han / da het han (Interface ICoHanSuDung)");
            System.out.println("  8. Nhap them hang vao kho");
            System.out.println("  9. THONG KE KHO HANG VA PHAN LOAI (Thong ke 1)");
            System.out.println("  0. Quay lai menu chinh");
            chon = ConsoleInput.nhapInt("Chon: ", 0, 9);

            switch (chon) {
                case 1: ds.xuatDanhSach(); break;
                case 2:
                    if (ds.them(SanPhamForm.nhapMoi(ds))) System.out.println("Da them san pham thanh cong!");
                    break;
                case 3: SanPhamForm.sua(ds); break;
                case 4:
                    String ma = ConsoleInput.nhapChuoi("Nhap ma SP can xoa: ");
                    System.out.println(ds.xoa(ma) ? "Da xoa san pham " + ma : "Khong tim thay san pham co ma " + ma);
                    break;
                case 5:
                    inKetQua(ds.timTheoTen(ConsoleInput.nhapChuoi("Nhap tu khoa ten san pham: ")));
                    break;
                case 6: timKiemNangCao(ds); break;
                case 7: ds.xuatHangCanDateVaHetHan(); break;
                case 8: SanPhamForm.nhapKho(ds); break;
                case 9: ThongKeKho.thongKe(ds); break;
            }
        } while (chon != 0);
    }

    private void timKiemNangCao(DanhSachSanPham ds) {
        System.out.println("\n--- TIM KIEM SAN PHAM NANG CAO ---");
        String tk = ConsoleInput.nhapChuoi("Tu khoa trong ten (Enter neu bo qua): ");
        System.out.println("Phan loai can loc: 0-Tat ca  1-Thuc pham  2-Do uong");
        int loai = ConsoleInput.nhapInt("Chon loai (0-2): ", 0, 2);
        double giaTu = ConsoleInput.nhapDouble("Gia ban thap nhat (0 neu khong gioi han): ", 0);
        double giaDen = ConsoleInput.nhapDouble("Gia ban cao nhat (0 neu khong gioi han): ", 0);
        inKetQua(ds.timKiemNangCao(tk, loai, giaTu, giaDen));
    }

    private void inKetQua(SanPham[] kq) {
        if (kq.length == 0) {
            System.out.println("Khong tim thay san pham nao thoa man!");
            return;
        }
        System.out.println("Ket qua (" + kq.length + " san pham):");
        SanPham.inTieuDe();
        for (SanPham sp : kq) sp.xuat();
    }
}
