import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Home extends JFrame {
    private JPanel panelMain;
    DatabaseConnection databaseConnection = new DatabaseConnection();
    ClientManager clientManager = new ClientManager();
    Statement statement;
    Timer tm1, tm2;
    Integer pl1 = 60, pl2 = 60;

    JPanel panelBackground = new JPanel();
    JPanel panelLeft = new JPanel();
    JPanel panelRight = new JPanel();
    JButton buttonClients = new JButton("Clients");
    JButton buttonServices = new JButton("Services");
    JTable tableClients = new JTable();
    JTable tableServices = new JTable();
    JTextField textFieldMain1 = new JTextField();
    JLabel iconLabel = new JLabel();
    JPanel panelUpRight = new JPanel();

    JButton btnNewButton = new JButton();
    JButton button_0 = new JButton("Add");
    JButton button_1 = new JButton("Edit");
    JButton button_2 = new JButton("Delete");
    JPanel panelUpRight1 = new JPanel();
    JButton buttonExt = new JButton();


    public Home() {
        setTitle("Service Center");
        add(panelMain);
        setSize(1015, 610);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        panelLeft.setBounds(0, 0, 300, 600);
        panelBackground.add(panelLeft);
        panelLeft.setBackground(new Color(14, 88, 110));
        buttonClients.setBounds(0, 100, 300, 50);
        buttonClients.setBackground(new Color(14, 88, 130));
        buttonClients.setForeground(Color.white);
        panelLeft.add(buttonClients);
        buttonServices.setBounds(0, 150, 300, 50);
        buttonServices.setBackground(new Color(14, 88, 130));
        buttonServices.setForeground(Color.white);

        panelLeft.add(buttonServices);
        panelLeft.setLayout(null);

        ImageIcon mainServicesIcon = new ImageIcon("C:\\Users\\SmartFox\\IdeaProjects\\Design\\src\\image\\mainicon.png");
        iconLabel.setIcon(mainServicesIcon);
        iconLabel.setBounds(200, 150, 300, 300);
        iconLabel.setForeground(Color.white);
        panelRight.add(iconLabel);
        panelRight.setBounds(300, 0, 700, 600);
        panelBackground.add(panelRight);
        panelRight.setBackground(new Color(74, 33, 149));
        panelRight.setLayout(null);


        buttonClients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forClients();
            }
        });
        buttonServices.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forServices();
            }
        });


        textFieldMain1.setText(" Service Center");
        textFieldMain1.setEditable(false);
        textFieldMain1.setBounds(250, 20, 200, 50);
        textFieldMain1.setFont(new Font(" Service Center", Font.BOLD, 26));
        panelRight.add(textFieldMain1);

        panelBackground.setSize(1000, 600);
        panelMain.add(panelBackground);
        panelBackground.setBackground(new Color(187, 187, 187));
        panelBackground.setLayout(null);


    }

    public void forClients() {
        panelUpRight.setBounds(540, 0, 148, 25);
        panelRight.add(panelUpRight);
        panelUpRight.setLayout(null);

        ImageIcon settingsIcon = new ImageIcon("C:\\Users\\SmartFox\\IdeaProjects\\Design\\src\\image\\settings.png");
        btnNewButton.setIcon(settingsIcon);
        btnNewButton.setBackground(Color.WHITE);
        btnNewButton.setBounds(0, 0, 148, 25);
        panelUpRight.add(btnNewButton);

        button_0.setBackground(Color.white);
        button_0.setBounds(0, 26, 148, 25);
        panelUpRight.add(button_0);


        button_1.setBackground(Color.white);
        button_1.setBounds(0, 52, 148, 25);
        panelUpRight.add(button_1);

        button_2.setBackground(Color.white);
        button_2.setBounds(0, 78, 148, 25);
        panelUpRight.add(button_2);

        panelUpRight1.setBounds(510, 0, 25, 25);
        panelRight.add(panelUpRight1);
        panelUpRight1.setLayout(null);

        ImageIcon exitIcon = new ImageIcon("C:\\Users\\SmartFox\\IdeaProjects\\Design\\src\\image\\exit1.png");
        buttonExt.setIcon(exitIcon);
        buttonExt.setBackground(Color.white);
        buttonExt.setBounds(0, 0, 25, 25);
        panelUpRight1.add(buttonExt);

        tm1 = new Timer(20, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // 300 the maximum height
                if (pl1 > 100) {
                    tm1.stop();
                } else {
                    panelUpRight.setSize(panelUpRight.getWidth(), pl1);
                    pl1 += 20;
                }

            }
        });
        tm2 = new Timer(20, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (pl2 > 10) {
                    tm2.stop();
                } else {
                    panelUpRight1.setSize(panelUpRight1.getWidth(), pl2);
                    pl2 += 20;
                }

            }
        });

        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                panelUpRight1.setSize(panelUpRight1.getWidth(), pl2);
                tm1.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {

                tm1.stop();
                pl1 = 25;
            }
        });
        buttonExt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                panelUpRight.setSize(panelUpRight.getWidth(), pl1);
//                tm2.start();
                pl1 = 60;

            }

            @Override
            public void mouseExited(MouseEvent e) {

                tm2.stop();
                pl2 = 25;
            }
        });
        try {
            String clientsql = "SELECT id_client AS 'ID',name_client AS 'Name',surname AS 'Surname',phonenumber 'Phone Number' FROM clients";
            ResultSet rs = databaseConnection.createConnection().prepareStatement(clientsql).executeQuery();
            tableClients.setModel(DbUtils.resultSetToTableModel(rs));
            tableClients.setBackground(new Color(74, 33, 149));
            tableClients.setForeground(Color.white);
            JScrollPane scrollPaneClients = new JScrollPane(tableClients);
            scrollPaneClients.setBounds(0, 100, 700, 600);
            panelRight.add(scrollPaneClients);
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void forServices() {
        ImageIcon settingsIcon = new ImageIcon("C:\\Users\\SmartFox\\IdeaProjects\\Design\\src\\image\\settings.png");
//        Icon icon = new ImageIcon("C:\\Users\\SmartFox\\IdeaProjects\\Design\\src\\image\\settings.png");


        try {
            String clientsql = "SELECT id_service,name_service,category,price,code FROM services";
            ResultSet rs = databaseConnection.createConnection().prepareStatement(clientsql).executeQuery();
            DefaultTableModel model = new DefaultTableModel();
            JTable table = new JTable(model);
//            Object[] rowData = {icon};
            String arr[] = {"Select", "Add", "Edit", "Delete"};
            JComboBox comboBox = new JComboBox(arr);
            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (comboBox.getSelectedItem() == "Add") {
                        comboBox.setSelectedIndex(0);
                    } else if (comboBox.getSelectedItem() == "Edit") {
                        comboBox.setSelectedIndex(0);

                    } else if (comboBox.getSelectedItem() == "Delete") {
                        comboBox.setSelectedIndex(0);

                    }
                }
            });

            String[] columnNames = {"ID", "Name", "Category", "Price", "Code", "Parameters"};
            model.setColumnIdentifiers(columnNames);
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString("id_service"), rs.getString("name_service"), rs.getString("category"), rs.getString("price"), rs.getString("code")});
//                table.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(comboBox));
//
                table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
                table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));

            }
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(0, 100, 700, 600);
            panelRight.add(scrollPane);
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    /**
     * @version 1.0 11/09/98
     */

    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;

        private String label;

        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            label = (value == null) ? "" : value.toString();
            button.setText("label");
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {


                JOptionPane.showMessageDialog(button, label + ": Ouch!");
                System.out.println(label + ": Ouch!");
            }
            isPushed = false;
            return new String(label);
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}
