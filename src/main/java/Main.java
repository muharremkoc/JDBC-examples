import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Scanner;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        PropertyConfigurator.configure("src/main/resources/database.properties");
        Scanner scanner=new Scanner(System.in);
        JDBCConnect jdbcConnect = new JDBCConnect();
        jdbcConnect.connect();
        int input;
        System.out.println("1-Listeleme\t"+"2-Ekleme\t"+"3-Güncelleme \t"+"4-Silme");
        System.out.print("Seciminiz:");
                input=scanner.nextInt();
                switch (input){
                    case 1:
                jdbcConnect.show();

                break;
            case 2:
                jdbcConnect.add();
                break;
            case 3:
                jdbcConnect.update();
                break;
            case 4:
                jdbcConnect.delete();
                break;
            default:
                jdbcConnect.close();
                break;
        }

    }
}
