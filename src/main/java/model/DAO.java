package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

    public ArrayList<String> allProduct2() throws SQLException {
        ArrayList<String> result = new ArrayList<>();
        String sql = "SELECT DESCRIPTION FROM PRODUCT WHERE QUANTITY_ON_HAND > 0";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String des = rs.getString("DESCRIPTION");
                result.add(des);
            }
        }
        return result;
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

    public ArrayList<String> productsDesc() throws SQLException {
        ArrayList<String> result = new ArrayList<>();
        String sql = "SELECT DESCRIPTION FROM PRODUCT WHERE QUANTITY_ON_HAND > 0";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String des = rs.getString("DESCRIPTION");
                result.add(des);
            }
        }
        return result;
    }

    //Récupère le taux de réduction d'un customer en fonction de son identifiant.
    public double customerRate(int customer_id) throws SQLException {
        double ret = 0;
        String sql = "SELECT RATE FROM DISCOUNT_CODE"
                + " INNER JOIN CUSTOMER"
                + " USING (DISCOUNT_CODE)"
                + " WHERE CUSTOMER_ID = ?";

        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customer_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                double rate = rs.getDouble("RATE");
                ret = rate;
            }
        }
        System.out.println("ma valeur est de  -----------------------" + ret);
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
                    c.setID(rs.getInt("CUSTOMER_ID"));
                    
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
    public Customer selectCustomerByMail(String email) throws SQLException {
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
                result = (rs.getDouble("PURCHASE_COST") * quantity)                //result = (rs.getDouble("PURCHASE_COST") * quantity);
;
                System.out.println(rs.getDouble("PURCHASE_COST"));
                System.out.println("____________________---------------______________"+result+"kkkkkkkkk"+quantity);
            }
        }
        return result;
    }

    public int generateOrderNum() throws SQLException {
        List<Integer> result = new ArrayList<>();

        String sql = "SELECT ORDER_NUM FROM PURCHASE_ORDER";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ORDER_NUM");
                result.add(id);
            }
        }
        int numAlea = (int) (Math.random() * 10000);
        while (result.contains(numAlea)) {
            numAlea = (int) (Math.random() * 10000);
        }
        return numAlea;
    }

    public boolean checkBuying(int id, int product_id, int quantity) throws SQLException {
        boolean ret = false;
        double solde = this.soldeClient(id);
        if (solde > price(id, product_id, quantity)) {
            ret = true;
        }
        return ret;
    }

    //Permet de créer une commande.

    public int addPurchaseOrder(int id, int product_id, int quantity) throws SQLException {
        int ret = 0;
        String sql = "INSERT INTO PURCHASE_ORDER (ORDER_NUM, CUSTOMER_ID, PRODUCT_ID, QUANTITY, SHIPPING_DATE) VALUES (?, ?, ?, ?, ?)";
        if (checkBuying(id, product_id, quantity) == true) {
            this.substractMoney(id, price(id, product_id, quantity));
            try (Connection connection = myDataSource.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, generateOrderNum());
                stmt.setInt(2, id);
                stmt.setInt(3, product_id);
                stmt.setInt(4, quantity);
                stmt.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
                ret = stmt.executeUpdate();
            }
        } else {
            throw new SQLException("Pas assez d'argent sur votre compte. L'achat coûte " + price(id, product_id, quantity) + " euros. Vous disposez de " + soldeClient(id) + " euros.");
        }
        return ret;
    }

    public int deletePurchaseOrder(int order_num) throws SQLException {
        int ret = 0;

        this.addMoney(this.CustomerIdByOrderNum(order_num), this.price(this.ancienneQuantite(order_num), this.prodId(order_num), this.CustomerIdByOrderNum(order_num)));

        String sql = "DELETE FROM PURCHASE_ORDER WHERE ORDER_NUM = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order_num);
            ret = stmt.executeUpdate();
        }
        return ret;
    }

    public int getProductIDByOrderNum(int order_num) throws SQLException {
        int ret = 0;
        String sql = "SELECT PRODUCT_ID FROM PURCHASE_ORDER WHERE ORDER_NUM = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order_num);
            ResultSet rs = stmt.executeQuery();
            ret = rs.getInt("PRODUCT_ID");
        }
        return ret;
    }

    public List<PurchaseOrder> customerCommandes(Customer c) throws SQLException {
        List<PurchaseOrder> result = new LinkedList<>();
        int id = Integer.parseInt(c.getPassword());
        String sql = "SELECT ORDER_NUM, CUSTOMER_ID, PRODUCT_ID, QUANTITY, SHIPPING_DATE, DESCRIPTION FROM PURCHASE_ORDER"
                + " INNER JOIN CUSTOMER"
                + " USING(CUSTOMER_ID)"
                + " INNER JOIN PRODUCT"
                + " USING (PRODUCT_ID)"
                + " WHERE CUSTOMER_ID = ? ";
        try (
                Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int code = rs.getInt("ORDER_NUM");
                int idCust = rs.getInt("CUSTOMER_ID");
                int quantity = rs.getInt("QUANTITY");
                int product = rs.getInt("PRODUCT_ID");
                double cost = price(quantity, product, idCust);
                Date date = rs.getDate("SHIPPING_DATE");
                String descritpion = rs.getString("DESCRIPTION");
                PurchaseOrder po = new PurchaseOrder(code, idCust, quantity);
                po.setDESCRIPTION(descritpion);
                System.out.println("ici le prix unitaire : " + cost);
                po.setCOST(cost);
                po.setSHIPPING_DATE(date);
                result.add(po);

            }
        }

        return result;
    }

    public int numProduct(String des) throws SQLException {
        int result = 0;

        String sql = "SELECT * FROM PRODUCT WHERE DESCRIPTION LIKE ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, des);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                result = rs.getInt("PRODUCT_ID");
            }
        }
        System.out.println("Le produit est : " + result + "----------------------");
        return result;
    }

    public boolean editCommande(int order_num, int quantity, int customerID) throws SQLException {
        Boolean res = false;
        int oldQuantity = this.ancienneQuantite(order_num);
        if (oldQuantity >= quantity) {
            addMoney(this.CustomerIdByOrderNum(order_num), this.price(oldQuantity - quantity, this.prodId(order_num), this.CustomerIdByOrderNum(order_num)));
            String sql = "UPDATE PURCHASE_ORDER SET QUANTITY = ? WHERE ORDER_NUM = ?";
            try (Connection connection = myDataSource.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, quantity);

                stmt.setInt(2, order_num);
                int result = stmt.executeUpdate();
                res = true;

            }
        } else {
            int diff = quantity - oldQuantity;
            if (this.checkBuying(customerID, this.prodId(order_num), diff)) {
                substractMoney(customerID, price(diff, this.prodId(order_num), customerID));
                String sql = "UPDATE PURCHASE_ORDER SET QUANTITY = ? WHERE ORDER_NUM = ?";
                try (Connection connection = myDataSource.getConnection();
                        PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setInt(1, quantity);

                    stmt.setInt(2, order_num);
                    int result = stmt.executeUpdate();
                    res = true;
                }
            }

        }
        return res;

    }

    public int ancienneQuantite(int order_num) throws SQLException {
        int res = 0;

        String sql = "SELECT QUANTITY FROM PURCHASE_ORDER WHERE ORDER_NUM = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order_num);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                res = rs.getInt("QUANTITY");
            }
        }
        return res;
    }

    public int CustomerIdByOrderNum(int order_num) throws SQLException {
        int res = 0;

        String sql = "SELECT CUSTOMER_ID FROM PURCHASE_ORDER WHERE ORDER_NUM = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order_num);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                res = rs.getInt("CUSTOMER_ID");
            }
        }
        return res;
    }

    public List<DiscountCode> customerCodes(Customer c) throws SQLException {
        List<DiscountCode> result = new LinkedList<>();
        int id = Integer.parseInt(c.getPassword());
        String sql = "SELECT * FROM DISCOUNT_CODE"
                + " INNER JOIN CUSTOMER"
                + " ON DISCOUNT_CODE.DISCOUNT_CODE = CUSTOMER.DISCOUNT_CODE"
                + " WHERE CUSTOMER_ID = ? ";
        try (
                Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String code = rs.getString("DISCOUNT_CODE");
                Float rate = rs.getFloat("RATE");
                DiscountCode dc = new DiscountCode(code, rate);
                result.add(dc);

            }
        }

        return result;
    }
    
     public int prodId(int order_num) throws SQLException {
        int res = 0;

        String sql = "SELECT PRODUCT_ID FROM PURCHASE_ORDER WHERE ORDER_NUM = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order_num);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                res = rs.getInt("PRODUCT_ID");
            }
        }
        return res;
    }

    public double benefice() throws SQLException {
        double result = 0;
        String sql = "SELECT * FROM PURCHASE_ORDER INNER JOIN PRODUCT ON PRODUCT.PRODUCT_ID = PURCHASE_ORDER.PRODUCT_ID";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = result + rs.getDouble("PURCHASE_COST") * rs.getInt("QUANTITY");
            }
        }
        return result;
    }
        public double getCost(int product_id) throws SQLException {
        double result = 0;
        String sql = "SELECT PURCHASE_COST FROM PRODUCT WHERE PRODUCT_ID = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, product_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                result = rs.getDouble("PURCHASE_COST");
            }
        }
        return result;
        }

    public int nbClients() throws SQLException {
        int result = 0;
        String sql = "SELECT * FROM CUSTOMER";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result++;
            }
        }
        return result;
    }

    public int nbManufacturer() throws SQLException {
        int result = 0;
        String sql = "SELECT * FROM MANUFACTURER";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result++;
            }
        }
        return result;
    }

    public int nbProduits() throws SQLException {
        int result = 0;
        String sql = "SELECT * FROM PRODUCT";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result++;
            }
        }
        return result;
    }
    
        public String descProduct(int product_id) throws SQLException {
        String ret = "";
        String sql = "SELECT DESCRIPTION FROM PRODUCT WHERE PRODUCT_ID = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, product_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ret = rs.getString("DESCRIPTION");

            }
        }

        return ret;
    }
        
        
            public String nameCustomer(int id) throws SQLException {
        String ret = "";
        String sql = "SELECT NAME FROM CUSTOMER WHERE CUSTOMER_ID = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ret = rs.getString("NAME");

            }
        }

        return ret;
    }
        
    
public Map<String, Double> chiffreAffaireByProduct(String deb, String fin) throws SQLException {
        Map<String, Double> ret = new HashMap<>();
        String sql = "SELECT PRODUCT_ID, SUM(QUANTITY) AS SALES FROM PURCHASE_ORDER"
                + " WHERE SHIPPING_DATE BETWEEN ? AND ?"
                + " GROUP BY PRODUCT_ID";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed1 = null;
            Date parsed2 = null;
            try {
                parsed1 = sdf.parse(deb);
            } catch (ParseException e1) {
                // TODO Auto-generated catch block

            }
            try {
                parsed2 = sdf.parse(fin);
            } catch (ParseException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            java.sql.Date data1 = new java.sql.Date(parsed1.getTime());
            java.sql.Date data2 = new java.sql.Date(parsed2.getTime());
            System.out.println("Le Ca est de ------------------------------------------------------------------ " + data1);
            System.out.println("Le Ca est de ------------------------------------------------------------------ " + data2);
            stmt.setDate(1, data1);
            stmt.setDate(2, data2);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String product = descProduct(rs.getInt("PRODUCT_ID"));
                double price = rs.getDouble("SALES") * getCost(rs.getInt("PRODUCT_ID"));
                ret.put(product, price);
                System.out.println("Le Ca est de ------------------------------------------------------------------ " + price);
            }
        }

        return ret;
    }

    /**
     * 
     * @param deb date de début de l'analyse
     * @param fin date de fin de l'analyse
     * @return le chiffre d'affaires effectué par client
     * @throws SQLException 
     */
    public Map<String, Double> chiffreAffaireByCustomer(String deb, String fin) throws SQLException {
        Map<String, Double> ret = new HashMap<>();
        String sql = "SELECT CUSTOMER_ID, PRODUCT_ID, QUANTITY FROM PURCHASE_ORDER"
                + " WHERE SHIPPING_DATE BETWEEN ? AND ?";

        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed1 = null;
            Date parsed2 = null;
            try {
                parsed1 = sdf.parse(deb);
            } catch (ParseException e1) {
                // TODO Auto-generated catch block

            }
            try {
                parsed2 = sdf.parse(fin);
            } catch (ParseException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            java.sql.Date data1 = new java.sql.Date(parsed1.getTime());
            java.sql.Date data2 = new java.sql.Date(parsed2.getTime());

            stmt.setDate(1, data1);
            stmt.setDate(2, data2);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String customer = nameCustomer(rs.getInt("CUSTOMER_ID"));
                double price = rs.getDouble("QUANTITY") * getCost(rs.getInt("PRODUCT_ID"));

                if (ret.containsKey(customer)) {
                    ret.put(customer, ret.get(customer) + price);
                    System.out.println("nouveau ca  " + customer + " est de " + ret.get(customer));
                } else {
                    ret.put(customer, price);
                    System.out.println("ca = " + customer + " est de " + price);
                }
            }
        }

        return ret;
    }

    /**
     * 
     * @param deb date de début d'analyse
     * @param fin date de fin d'analyse 
     * @return le chiffre d'affaires représenté par état
     * @throws SQLException 
     */
    public Map<String, Double> chiffreAffaireByState(String deb, String fin) throws SQLException {
        Map<String, Double> ret = new HashMap<>();
        String sql = "SELECT PRODUCT_ID, CUSTOMER_ID, QUANTITY, STATE FROM PURCHASE_ORDER"
                + " INNER JOIN CUSTOMER"
                + " USING (CUSTOMER_ID)"
                + " WHERE SHIPPING_DATE BETWEEN ? AND ?";

        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed1 = null;
            Date parsed2 = null;
            try {
                parsed1 = sdf.parse(deb);
            } catch (ParseException e1) {
                // TODO Auto-generated catch block

            }
            try {
                parsed2 = sdf.parse(fin);
            } catch (ParseException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            java.sql.Date data1 = new java.sql.Date(parsed1.getTime());
            java.sql.Date data2 = new java.sql.Date(parsed2.getTime());

            stmt.setDate(1, data1);
            stmt.setDate(2, data2);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String state = rs.getString("STATE");
                double price = rs.getDouble("QUANTITY") * getCost(rs.getInt("PRODUCT_ID"));
                if (ret.containsKey(state)) {
                    ret.put(state, ret.get(state) + price);
                    System.out.println("nouveau ca  " + state + " est de " + ret.get(state));
                } else {
                    ret.put(state, price);
                    System.out.println("ca = " + state + " est de " + price);
                }

            }
        }

        return ret;
    }

    /**
     * 
     * @param deb date de début d'analyse
     * @param fin date de fin d'analyse
     * @return le chiffre d'affaires de l'entreprise effectué par rapport au produit (à leur code)
     * @throws SQLException 
     */
    public Map<String, Double> chiffreAffaireByProductCode(String deb, String fin) throws SQLException {
        Map<String, Double> ret = new HashMap<>();
        String sql = "SELECT PURCHASE_ORDER.PRODUCT_ID, QUANTITY, PRODUCT_CODE.DESCRIPTION FROM PURCHASE_ORDER"
                + " INNER JOIN PRODUCT"
                + " USING (PRODUCT_ID)"
                + " INNER JOIN PRODUCT_CODE"
                + " ON PRODUCT.PRODUCT_CODE = PRODUCT_CODE.PROD_CODE"
                + " WHERE SHIPPING_DATE BETWEEN ? AND ?";

        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed1 = null;
            Date parsed2 = null;
            try {
                parsed1 = sdf.parse(deb);
            } catch (ParseException e1) {
                // TODO Auto-generated catch block

            }
            try {
                parsed2 = sdf.parse(fin);
            } catch (ParseException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            java.sql.Date data1 = new java.sql.Date(parsed1.getTime());
            java.sql.Date data2 = new java.sql.Date(parsed2.getTime());

            stmt.setDate(1, data1);
            stmt.setDate(2, data2);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String state = rs.getString("DESCRIPTION");
                double price = rs.getDouble("QUANTITY") * getCost(rs.getInt("PRODUCT_ID"));
                if (ret.containsKey(state)) {
                    ret.put(state, ret.get(state) + price);
                    System.out.println("nouveau ca  " + state + " est de " + ret.get(state));
                } else {
                    ret.put(state, price);
                    System.out.println("ca = " + state + " est de " + price);
                }

            }
        }

        return ret;
    }

    /**
     * 
     * @param deb date de début d'analyse
     * @param fin date de fin d'analyse
     * @return le chiffre d'affaires de l'entreprise en fonction du code postal
     * @throws SQLException 
     */
    public Map<String, Double> chiffreAffaireByZip(String deb, String fin) throws SQLException {
        Map<String, Double> ret = new HashMap<>();
        String sql = "SELECT PRODUCT_ID, CUSTOMER_ID, QUANTITY, ZIP FROM PURCHASE_ORDER"
                + " INNER JOIN CUSTOMER"
                + " USING (CUSTOMER_ID)"
                + " WHERE SHIPPING_DATE BETWEEN ? AND ?";

        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed1 = null;
            Date parsed2 = null;
            try {
                parsed1 = sdf.parse(deb);
            } catch (ParseException e1) {
                // TODO Auto-generated catch block

            }
            try {
                parsed2 = sdf.parse(fin);
            } catch (ParseException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            java.sql.Date data1 = new java.sql.Date(parsed1.getTime());
            java.sql.Date data2 = new java.sql.Date(parsed2.getTime());

            stmt.setDate(1, data1);
            stmt.setDate(2, data2);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String state = rs.getString("ZIP");
                double price = rs.getDouble("QUANTITY") * getCost(rs.getInt("PRODUCT_ID"));
                if (ret.containsKey(state)) {
                    ret.put(state, ret.get(state) + price);
                    System.out.println("nouveau ca  " + state + " est de " + ret.get(state));
                } else {
                    ret.put(state, price);
                    System.out.println("ca = " + state + " est de " + price);
                }

            }
        }

        return ret;
    }
}
