package interfaces;

public interface IQuanLyDanhSach<T> {

    boolean them(T doiTuong);

    boolean xoa(String ma);

    T timTheoMa(String ma);

    void xuatDanhSach();

    int getSoLuong();
}
