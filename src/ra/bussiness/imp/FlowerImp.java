package ra.bussiness.imp;

import ra.bussiness.design.IFlower;
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

public class FlowerImp implements IFlower<Flower,String> {


    @Override
    public List<Flower> searchByNameOrPrice(String str, Float print) {
        List<Flower> listFlowerFull = readFromFile();
        List<Flower> listFlower = new ArrayList<>();
        for (Flower flower: listFlowerFull) {
            if (flower.getFlowerName().contains(str)|| flower.getPrice()==print) {
                listFlower.add(flower);
            }
        }
        return listFlower;
    }




    @Override
    public boolean delete(String id) {
        List<Flower> listFlower = readFromFile();
        boolean returnData = false;
        for (int i = 0; i < listFlower.size(); i++) {
            if (listFlower.get(i).getFlowerId().equals(id)){
                listFlower.get(i).setFlowerStatus(!listFlower.get(i).isFlowerStatus());
                returnData = true;
                break;
            }
        }
        boolean result = writeToFile(listFlower);
        if (result && returnData) {
            return true;
        }
        return false;
    }

    @Override
    public boolean create(Flower flower) {
        List<Flower> listFlower = readFromFile();
        if (listFlower == null) {
            listFlower = new ArrayList<>();
        }
        listFlower.add(flower);
        boolean result = writeToFile(listFlower);
        return result;
    }

    @Override
    public boolean  update(Flower flower) {
        List<Flower> listFlower = readFromFile();
        boolean returnData = false;
        for (int i = 0; i < listFlower.size(); i++) {
            if (listFlower.get(i).getFlowerId().equals(flower.getFlowerId())) {
                //Thuc hien cap nhat
                listFlower.set(i, flower);
                returnData = true;
                break;
            }
        }
        boolean result = writeToFile(listFlower);
        if (result && returnData) {
            return true;
        }
        return false;
    }

    @Override
    public Flower inputData(Scanner sc) {
            List<Flower> listFlower = readFromFile();
            if (listFlower == null) {
                listFlower = new ArrayList<>();
            }
            //Khoi tao doi tuong de nhap thong tin
            Flower flowerNew = new Flower();

             do {
            System.out.print("Nh???p v??o m?? lo??i hoa: ");
            String id = sc.nextLine();
            System.out.print("\n");
            boolean check = ShopValidation.checkId5(id);
            if (check){
                check = ShopValidation.checkFlowerId(id);
                if (check){
                    for (Flower flowers :listFlower) {
                        if (flowers.getFlowerId().equals(id)){
                            check = false;
                            break;
                        }
                    }
                    if (check){
                        flowerNew.setFlowerId(id);
                        break;
                    }else {
                        System.err.println(ShopMessage.THIS_ID_ALREADY_EXISTS);
                    }
                }else {
                    System.err.println("M?? lo??i hoa b???t ?????u b???ng k?? t??? 'H' !!!");
                }
            }else {
                System.err.println(ShopMessage.ID_LENGTH_WRONG);
            }
        }while (true);
        do {
            System.out.println("Nh???p v??o t??n lo??i hoa: ");
            String name = sc.nextLine();
            boolean check = ShopValidation.checkValidateName(name);
            if (check){
                for (Flower flowers :listFlower) {
                    if (flowers.getFlowerName().equals(name)){
                        check = false;
                        break;
                    }
                }
                if (check){
                    flowerNew.setFlowerName(name);
                    break;
                }else {
                    System.err.println(ShopMessage.THIS_NAME_ALREADY_EXISTS);
                }
            }else {
                System.err.println(ShopMessage.NAME_WRONG);
            }
        }while (true);
        do {
            System.out.println("Vui l??ng ch???n lo???i hoa !");
            FlowerTypesImp flowerTypesImp = new FlowerTypesImp();
            List<FlowerTypes> listTypeFlower = flowerTypesImp.readFromFile() ;
            System.out.printf("%-10s%-30s\n","STT"," T??n lo???i hoa ");
            for (int i = 0; i < listTypeFlower.size(); i++) {
                System.out.printf("%-10d%-30s\n",(i+1),listTypeFlower.get(i).getFlowerTypesName());
            }
            System.out.print("Vui l??ng ch???n s??? t????ng ???ng: ");
            int choice = 0 ;
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice <0 || choice>listTypeFlower.size()){
                    System.err.println("Vui l??ng nh???p t??? 1 - " + listTypeFlower.size());
                }else {
                    flowerNew.setFlowerTypes(listTypeFlower.get(choice-1));
                    break;
                }
            }catch (NumberFormatException e){
                System.err.println("Vui l??ng nh???p v??o s??? !!!");
            }
        }while (true);

        do {
            System.out.println("Nh???p v??o gi?? ti???n nh???p v??o: ");
            String price = sc.nextLine();
            boolean check = ShopValidation.checkInputPrice(price);
            if (check){
                flowerNew.setImportPrice(Float.parseFloat(price));
                break;
            }else {
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
        }while (true);
        do {
            System.out.println("Nh???p v??o gi?? b??n: ");
            String price = sc.nextLine();
            boolean check = ShopValidation.checkInputPrice(price);
            if (check){
                if (Float.parseFloat(price)>flowerNew.getImportPrice()){
                    flowerNew.setPrice(Float.parseFloat(price));
                    break;
                }else {
                    System.err.println(ShopMessage.EXPORT_PRICE_MORE_THAN_IMPORT_PRINCE);
                }

            }else {
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
        }while (true);
        do {
            System.out.println("Nh???p v??o m?? t??? lo??i hoa:  ");
            String content = sc.nextLine();
            boolean check = ShopValidation.checkEmptyString(content);
            if (check){
                flowerNew.setTitle(content);
                break;
            }else {
                System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
            }
        }while (true);
        do {
            System.out.println("Vui l??ng ch???n tr???ng th??i c???a lo???i hoa: ");
            System.out.println("1. C??n hoa.");
            System.out.println("2. H???t hoa.");
            System.out.print("l???a ch???n c???a b???n: ");
            System.out.print("\n");
            String choice = sc.nextLine();
            String check = ShopValidation.checkInputStatus(choice);
            if (check.equals("1")){
                flowerNew.setFlowerStatus(true);
                break;
            }else if (check.equals("2")){
                flowerNew.setFlowerStatus(false);
                break;
            }else {
                System.err.println(ShopMessage.PLEASE_CHOOSE_1_OR_2);
            }
        }while (true);
            return flowerNew;
    }

    @Override
    public List findAll() {
        return readFromFile();
    }

    @Override
    public boolean writeToFile(List<Flower> list) {
        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean returnData = true;
        try {
            file = new File(DataURL.URL_FLOWER_FILE);
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
    public  List<Flower> readFromFile() {
        List<Flower> listFlower = new ArrayList<>();
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            file = new File(DataURL.URL_FLOWER_FILE);
            if (file.exists()) {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                listFlower = (List<Flower>) ois.readObject();
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
        return listFlower;
    }

    @Override
        public void displayData() {
            List<Flower> listFlower = readFromFile();
            for (Flower flower : listFlower) {
                System.out.printf("Ma Hoa: %s - T??n lo??i hoa: %s  - G??a ti???n nh???p: %f  - G??a ti???n b??n: %f - M?? t??? lo??i hoa: %s - Tr???ng th??i: %b\n", flower.getFlowerId(),flower.getFlowerName(),flower.getImportPrice(),flower.getPrice(),flower.getTitle(),flower.isFlowerStatus());
            }
        }

    public static List<Flower> inputFlower (Scanner scanner){
       FlowerImp flowerImpl = new FlowerImp();
        System.out.println("Nh???p v??o s??? l?????ng lo??i hoa b???n mu???n nh???p l???n n??y: ");
        int number = 0;
        while (true){
            try {
                number = Integer.parseInt(scanner.nextLine());
                break;
            }catch (NumberFormatException e){
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
        }

        for (int i = 0; i < number; i++) {
            System.out.println("Nh???p v??o lo??i hoa th??? " + (i+1));
            Flower flower = new Flower();
            flower = flowerImpl.inputData(scanner);
            boolean check = flowerImpl.create(flower);
            if (check){
                System.out.println("???? th??m m???i th??nh c??ng !");
            }else {
                System.err.println("Th??m m???i th???t b???i !!!");
            }
        }
        return null;
    }
    public Flower newInputData(Flower flowerNew,Scanner sc) {
        List<Flower> listFlower = readFromFile();


        do {
            System.out.println("Nh???p v??o t??n lo??i hoa: ");
            String name = sc.nextLine();
            boolean check = ShopValidation.checkValidateName(name);
            if (check){
                for (Flower flowers :listFlower) {
                    if (flowers.getFlowerName().equals(name)){
                        check = false;
                        break;
                    }
                }
                if (check){
                    flowerNew.setFlowerName(name);
                    break;
                }else {
                    System.err.println(ShopMessage.THIS_NAME_ALREADY_EXISTS);
                }
            }else {
                System.err.println(ShopMessage.NAME_WRONG);
            }
        }while (true);
        do {
            System.out.println("Vui l??ng ch???n lo???i hoa !");
            FlowerTypesImp flowerTypesImp = new FlowerTypesImp();
            List<FlowerTypes> listTypeFlower = flowerTypesImp.readFromFile() ;
            System.out.printf("%-10s%-30s\n","STT"," T??n lo???i hoa ");
            for (int i = 0; i < listTypeFlower.size(); i++) {
                System.out.printf("%-10d%-30s\n",(i+1),listTypeFlower.get(i).getFlowerTypesName());
            }
            System.out.print("Vui l??ng ch???n s??? t????ng ???ng: ");
            int choice = 0 ;
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice <0 || choice>listTypeFlower.size()){
                    System.err.println("Vui l??ng nh???p t??? 1 - " + listTypeFlower.size());
                }else {
                    flowerNew.setFlowerTypes(listTypeFlower.get(choice-1));
                    break;
                }
            }catch (NumberFormatException e){
                System.err.println("Vui l??ng nh???p v??o s??? !!!");
            }
        }while (true);

        do {
            System.out.println("Nh???p v??o gi?? ti???n nh???p v??o: ");
            String price = sc.nextLine();
            boolean check = ShopValidation.checkInputPrice(price);
            if (check){
                flowerNew.setImportPrice(Float.parseFloat(price));
                break;
            }else {
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
        }while (true);
        do {
            System.out.println("Nh???p v??o gi?? b??n: ");
            String price = sc.nextLine();
            boolean check = ShopValidation.checkInputPrice(price);
            if (check){
                if (Float.parseFloat(price)>flowerNew.getImportPrice()){
                    flowerNew.setPrice(Float.parseFloat(price));
                    break;
                }else {
                    System.err.println(ShopMessage.EXPORT_PRICE_MORE_THAN_IMPORT_PRINCE);
                }

            }else {
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
        }while (true);
        do {
            System.out.println("Nh???p v??o m?? t??? lo??i hoa:  ");
            String content = sc.nextLine();
            boolean check = ShopValidation.checkEmptyString(content);
            if (check){
                flowerNew.setTitle(content);
                break;
            }else {
                System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
            }
        }while (true);
        do {
            System.out.println("Vui l??ng ch???n tr???ng th??i c???a lo???i hoa: ");
            System.out.println("1. C??n hoa.");
            System.out.println("2. H???t hoa.");
            System.out.print("l???a ch???n c???a b???n: ");
            System.out.print("\n");
            String choice = sc.nextLine();
            String check = ShopValidation.checkInputStatus(choice);
            if (check.equals("1")){
                flowerNew.setFlowerStatus(true);
                break;
            }else if (check.equals("2")){
                flowerNew.setFlowerStatus(false);
                break;
            }else {
                System.err.println(ShopMessage.PLEASE_CHOOSE_1_OR_2);
            }
        }while (true);
        return flowerNew;
    }




}

