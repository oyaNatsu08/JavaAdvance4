import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        var productService = new ProductService();

        //id=103、ある
        var findIdProduct = productService.findById(103);
        System.out.println(findIdProduct);

        //id=110、ない
        findIdProduct = productService.findById(110);
        System.out.println(findIdProduct);

        //name='鉛筆'、ある
        var findNameProduct = productService.findByName("鉛筆");
        System.out.println(findNameProduct);

        //name='ハサミ'、ない
        findNameProduct = productService.findByName("ハサミ");
        System.out.println(findNameProduct);

        //id=104、追加
        productService.insert(new ProductRecord(104, "定規", 100));

        //id=104、追加、既にある場合
        productService.insert(new ProductRecord(104, "定規", 100));

        //id=104、情報更新
        productService.update(new ProductRecord(104, "定規13300本", 10000));

        //id=108、情報更新、id=108は存在しない
        productService.update(new ProductRecord(108, "定規100本", 10000));

        //id=104、情報削除
       // productService.delete(104);

        //id=108、情報削除、id=108は存在しない
        productService.delete(108);

    }
}