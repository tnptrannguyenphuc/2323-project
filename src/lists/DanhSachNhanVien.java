import model.*;
import java.util.Arrays;

/**
 * Danh sach nhan vien: ke thua DanhSachCoBan<NhanVien>.
 * Mang chua QuanLy / ThuNgan / NhanVienKho (da hinh).
 */
public class DanhSachNhanVien extends DanhSachCoBan<NhanVien> {

    private static final String[] MA_LOAI = {"QU", "TH", "NV"};          // ma loai trong file
    private static final String[] TIEN_TO = {"QL", "TN", "NK"};          // tien to ma nhan vien
    private static final String[] TEN_LOAI = {"Quan ly", "Thu ngan", "Nhan vien kho"};

    public DanhSachNhanVien() { super("nhan vien"); }

    @Override protected String maCua(NhanVien n)   { return n.getMaNV(); }
    @Override protected void tieuDe()              { NhanVien.inTieuDe(); }
    @Override protected void inMot(NhanVien n)     { n.xuat(); } // da hinh
    @Override protected String ghiDong(NhanVien n) { return n.toFileString(); }

    @Override
    protected NhanVien docDong(String dong) {
        String[] t = dong.split(";", -1);
        return tao(t[0], t[1], t[2], t[3], Double.parseDouble(t[4]), t[5]);
    }

    // Tao dung lop con theo ma loai (dung chung cho doc file va nhap tay)
    private static NhanVien tao(String loai, String ma, String ten, String sdt, double luong, String mk) {
        switch (loai) {
            case "QU": return new QuanLy(ma, ten, sdt, luong, mk);
            case "TH": return new ThuNgan(ma, ten, sdt, luong, mk);
            case "NV": return new NhanVienKho(ma, ten, sdt, luong, mk);
            default:   return null;
        }
    }

    // loai: 1-Quan ly, 2-Thu ngan, 3-Nhan vien kho
    public NhanVien taoMoi(int loai, String ten, String sdt, double luong, String mk) {
        String ma = TIEN_TO[loai - 1] + String.format("%02d", getSoLuong() + 1);
        return tao(MA_LOAI[loai - 1], ma, ten, sdt, luong, mk);
    }

    public NhanVien dangNhap(String maNV, String matKhau) {
        for (int i = 0; i < getSoLuong(); i++) {
            if (lay(i).kiemTraDangNhap(maNV, matKhau)) return lay(i);
        }
        return null;
    }

    // THONG KE 3: quy luong theo vai tro (da hinh: tinhLuong())
    public void thongKeLuong() {
        int[] so = new int[MA_LOAI.length];
        double[] luong = new double[MA_LOAI.length];
        double tong = 0;
        for (int i = 0; i < getSoLuong(); i++) {
            int k = Arrays.asList(MA_LOAI).indexOf(lay(i).getMaLoai());
            so[k]++;
            luong[k] += lay(i).tinhLuong();
            tong += lay(i).tinhLuong();
        }
        System.out.println("\n=======================================================");
        System.out.println("           THONG KE QUY LUONG VA NHAN SU               ");
        System.out.println("=======================================================");
        System.out.printf("  Tong so nhan vien      : %d nguoi%n", getSoLuong());
        for (int k = 0; k < so.length; k++) {
            System.out.printf("  - %-19s: %d nguoi (Tong luong: %,.0f VND)%n", TEN_LOAI[k], so[k], luong[k]);
        }
        System.out.println("-------------------------------------------------------");
        System.out.printf("  TONG QUY LUONG HANG THANG: %,15.0f VND%n", tong);
        System.out.println("=======================================================");
    }
}
