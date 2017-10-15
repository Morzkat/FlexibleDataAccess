import Adapter.ConexionAdapter;
import XmlManager.XMLManager;

import java.util.Scanner;

public class Main
{
    public Main()
    {
        //Adapter
        ConexionAdapter manager = new XMLManager();
        //database name
        String databaseName = "";
        //table name
        String tableName= "";
        //columns of the table
        String [] columns;
        //values of the table
        String [] values;
        //scanner
        Scanner scanner = new Scanner(System.in);

        System.out.println("Show columns (show), Create a new database and also insert new column (insert)[can be use for both], " +
                "Delete column (deleteC), " + "Alter column (alter), delete a database (deleteDB)");

        String decision = scanner.nextLine();

        //decisions for the users
        switch (decision)
        {
            //call the method show
            case "show" :

                System.out.println("Database name");

                //get the database name
                databaseName = scanner.nextLine();

                //show the data of the database
                manager.Show(databaseName);

                break;

            //call the method insert
            case "insert" :

                System.out.println("Database name");
                //get the database name
                databaseName = scanner.nextLine();

                System.out.println("Name of the table");
                //get the table name
                tableName = scanner.nextLine();

                System.out.println("Number of columns");
                //get the numbers of column to insert
                int length = Integer.parseInt(scanner.nextLine());

                //set the number of columns and values to the arrays
                columns = new String[length];
                values = new String[length];

                //get the columns and the values to insert
                for (int i = 0; i < length; i++)
                {
                    System.out.println("column name");
                    //column name
                    columns[i] = scanner.nextLine();

                    System.out.println("column value");
                    //value to insert
                    values[i] = scanner.nextLine();
                }

                manager.Insert(databaseName,tableName, columns,values);
                break;

            //call the method alter
            case "alter" :
                System.out.println("Database to alter");
                //get the database name
                databaseName = scanner.nextLine();

                System.out.println("table to alter");
                //get the table name
                tableName = scanner.nextLine();

                System.out.println("number of columns to alter");
                //get the numbers of columns
                length = Integer.parseInt(scanner.nextLine());

                //set numbers of columns and values to the array
                columns = new String[length];
                values = new String[length];

                //get columns to alter and the news values to insert
                for (int i = 0; i < length; i++)
                {
                    System.out.println("column name");
                    //column name
                    columns[i] = scanner.nextLine();

                    System.out.println("column value");
                    //value to insert
                    values[i] = scanner.nextLine();
                }

                manager.Alter(databaseName,tableName,columns,values);
                break;

            //call the method delete(for delete database)
            case "deleteDB" :
                System.out.println("Database to delete");
                //get the database name
                databaseName = scanner.nextLine();

                manager.DeleteDataBase(databaseName);
                break;

            //call the method delete(for a column)
            case "deleteC" :
                System.out.println("Database name");
                //get the database Name
                databaseName = scanner.nextLine();

                System.out.println("Column of database to delete");
                //get the column of database to delete
                String column = scanner.nextLine();

                manager.DeleteColumn(databaseName, column);
                break;
        }
    }

    public static void main(String[] args)
    {

        System.out.println("Flexible Data Access (System for Books)");

        //scanner
        Scanner c = new Scanner(System.in);

        //running the system
        boolean running = true;

        while (running)
        {
            //check if the user want close the system
            System.out.println("close(y/n)");
            //get the answer
            String str = c.nextLine();

            switch (str)
            {
                //close the system
                case "y" : running = false;
                    System.out.println("Closed");
                    break;

                //run the system
                case "n" :
                    Main m = new Main();
                    break;
            }
        }
    }
}
