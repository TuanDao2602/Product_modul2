package ra.bussiness.design;

public interface IUser<T,E> extends IShop<T,E> {
    boolean searchByName(String str);
    boolean delete(T t);
}
