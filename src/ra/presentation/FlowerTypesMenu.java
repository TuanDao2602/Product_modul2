package ra.presentation;

import ra.bussiness.entity.Catalog;
import ra.bussiness.entity.FlowerTypes;
import ra.bussiness.imp.FlowerTypesImp;
import ra.config.ShopMessage;

import java.util.List;
import java.util.Scanner;

public class FlowerTypesMenu {
    static FlowerTypesImp typeOfFlowerImp = new FlowerTypesImp();

    public static void flowerTypesMenu(Scanner sc) {
        boolean exitFlowertypes = true;
        do {
            System.out.println("|-----------FlowerTypesMenu-----------|");
            System.out.println("| 1. Danh sách các loại hoa           |");
            System.out.println("|-------------------------------------|");
            System.out.println("| 2. Tạo mới loại hoa                 |");
            System.out.println("|-------------------------------------|");
            System.out.println("| 3. Cập nhật thông tin loại hoa      |");
            System.out.println("|-------------------------------------|");
            System.out.println("| 4. Xóa loại hoa                     |");
            System.out.println("|-------------------------------------|");
            System.out.println("| 5. Tìm kiếm theo tên hoặc mã        |");
            System.out.println("|-------------------------------------|");
            System.out.println("| 6. Thoát                            |");
            System.out.println("|-------------------------------------|");
            System.out.println("sự lựa chọn của bạn là");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    typeOfFlowerImp.displayData();
                    break;
                case 2:
                    inputTypeOfFlower(sc);
                    break;
                case 3:
                     typeOfFlowerImp.displayData();
                    System.out.println("mời bạn nhập id muốn cập nhật ");
                    int idUpdate = Integer.parseInt(sc.nextLine());
                    List<FlowerTypes> listFlowerTypes = typeOfFlowerImp.readFromFile();
                    for (FlowerTypes flowerTypes: listFlowerTypes) {
                        if (flowerTypes.getFlowerTypesId()==idUpdate){
                            typeOfFlowerImp.update(typeOfFlowerImp.inputDataNew(flowerTypes,sc));
                        }
                    }
                    break;
                case 4:
                    deleteTypeOfFlower(sc);
                    break;
                case 5:
                    searchFlowerTypes(sc);
                    break;
                case 6:
                    exitFlowertypes = false;
                    break;
                default:
                    System.err.println(ShopMessage.NOTIFILE_FlowerTypes_CHOICE);
            }
        } while (exitFlowertypes);

    }

    public static void inputTypeOfFlower(Scanner scanner) {
        System.out.println("Nhập vào số lượng loại hoa bạn muốn nhập lần này: ");
        int number = 0;
        while (true) {
            try {
                number = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
            break;
        }
        for (int i = 0; i < number; i++) {
            System.out.println("Nhập vào loại hoa thứ " + (i + 1));
            FlowerTypes typeOfFlower = new FlowerTypes();
            typeOfFlower = typeOfFlowerImp.inputData(scanner);
            boolean check = typeOfFlowerImp.create(typeOfFlower);
            if (check) {
                System.out.println("Đã thêm mới thành công !");
            } else {
                System.err.println("Thêm mới thất bại !!!");
            }
        }
    }

    public static void deleteTypeOfFlower(Scanner scanner) {

        while (true) {
            try {
                System.out.println("Nhập vào mã loại hoa cần xóa : ");
                int id = Integer.parseInt(scanner.nextLine());
                boolean check = typeOfFlowerImp.delete(id);
                if (check) {
                    System.out.println("Đã xóa thành công !");
                    break;
                } else {
                    System.err.println("Xóa không thành công ");
                }

            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập vào số !!!");
            }
        }
    }

    public static void searchFlowerTypes(Scanner sc) {
        System.out.println("nhập vào tên thư mục cần tìm");
        String seachName = sc.nextLine();
        List<FlowerTypes> listFlower = typeOfFlowerImp.searchByNameOrId(seachName, seachName);
        if (listFlower.size() == 0) {
            System.out.println("không tìm thấy danh mục");
        } else {
            for (FlowerTypes flowerTypes : listFlower) {
                System.out.printf("Mã DM: %d - Tên DM: %s - Tiêu Đề: %s -  Trạng Thái: %b\n", flowerTypes.getFlowerTypesId(), flowerTypes.getFlowerTypesName(), flowerTypes.getTitle(), flowerTypes.isFlowerTypesStatus());

            }
        }

    }
}

