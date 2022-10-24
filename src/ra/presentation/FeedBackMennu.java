package ra.presentation;

import ra.config.ShopMessage;

import java.util.Scanner;

public class FeedBackMennu {
    public static  void feedBackMenu(Scanner sc){
        boolean exitFeedBack=true;
        do {
            System.out.println("-----------FeedBackMenu--------------");
            System.out.println("| 1. Hiển thị nội dung phản hồi     |");
            System.out.println("|-----------------------------------|");
            System.out.println("| 2. Thoát                          |");
            System.out.println("|-----------------------------------|");
            System.out.println("Sự lựa chọn của bạn");

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    break;
                case 2:
                    exitFeedBack=false;
                    break;
                default:
                    System.err.println(ShopMessage.NOTIFILE_FEEDBACLMENU_CHOICE);
            }
        }while (exitFeedBack);

    }
}
