package model;

public class KhachHang {

    public static final String[] TEN_HANG  = {"Thường", "Bạc", "Vàng", "Kim cương"};
    public static final int[]    DIEM_MIN  = {0, 100, 500, 1500};

    private String maKH;
    private String hoTen;
    private String sdt;
    private int    diemTichLuy;

    public KhachHang(String maKH, String hoTen, String sdt, int diemTichLuy) {
        this.maKH        = maKH;
        this.hoTen       = hoTen;
        this.sdt         = sdt;
        this.diemTichLuy = diemTichLuy;
    }

    public int getChiSoHang() {
        for (int i = DIEM_MIN.length - 1; i >= 0; i--) {
            if (diemTichLuy >= DIEM_MIN[i]) return i;
        }
        return 0;
    }

    public String getHangTV() {
        return TEN_HANG[getChiSoHang()];
    }

    public void congDiem(double soTienMua) {
        int diemMoi = (int)(soTienMua / 10_000);
        if (diemMoi > 0) diemTichLuy += diemMoi;
    }

    public static void inTieuDe() {
        System.out.printf("%-7s %-25s %-13s %10s %-12s%n",
                "Mã KH", "Họ và tên", "Số ĐT", "Điểm", "Hạng TV");
        System.out.println("-".repeat(70));
    }

    public void xuat() {
        System.out.printf("%-7s %-25s %-13s %,10d %-12s%n",
                maKH, hoTen, sdt, diemTichLuy, getHangTV());
    }

    public String toFileString() {
        return maKH + ";" + hoTen + ";" + sdt + ";" + diemTichLuy;
    }

    public static KhachHang tuChuoi(String dong) {
        String[] t = dong.split(";", -1);
        return new KhachHang(t[0], t[1], t[2], Integer.parseInt(t[3]));
    }

    public String getMaKH()   { return maKH; }
    public String getHoTen()  { return hoTen; }
    public String getSdt()    { return sdt; }
    public int    getDiem()   { return diemTichLuy; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    public void setSdt(String sdt)     { this.sdt   = sdt; }
}
