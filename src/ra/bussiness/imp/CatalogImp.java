package ra.bussiness.imp;

import ra.bussiness.design.ICatalog;
import ra.bussiness.entity.Catalog;
import ra.config.ShopMessage;
import ra.config.ShopValidation;
import ra.data.DataURL;

import java.io.*;
import java.util.*;

public class CatalogImp implements ICatalog<Catalog ,Integer> {


    @Override
    public List<Catalog> searchByName(String catalogName) {
        List<Catalog> listCatalogFull = readFromFile();
        List<Catalog> listCatalog = new ArrayList<>();
        for (Catalog cat : listCatalogFull) {
            if (cat.getCatalogName().contains(catalogName)) {
                listCatalog.add(cat);
            }
        }
        return listCatalog;

    }

    @Override
    public boolean delete(Integer id) {
        List<Catalog> listCatalog = readFromFile();
        boolean returnData = false;
        for (int i = 0; i < listCatalog.size(); i++) {
            if (listCatalog.get(i).getCatalogId() == id) {
                listCatalog.get(i).setCatalogStatus(!listCatalog.get(i).isCatalogStatus());
                returnData = true;
                break;
            }
        }
        boolean result = writeToFile(listCatalog);
        if (result && returnData) {
            return true;
        }
        return false;
    }

    @Override
    public boolean create(Catalog catalog) {
        List<Catalog> listCatalog = readFromFile();
        if (listCatalog == null) {
            listCatalog = new ArrayList<>();
        }
        listCatalog.add(catalog);
        boolean result = writeToFile(listCatalog);
        return result;
    }


    @Override
    public boolean update(Catalog catalog ) {
        List<Catalog> listCatalog = readFromFile();
        boolean returnData = false;
        for (int i = 0; i < listCatalog.size(); i++) {
            if (listCatalog.get(i).getCatalogId() == catalog.getCatalogId()) {
                //Thuc hien cap nhat
                listCatalog.set(i, catalog);
                returnData = true;
                break;
            }
        }
        boolean result = writeToFile(listCatalog);
        if (result && returnData) {
            return true;
        }
        return false;

    }

    @Override
    public Catalog inputData(Scanner sc) {
        List<Catalog> listCatalog = readFromFile();
        if (listCatalog == null) {
            listCatalog = new ArrayList<>();
        }
        //Khoi tao doi tuong de nhap thong tin
        Catalog catNew = new Catalog();

        do {
            System.out.println("Nhap vao ma danh muc: ");
            catNew.setCatalogId(Integer.parseInt(sc.nextLine()));
            boolean checkExist = false;
            for (Catalog cat : listCatalog) {
                if (cat.getCatalogId() == catNew.getCatalogId()) {
                    checkExist = true;
                    break;
                }
            }
            if (!checkExist) {
                break;
            } else {
                System.err.println(ShopMessage.NOTIFY_CATALOGID_EXIST);
            }
        } while (true);
        do {
            System.out.println("Nh???p v??o t??n danh m???c: ");
            String name = sc.nextLine();
            boolean check = ShopValidation.checkValidateName(name);
            if (check){
                for (Catalog cat :listCatalog) {
                    if (cat.getCatalogName().equals(name)){
                        check = false;
                        break;
                    }
                }
                if (check){
                    catNew.setCatalogName(name);
                    break;
                }else {
                    System.err.println(ShopMessage.THIS_NAME_ALREADY_EXISTS);
                }
            }else {
                System.err.println(ShopMessage.NAME_WRONG);
            }
        }while (true);
        do {
            System.out.println("Nh???p v??o m?? t??? danh m???c: ");
            String content = sc.nextLine();
            boolean check= ShopValidation.checkEmptyString(content);
            if (check){
                catNew.setTitle(content);
                break;
            }else {
                System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
            }
        }while (true);
        do {
            try {
                System.out.println("Nh???p v??o ????? ??u ti??n: ");
                int priority = Integer.parseInt(sc.nextLine());
                catNew.setPriority(priority);
                break;
            }catch (NumberFormatException e){
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }

        }while (true);
        do {
            System.out.println("Vui l??ng ch???n tr???ng th??i c???a lo???i hoa: ");
            System.out.println("1. Ho???t ?????ng .");
            System.out.println("2. Kh??ng ho???t ?????ng.");
            System.out.print("l???a ch???n c???a b???n: ");
            String choice = sc.nextLine();
            String check = ShopValidation.checkInputStatus(choice);
            if (check.equals("1")){
                catNew.setCatalogStatus(true);
                break;
            }else if (check.equals("2")){
                catNew.setCatalogStatus(false);
                break;
            }else {
                System.err.println(ShopMessage.PLEASE_CHOOSE_1_OR_2);
            }
        }while (true);


        return catNew;
    }

    @Override
    public List findAll() {
        return readFromFile();
    }

    @Override

        public void displayData() {
            List<Catalog> listCatalog = readFromFile();
            for (Catalog cat : listCatalog) {
                System.out.printf("Ma DM: %d - Ten DM: %s  - M?? T???: %s - ????? ??u ti??n :%d Trang thai: %b\n", cat.getCatalogId(),cat.getCatalogName(),cat.getTitle(),cat.getPriority(),cat.isCatalogStatus());
            }
        }



    public List<Catalog> readFromFile() {
        List<Catalog> listCatalog = null;
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            file = new File(DataURL.URL_CATALOG_FILE);
            if (file.exists()) {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                listCatalog = (List<Catalog>) ois.readObject();
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
        return listCatalog;
    }

    public boolean writeToFile(List<Catalog> list) {
        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean returnData = true;
        try {
            file = new File(DataURL.URL_CATALOG_FILE);
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

    public List<Catalog> sortbyId() {                                 //1. sort by name
        List<Catalog> catalogList = readFromFile();
        if (catalogList == null) {
            catalogList = new ArrayList<>();
        }
        Collections.sort(catalogList, new Comparator<Catalog>() {
            public int compare(Catalog o1, Catalog o2) {
                if (o1.getPriority() > o2.getPriority()) {
                    return 1;
                } else if (o1.getPriority() == o2.getPriority()) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        return catalogList;
    }

    public static void inputCatalog (Scanner scanner){
        CatalogImp catalogImpl = new CatalogImp();
        System.out.println("Nh???p v??o s??? danh m???c s???n ph???m b???n mu???n nh???p l???n n??y: ");
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
            Catalog catalog = new Catalog();
            catalog = catalogImpl.inputData(scanner);
            boolean check = catalogImpl.create(catalog);
            if (check){
                System.out.println("???? th??m m???i th??nh c??ng !");
            }else {
                System.err.println("Th??m m???i th???t b???i !!!");
            }
        }
    }
    public Catalog newInputData(Catalog catalogNew,Scanner sc){
        List<Catalog> catalogList = readFromFile();
        // Kh???i t???o ?????i t?????ng ????? nh???n th??ng tin
        String newName ="";
        String newTitle="";
        int newprioty;
        do {
            System.out.println("Nh???p v??o t??n m???i cho danh m???c: ");
            newName= sc.nextLine();
            boolean check = ShopValidation.checkValidateName(newName);
            if (check){
                for (Catalog catalog1 :catalogList) {
                    if (catalog1.getCatalogName().equals(newName)){
                        check = false;
                        break;
                    }
                }
                if (check){
                    catalogNew.setCatalogName(newName);
                    break;
                }else {
                    System.err.println(ShopMessage.THIS_NAME_ALREADY_EXISTS);
                }
            }else {
                System.err.println(ShopMessage.NAME_WRONG);
            }
        }while (true);
        do {
            System.out.println("Nh???p v??o m?? t??? m???i cho danh m???c: ");
            newTitle = sc.nextLine();
            boolean check= ShopValidation.checkEmptyString(newTitle);
            if (check){
                catalogNew.setTitle(newTitle);
                break;
            }else {
                System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
            }
        }while (true);
        do {
            try {
                System.out.println("Nh???p v??o ????? ??u ti??n m???i cho danh m???c: ");
                newprioty = Integer.parseInt(sc.nextLine());
                catalogNew.setPriority(newprioty);
                break;
            }catch (NumberFormatException e){
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }

        }while (true);
        do {
            System.out.println("Vui l??ng ch???n tr???ng th??i c???a lo???i hoa: ");
            System.out.println("1. Ho???t ?????ng .");
            System.out.println("2. Kh??ng ho???t ?????ng.");
            System.out.print("l???a ch???n c???a b???n: ");
            String choice = sc.nextLine();
            String check = ShopValidation.checkInputStatus(choice);
            if (check.equals("1")){
                catalogNew.setCatalogStatus(true);
                break;
            }else if (check.equals("2")){
                catalogNew.setCatalogStatus(false);
                break;
            }else {
                System.err.println(ShopMessage.PLEASE_CHOOSE_1_OR_2);
            }
        }while (true);


        return catalogNew;
    }
//    public static void update(Scanner sc){
//        Catalog catalog  = new Catalog();
//        System.out.println("nh???p v??o id c???n update");
//        int updateId = Integer.parseInt(sc.nextLine());
//        for (Catalog catalog1:) {
//
//        }
//
//            }
}





//    @Override
//    public int compare(Catalog o1, Catalog o2) {
//        return 0;






