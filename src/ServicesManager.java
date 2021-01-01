import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ServicesManager extends DatabaseConnection {
    Statement statement;
    Scanner sc = new Scanner(System.in);


    public void addNewService() {
        Statement statement;
        System.out.println("This process is the creation of a new service.");
        System.out.println("Please add Service name");
        String serviceName = sc.nextLine();
        System.out.println("Please add Category");
        String category = sc.nextLine();
        System.out.println("Please add Price for this service");
        int price = sc.nextInt();
        System.out.println("Please add Code for this service");
        String code = sc.next();
        // String idadd = serviceName.charAt(0) + String.valueOf(category.charAt(0)) + String.valueOf(price).charAt(0) + code.length();
        String addservicesql = "INSERT INTO services(name_service, category, price , code ) VALUES ('" + serviceName + "','" + category + "'," + price + ",'" + code + "')";
        try {
            statement = createConnection().createStatement();
            statement.executeUpdate(addservicesql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editService() {
        Statement statement;
        System.out.println("What do you want to update the service?");
        System.out.println("Name - write 1" + "\nCategory - write 2" + "\nPrice - write 3" + "\nCode - write 4" + "\nGo back - 5");
        System.out.print("Write here : ");
        int choose = sc.nextInt();
        if (choose == 5) {
            System.out.println("Okay. Bye..");
        }
        if (choose < 5) {
            System.out.println("\nOkay. Select the service you need to update.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            viewAllServices();
            System.out.println("\nWrite down the ID of the service you will update.");
            System.out.print("Write here : ");
            int serviceid = sc.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("\nWhat will replace the name?");
                    System.out.print("Write here : ");
                    String name = sc.next();
                    String updatesql1 = "UPDATE services SET name_service ='" + name + "' WHERE id_service = " + serviceid;
                    try {
                        statement = createConnection().createStatement();
                        statement.executeUpdate(updatesql1);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("\nWhat will replace the category?");
                    System.out.print("Write here : ");
                    String category = sc.next();
                    String updatesql2 = "UPDATE services SET category ='" + category + "' WHERE id_service = " + serviceid;
                    try {
                        statement = createConnection().createStatement();
                        statement.executeUpdate(updatesql2);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("\nWhat will replace the price?");
                    System.out.print("Write here : ");
                    int price = sc.nextInt();
                    String updatesql3 = "UPDATE services SET price =" + price + " WHERE id_service = " + serviceid;
                    try {
                        statement = createConnection().createStatement();
                        statement.executeUpdate(updatesql3);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.println("\nWhat will replace the code?");
                    System.out.print("Write here : ");
                    String code = sc.next();
                    String updatesql4 = "UPDATE services SET code ='" + code + "' WHERE id_service = " + serviceid;
                    try {
                        statement = createConnection().createStatement();
                        statement.executeUpdate(updatesql4);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            System.out.println("\nCompleted.");
        }

    }

    public void deleteService() {
        Statement statement;
        System.out.println("Hi. Are you sure delete any service?");
        System.out.println("Yes - write 1" + "\nNo - write 2");
        System.out.print("Write here : ");
        int choose = sc.nextInt();
        if (choose == 2) {
            System.out.println("\nOkay. Bye..");
        }
        if (choose == 1) {
            System.out.println("\nOkay. Select the service you need to delete.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            viewAllServices();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\nWrite down the ID of the service you will delete.");
            System.out.print("Write here : ");
            int serviceid = sc.nextInt();
            String deletesql = "DELETE FROM services WHERE id_service =" + serviceid;
            try {
                statement = createConnection().createStatement();
                statement.executeUpdate(deletesql);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Thank you. Deleted this service.");
        }
    }

    public void viewAllServices() {
        Statement statement;
        System.out.println("Your all services.");
        String servicesql = "SELECT * FROM services";
        try {
            statement = createConnection().createStatement();
            ResultSet rs = statement.executeQuery(servicesql);
            while (rs.next()) {
                System.out.print("ID : " + rs.getInt("id_service"));
                System.out.print("      Name : " + rs.getString("name_service"));
                System.out.print("      Category : " + rs.getString("category"));
                System.out.print("      Price : " + rs.getInt("price"));
                System.out.println("      Code : " + rs.getString("code"));
                System.out.println("- - - - - -");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}