package Pizza;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Pizza {
    private JPanel loginPanel;
    private JTextField idTextField;
    private JPasswordField passPasswordField;
    private JButton loginButton;

    private JPanel menuPanel;
    private JComboBox crustComboBox;
    private JComboBox sauceComboBox;
    private JComboBox cheeseComboBox;
    private JList toppingsList;
    private JTextField mobileTextField;
    private JButton checkoutButton;
    private JTextField crustTextField;
    private JTextField sauceTextField;
    private JTextField cheeseTextField;
    private JTextField priceTextField;
    private JTextField toppingsTextField;
    private JButton logoutButton;

    public int pizzaCount;
    public double totalSales;

    public Pizza() {

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idTextField.getText();
                String password = new String(passPasswordField.getPassword());

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pizza","root","hello");

                    String query = "SELECT Id, Password FROM Pizza.Staff WHERE Id = ? AND Password = ?";

                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, id);
                    ps.setString(2, password);
                    ps.executeQuery();

                    ResultSet rs = ps.executeQuery();
                    rs.next();
                    String checkId = rs.getString(1);
                    String checkPassword = rs.getString(2);

                    if((checkId.equals(id)) && (checkPassword).equals(password)) {

                        JOptionPane.showMessageDialog(null,"Login Successful.");
                        JFrame menuFrame = new JFrame("Menu");
                        menuFrame.setContentPane(new Pizza().menuPanel);
                        menuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        menuFrame.pack();
                        menuFrame.setVisible(true);
                        pizzaCount = 0;
                        totalSales = 0;

                    }
                    else {
                        JOptionPane.showMessageDialog(menuPanel,"Login Failed.");
                    }

                }
                catch (Exception ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null,"Login Failed.");
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Logout Successful. You sold " + pizzaCount +" Pizza(s) worth ₹" + totalSales);
                JFrame loginFrame = new JFrame("Login");
                loginFrame.setContentPane(new Pizza().loginPanel);
                loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                loginFrame.pack();
                loginFrame.setVisible(true);
            }
        });

        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double crustPrice = 0;
                double saucePrice = 0;
                double cheesePrice = 0;
                double toppingsPrice = 0;
                double price = 0;

                if(toppingsList.getSelectedIndices().length > 4) {
                    JOptionPane.showMessageDialog(null,"Toppings cannot not be more than 4.");
                }
                else {
                    String mobile = mobileTextField.getText();

                    if(!mobile.matches("^[0-9]{10}$")) {
                        JOptionPane.showMessageDialog(null,"Enter a valid 10 digit mobile number.");
                    }
                    else {
                        try {
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pizza","root","hello");

                            String query = "SELECT Name FROM Pizza.Customers WHERE Mobile = ?";

                            PreparedStatement ps = con.prepareStatement(query);
                            ps.setString(1, mobile);
                            ps.executeQuery();

                            ResultSet rs = ps.executeQuery();
                            rs.next();

                            String cusName = rs.getString(1);
                        }
                        catch (Exception ex) {
                            System.out.println(ex);
                            String cusName = JOptionPane.showInputDialog("Enter customer name:");

                            try {
                                Class.forName("com.mysql.jdbc.Driver");
                                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pizza","root","hello");

                                String query = "INSERT INTO Pizza.Customers (Name, Mobile) VALUES ('" + cusName + "','" + mobile + "');";

                                Statement st = con.createStatement();
                                st.executeUpdate(query);
                            }
                            catch (Exception exe) {
                                System.out.println(ex);
                            }
                        }

                        String crust = crustComboBox.getSelectedItem().toString();
                        String sauce = sauceComboBox.getSelectedItem().toString();
                        String cheese = cheeseComboBox.getSelectedItem().toString();
                        String toppings = toppingsList.getSelectedValuesList().toString();

                        try {
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pizza","root","hello");

                            String query = "SELECT Price FROM Pizza.Ingredients WHERE Name = ?;";

                            PreparedStatement ps = con.prepareStatement(query);
                            ps.setString(1, crust);
                            ps.executeQuery();

                            ResultSet rs = ps.executeQuery();
                            rs.next();

                            crustPrice = rs.getDouble(1);
                            price += crustPrice;

                            PreparedStatement psSauce = con.prepareStatement(query);
                            psSauce.setString(1, sauce);
                            psSauce.executeQuery();

                            ResultSet rsSauce = psSauce.executeQuery();
                            rsSauce.next();

                            saucePrice = rsSauce.getDouble(1);
                            price += saucePrice;

                            PreparedStatement psCheese = con.prepareStatement(query);
                            psCheese.setString(1, cheese);
                            psCheese.executeQuery();

                            ResultSet rsCheese = psCheese.executeQuery();
                            rsCheese.next();

                            cheesePrice = rsCheese.getDouble(1);
                            price += cheesePrice;

                            int[] selectedIndices = toppingsList.getSelectedIndices();

                            for (int i = 0; i < selectedIndices.length; i++) {
                                PreparedStatement psTopping = con.prepareStatement(query);
                                psTopping.setString(1, String.valueOf(toppingsList.getModel().getElementAt(selectedIndices[i])));
                                psTopping.executeQuery();

                                ResultSet rsTopping = psTopping.executeQuery();
                                rsTopping.next();

                                double currentToppingPrice = rsTopping.getDouble(1);
                                toppingsPrice += currentToppingPrice;
                            }

                            price += toppingsPrice;
                        }
                        catch (Exception ex) {
                            System.out.println(ex);
                        }

                        totalSales += price;

                        crustTextField.setText(crust);
                        sauceTextField.setText(sauce);
                        cheeseTextField.setText(cheese);
                        toppingsTextField.setText(toppings);
                        priceTextField.setText("₹" + String.valueOf(price));

                        try {
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pizza","root","hello");

                            String queryOrder = "INSERT INTO Pizza.Orders(Crust, Sauce, Cheese, Toppings, Price, Mobile) VALUES ('"+ crust +"','" + sauce +"','"+ cheese +"','"+ toppings +"','"+ price +"','"+ mobile +"')";

                            Statement stOrder = con.createStatement();
                            stOrder.executeUpdate(queryOrder);
                        }
                        catch (Exception exe) {
                            System.out.println(exe);
                        }

                        pizzaCount += 1;
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setContentPane(new Pizza().loginPanel);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.pack();
        loginFrame.setVisible(true);
    }
}