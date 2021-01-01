import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RequestManager extends DatabaseConnection {
    Statement statement;
    Scanner sc = new Scanner(System.in);
    ServicesManager servicesManager = new ServicesManager();
    ClientManager clientManager = new ClientManager();

    public void createRequest() {
        Statement statement;
        System.out.println("This process is the creation of a new request.");
        servicesManager.viewAllServices();
        System.out.println("Please add Service ID");
        int idservice = sc.nextInt();
        clientManager.viewAllClient();
        System.out.println("Please add Client ID");
        int clientid = sc.nextInt();
        System.out.println("Please add Comment for this request");
        String comment = sc.next();
        String addrequestsql = "INSERT INTO requests(id_service1,id_client1,comment) VALUES (" + idservice + "," + clientid + ",'" + comment + "')";
        String lastrequestdatesql = "UPDATE clients SET lastrequestdate='" + java.time.LocalDate.now() + "' WHERE id_client = " + clientid;
        String moneypaidsql = "UPDATE clients SET SummeryMoneyPaid = SummeryMoneyPaid + (SELECT price) FROM services INNER JOIN  requests ON services.id_service=" + idservice + " WHERE clients.id_client=" + clientid;
        try {
            statement = createConnection().createStatement();
            statement.executeUpdate(addrequestsql);
            statement = createConnection().createStatement();
            statement.executeUpdate(lastrequestdatesql);
            statement = createConnection().createStatement();
            statement.executeUpdate(moneypaidsql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRequest() {
        Statement statement;
        System.out.println("Hi. Are you sure delete any request?");
        System.out.println("Yes - write 1" + "\nNo - write 2");
        System.out.print("Write here : ");
        int choose = sc.nextInt();
        if (choose == 2) {
            System.out.println("\nOkay. Bye..");
        }
        if (choose == 1) {
            System.out.println("\nOkay. Select the request you need to delete.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            viewRequests();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\nWrite down the ID of the request you will delete.");
            System.out.print("Write here : ");
            int requestid = sc.nextInt();
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Thank you. Deleted this request.");
        }
    }

    public void viewQuantity() {
        String requestsql = "SELECT id_client,Name_client,surname,COUNT(id_client1) AS 'countrequest' FROM clients INNER JOIN requests ON clients.id_client=requests.id_client1 GROUP BY id_client,Name_client,Surname";
        try {
            statement = createConnection().createStatement();
            ResultSet rs = statement.executeQuery(requestsql);
            while (rs.next()) {
                System.out.print("\nClient ID : " + rs.getInt("id_client"));
                System.out.print("      Name  : " + rs.getString("name_client"));
                System.out.print("      Surname : " + rs.getString("surname"));
                System.out.println("      Request Quantity : " + rs.getInt("countrequest"));
                System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewRequests() {
        String requestsql = "SELECT name_client,surname,phonenumber,birthday,lastrequestdate,summerymoneypaid,name_service,category,price,code,comment,id_request " +
                "FROM clients INNER JOIN requests ON  clients.id_client = requests.id_client1 INNER JOIN services ON services.id_service = requests.id_service1 GROUP BY " +
                "name_client,surname,phonenumber,birthday,lastrequestdate,summerymoneypaid,name_service,category,price,code,comment,id_request";
        try {
            System.out.println("Your all requests");
            statement = createConnection().createStatement();
            ResultSet rs = statement.executeQuery(requestsql);
            while (rs.next()) {
                System.out.println("\nClient: ");
                System.out.print("Name : " + rs.getString("name_client"));
                System.out.print("      Surname : " + rs.getString("surname"));
                System.out.print("      Phone Number : " + rs.getInt("phonenumber"));
                System.out.print("      Birthday : " + rs.getString("birthday"));
                System.out.print("      Last Request Date : " + rs.getString("lastrequestdate"));
                System.out.println("      Summery Money Paid : " + rs.getInt("summerymoneypaid"));
                System.out.println("Service: ");
                System.out.print("Name : " + rs.getString("name_service"));
                System.out.print("      Category : " + rs.getString("category"));
                System.out.print("      Price : " + rs.getInt("price"));
                System.out.println("      Code : " + rs.getString("code"));
                System.out.println("Request : ");
                System.out.print("ID Request : " + rs.getInt("id_request"));
                System.out.println("      Comment : " + rs.getString("comment"));
                System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

