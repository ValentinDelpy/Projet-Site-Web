package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public DAO() {
        this.myDataSource = DataSourceFactory.getDataSource();
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
            stmt.setString(1, email);
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

    public List<Product> allProducts() throws SQLException {
        ArrayList<Product> myProducts = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCT";
        try (
                Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("PRODUCT_ID");
                String descr = rs.getString("DESCRIPTION");
                int price = rs.getInt("PURCHASE_COST");
                Product p = new Product(id);
                p.setDescription(descr);
                p.setPurchaseCost(price);
                myProducts.add(p);
            }
        }
        return myProducts;
    }

    //Récupère le taux de réduction d'un customer en fonction de son identifiant.
    public double customerRate(int customer_ID) throws SQLException {
        double ret = 0;
        String sql = "SELECT RATE FROM DISCOUNT_CODE INNER JOIN CUSTOMER ON CUSTOMER.DISCOUNT_CODE = DISCOUNT_CODE.DISCOUNT_CODE WHERE CUSTOMER_ID = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customer_ID);
            ResultSet rs = stmt.executeQuery();
            ret = rs.getDouble("RATE");
        }
        return ret;
    }

    //Retourne la liste de tous les clients.
    public List<Customer> allCustomers() throws SQLException {
        ArrayList<Customer> myCustomers = new ArrayList<>();
        String sql = "SELECT * FROM CUSTOMER";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                Customer c = new Customer("NaN");
                c.setEmail("NaN");
                c.setPassword("NaN");
                c.setName("NaN");
                myCustomers.add(c);
            } else {
                while (rs.next()) {
                    String email = rs.getString("EMAIL");
                    Customer c = new Customer(email);
                    c.setCity(rs.getString("CITY"));
                    c.setCredit(rs.getInt("CREDIT_LIMIT"));
                    c.setName(rs.getString("NAME"));
                    c.setAddressline1(rs.getString("ADDRESSLINE1"));
                    c.setState(rs.getString("STATE"));
                    c.setPassword(String.valueOf(rs.getInt("CUSTOMER_ID")));
                    myCustomers.add(c);
                }
            }
        }
        return myCustomers;
    }

    //Retourne toutes les infos d'un customer, en fonction de son adresse mail.
    public Customer selectClientByID(String email) throws SQLException {
        Customer c = new Customer(email);
        String sql = "SELECT * FROM CUSTOMER WHERE EMAIL = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                c.setEmail("NaN");
                c.setPassword("NaN");
                c.setName("NaN");
            } else {
                c.setCity(rs.getString("CITY"));
                c.setCredit(rs.getInt("CREDIT_LIMIT"));
                c.setName(rs.getString("NAME"));
                c.setAddressline1(rs.getString("ADDRESSLINE1"));
                c.setState(rs.getString("STATE"));
                c.setPassword(String.valueOf(rs.getInt("CUSTOMER_ID")));
            }
        }
        return c;
    }
    
   public double price(int id, int product_id, int quantity) throws SQLException {
        double result = 0;
        String sql = "SELECT PURCHASE_COST FROM PRODUCT WHERE PRODUCT_ID = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, product_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = (rs.getDouble("PURCHASE_COST") * quantity) * ((100 - customerRate(id)) / 100);
            }
        }
        return result;
    }
   
    public int generateOrderNum() throws SQLException{
        int ret = 0;
        String sql = "SELECT MAX(ORDER_NUM) FROM PURCHASE_ORDER";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            ret = rs.getInt("ORDER_NUM") + 1;
        }
        return ret;
    }
    
    public boolean checkBuying(int id, int product_id, int quantity) throws SQLException{
        boolean ret = false;
        double solde = this.soldeClient(id);
        if(solde > price(id,product_id,quantity)){
            ret = true;
        }
        return ret;
    }
    //Permet de créer une commande.
    public int addPurchaseOrder(int id, int product_id, int quantity) throws SQLException{
        int ret = 0;
        String sql = "INSERT INTO PURCHASE_ORDER (ORDER_NUM, CUSTOMER_ID, PRODUCT_ID, QUANTITY, SHIPPING_DATE) VALUES (?, ?, ?, ?, ?)";
        if(checkBuying(id,product_id,quantity)==true){
                try(Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)){
                    stmt.setInt(1,generateOrderNum());
                    stmt.setInt(2,id);
                    stmt.setInt(3,product_id);
                    stmt.setInt(4, quantity);
                    stmt.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
                    ret = stmt.executeUpdate();
        }    
        }else{
            throw new SQLException("Pas assez d'argent sur votre compte. L'achat coûte "+price(id,product_id,quantity)+" euros. Vous disposez de "+soldeClient(id)+" euros.");
        }
        return ret;
    }
    
    public int deletePurchaseOrder(int purchase_id) throws SQLException{
        int ret =0;
        String sql = "DELETE FROM PURCHASE_ORDER WHERE ORDER_NUM = ?";
        try(Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)){
                stmt.setInt(1,purchase_id);
                ret = stmt.executeUpdate();
        }
        return ret;
    }
    
    public int getProductIDByOrderNum(int order_num) throws SQLException{
        int ret = 0;
        String sql = "SELECT PRODUCT_ID FROM PURCHASE_ORDER WHERE ORDER_NUM = ?";
        try(Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, order_num);
            ResultSet rs = stmt.executeQuery();
            ret = rs.getInt("PRODUCT_ID");
        }
        return ret;
    }
}
