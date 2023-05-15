import java.io.Serial;

public class ProductNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    ProductNotFoundException() {
        System.out.println("指定の商品は見つかりませんでした。");
    }
}
