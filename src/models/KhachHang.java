package model;

/**
 * Lớp KhachHang: đại diện cho khách hàng thành viên của siêu thị.
 *
 * Tính năng nổi bật:
 *   - Tích lũy điểm sau mỗi lần mua hàng (cứ 10.000 đ = 1 điểm).
 *   - Hạng thành viên tăng dần: Thường -> Bạc -> Vàng -> Kim cương.
 *   - Hạng cao hơn được giảm giá nhiều hơn khi thanh toán hóa đơn.
 */
public class KhachHang {

    // Hạng thành viên: index 0=Thường, 1=Bạc, 2=Vàng, 3=Kim cương
    public static final String[] TEN_HANG  = {"Thường", "Bạc", "Vàng", "Kim cương"};
    // Điểm tối thiểu để đạt mỗi hạng tương ứng
    public static final int[]    DIEM_MIN  = {0, 100, 500, 1500};
    // % giảm giá theo từng hạng (0%, 1%, 2%, 3%)
    public static final double[] GIAM_HANG = {0.0, 0.01, 0.02, 0.03};

    private String maKH;        // mã khách hàng (ví dụ: KH001)
    private String hoTen;       // họ và tên
    private String sdt;         // số điện thoại (dùng để tìm kiếm nhanh khi bán hàng)
    private int    diemTichLuy; // tổng điểm đã tích lũy

    // Hàm khởi tạo
    public KhachHang(String maKH, String hoTen, String sdt, int diemTichLuy) {
        this.maKH        = maKH;
        this.hoTen       = hoTen;
        this.sdt         = sdt;
        this.diemTichLuy = diemTichLuy;
    }

    // Tìm chỉ số hạng hiện tại (0..3) dựa trên điểm tích lũy
    public int getChiSoHang() {
        for (int i = DIEM_MIN.length - 1; i >= 0; i--) {
            if (diemTichLuy >= DIEM_MIN[i]) return i;
        }
        return 0; // mặc định: hạng Thường
    }

    // Tên hạng thành viên hiện tại
    public String getHangTV() { 
        return TEN_HANG[getChiSoHang()]; 
    }

    // % giảm giá theo hạng hiện tại
    public double getPhanTramGiam() { 
        return GIAM_HANG[getChiSoHang()]; 
    }

    // Cộng điểm sau khi mua hàng: cứ 10.000 đ = 1 điểm
    public void congDiem(double soTienMua) {
        int diemMoi = (int)(soTienMua / 10_000);
        if (diemMoi > 0) diemTichLuy += diemMoi;
    }

    // In tiêu đề bảng danh sách khách hàng
    public static void inTieuDe() {
        System.out.printf("%-7s %-25s %-13s %10s %-12s%n",
                "Mã KH", "Họ và tên", "Số ĐT", "Điểm", "Hạng TV");
        System.out.println("-".repeat(70));
    }

    // In thông tin 1 dòng
    public void xuat() {
        System.out.printf("%-7s %-25s %-13s %,10d %-12s%n",
                maKH, hoTen, sdt, diemTichLuy, getHangTV());
    }

    // Chuyển thành chuỗi để ghi file
    public String toFileString() {
        return maKH + ";" + hoTen + ";" + sdt + ";" + diemTichLuy;
    }

    // Tạo đối tượng KhachHang từ 1 dòng trong file
    public static KhachHang tuChuoi(String dong) {
        String[] t = dong.split(";", -1);
        return new KhachHang(t[0], t[1], t[2], Integer.parseInt(t[3]));
    }

    // Getter / Setter
    public String getMaKH()   { return maKH; }
    public String getHoTen()  { return hoTen; }
    public String getSdt()    { return sdt; }
    public int    getDiem()   { return diemTichLuy; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    public void setSdt(String sdt)     { this.sdt   = sdt; }
}
