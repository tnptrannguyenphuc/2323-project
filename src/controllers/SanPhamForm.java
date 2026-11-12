import model.*;
import java.time.LocalDate;

/**
 * Cac man hinh nhap lieu cua san pham (them moi, sua, nhap kho).
 * Doc du lieu qua ConsoleInput nen khong can truyen Scanner.
 */
public class SanPhamForm {

    public static SanPham nhapMoi(DanhSachSanPham ds) {
        System.out.println("  Chon loai san pham: 1-Thuc pham  2-Do uong");
        int loai = ConsoleInput.nhapInt("  Chon loai (1-2): ", 1, 2);
        String ma = ConsoleInput.nhapMaMoi("  Ma SP (vd: TP005, DU004): ", ds);
        String ten = ConsoleInput.nhapChuoi("  Ten san pham: ");
        String dv = ConsoleInput.nhapChuoi("  Don vi tinh (kg/chai/hop/vi/cai): ");
        double gn = ConsoleInput.nhapDouble("  Gia nhap: ", 0);
        double gb = ConsoleInput.nhapDouble("  Gia ban (>= gia nhap): ", gn);
        int sl = ConsoleInput.nhapInt("  So luong ton: ", 0, Integer.MAX_VALUE);

        if (loai == 2) {
            double dt = ConsoleInput.nhapDouble("  Dung tich: ", 0);
            return new DoUong(ma, ten, dv, gn, gb, sl, dt);
        }
        String hsd = ConsoleInput.nhapChuoi("  Han su dung (yyyy-MM-dd, vi du 2026-12-31): ");
        LocalDate ngay;
        try {
            ngay = LocalDate.parse(hsd);
        } catch (Exception e) {
            System.out.println("  Dinh dang sai, mac dinh them 7 ngay nua.");
            ngay = LocalDate.now().plusDays(7);
        }
        double kl = ConsoleInput.nhapDouble("  Khoi luong: ", 0);
        return new ThucPham(ma, ten, dv, gn, gb, sl, ngay, kl);
    }

    public static void sua(DanhSachSanPham ds) {
        String ma = ConsoleInput.nhapChuoi("  Nhap ma SP can sua: ");
        SanPham sp = ds.timTheoMa(ma);
        if (sp == null) {
            System.out.println("  Khong tim thay san pham co ma: " + ma);
            return;
        }
        System.out.println("  Dang sua san pham: " + sp);
        String ten = ConsoleInput.nhapChuoi("  Ten moi (Nhan Enter neu giu nguyen): ");
        if (!ten.isEmpty())
            sp.setTenSP(ten);
        double gb = ConsoleInput.nhapDouble("  Gia ban moi (Nhap 0 de giu nguyen): ", 0);
        if (gb > 0)
            sp.setGiaBan(gb);
        int sl = ConsoleInput.nhapInt("  So luong ton moi (Nhap -1 de giu nguyen): ", -1, Integer.MAX_VALUE);
        if (sl >= 0)
            sp.setSoLuong(sl);
        if (sp instanceof DoUong) {
            double dt = ConsoleInput.nhapDouble("  Dung tich moi (Nhap 0 de giu nguyen): ", 0);
            if (dt > 0)
                ((DoUong) sp).setDungTich(dt);
        } else if (sp instanceof ThucPham) {
            double kl = ConsoleInput.nhapDouble("  Khoi luong moi (Nhap 0 de giu nguyen): ", 0);
            if (kl > 0)
                ((ThucPham) sp).setKhoiLuong(kl);
        }
        
        System.out.println("  Cap nhat san pham thanh cong!");
    }

    public static void nhapKho(DanhSachSanPham ds) {
        SanPham sp = ds.timTheoMa(ConsoleInput.nhapChuoi("  Nhap ma SP can nhap kho: "));
        if (sp == null) {
            System.out.println("  Khong tim thay san pham!");
            return;
        }
        sp.nhapKho(ConsoleInput.nhapInt("  Nhap so luong nhap them: ", 1, Integer.MAX_VALUE));
        System.out.println("  Nhap kho thanh cong. So luong ton moi: " + sp.getSoLuong());
    }
}
