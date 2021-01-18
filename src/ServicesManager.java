import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ServicesManager extends DatabaseConnection {
    Statement statement;
    Scanner sc = new Scanner(System.in);


    public void addNewService() {
        final Statement[] statement = new Statement[1];
        JFrame addNewServiceFrame = new JFrame();

        JTextField textFieldName = new JTextField("Name : ");
        textFieldName.setBounds(50, 70, 100, 20);
        textFieldName.setEditable(false);
        JTextField textFieldNameAdd = new JTextField();
        textFieldNameAdd.setBounds(180, 70, 150, 20);
        addNewServiceFrame.add(textFieldName);
        addNewServiceFrame.add(textFieldNameAdd);

        JTextField textFieldCategory = new JTextField("Category : ");
        textFieldCategory.setBounds(50, 90, 100, 20);
        textFieldCategory.setEditable(false);
        JTextField textFieldCategoryAdd = new JTextField();
        textFieldCategoryAdd.setBounds(180, 90, 150, 20);
        addNewServiceFrame.add(textFieldCategory);
        addNewServiceFrame.add(textFieldCategoryAdd);

        JTextField textFieldPrice = new JTextField("Price : ");
        textFieldPrice.setBounds(50, 110, 100, 20);
        textFieldPrice.setEditable(false);
        JTextField textFieldPriceAdd = new JTextField();
        textFieldPriceAdd.setBounds(180, 110, 150, 20);
        addNewServiceFrame.add(textFieldPrice);
        addNewServiceFrame.add(textFieldPriceAdd);


        JTextField textFieldCode = new JTextField("Code : ");
        textFieldCode.setBounds(50, 130, 100, 20);
        textFieldCode.setEditable(false);
        JTextField textFieldCodeAdd = new JTextField();
        textFieldCodeAdd.setBounds(180, 130, 150, 20);
        addNewServiceFrame.add(textFieldCode);
        addNewServiceFrame.add(textFieldCodeAdd);


        JButton button = new JButton("Confirm");
        button.setBounds(230, 250, 100, 20);
        addNewServiceFrame.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String addservicesql = "INSERT INTO services(name_service, category, price , code ) VALUES ('" + textFieldNameAdd.getText() + "','" + textFieldCategoryAdd.getText() + "'," + Integer.parseInt(textFieldPriceAdd.getText()) + ",'" + textFieldCodeAdd.getText() + "')";
                try {
                    statement[0] = createConnection().createStatement();
                    statement[0].executeUpdate(addservicesql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(addNewServiceFrame, "That service has been added.");
            }
        });

        addNewServiceFrame.setSize(400, 350);
        addNewServiceFrame.setLayout(null);
        addNewServiceFrame.setLocationRelativeTo(null);
        addNewServiceFrame.setVisible(true);
        addNewServiceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void editService() {
        final Statement[] statement = new Statement[1];
        JFrame editServiceFrame = new JFrame();
        JButton button1 = new JButton("Name");
        button1.setBounds(140, 70, 100, 30);

        JButton button2 = new JButton("Category");
        button2.setBounds(140, 110, 100, 30);

        JButton button3 = new JButton("Price");
        button3.setBounds(140, 150, 100, 30);

        JButton button4 = new JButton("Code");
        button4.setBounds(140, 190, 100, 30);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int serviceid = Integer.parseInt(JOptionPane.showInputDialog("Write down the ID of the service you will update."));
                String name = JOptionPane.showInputDialog("What will replace the name?");
                String updatesql1 = "UPDATE services SET name_service ='" + name + "' WHERE id_service = " + serviceid;
                try {
                    statement[0] = createConnection().createStatement();
                    statement[0].executeUpdate(updatesql1);
                    JOptionPane.showMessageDialog(editServiceFrame, "Changed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int serviceid = Integer.parseInt(JOptionPane.showInputDialog("Write down the ID of the service you will update."));
                String category = JOptionPane.showInputDialog("What will replace the category?");
                String updatesql2 = "UPDATE services SET category ='" + category + "' WHERE id_service = " + serviceid;
                try {
                    statement[0] = createConnection().createStatement();
                    statement[0].executeUpdate(updatesql2);
                    JOptionPane.showMessageDialog(editServiceFrame, "Changed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int serviceid = Integer.parseInt(JOptionPane.showInputDialog("Write down the ID of the service you will update."));
                int price = Integer.parseInt(JOptionPane.showInputDialog("What will replace the price?"));
                String updatesql3 = "UPDATE services SET price =" + price + " WHERE id_service = " + serviceid;
                try {
                    statement[0] = createConnection().createStatement();
                    statement[0].executeUpdate(updatesql3);
                    JOptionPane.showMessageDialog(editServiceFrame, "Changed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int serviceid = Integer.parseInt(JOptionPane.showInputDialog("Write down the ID of the service you will update."));
                String code = JOptionPane.showInputDialog("What will replace the code?");
                    String updatesql4 = "UPDATE services SET code ='" + code + "' WHERE id_service = " + serviceid;
                try {
                    statement[0] = createConnection().createStatement();
                    statement[0].executeUpdate(updatesql4);
                    JOptionPane.showMessageDialog(editServiceFrame, "Changed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        editServiceFrame.add(button1);
        editServiceFrame.add(button2);
        editServiceFrame.add(button3);
        editServiceFrame.add(button4);

        editServiceFrame.setSize(400, 350);
        editServiceFrame.setLocationRelativeTo(null);
        editServiceFrame.setLayout(null);
        editServiceFrame.setVisible(true);
        editServiceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public void deleteService() {
        Statement statement;
        JFrame deleteServiceFrame = new JFrame();
        int reply = JOptionPane.showConfirmDialog(deleteServiceFrame, "Are you sure delete any service?", "", JOptionPane.YES_NO_CANCEL_OPTION);
        if (reply == JOptionPane.NO_OPTION || reply == JOptionPane.CANCEL_OPTION) {
            System.exit(0);
        }
        int serviceid = Integer.parseInt(JOptionPane.showInputDialog("Write down the ID of the service you will delete."));
        String deletesql = "DELETE FROM services WHERE id_service =" + serviceid;
        try {
            statement = createConnection().createStatement();
            statement.executeUpdate(deletesql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(deleteServiceFrame, "Deleted.");
        System.exit(0);
        deleteServiceFrame.setSize(400, 350);
        deleteServiceFrame.setLocationRelativeTo(null);
        deleteServiceFrame.setLayout(null);
        deleteServiceFrame.setVisible(true);
        deleteServiceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void viewAllServices() {
        Statement statement;
        JFrame viewAllServicesFrame = new JFrame();
        String servicesql = "SELECT * FROM services";
        JTable table = new JTable();
        try {
            ResultSet rs = createConnection().prepareStatement(servicesql).executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            JScrollPane scrollPane = new JScrollPane(table);
            viewAllServicesFrame.add(scrollPane);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        viewAllServicesFrame.setLocationRelativeTo(null);
        viewAllServicesFrame.setSize(400, 350);
        viewAllServicesFrame.setVisible(true);
        viewAllServicesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}