import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.*;
import java.util.*;

public class JDBCConnect {
    private static final org.apache.log4j.Logger logger = Logger.getLogger(JDBCConnect.class);
    private Connection connection=null;
    private PreparedStatement preparedInsertStatement=null;
    private PreparedStatement preparedUpdateStatement=null;
    private Statement statement=null;
    private ResultSet resultSet;
    Scanner keyboard=new Scanner(System.in);
    Person person=new Person();
    private String url="jdbc:mysql://localhost:3306/person";
    private String userName="root";
    private String passWord="";//Your MySQL password

    public  void connect(){

        try {
            connection = DriverManager.getConnection(url,userName,passWord);
            System.out.println("Connection Successed!");
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
    }
    public void show(){

        try {
            statement=connection.createStatement();
            resultSet=statement.executeQuery("select ID,name,surName from persons");
            /*
                  LinkedList<Person> list=new LinkedList<>();

                              list.add(new Person(
                        resultSet.getInt("ID"),
                        resultSet.getString("name"),
                        resultSet.getString("surName")));
                     for (Person persons:list) {
                System.out.print("ID:"+persons.getID()+" ");
                System.out.print("Name: "+persons.getName()+" ");
                System.out.print("SurName:"+persons.getSurName()+"\n" );
            }
             */

            while (resultSet.next()){

                logger.info("ID:"+resultSet.getInt("ID")+" "
                        +"Name:"+resultSet.getString("name")+" "+
                        "SurName:"+resultSet.getString("surName")+" \n");
                System.out.print("ID:"+resultSet.getInt("ID")+" ");
                System.out.print("Name:"+resultSet.getString("name")+" ");
                System.out.print("SurName:"+resultSet.getString("surName")+" \n");

            }


/*

  */


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void add(){
            int idInput;
            String nameInput;
            String lastNameInput;
        String sql="insert into persons values (?,?,?)";
        try {

            System.out.println("Enter ID:");
            idInput= keyboard.nextInt();
            keyboard.nextLine();
            System.out.println("Enter Name:");
            nameInput= keyboard.nextLine();

            System.out.println("Enter SurName:");
            lastNameInput= keyboard.nextLine();

            person.setID(idInput);
            person.setName(nameInput);
            person.setSurName(lastNameInput);

            preparedInsertStatement=connection.prepareStatement(sql);
            preparedInsertStatement.setInt(1,person.getID());
            preparedInsertStatement.setString(2, person.getName());
            preparedInsertStatement.setString(3, person.getSurName());
            int result= preparedInsertStatement.executeUpdate();

            System.out.println("Data Added!!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void update(){
        String sql=" ";
        String updateName;
        String updateLastName;
        int updateID;
        int currentID;
        int choose;
        try {
            System.out.print("Please Enter Update Choose:");choose= keyboard.nextInt();
            if(choose==1){
                System.out.println("Choosing Update Name");
                sql="update  persons set name=? where ID=?";
                preparedUpdateStatement=connection.prepareStatement(sql);
                keyboard.nextLine();
                System.out.print("Enter New Name:");updateName= keyboard.nextLine();
                System.out.print("Enter Current ID:");currentID= keyboard.nextInt();
                preparedUpdateStatement.setString(1,updateName);
                preparedUpdateStatement.setInt(2,currentID);
                int result= preparedUpdateStatement.executeUpdate();
                System.out.println("Data Updated!!");
            }
            if(choose==2){
                sql="update  persons set surName=? where ID=?";
                preparedUpdateStatement=connection.prepareStatement(sql);
                keyboard.nextLine();
                System.out.print("Enter New SurName:");updateLastName= keyboard.nextLine();
                System.out.print("Enter Current ID:");currentID= keyboard.nextInt();
                preparedUpdateStatement.setString(1,updateLastName);
                preparedUpdateStatement.setInt(2,currentID);
                int result= preparedUpdateStatement.executeUpdate();
                System.out.println("Data Updated!!");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void delete(){
        String sql="delete from persons where id=?";
        try {
            preparedUpdateStatement=connection.prepareStatement(sql);
            preparedUpdateStatement.setInt(1,2);
            int result= preparedUpdateStatement.executeUpdate();

            System.out.println("Data Deleted!!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void close(){
        try {
            preparedUpdateStatement.close();
            preparedInsertStatement.close();
            statement.close();
            connection.close();
            System.out.println("Connection Closed!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
