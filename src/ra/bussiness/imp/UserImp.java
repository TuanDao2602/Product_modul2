package ra.bussiness.imp;

import ra.bussiness.design.IUser;
import ra.bussiness.entity.Catalog;
import ra.bussiness.entity.User;
import ra.config.ShopMessage;
import ra.config.ShopValidation;
import ra.data.DataURL;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserImp implements IUser<User,Integer> {


    @Override
    public boolean create(User user) {
        List<User> listUser = readFromFile();
        if (listUser==null){
            listUser = new ArrayList<>();
        }
        listUser.add(user);
        boolean result = writeToFile(listUser);
        return result;
    }

    @Override
    public boolean update(User user) {
        List<User> listUser = readFromFile();
        boolean returnData = false;
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).getUserId() ==user.getUserId()) {
                //Thuc hien cap nhat
                listUser.set(i, user);
                returnData = true;
                break;
            }
        }
        boolean result = writeToFile(listUser);
        if (result && returnData) {
            return true;
        }
        return false;

    }

    @Override
    public User inputData(Scanner sc) {
        List<User> listUser = readFromFile();
        if (listUser==null){
            listUser = new ArrayList<>();
        }
        User user = new User();
        user.setUserId(listUser.size()+1);
        do {
            System.out.print("Nhập vào tên tài khoản: ");
            String name = sc.nextLine();
            System.out.print("\n");
            boolean check = ShopValidation.checkUserNameLength(name);
            if (check){
                check = ShopValidation.checkValidateUserName(name);
                if (check){
                    for (User userEx :listUser) {
                        if (userEx.getUserName().equals(name)){
                            check = false;
                            break;
                        }
                    }
                    if (check){
                        user.setUserName(name);
                        break;
                    }else {
                        System.err.println(ShopMessage.ALREAD_THIS_USERNAME);
                    }
                }
            }else {
                System.err.println(ShopMessage.USER_NAME_LENGTH);
            }
        }while (true);
        do {
            System.out.print("Nhập vào mật khẩu: ");
            String password = sc.nextLine();
            System.out.print("\n");
            boolean check = ShopValidation.checkPassword(password);
            if (check){
                System.out.print("Nhập lại mật khẩu: ");
                String repeatPasswood = sc.nextLine();
                check = ShopValidation.checkPassword(repeatPasswood);
                if (check){
                    if (password.equals(repeatPasswood)){
                        user.setPassword(password);
                        break;
                    }else {
                        System.err.println(ShopMessage.REPEAT_PASSWORD_WRONG);
                    }
                }
            }else {
                System.err.println(ShopMessage.PASSWORD_LENGTH);
            }
        }while (true);
        do {
            System.out.print("Nhập họ và tên của bạn: ");
            String name = sc.nextLine();
            System.out.print("\n");
            boolean check = ShopValidation.checkEmptyString(name);
            if (check){
                user.setFullName(name);
                break;
            }else {
                System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
            }
        }while (true);
        do {
            System.out.print("Nhập vào email:");
            String email = sc.nextLine();
            System.out.print("\n");
            boolean check = ShopValidation.checkEmail(email);
            if (check){
                user.setEmail(email);
                break;
            }else {
                System.err.println(ShopMessage.EMAIL_WRONG);
            }
        }while (true);
        do {
            System.out.print("Nhập vào số điện thoại (Số điện thoại bắt đầu bằng 84) :  ");
            String phoneNumber = sc.nextLine();
            System.out.println("\n");
            boolean checkPhone = ShopValidation.checkPhoneNumber(phoneNumber);
            if (checkPhone){
                user.setPhoneNumber(phoneNumber);
                break;
            }else {
                System.err.println(ShopMessage.PHONE_NUMBER_WRONG);
            }

        }while (true);
        Date date = new Date();
        user.setUserStatus(true);
        user.setDate(date);
        return user;
    }

    @Override
    public List findAll() {
        return readFromFile();
    }

    @Override
    public boolean writeToFile(List<User> list) {
        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean returnData = true;
        try {
            file = new File(DataURL.URL_USER_FILE);
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
    public List<User> readFromFile() {
        List<User> listUser = null;
        File file=null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            file = new File(DataURL.URL_USER_FILE);
            if (file.exists()) {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                listUser = (List<User>) ois.readObject();
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
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        return listUser;
    }

    @Override
    public void displayData() {

    }


    @Override
    public boolean searchByName(String str) {
        return false;
    }


    @Override
    public boolean delete(User user) {
        List<User> userList = readFromFile();
        for (User user1 :userList) {
            if (user.getUserName().equals(user1)){
                if (user.isUserStatus()){
                    user.setUserStatus(false);
                    return  true;
                }else {
                    user.setUserStatus(true);
                    return  true;
                }
            }
        }
        return false;
    }


}