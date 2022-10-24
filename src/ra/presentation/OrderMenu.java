package ra.presentation;

import ra.config.ShopMessage;

import java.util.Scanner;

public class OrderMenu {
    public static void orderMenu(Scanner sc){
        boolean exitorderMenu = true;
        do {
            System.out.println("|---------OrderMenu--------|");
            System.out.println("| 1. Xem chi tiết đơ hàng  |");
            System.out.println("| -------------------------|");
            System.out.println("| 2. Hủy và duyệt đơn hàng |");
            System.out.println("| -------------------------|");
            System.out.println("| 3. thoát                 |");
            System.out.println("|--------------------------|");
            System.out.println("sự lựa  chọn của bạn là");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    exitorderMenu=false;
                    break;
                default:
                    System.err.println(ShopMessage.NOTIFILE_ORDERMENU_CHOICE);
            }
        }while (exitorderMenu);

    }
}
