package ra.bussiness.design;

import ra.bussiness.entity.FlowerTypes;

import java.util.List;

public interface IFlowerTypes<T,E> extends IShop<T,E> {
    List<FlowerTypes> searchByNameOrId(String str, String id);
    boolean delete(E id);

}
