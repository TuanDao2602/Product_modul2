package ra.bussiness.design;

import ra.bussiness.entity.Product;

public interface IProduct<T,E> extends IShop<T,E>{
    boolean delete(E id);

    void displayData(T t);
}
