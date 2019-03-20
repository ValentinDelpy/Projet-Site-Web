package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;

public class DAO {

    private final DataSource myDataSource;

    /**
     * Construit le AO avec sa source de données
     *
     * @param dataSource la source de données à utiliser
     */
    public DAO(DataSource dataSource) {
        this.myDataSource = dataSource;
    }

    /**
     * Contenu de la table DISCOUNT_CODE
     *
     * @return Liste des discount codes
     * @throws SQLException renvoyées par JDBC
     */
    public List<DiscountCode> allCodes() throws SQLException {

        List<DiscountCode> result = new LinkedList<>();

        String sql = "SELECT * FROM DISCOUNT_CODE ORDER BY DISCOUNT_CODE";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("DISCOUNT_CODE");
                float rate = rs.getFloat("RATE");
                DiscountCode c = new DiscountCode(id, rate);
                result.add(c);
            }
        }
        return result;
    }

    /**
     * Ajout d'un enregistrement dans la table DISCOUNT_CODE
     *
     * @param code le code (non null)
     * @param rate le taux (positive or 0)
     * @return 1 si succès, 0 sinon
     * @throws SQLException renvoyées par JDBC
     */
    public int addDiscountCode(String code, float rate) throws SQLException {
        int result = 0;
        String sql = "INSERT INTO DISCOUNT_CODE VALUES (?, ?)";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code);
            stmt.setFloat(2, rate);
            result = stmt.executeUpdate();
        }
        return result;
    }

    /**
     * Supprime un enregistrement dans la table DISCOUNT_CODE
     *
     * @param code la clé de l'enregistrement à supprimer
     * @return le nombre d'enregistrements supprimés (1 ou 0)
     * @throws java.sql.SQLException renvoyées par JDBC
	 *
     */
    public int deleteDiscountCode(String code) throws SQLException {
        int result = 0;
        String sql = "DELETE FROM DISCOUNT_CODE WHERE DISCOUNT_CODE = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code);
            result = stmt.executeUpdate();
        }
        return result;
    }

    public int getCustomerID(String email) throws SQLException {
        int result = 0;
        String sql = "SELECT CUSTOMER_ID FROM CUSTOMER WHERE EMAIL = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            result = rs.getInt("CUSTOMER_ID");
        }
        return result;
    }

    //Méthode pour connnaître le solde d'un client.
    public double soldeClient(int id) throws SQLException {
        double ret = 0;
        String sql = "SELECT CREDIT_LIMIT FROM CUSTOMER WHERE CUSTOMER_ID = ?";
        try (
                Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ret = rs.getDouble("CREDIT_LIMIT");
            }
        }
        return ret;
    }
    
    //Permet de définir le montant d'argent sur le compte d'un client.
    public int addMoney(int id, double montant) throws SQLException {
        int ret = 0;
        String sql = "UPDATE CUSTOMER SET CREDIT_LIMIT = ? WHERE CUSTOMER_ID = ?";
        try (
                Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, (int) (soldeClient(id) + montant));
            stmt.setInt(2, id);
            ret = stmt.executeUpdate();
        }
        return ret;
    }
    
    //Permet de mettre à jour le montant d'argent sur le compte d'un client.
    public int substractMoney(int id, double price) throws SQLException {
        int ret = 0;
        String sql = "UPDATE CUSTOMER SET CREDIT_LIMIT = ? WHERE CUSTOMER_ID = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, (int) (this.soldeClient(id) - price));
            stmt.setInt(2, id);
            ret = stmt.executeUpdate();
        }
        return ret;
    }
    
}
