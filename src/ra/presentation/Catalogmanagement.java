package ra.presentation;

import java.util.Scanner;

public class Catalogmanagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("|-------------Cửa Hàng Hoa---------------|");
            System.out.println("| 1. Quản trị các loại hoa               |");
            System.out.println("|----------------------------------------|");
            System.out.println("| 2. Quản trị ca loài hoa                |");
            System.out.println("|----------------------------------------|");
            System.out.println("| 3. Quản trị các danh mục sản phẩm      |");
            System.out.println("|----------------------------------------|");
            System.out.println("| 4. Quản trị các sản phẩm bán           |");
            System.out.println("|----------------------------------------|");
            System.out.println("| 5. Quản trị các tài khoản              |");
            System.out.println("|----------------------------------------|");
            System.out.println("| 6. Quản trị các đơn hàng của khách hàng|");
            System.out.println("|----------------------------------------|");
            System.out.println("| 7. Quản trị các phản hồi của khách hàng |");
            System.out.println("|----------------------------------------|");
            System.out.println("| 8. Thoát                               |");
            System.out.println("|----------------------------------------|");
            System.out.println("sự lựa chọn của bạn là");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    FlowerTypesMenu.flowerTypesMenu(sc);
                    break;
                case 2:
                    FlowerMenu.flower(sc);
                    break;
                case 3:
                    Catalogmenu.catalogMenu(sc);
                    break;
                case 4:
                    Productmenu.productMenu(sc);
                    break;
                case 5:
                    UserMenu.userMenu(sc);
                    break;
                case 6:
                    OrderMenu.orderMenu(sc);
                    break;
                case 7:
                    FeedBackMennu.feedBackMenu(sc);
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.err.println("vui lòng nhập vào số 1-8");
            }

        } while (true);
    }
}
