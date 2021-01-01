import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {
    private JPanel panelMain;
    private JButton clientManagerButton;
    private JButton servicesManagerButton;
    private JButton requestManagerButton;
    private JTextField choose1OfThemTextField;
    JPanel clientManagerPanel = new JPanel();
    JPanel servicesManagerPanel = new JPanel();
    JPanel requestManagerPanel = new JPanel();
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    JButton button5 = new JButton();
    ClientManager clientManager = new ClientManager();
    ServicesManager servicesManager = new ServicesManager();
    RequestManager requestManager = new RequestManager();

    public App() {
        JOptionPane.showMessageDialog(panelMain, "Welcome to the Service Center application.");
        add(panelMain);
        setTitle("Service Center");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        clientManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                panelMain.setVisible(false);
                button1.setText("Add new client");
                button2.setText("Edit any client");
                button3.setText("Delete any client");
                button4.setText("View all client");
                button5.setText("Go back");
                clientManagerPanel.add(button1);
                clientManagerPanel.add(button2);
                clientManagerPanel.add(button3);
                clientManagerPanel.add(button4);
                clientManagerPanel.add(button5);
                clientManagerPanel.setVisible(true);
                add(clientManagerPanel);
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        clientManagerPanel.setVisible(true);
                        clientManager.addNewClient();
                        JOptionPane.showMessageDialog(clientManagerPanel, "This process is the adding of a new client.");

                    }
                });
                button2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        clientManagerPanel.setVisible(true);
                        clientManager.editClient();
                        JOptionPane.showMessageDialog(clientManagerPanel, "What do you want to update the client?" + "\n                Choose button");

                    }
                });
                button3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        clientManagerPanel.setVisible(true);
                        clientManager.deleteClient();
                    }
                });
                button4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        clientManagerPanel.setVisible(true);
                        clientManager.viewAllClient();
                        JOptionPane.showMessageDialog(clientManagerPanel, "Your all clients.");

                    }
                });
                button5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        panelMain.setVisible(true);
                        clientManagerPanel.setVisible(false);
                    }
                });
            }
        });
        servicesManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                panelMain.setVisible(false);
                button1.setText("Add new service");
                button2.setText("Edit any service");
                button3.setText("Delete any service");
                button4.setText("View all services");
                button5.setText("Go back");
                servicesManagerPanel.add(button1);
                servicesManagerPanel.add(button2);
                servicesManagerPanel.add(button3);
                servicesManagerPanel.add(button4);
                servicesManagerPanel.add(button5);
                servicesManagerPanel.setVisible(true);
                add(servicesManagerPanel);
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        servicesManagerPanel.setVisible(true);
                        servicesManager.addNewService();
                        JOptionPane.showMessageDialog(servicesManagerPanel, "This process is the adding of a new client.");
                    }
                });
                button2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        servicesManagerPanel.setVisible(true);
                        servicesManager.editService();
                        JOptionPane.showMessageDialog(servicesManagerPanel, "What do you want to update the service?" + "\n                Choose button");
                    }
                });
                button3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        servicesManagerPanel.setVisible(true);
                        servicesManager.deleteService();
                    }
                });
                button4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        servicesManagerPanel.setVisible(true);
                        servicesManager.viewAllServices();
                        JOptionPane.showMessageDialog(servicesManagerPanel, "Your all services.");
                    }
                });
                button5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        panelMain.setVisible(true);
                        servicesManagerPanel.setVisible(false);
                    }
                });
            }
        });
        requestManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                panelMain.setVisible(false);
                button1.setText("Create request");
                button2.setText("Delete request");
                button3.setText("View quantity");
                button4.setText("View all requests");
                button5.setText("Go back");
                requestManagerPanel.add(button1);
                requestManagerPanel.add(button2);
                requestManagerPanel.add(button3);
                requestManagerPanel.add(button4);
                requestManagerPanel.add(button5);
                requestManagerPanel.setVisible(true);
                add(requestManagerPanel);
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        requestManagerPanel.setVisible(true);
                        requestManager.createRequest();
                        JOptionPane.showMessageDialog(requestManagerPanel, "This process is the creation of a new request.");
                    }
                });
                button2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        requestManagerPanel.setVisible(true);
                        requestManager.deleteRequest();
                    }
                });
                button3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        requestManagerPanel.setVisible(true);
                        requestManager.viewQuantity();
                        JOptionPane.showMessageDialog(requestManagerPanel, "Your clients request quantity.");
                    }
                });
                button4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        requestManagerPanel.setVisible(true);
                        requestManager.viewRequests();
                        JOptionPane.showMessageDialog(requestManagerPanel, "Your all requests.");
                    }
                });
                button5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        panelMain.setVisible(true);
                        requestManagerPanel.setVisible(false);
                    }
                });
            }
        });
    }
}
