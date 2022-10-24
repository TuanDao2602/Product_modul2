package ra.bussiness.imp;

import ra.bussiness.design.IFlowerTypes;
import ra.bussiness.entity.Catalog;
import ra.bussiness.entity.Flower;
import ra.bussiness.entity.FlowerTypes;
import ra.config.ShopMessage;
import ra.config.ShopValidation;
import ra.data.DataURL;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlowerTypesImp implements IFlowerTypes<FlowerTypes,Integer> {
    @Override
    public List<FlowerTypes> searchByNameOrId(String str, String id) {
            List<FlowerTypes> listFlowerTypesFull = readFromFile();
            List<FlowerTypes> listFlowerTypes = new ArrayList<>();
            for (FlowerTypes flowerTypes : listFlowerTypesFull) {
                if (flowerTypes.getFlowerTypesName().contains(str) || flowerTypes.getFlowerTypesName().contains(id)) {
                    listFlowerTypes.add(flowerTypes);
                }
            }
            return listFlowerTypes;

        }


    @Override
    public boolean delete(Integer id) {
        List<FlowerTypes> list = readFromFile();
        boolean returnd = false;
        for (FlowerTypes tyFlower : list) {
            if (tyFlower.getFlowerTypesId() == id) {
                tyFlower.setFlowerTypesStatus(!tyFlower.isFlowerTypesStatus());
                returnd = true;
                break;
            }
        }
        boolean result = writeToFile(list);
        if (result && returnd) {
            return true;
        }
        return false;

    }
    @Override
    public boolean create(FlowerTypes flowerTypes) {
        List<FlowerTypes> list = readFromFile();
        if (list==null){
            list = new ArrayList<>();
        }
        list.add(flowerTypes);
        boolean result = writeToFile(list);
        return result;
    }


    @Override
    public boolean update(FlowerTypes flowerTypes) {
        List<FlowerTypes> listFlowerTypes = readFromFile();
        boolean returnData = false;
        for (int i = 0; i < listFlowerTypes.size(); i++) {
            if (listFlowerTypes.get(i).getFlowerTypesId() == flowerTypes.getFlowerTypesId()) {
                //Thuc hien cap nhat
                listFlowerTypes.set(i, flowerTypes);
                returnData = true;
                break;
            }
        }
        boolean result = writeToFile(listFlowerTypes);
        if (result && returnData) {
            return true;
        }
        return false;
    }

    @Override
    public FlowerTypes inputData(Scanner sc) {
            List<FlowerTypes> list = readFromFile();
            if (list==null){
                list = new ArrayList<>();
            }
            FlowerTypes toFlower = new FlowerTypes();
            toFlower.setFlowerTypesId(list.size()+1);
            do {
                System.out.print("Nhập vào tên loại hoa: ");
                String name = sc.nextLine();
                System.out.print("\n");
                boolean check = ShopValidation.checkValidateName(name);
                if (check){
                    toFlower.setFlowerTypesName(name);
                    break;
                }else {
                    System.err.println(ShopMessage.NAME_WRONG);
                }
            }while (true);
            do {
                System.out.println("Nhập vào mô tả về loại hoa: ");
                String content = sc.nextLine();
                boolean check = ShopValidation.checkEmptyString(content);
                if (check){
                    toFlower.setTitle(content);
                    break;
                }else {
                    System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
                }
            }while (true);
            do {
                System.out.println("Vui lòng chọn trạng thái của loại hoa: ");
                System.out.println("1. Có sử dụng.");
                System.out.println("2. Không sử dụng.");
                System.out.print("lựa chọn của bạn: ");
                String choice = sc.nextLine();
                String check = ShopValidation.checkInputStatus(choice);
                if (choice.equals("1")){
                    toFlower.setFlowerTypesStatus(true);
                    break;
                }else if (choice.equals("2")){
                    toFlower.setFlowerTypesStatus(false);
                    break;
                }else {
                    System.err.println(ShopMessage.PLEASE_CHOOSE_1_OR_2);
                }
            }while (true);
            return toFlower;
        }

    public FlowerTypes inputDataNew(FlowerTypes flowerTypesNew, Scanner sc){

            do {
                System.out.print("Nhập vào tên mới loại hoa: ");
                String name = sc.nextLine();
                System.out.print("\n");
                boolean check = ShopValidation.checkValidateName(name);
                if (check){
                    flowerTypesNew.setFlowerTypesName(name);
                    break;
                }else {
                    System.err.println(ShopMessage.NAME_WRONG);
                }
            }while (true);
            do {
                System.out.println("Nhập vào mô tả mới cho loại hoa: ");
                String content = sc.nextLine();
                boolean check = ShopValidation.checkEmptyString(content);
                if (check){
                    flowerTypesNew.setTitle(content);
                    break;
                }else {
                    System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
                }
            }while (true);
            do {
                System.out.println("Vui lòng chọn trạng thái mới của loại hoa: ");
                System.out.println("1. Có sử dụng.");
                System.out.println("2. Không sử dụng.");
                System.out.print("lựa chọn của bạn: ");
                String choice = sc.nextLine();
                String check = ShopValidation.checkInputStatus(choice);
                if (choice.equals("1")){
                    flowerTypesNew.setFlowerTypesStatus(true);
                    break;
                }else if (choice.equals("2")){
                    flowerTypesNew.setFlowerTypesStatus(false);
                    break;
                }else {
                    System.err.println(ShopMessage.PLEASE_CHOOSE_1_OR_2);
                }
            }while (true);
            return flowerTypesNew;
        }



    @Override
    public List findAll() {
        return readFromFile();
    }

    @Override
    public boolean writeToFile(List<FlowerTypes> list) {
        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean returnData = true;
        try {
            file = new File(DataURL.URL_FLOWERTYPES_FILE);
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
        } catch (Exception ex) {
            returnData = false;
            ex.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
        return returnData;
    }

    @Override
    public  List<FlowerTypes> readFromFile() {
        List<FlowerTypes> listFlowerTypes = null;
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            file = new File(DataURL.URL_FLOWERTYPES_FILE);
            if (file.exists()) {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
               listFlowerTypes= (List<FlowerTypes>) ois.readObject();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
        return listFlowerTypes;
    }

    @Override
    public void displayData() {
        List<FlowerTypes> list = readFromFile();
        for (FlowerTypes flowerTypes : list) {
            System.out.printf("Ma DM: %d - Ten DM: %s  - Mô Tả: %s  Trang thai: %b\n", flowerTypes.getFlowerTypesId(),flowerTypes.getFlowerTypesName(),flowerTypes.getTitle(),flowerTypes.isFlowerTypesStatus());
        }

    }
}
