import interfaces.IQuanLyDanhSach;
import java.util.Scanner;

/**
 * Lop ConsoleInput: noi DUY NHAT doc du lieu tu ban phim.
 * Scanner nam private ben trong, cac lop khac chi goi ConsoleInput.nhapXxx(...)
 * nen KHONG can truyen Scanner qua tham so.
 */
public class ConsoleInput {

    private static final Scanner SC = new Scanner(System.in);

    private ConsoleInput() {}

    public static String nhapChuoi(String thongBao) {
        System.out.print(thongBao);
        return SC.nextLine().trim();
    }

    // Nhap ma moi cho danh sach: khong duoc de trong, tu doi sang chu hoa,
    // va khong duoc trung voi ma da co trong danh sach (kiem tra ngay khi nhap)
    public static String nhapMaMoi(String thongBao, IQuanLyDanhSach<?> ds) {
        while (true) {
            String ma = nhapChuoi(thongBao).toUpperCase();
            if (ma.isEmpty()) {
                System.out.println("  Loi: Ma khong duoc de trong!");
            } else if (ds.timTheoMa(ma) != null) {
                System.out.println("  Loi: Ma " + ma + " da ton tai, vui long nhap ma khac!");
            } else {
                return ma;
            }
        }
    }

    public static int nhapInt(String thongBao, int min, int max) {
        while (true) {
            try {
                int v = Integer.parseInt(nhapChuoi(thongBao));
                if (v >= min && v <= max) return v;
                System.out.println("  Loi: So phai nam trong khoang [" + min + " - " + max + "]");
            } catch (NumberFormatException e) {
                System.out.println("  Loi: Vui long nhap so nguyen hop le!");
            }
        }
    }

    public static double nhapDouble(String thongBao, double min) {
        while (true) {
            try {
                double v = Double.parseDouble(nhapChuoi(thongBao));
                if (v >= min) return v;
                System.out.println("  Loi: Gia tri phai lon hon hoac bang " + min);
            } catch (NumberFormatException e) {
                System.out.println("  Loi: Vui long nhap so thuc hop le!");
            }
        }
    }
}
