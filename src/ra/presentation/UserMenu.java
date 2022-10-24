package ra.presentation;

import ra.bussiness.entity.Flower;
import ra.bussiness.entity.User;
import ra.bussiness.imp.CatalogImp;
import ra.bussiness.imp.UserImp;
import ra.config.ShopMessage;

import java.util.Scanner;

public class UserMenu {
    private static UserImp userImp = new UserImp();
    public static void userMenu(Scanner sc){
        boolean exitUserMenu = true;
        do {
            System.out.println("|---------------------UserMenu---------------------|");
            System.out.println("| 1. Danh sách tài khoản                           |");
            System.out.println("|--------------------------------------------------|");
            System.out.println("| 2. Thêm tài khoản quản trị                       |");
            System.out.println("|--------------------------------------------------|");
            System.out.println("| 3. Cập nhật tài khoản quản trị                   |");
            System.out.println("|--------------------------------------------------|");
            System.out.println("| 4. Cập nhật trạng thái tài khoản khách hàng      |");
            System.out.println("|--------------------------------------------------|");
            System.out.println("| 5. Tìm kiếm tài khoản khách hàng theo userName   |");
            System.out.println("|--------------------------------------------------|");
            System.out.println("| 6. thoát                                         |");
            System.out.println("|--------------------------------------------------|");
            System.out.println(" sự lựa chọn của bạn là:");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    break;
                case 2:
                    User userNew = userImp.inputData(sc);
                    //Thực hiện thêm mới hoa
                    userImp.create(userNew);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    exitUserMenu=false;
                    break;
                default:
                    System.err.println(ShopMessage.NOTIFILE_USERMENU_CHOICE);            }

        }while (exitUserMenu);

    }
}
