package ra.presentation;

import ra.bussiness.entity.Catalog;
import ra.bussiness.imp.CatalogImp;
import ra.config.ShopMessage;

import java.util.List;
import java.util.Scanner;

public class Catalogmenu {
    private static CatalogImp catImp = new CatalogImp();
    public static void catalogMenu(Scanner sc){
        boolean exitCatalogMenu =true;
        do {
            System.out.println("|-----------CatalogMenu-------------|");
            System.out.println("| 1. Danh mục sản phẩm              |");
            System.out.println("|-----------------------------------|");
            System.out.println("| 2. Tạo mới danh mục sản phẩm      |");
            System.out.println("|-----------------------------------|");
            System.out.println("| 3. Cập nhật danh mục sản phẩm     |");
            System.out.println("|-----------------------------------|");
            System.out.println("| 4. Xóa danh mục sản phẩm          |");
            System.out.println("|-----------------------------------|");
            System.out.println("| 5. Tìm kiếm danh mục theo tên     |");
            System.out.println("|-----------------------------------|");
            System.out.println("| 6. Thoát                          |");
            System.out.println("|-----------------------------------|");
            System.out.println("Sự lựa chọn của bạn là : ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    catImp.displayData();
                    break;
                case 2:
                    catImp.inputCatalog(sc);

                    break;
                case 3:
                    catImp.displayData();
                    System.out.println("mời bạn nhập id muốn cập nhật ");
                    int idUpdate = Integer.parseInt(sc.nextLine());
                    List<Catalog> listCatalog = catImp.readFromFile();
                    for (Catalog catalog: listCatalog) {
                        if (catalog.getCatalogId()==idUpdate){
                            catImp.update(catImp.newInputData(catalog,sc));
                    }
                    }
                  break;


                case 4:
                    System.out.println("nhập vào danh mục cần xóa");
                    int deleteId = Integer.parseInt(sc.nextLine());
                    CatalogImp catDelete = new CatalogImp();
                    catDelete.delete(deleteId);

                    break;
                case 5:
                    System.out.println("nhập vào tên thư mục cần tìm");
                    String seachName = sc.nextLine();

                    List<Catalog> catalogList = catImp.searchByName(seachName);
                    if (catalogList.size()==0){
                        System.out.println("không tìm thấy danh mục");
                    }else {
                        for (Catalog catalog:catalogList) {
                            System.out.printf("Mã DM: %d - Tên DM: %s - Tiêu Đề: %s - Độ Ưu Tiên: %d Trạng Thái: %b\n", catalog.getCatalogId(), catalog.getCatalogName(),catalog.getTitle(),catalog.getPriority(), catalog.isCatalogStatus());

                        }


                    }
                    break;
                case 6:
                    exitCatalogMenu=false;
                    break;
                default:
                    System.err.println(ShopMessage.NOTIFILE_CATALOGMENU_CHOICE);

            }

        }while (exitCatalogMenu);
    }
}
