import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ClientManager extends DatabaseConnection {
    Statement statement;
    Scanner sc = new Scanner(System.in);


    public void addNewClient() {
        Statement statement;
        System.out.println("This process is the adding of a new client.");
        System.out.println("Please add new Client name");
        String name = sc.nextLine();
        System.out.println("Please add surname");
        String surname = sc.nextLine();
        System.out.println("Phone Number");
        System.out.println("! Type without using any symbols.");
        int phonenumber = sc.nextInt();
        System.out.println("Birthday (year-month-day)");
        String birthday = sc.next();
        // String idadd = serviceName.charAt(0) + String.valueOf(category.charAt(0)) + String.valueOf(price).charAt(0) + code.length();
        String addservicesql = "INSERT INTO clients(name_client, surname, phonenumber , birthday,Summerymoneypaid ) VALUES ('" + name + "','" + surname + "'," + phonenumber + ",'" + birthday + "',0)";
        try {
            statement = createConnection().createStatement();
            statement.executeUpdate(addservicesql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editClient() {
        Statement statement;
        System.out.println("What do you want to update the client?");
        System.out.println("Name - write 1" + "\nSurname - write 2" + "\nPhone Number - write 3" + "\nBirthday - write 4" + "\nGo back - 5");
        System.out.print("Write here : ");
        int choose = sc.nextInt();
        if (choose == 5) {
            System.out.println("Okay. Bye..");
        }
        if (choose < 5) {
            System.out.println("\nOkay. Select the client you need to update.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            viewAllClient();
            System.out.println("\nWrite down the ID of the client you will update.");
            System.out.print("Write here : ");
            int clientid = sc.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("\nWhat will replace the name?");
                    System.out.print("Write here : ");
                    String name = sc.next();
                    String updatesql1 = "UPDATE clients SET name_client ='" + name + "' WHERE id_client=" + clientid;
                    try {
                        statement = createConnection().createStatement();
                        statement.executeUpdate(updatesql1);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("\nWhat will replace the surname?");
                    System.out.print("Write here : ");
                    String surname = sc.next();
                    String updatesql2 = "UPDATE clients SET surname ='" + surname + "' WHERE id_client=" + clientid;
                    try {
                        statement = createConnection().createStatement();
                        statement.executeUpdate(updatesql2);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("\nWhat will replace the phone number?");
                    System.out.print("Write here : ");
                    int phonenumber = sc.nextInt();
                    String updatesql3 = "UPDATE client SET phonenumber =" + phonenumber + " WHERE id_client=" + clientid;
                    try {
                        statement = createConnection().createStatement();
                        statement.executeUpdate(updatesql3);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.println("\nWhat will replace the birthday?");
                    System.out.print("Write here : ");
                    String birthday = sc.next();
                    String updatesql4 = "UPDATE clients SET birthday ='" + birthday + "' WHERE id_client=" + clientid;
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

    public void deleteClient() {
        Statement statement;
        System.out.println("Hi. Are you sure delete any client?");
        System.out.println("Yes - write 1" + "\nNo - write 2");
        System.out.print("Write here : ");
        int choose = sc.nextInt();
        if (choose == 2) {
            System.out.println("\nOkay. Bye..");
        }
        if (choose == 1) {
            System.out.println("\nOkay. Select the client you need to delete.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            viewAllClient();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\nWrite down the ID of the client you will delete.");
            System.out.print("Write here : ");
            int clientid = sc.nextInt();
            String deletesql = "DELETE FROM client WHERE id_client =" + clientid;
            try {
                statement = createConnection().createStatement();
                statement.executeUpdate(deletesql);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Thank you. Deleted this client.");
        }
    }

    public void viewAllClient() {
        Statement statement;
        System.out.println("Your all services.");
        String clientsql = "SELECT * FROM clients";
        try {
            statement = createConnection().createStatement();
            ResultSet rs = statement.executeQuery(clientsql);
            while (rs.next()) {
                System.out.print("ID : " + rs.getInt("id_client"));
                System.out.print("      Name : " + rs.getString("name_client"));
                System.out.print("      Surname : " + rs.getString("surname"));
                System.out.print("      Phone Number : " + rs.getInt("phonenumber"));
                System.out.println("      Birthday : " + rs.getString("birthday"));
                System.out.println("- - - - - -");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editLastRequestDate() {
        Statement statement;
        System.out.println("Choose which client sent the request.");
        viewAllClient();
        System.out.println("\nWrite down the ID of the client who sent the request.");
        System.out.print("Write here : ");
        int clientid = sc.nextInt();
        System.out.println("Is this client send the request today or another day?");
        System.out.println("Today - write 1" +
                "\nAnother - write 2");
        System.out.print("Write here : ");
        int choose = sc.nextInt();
        if (choose == 1) {
            String requestsql = "UPDATE clients SET lastrequestdate='" + java.time.LocalDate.now() + "' WHERE id_client = " + clientid;
            try {
                statement = createConnection().createStatement();
                statement.executeUpdate(requestsql);

            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        if (choose == 2) {
            System.out.print("Okay. add here date(year-month-date): ");
            String date = sc.next();
            String requestsql = "UPDATE clients SET lastrequestdate='" + date + "' WHERE id_client = " + clientid;
            try {
                statement = createConnection().createStatement();
                statement.executeUpdate(requestsql);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Completed.");
    }

}
