import model.NhanVien;

public class LoginController {

    private static final int SO_LAN_TOI_DA = 3;
    private final SieuThi st;

    public LoginController(SieuThi st) {
        this.st = st;
    }

    public NhanVien thucHienDangNhap() {
        System.out.println("\n--- DANG NHAP HE THONG ---");
        System.out.println("(Tai khoan mau: QL01 / mk: 123 hoac TN01 / mk: 123)");
        for (int i = 1; i <= SO_LAN_TOI_DA; i++) {
            String ma = ConsoleInput.nhapChuoi("Ma nhan vien: ");
            String mk = ConsoleInput.nhapChuoi("Mat khau: ");
            NhanVien nv = st.dangNhap(ma, mk);
            if (nv != null) {
                return nv;
            }
            System.out.println("Sai ma hoac mat khau! (Con " + (SO_LAN_TOI_DA - i) + " lan thu)");
        }
        return null;
    }
}

