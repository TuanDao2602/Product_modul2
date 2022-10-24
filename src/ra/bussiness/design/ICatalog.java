package ra.bussiness.design;

import ra.bussiness.entity.Catalog;

import java.util.List;

public interface ICatalog<T,E> extends IShop<T,E> {
    List<Catalog> searchByName(String str);
    boolean delete(E id);

}
