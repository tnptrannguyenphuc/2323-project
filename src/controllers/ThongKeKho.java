import model.*;

/**
 * THONG KE 1: kho hang va phan loai san pham (doc du lieu tu DanhSachSanPham).
 */
public class ThongKeKho {

    private static final int NGUONG_TON_THAP = 10;

    public static void thongKe(DanhSachSanPham ds) {
        int soTP = 0, soDU = 0, tongTon = 0, tonThap = 0;
        double von = 0, banDuKien = 0;
        for (int i = 0; i < ds.getSoLuong(); i++) {
            SanPham sp = ds.lay(i);
            tongTon   += sp.getSoLuong();
            von       += sp.getGiaNhap() * sp.getSoLuong();
            banDuKien += sp.getGiaBan() * sp.getSoLuong();
            if (sp instanceof ThucPham) soTP++;
            else if (sp instanceof DoUong) soDU++;
            if (sp.getSoLuong() <= NGUONG_TON_THAP) tonThap++;
        }
        System.out.println("\n=======================================================");
        System.out.println("        THONG KE KHO HANG VA PHAN LOAI SAN PHAM        ");
        System.out.println("=======================================================");
        System.out.printf("  Tong so mat hang dang quan ly : %d mat hang%n", ds.getSoLuong());
        System.out.printf("  - Thuc pham                   : %d mat hang%n", soTP);
        System.out.printf("  - Do uong                     : %d mat hang%n", soDU);
        System.out.println("-------------------------------------------------------");
        System.out.printf("  Tong so luong ton kho         : %d san pham%n", tongTon);
        System.out.printf("  Tong gia tri von (gia nhap)   : %,15.0f VND%n", von);
        System.out.printf("  Tong gia tri ban ra du kien   : %,15.0f VND%n", banDuKien);
        System.out.printf("  Loi nhuan uoc tinh khi ban het: %,15.0f VND%n", banDuKien - von);
        System.out.printf("  So san pham can nhap them (<=%d) : %d mat hang%n", NGUONG_TON_THAP, tonThap);
        System.out.println("=======================================================");
        if (tonThap > 0) {
            System.out.println("  DANH SACH CANH BAO HANG TON THAP (<= " + NGUONG_TON_THAP + "):");
            SanPham.inTieuDe();
            for (SanPham sp : ds.loc(s -> s.getSoLuong() <= NGUONG_TON_THAP, SanPham[]::new)) sp.xuat();
        }
    }
}
