import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDao dao;
    private Connection connection;

    public ProductService() {
        this.connection = DbUtil.getConnection();
        this.dao = new ProductDao(connection);
    }

    public ProductRecord findById(int id) throws ProductNotFoundException {

        var productDao = dao;

        var product = productDao.findById(id);

        if (product == null) {
            throw new ProductNotFoundException();
        }

        return product;

    }

    public List<ProductRecord> findByName(String name) throws ProductNotFoundException {
        var productDao = dao;

        var product = productDao.findByName(name);

        if (product.size() == 0) {
            throw new ProductNotFoundException();
        }

        return product;

    }

    public int insert(ProductRecord product) throws SQLException {
        try {
            var productDao = dao;

            return productDao.insert(new ProductRecord(product.id(), product.name(), product.price()));

        } catch (RuntimeException e) {
            throw new RuntimeException();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public int update(ProductRecord product) {

        var productDao = dao;
        return productDao.update(new ProductRecord(product.id(), product.name(), product.price()));

    }

    public int delete(int id) {

        var productDao = dao;
        return productDao.delete(id);

    }

    //クローズ処理
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                //System.out.println("データベースを閉じます");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
