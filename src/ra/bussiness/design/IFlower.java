package ra.bussiness.design;

import ra.bussiness.entity.Flower;

import java.util.List;

public interface IFlower<T,E> extends IShop<T,E>{
    List<Flower> searchByNameOrPrice(String str, Float print);
    boolean delete(E id);


    boolean delete(String id);
}
