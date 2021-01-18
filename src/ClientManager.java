import com.toedter.calendar.JDateChooser;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class ClientManager extends DatabaseConnection {
    Statement statement;

    public void addNewClient() {
        final Statement[] statement = new Statement[1];


        JFrame addNewClientFrame = new JFrame();

        JTextField textFieldName = new JTextField("Name : ");
        textFieldName.setBounds(20, 70, 130, 20);
        textFieldName.setEditable(false);
        JTextField textFieldNameAdd = new JTextField();
        textFieldNameAdd.setBounds(220, 70, 150, 20);
        addNewClientFrame.add(textFieldName);
        addNewClientFrame.add(textFieldNameAdd);

        JTextField textFieldSurname = new JTextField("Surname : ");
        textFieldSurname.setBounds(20, 90, 130, 20);
        textFieldSurname.setEditable(false);
        JTextField textFieldSurnameAdd = new JTextField();
        textFieldSurnameAdd.setBounds(220, 90, 150, 20);
        addNewClientFrame.add(textFieldSurname);
        addNewClientFrame.add(textFieldSurnameAdd);

        JTextField textFieldNumber = new JTextField("Phone Number :  +994");
        textFieldNumber.setBounds(20, 110, 130, 20);
        textFieldNumber.setEditable(false);
        JTextField textFieldNumberAdd = new JTextField();
        textFieldNumberAdd.setBounds(220, 110, 150, 20);
        addNewClientFrame.add(textFieldNumber);
        addNewClientFrame.add(textFieldNumberAdd);
        textFieldNumberAdd.addKeyListener(new KeyAdapter() {
                @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                String phone = textFieldNumberAdd.getText();
                int lenght = phone.length();
                char c = e.getKeyChar();
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
                    if (lenght < 7) {
                        textFieldNumberAdd.setEditable(true);
                    } else {
                        textFieldNumberAdd.setEditable(false);
                    }
                } else {
                    if (Character.isLetter(c)) {
                        textFieldNumberAdd.setEditable(false);
                    } else {
                        textFieldNumberAdd.setEditable(true);
                    }
                }
            }
        });

        String arr[] = {"50", "51", "70", "77", "55", "99"};
        JComboBox comboBox = new JComboBox(arr);
        comboBox.setBounds(160, 110, 50, 20);
        addNewClientFrame.add(comboBox);


        JTextField textFieldBirthday = new JTextField("Birthday : ");
        textFieldBirthday.setBounds(20, 130, 130, 20);
        textFieldBirthday.setEditable(false);
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(220, 130, 150, 20);
        addNewClientFrame.add(dateChooser);
        addNewClientFrame.add(textFieldBirthday);


        JButton button = new JButton("Confirm");
        button.setBounds(230, 250, 100, 20);
        addNewClientFrame.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
                String date = dcn.format(dateChooser.getDate());
                String prefix = comboBox.getSelectedItem().toString();
                String addclientsql = "INSERT INTO clients(name_client, surname, phonenumber , birthday,Summerymoneypaid ) VALUES ('" + textFieldNameAdd.getText() + "','" + textFieldSurnameAdd.getText() + "', " + prefix + String.valueOf(Integer.parseInt(textFieldNumberAdd.getText())) + ",'" + date + "',0)";
                try {
                    statement[0] = createConnection().createStatement();
                    statement[0].executeUpdate(addclientsql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(addNewClientFrame, "That client has been added.");
            }
        });

        addNewClientFrame.setSize(400, 350);
        addNewClientFrame.setLayout(null);
        addNewClientFrame.setLocationRelativeTo(null);
        addNewClientFrame.setVisible(true);
        addNewClientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void editClient() {
        final Statement[] statement = new Statement[1];
        JFrame editClientFrame = new JFrame();
        JButton button1 = new JButton("Name");
        button1.setBounds(140, 70, 100, 30);

        JButton button2 = new JButton("Surname");
        button2.setBounds(140, 110, 100, 30);

        JButton button3 = new JButton("Phone Number");
        button3.setBounds(140, 150, 100, 30);

        JButton button4 = new JButton("Birthday");
        button4.setBounds(140, 190, 100, 30);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int clientid = Integer.parseInt(JOptionPane.showInputDialog("Write down the ID of the client you will update."));
                String name = JOptionPane.showInputDialog("What will replace the name?");
                String updatesql1 = "UPDATE clients SET name_client ='" + name + "' WHERE id_client=" + clientid;
                try {
                    statement[0] = createConnection().createStatement();
                    statement[0].executeUpdate(updatesql1);
                    JOptionPane.showMessageDialog(editClientFrame, "Changed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int clientid = Integer.parseInt(JOptionPane.showInputDialog("Write down the ID of the client you will update."));
                String surname = JOptionPane.showInputDialog("What will replace the surname?");
                String updatesql2 = "UPDATE clients SET surname ='" + surname + "' WHERE id_client=" + clientid;
                try {
                    statement[0] = createConnection().createStatement();
                    statement[0].executeUpdate(updatesql2);
                    JOptionPane.showMessageDialog(editClientFrame, "Changed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int clientid = Integer.parseInt(JOptionPane.showInputDialog("Write down the ID of the client you will update."));
                int phonenumber = Integer.parseInt(JOptionPane.showInputDialog("What will replace the phone number?"));
                String updatesql3 = "UPDATE clients SET phonenumber =" + phonenumber + " WHERE id_client=" + clientid;
                try {
                    statement[0] = createConnection().createStatement();
                    statement[0].executeUpdate(updatesql3);
                    JOptionPane.showMessageDialog(editClientFrame, "Changed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int clientid = Integer.parseInt(JOptionPane.showInputDialog("Write down the ID of the client you will update."));
                String birthday = JOptionPane.showInputDialog("What will replace the birthday?");
                String updatesql4 = "UPDATE clients SET birthday ='" + birthday + "' WHERE id_client=" + clientid;
                try {
                    statement[0] = createConnection().createStatement();
                    statement[0].executeUpdate(updatesql4);
                    JOptionPane.showMessageDialog(editClientFrame, "Changed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        editClientFrame.add(button1);
        editClientFrame.add(button2);
        editClientFrame.add(button3);
        editClientFrame.add(button4);

        editClientFrame.setSize(400, 350);
        editClientFrame.setLocationRelativeTo(null);
        editClientFrame.setLayout(null);
        editClientFrame.setVisible(true);
        editClientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void deleteClient() {
        Statement statement;
        JFrame deleteClientFrame = new JFrame();
        int reply = JOptionPane.showConfirmDialog(deleteClientFrame, "Are you sure delete any client?", "", JOptionPane.YES_NO_CANCEL_OPTION);
        if (reply == JOptionPane.NO_OPTION || reply == JOptionPane.CANCEL_OPTION) {
            System.exit(0);
        }
        int clientid = Integer.parseInt(JOptionPane.showInputDialog("Write down the ID of the client you will delete."));
        String deletesql = "DELETE FROM clients WHERE id_client =" + clientid;
        try {
            statement = createConnection().createStatement();
            statement.executeUpdate(deletesql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(deleteClientFrame, "Deleted.");
        System.exit(0);
        deleteClientFrame.setSize(400, 350);
        deleteClientFrame.setLocationRelativeTo(null);
        deleteClientFrame.setLayout(null);
        deleteClientFrame.setVisible(true);
        deleteClientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void viewAllClient() {
        Statement statement;
        JFrame viewAllClientFrame = new JFrame();
        String clientsql = "SELECT * FROM clients";
        JTable table = new JTable();
        try {
            ResultSet rs = createConnection().prepareStatement(clientsql).executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            JScrollPane scrollPane = new JScrollPane(table);
            viewAllClientFrame.add(scrollPane);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        viewAllClientFrame.setLocationRelativeTo(null);
        viewAllClientFrame.setSize(400, 350);
        viewAllClientFrame.setVisible(true);
        viewAllClientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    public void editLastRequestDate() {
//        Statement statement;
//        System.out.println("Choose which client sent the request.");
//        viewAllClient();
//        System.out.println("\nWrite down the ID of the client who sent the request.");
//        System.out.print("Write here : ");
//        int clientid = sc.nextInt();
//        System.out.println("Is this client send the request today or another day?");
//        System.out.println("Today - write 1" +
//                "\nAnother - write 2");
//        System.out.print("Write here : ");
//        int choose = sc.nextInt();
//        if (choose == 1) {
//            String requestsql = "UPDATE clients SET lastrequestdate='" + java.time.LocalDate.now() + "' WHERE id_client = " + clientid;
//            try {
//                statement = createConnection().createStatement();
//                statement.executeUpdate(requestsql);
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//
//        }
//        if (choose == 2) {
//            System.out.print("Okay. add here date(year-month-date): ");
//            String date = sc.next();
//            String requestsql = "UPDATE clients SET lastrequestdate='" + date + "' WHERE id_client = " + clientid;
//            try {
//                statement = createConnection().createStatement();
//                statement.executeUpdate(requestsql);
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }
//        System.out.println("Completed.");
//    }
//
}
