package ra.presentation;

import ra.bussiness.entity.Catalog;
import ra.bussiness.entity.Flower;
import ra.bussiness.imp.CatalogImp;
import ra.bussiness.imp.FlowerImp;
import ra.config.ShopMessage;

import java.util.List;
import java.util.Scanner;

public class FlowerMenu {
    private static FlowerImp flowerImp = new FlowerImp();

    public static  void flower(Scanner sc){

        boolean exitFlower = true;
        do {
            System.out.println("|----------------Flower-------------------|");
            System.out.println("| 1. Danh sách các loài hoa               |");
            System.out.println("|-----------------------------------------|");
            System.out.println("| 2. Tạo mới loài hoa                     |");
            System.out.println("|-----------------------------------------|");
            System.out.println("|3. Cập nhật thông tin loài hoa           |");
            System.out.println("|-----------------------------------------|");
            System.out.println("| 4. Xóa loài hoa(Cập nhật thành hết hoa) |");
            System.out.println("|-----------------------------------------|");
            System.out.println("| 5. Tìm kiếm loài hoa theo tên hoặc giá  |");
            System.out.println("|-----------------------------------------|");
            System.out.println("| 6. thoát                                |");
            System.out.println("|-----------------------------------------|");
            System.out.println(" Sự lựa chọn của bạn là : ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    flowerImp.displayData();
                     break;
                case 2:

                    Flower flowerNew = flowerImp.inputData(sc);
                    //Thực hiện thêm mới hoa
                    flowerImp.create(flowerNew);
//

                    break;
                case 3:
                   flowerImp.displayData();
                    System.out.println("mời bạn nhập id muốn cập nhật ");
                    String idUpdate =sc.nextLine();
                    List<Flower> listFlower = flowerImp.readFromFile();
                    for (Flower flower: listFlower) {
                        if (flower.getFlowerId().equals(idUpdate)){
                            flowerImp.update(flowerImp.newInputData(flower,sc));
                        }
                    }



                    break;
                case 4:
                    while (true) {
                        try {
                            System.out.println("Nhập vào mã loại hoa cần xóa : ");
                            String id = sc.nextLine();
                            boolean check = flowerImp.delete(id);
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
                    break;
                case 5:
                    break;
                case 6:
                    exitFlower=false;
                    break;
                default:
                    System.err.println(ShopMessage.NOTIFILE_FlowerMenu_CHOICE);
            }
        }while (exitFlower);


    }
}
