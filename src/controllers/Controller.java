/**
 * Lop cha chung cua cac Controller: giu SieuThi dung chung va quy dinh ham chay().
 * Cac lop con khong phai tu khai bao lai truong st va constructor.
 */
public abstract class Controller {

    protected final SieuThi st;

    protected Controller(SieuThi st) { this.st = st; }

    // Chay man hinh/menu cua controller (da hinh: moi lop con chay khac nhau)
    public abstract void chay();
}
