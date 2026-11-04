import interfaces.IQuanLyDanhSach;
import java.io.*;
import java.util.function.IntFunction;
import java.util.function.Predicate;

/**
 * Lop cha TRUU TUONG cho moi danh sach (SanPham, NhanVien, KhachHang, HoaDon).
 * Phan chung (mang tu gian, them/xoa/tim, in, doc/ghi file) viet MOT lan o day;
 * lop con chi khai bao cac ham rieng: maCua, tieuDe, inMot, ghiDong, docDong.
 */
@SuppressWarnings("unchecked")
public abstract class DanhSachCoBan<T> implements IQuanLyDanhSach<T> {

    private T[] mang = (T[]) new Object[10];
    private int soLuong = 0;
    private final String ten; // ten phan tu dung cho thong bao ("san pham", ...)
    protected DanhSachCoBan(String ten) { this.ten = ten; }

    protected abstract String maCua(T t);              // ma dinh danh
    protected abstract void tieuDe();                  // in tieu de bang
    protected abstract void inMot(T t);                // in 1 phan tu
    protected abstract String ghiDong(T t);            // chuoi de ghi file
    protected T docDong(String dong) { return null; }  // doc 1 dong file (null: bo qua)

    private void them0(T t) { // mang day thi tu mo rong gap doi
        if (soLuong == mang.length) mang = java.util.Arrays.copyOf(mang, soLuong * 2);
        mang[soLuong++] = t;
    }

    @Override
    public boolean them(T t) {
        boolean trung = timTheoMa(maCua(t)) != null;
        if (trung) System.out.println("  Loi: Ma " + ten + " " + maCua(t) + " da ton tai!");
        else them0(t);
        return !trung;
    }

    @Override
    public boolean xoa(String ma) {
        for (int i = 0; i < soLuong; i++) {
            if (maCua(mang[i]).equalsIgnoreCase(ma)) {
                System.arraycopy(mang, i + 1, mang, i, soLuong - i - 1);
                mang[--soLuong] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public T timTheoMa(String ma) {
        for (int i = 0; i < soLuong; i++) {
            if (maCua(mang[i]).equalsIgnoreCase(ma)) return mang[i];
        }
        return null;
    }

    @Override
    public void xuatDanhSach() {
        if (soLuong == 0) System.out.println("  Danh sach " + ten + " hien dang trong.");
        else tieuDe();
        for (int i = 0; i < soLuong; i++) inMot(mang[i]);
    }

    @Override
    public int getSoLuong() { return soLuong; }
    public T lay(int i) { return mang[i]; }

    // Loc phan tu thoa dieu kien; "tao" tao mang dung kieu, vi du SanPham[]::new
    public T[] loc(Predicate<T> dieuKien, IntFunction<T[]> tao) {
        int dem = 0;
        for (int i = 0; i < soLuong; i++) if (dieuKien.test(mang[i])) dem++;
        T[] kq = tao.apply(dem);
        for (int i = 0, k = 0; i < soLuong; i++) if (dieuKien.test(mang[i])) kq[k++] = mang[i];
        return kq;
    }

    public void luuFile(String duongDan) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(duongDan))) {
            for (int i = 0; i < soLuong; i++) pw.println(ghiDong(mang[i]));
            System.out.println("  Da luu " + soLuong + " " + ten + ".");
        } catch (IOException e) { System.out.println("  Loi ghi file " + ten + ": " + e.getMessage()); }
    }

    public void docFile(String duongDan) {
        if (!new File(duongDan).exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(duongDan))) {
            String dong;
            int dem = 0;
            while ((dong = br.readLine()) != null) {
                T t = dong.trim().isEmpty() ? null : docDong(dong);
                if (t != null && timTheoMa(maCua(t)) == null) { them0(t); dem++; }
            }
            System.out.println("  Da doc " + dem + " " + ten + ".");
        } catch (IOException e) { System.out.println("  Loi doc file " + ten + ": " + e.getMessage()); }
    }
}
