import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private Connection con;

    public  ProductDao(Connection con) {
        this.con = con;
    }

    public ProductRecord findById(int id) {
        final var sql = "SELECT id, name, price FROM products WHERE id = ?";

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {     // tryの()内にPreparedStatementを書く場合は、明示的にcloseする必要はない
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new ProductRecord(rs.getInt("id"), rs.getString("name"), rs.getInt("price"));
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<ProductRecord> findByName(String name) {
        final var sql = "SELECT id, name, price FROM products WHERE name LIKE ?";

        var list = new ArrayList<ProductRecord>();

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                var product = new ProductRecord(rs.getInt("id"), rs.getString("name"), rs.getInt("price"));
                list.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;

    }

    public int insert(ProductRecord product) throws SQLException {
        var id = product.id();
        var name = product.name();
        var price = product.price();

        final var sql = "INSERT INTO products (id, name, price) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setInt(3, price);

            return stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException();
        }

    }

    public int update(ProductRecord product) {
        var id = product.id();
        var name = product.name();
        var price = product.price();

        final var sql = "UPDATE products SET name = ?, price = ? WHERE id = ? ";

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setInt(2, price);
            stmt.setInt(3, id);

            return stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int delete(int id) {
        final var sql = "DELETE FROM products WHERE id = ? ";

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
