import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RequestManager extends DatabaseConnection {
    Statement statement;


    public void createRequest() {
        final Statement[] statement = new Statement[1];
        JFrame createRequestFrame = new JFrame();

        JTextField textFieldClientID = new JTextField("Type in client ID : ");
        textFieldClientID.setBounds(10, 70, 160, 20);
        textFieldClientID.setEditable(false);
        JTextField textFieldClientIDAdd = new JTextField();
        textFieldClientIDAdd.setBounds(180, 70, 150, 20);
        createRequestFrame.add(textFieldClientID);
        createRequestFrame.add(textFieldClientIDAdd);

        JTextField textFieldServiceID = new JTextField("Type in service ID : ");
        textFieldServiceID.setBounds(10, 90, 160, 20);
        textFieldServiceID.setEditable(false);
        JTextField textFieldServiceIDAdd = new JTextField();
        textFieldServiceIDAdd.setBounds(180, 90, 150, 20);
        createRequestFrame.add(textFieldServiceID);
        createRequestFrame.add(textFieldServiceIDAdd);

        JTextField textFieldComment = new JTextField("Comment for this request : ");
        textFieldComment.setBounds(10, 110, 160, 20);
        textFieldComment.setEditable(false);
        JTextField textFieldCommentAdd = new JTextField();
        textFieldCommentAdd.setBounds(180, 110, 150, 20);
        createRequestFrame.add(textFieldComment);
        createRequestFrame.add(textFieldCommentAdd);

        JButton button = new JButton("Confirm");
        button.setBounds(230, 250, 100, 20);
        createRequestFrame.add(button);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String addrequestsql = "INSERT INTO requests(id_service1,id_client1,comment) VALUES (" + Integer.parseInt(textFieldServiceIDAdd.getText()) + "," + Integer.parseInt(textFieldClientIDAdd.getText()) + ",'" + textFieldCommentAdd.getText() + "')";
                String lastrequestdatesql = "UPDATE clients SET lastrequestdate='" + java.time.LocalDate.now() + "' WHERE id_client = " + Integer.parseInt(textFieldClientIDAdd.getText());
                String moneypaidsql = "UPDATE clients SET SummeryMoneyPaid = SummeryMoneyPaid + (SELECT price) FROM services INNER JOIN  requests ON services.id_service=" + Integer.parseInt(textFieldServiceIDAdd.getText()) + " WHERE clients.id_client=" + Integer.parseInt(textFieldClientIDAdd.getText());
                try {
                    statement[0] = createConnection().createStatement();
                    statement[0].executeUpdate(addrequestsql);
                    statement[0] = createConnection().createStatement();
                    statement[0].executeUpdate(lastrequestdatesql);
                    statement[0] = createConnection().createStatement();
                    statement[0].executeUpdate(moneypaidsql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(createRequestFrame, "That create has been added.");
            }
        });

        createRequestFrame.setSize(400, 350);
        createRequestFrame.setLayout(null);
        createRequestFrame.setLocationRelativeTo(null);
        createRequestFrame.setVisible(true);
        createRequestFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void deleteRequest() {
        Statement statement;
        JFrame deleteRequestFrame = new JFrame();
        int reply = JOptionPane.showConfirmDialog(deleteRequestFrame, "Are you sure delete any request?", "", JOptionPane.YES_NO_CANCEL_OPTION);
        if (reply == JOptionPane.NO_OPTION || reply == JOptionPane.CANCEL_OPTION) {
            System.exit(0);
        }
        JOptionPane.showMessageDialog(deleteRequestFrame, "Okay. Select the request you need to delete.");
        int requestid = Integer.parseInt(JOptionPane.showInputDialog("Write down the ID of the request you will delete."));
        String deletesql = "DELETE FROM requests WHERE id_request =" + requestid;
        String summerymoneysql = "UPDATE clients SET SummeryMoneyPaid = (SELECT SummeryMoneyPaid-price) FROM services INNER JOIN requests ON services.id_service=requests.id_service1 INNER JOIN clients ON requests.id_client1=clients.id_client WHERE requests.id_request=" + requestid;
        String lastrequestsql = "UPDATE clients SET LastRequestDate= NULL FROM clients INNER JOIN requests ON clients.id_client=requests.id_client1 WHERE requests.id_request=" + requestid;
        try {
            statement = createConnection().createStatement();
            statement.executeUpdate(lastrequestsql);
            statement = createConnection().createStatement();
            statement.executeUpdate(summerymoneysql);
            statement = createConnection().createStatement();
            statement.executeUpdate(deletesql);
            JOptionPane.showMessageDialog(deleteRequestFrame, "Thank you. Deleted this request.");
            System.exit(0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        deleteRequestFrame.setSize(400, 350);
        deleteRequestFrame.setLayout(null);
        deleteRequestFrame.setLocationRelativeTo(null);
        deleteRequestFrame.setVisible(true);
        deleteRequestFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public void viewQuantity() {
        Statement statement;
        JFrame viewQuantityFrame = new JFrame();
        String requestsql = "SELECT id_client,Name_client,surname,COUNT(id_client1) AS 'countrequest' FROM clients INNER JOIN requests ON clients.id_client=requests.id_client1 GROUP BY id_client,Name_client,Surname";
        JTable table = new JTable();
        try {
            ResultSet rs = createConnection().prepareStatement(requestsql).executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            JScrollPane scrollPane = new JScrollPane(table);
            viewQuantityFrame.add(scrollPane);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        viewQuantityFrame.setLocationRelativeTo(null);
        viewQuantityFrame.setSize(400, 350);
        viewQuantityFrame.setVisible(true);
        viewQuantityFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void viewRequests() {
        Statement statement;
        JFrame viewRequestFrame = new JFrame();
        String requestsql = "SELECT name_client,surname,phonenumber,birthday,lastrequestdate,summerymoneypaid,name_service,category,price,code,comment,id_request " +
                "FROM clients INNER JOIN requests ON  clients.id_client = requests.id_client1 INNER JOIN services ON services.id_service = requests.id_service1 GROUP BY " +
                "name_client,surname,phonenumber,birthday,lastrequestdate,summerymoneypaid,name_service,category,price,code,comment,id_request";
        JTable table = new JTable();
        try {
            ResultSet rs = createConnection().prepareStatement(requestsql).executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            JScrollPane scrollPane = new JScrollPane(table);
            viewRequestFrame.add(scrollPane);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        viewRequestFrame.setLocationRelativeTo(null);
        viewRequestFrame.setSize(400, 350);
        viewRequestFrame.setVisible(true);
        viewRequestFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

