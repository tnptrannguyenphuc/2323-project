import model.*;
import java.time.LocalDate;

/**
 * Du lieu mau ban dau, nap vao SieuThi khi chua co file du lieu.
 */
public class DuLieuMau {

    public static void nap(SieuThi st) {
        LocalDate homNay = LocalDate.now();

        st.getDsNV().them(new QuanLy("QL01", "Nguyen Van An", "0901000001", 15000000, "123"));
        st.getDsNV().them(new ThuNgan("TN01", "Tran Thi Bich", "0902000002", 10000000, "123"));
        st.getDsNV().them(new ThuNgan("TN02", "Le Van Cuong", "0903000003", 10000000, "456"));
        st.getDsNV().them(new NhanVienKho("NK01", "Pham Van Dung", "0904000004", 9000000, "789"));

        // Da hinh: cung mot danh sach SanPham chua ca ThucPham va DoUong
        st.getDsSP().them(new ThucPham("TP001", "Thit heo ba chi", "kg", 80000, 120000, 30, homNay.plusDays(3), 1.0));
        st.getDsSP().them(new ThucPham("TP002", "Ca thu tuoi", "kg", 60000, 90000, 20, homNay.plusDays(1), 1.0));
        st.getDsSP().them(new ThucPham("TP003", "Bap cai xanh", "kg", 8000, 15000, 50, homNay.plusDays(5), 0.5));
        st.getDsSP().them(new ThucPham("TP004", "Trung ga", "vi", 25000, 38000, 100, homNay.plusDays(14), 0.6));
        st.getDsSP().them(new DoUong("DU001", "Nuoc loc Dasani", "thung", 90000, 130000, 40, 500));
        st.getDsSP().them(new DoUong("DU002", "Pepsi lon", "thung", 160000, 220000, 25, 330));
        st.getDsSP().them(new DoUong("DU003", "Bia Tiger lon", "thung", 250000, 350000, 15, 330));

        st.getDsKH().them(new KhachHang("KH001", "Nguyen Thi Hoa", "0911111111", 50));
        st.getDsKH().them(new KhachHang("KH002", "Tran Van Minh", "0922222222", 200));
        st.getDsKH().them(new KhachHang("KH003", "Le Thi Lan", "0933333333", 600));
    }
}
