package ra.bussiness.design;

import ra.bussiness.entity.Catalog;

import java.util.List;
import java.util.Scanner;

public interface IShop<T,E> {
    boolean create(T t);
    boolean update(T t);
    T inputData(Scanner sc);
    List findAll();
    boolean writeToFile(List<T> list);
    List<T> readFromFile();
    void  displayData();
}
