import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void findById_success() {
        //id=103、ある
        var findIdProduct = productService.findById(103);
        System.out.println(findIdProduct);
    }

    @Test
    public void findById_error() {
        //id=110、ない
        var findIdProduct = productService.findById(110);
        System.out.println(findIdProduct);
    }

    @Test
    public void findByName_success() {
        //name='鉛筆'、ある
        var findNameProduct = productService.findByName("鉛筆");
        System.out.println(findNameProduct);
    }

    @Test
    public void findByName_error() {
        //name='ハサミ'、ない
        var findNameProduct = productService.findByName("ハサミ");
        System.out.println(findNameProduct);
    }

    @Test
    public void insert_success() {
        //id=104、追加
        productService.insert(new ProductRecord(104, "定規", 100));
    }

    @Test
    public void insert_error() {
        //id=104、追加、既にある場合
        productService.insert(new ProductRecord(104, "定規", 100));
    }

    @Test
    public void update_success() {
        //id=104、情報更新
        productService.update(new ProductRecord(104, "定規13300本", 10000));
    }

    @Test
    public void update_error() {
        //id=108、情報更新、id=108は存在しない
        productService.update(new ProductRecord(108, "定規100本", 10000));
    }

    @Test
    public void delete_success() {
        //id=104、情報削除
        productService.delete(104);
    }

    @Test
    public void delete_error() {
        //id=108、情報削除、id=108は存在しない
        productService.delete(108);
    }

}