package model;

/**
 * Lớp DoUong: kế thừa SanPham, đại diện cho nước giải khát, bia, sữa...
 * Đặc điểm riêng: dung tích (dungTich).
 */
public class DoUong extends SanPham {

    // Dung tích (ví dụ: ml hoặc lít)
    private double dungTich;

    public DoUong(String maSP, String tenSP, String donVi,
                  double giaNhap, double giaBan, int soLuong,
                  double dungTich) {
        super(maSP, tenSP, donVi, giaNhap, giaBan, soLuong);
        this.dungTich = dungTich;
    }

    @Override
    public String getLoai() { return "Đồ uống"; }

    // Ghi đè xuất để in thêm thông tin dung tích
    @Override
    public void xuat() {
        super.xuat();
        String dtStr = (dungTich == (long) dungTich) ? String.format("%d", (long) dungTich) : String.format("%.2f", dungTich);
        System.out.printf("          -> Dung tích: %s%n", dtStr);
    }

    @Override
    public String toFileString() {
        return "DU;" + super.toFileString() + ";" + dungTich;
    }

    // Tạo đối tượng từ dòng file đã tách bằng ";"
    public static DoUong tuChuoi(String[] t) {
        return new DoUong(t[1], t[2], t[3],
                Double.parseDouble(t[4]), Double.parseDouble(t[5]),
                Integer.parseInt(t[6]), Double.parseDouble(t[7]));
    }

    public double getDungTich() { return dungTich; }
    public void setDungTich(double dungTich) { this.dungTich = dungTich; }
}
