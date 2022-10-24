package ra.presentation;

import ra.bussiness.imp.ProductImp;
import ra.config.ShopMessage;

import java.util.Scanner;

public class Productmenu {
    public static void productMenu(Scanner sc){
        ProductImp productImp = new ProductImp();
        boolean exitproductMenu =true;
        do {
            System.out.println("|----------ProductMenu------------|");
            System.out.println("| 1. Danh sách sản phẩm           |");
            System.out.println("|---------------------------------|");
            System.out.println("| 2. Thêm mới sản phẩm            |");
            System.out.println("|---------------------------------|");
            System.out.println("| 3. Cập nhật sản phẩm            |");
            System.out.println("|---------------------------------|");
            System.out.println("| 4. Xóa sản phẩm                 |");
            System.out.println("|---------------------------------|");
            System.out.println("| 5. Thoát                        |");
            System.out.println("|---------------------------------|");
            System.out.println("sự lựa chọn của bạn là: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    productImp.displayProduct();

                    break;
                case 2:
                   productImp.inputProduct(sc);
                    break;
                case 3:
                    break;
                case 4:
                    productImp.deleteProduct(sc);
                    break;
                case 5:
                    exitproductMenu=false;
                    break;
                default:
                    System.err.println(ShopMessage.NOTIFILE_PRODUCTMENU_CHOICE);
            }

        }while (exitproductMenu);
    }

}
