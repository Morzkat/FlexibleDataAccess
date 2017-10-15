package XmlManager;

import java.io.*;
import java.util.List;

import Adapter.ConexionAdapter;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

public class XMLManager implements ConexionAdapter
{
    //builder
    SAXBuilder constructorXML = new SAXBuilder();
    //document with all the info about xml file
    Document document = null;
    //root of the document
    Element root = null;

    //method for show the data
    @Override
    public void Show(String dataBaseName)
    {
        //get the xml file (database)
        try {document = constructorXML.build(new File(dataBaseName + ".xml"));}

        catch (JDOMException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}

        root = document.getRootElement();

        //show the data(note find how to show the elements too)
        for (int i = 0; i < root.getContentSize(); i++)
        {
            System.out.println("Table " + i + " \n content \n " + root.getContent(i).getValue() + "\n");
        }
    }

    @Override
    public void Insert(String dataBaseName, String tableName, String[] columns, String[] values)
    {
        //get the xml file (database)
        dataBaseName = dataBaseName + ".xml";
        File xmlFile = new File(dataBaseName);

        if (xmlFile.exists())
        {
            //check if the file (database) exist if exist override the root of file
            FileInputStream inputStream = null;

            try {inputStream = new FileInputStream(xmlFile);}
            catch (FileNotFoundException e)
            {e.printStackTrace();}

            try {document = constructorXML.build(xmlFile);}
            catch (JDOMException e) {e.printStackTrace();}
            catch (IOException e) {e.printStackTrace();}

            //get the root of the document
            root = document.getRootElement();

            try {inputStream.close();}
            catch (IOException e)
            {e.printStackTrace();}
        }

        else
        {
            //if doesn't exist create a new root for the file
            document = new Document();
            root = new Element("Tables");
        }

        //insert a new "table"
        Element table = new Element(tableName);

        //insert the data of the "table"
        for (int i = 0; i < columns.length; i++)
        {table.addContent(new Element(columns[i]).setText(values[i]));}

        //add the content
        root.addContent(table);
        document.setContent(root);

        FileWriter writer = null;

        try {writer = new FileWriter(dataBaseName);}
        catch (IOException e) {e.printStackTrace();}

        XMLOutputter xmlOutputter = new XMLOutputter(org.jdom2.output.Format.getPrettyFormat());

        //the output of the info
        try {xmlOutputter.output(document,writer);}
        catch (IOException e) {e.printStackTrace();}

        try {writer.close();}
        catch (IOException e) {e.printStackTrace();}

        System.out.println("Table " + tableName + " added " + "to a database " + dataBaseName);
    }

    //alter the "database"
    @Override
    public void Alter(String dataBaseName, String table, String[] columns, String[] values)
    {
        InputStream stream = null;

        //get the xml file (database)
        try {stream = new FileInputStream(dataBaseName + ".xml");}
        catch (FileNotFoundException e)
        {e.printStackTrace();}

        try {document = constructorXML.build(stream);}
        catch (JDOMException e) {e.printStackTrace();}
        catch (IOException e)
        {e.printStackTrace();}

        //get the root of the file
        root = document.getRootElement();

        //gets the elements of the file
        List<Element> tableContent = root.getChildren(table);
        int i = 0;

        for (Element element: tableContent)
        {
            //search a specific element and set the news values
            element.getChild(columns[i]).setText(values[i]);
            System.out.println(element.getChild(columns[i]).getText());
            i++;
        }

        FileWriter writer = null;

        try {writer = new FileWriter(dataBaseName + ".xml");}
        catch (IOException e) {e.printStackTrace();}

        XMLOutputter xmlOutputter = new XMLOutputter(org.jdom2.output.Format.getPrettyFormat());

        //output
        try {xmlOutputter.output(document,writer);}
        catch (IOException e) {e.printStackTrace();}

        try {writer.close();}
        catch (IOException e) {e.printStackTrace();}
    }

    //delete a column
    @Override
    public void DeleteColumn(String dataBaseName, String column)
    {
        InputStream inputStream = null;

        //get the root of the file
        try {inputStream = new FileInputStream(dataBaseName + ".xml");}
        catch (FileNotFoundException e) {e.printStackTrace();}

        try {document = constructorXML.build(inputStream);}
        catch (JDOMException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}

        //get the root
        root = document.getRootElement();

        //check the state of the action
        boolean removeChild = root.removeChild(column);

        //check if the action was did it
        if (removeChild)
        {
            FileWriter writer = null;

            try {writer = new FileWriter(dataBaseName + ".xml");}
            catch (IOException e) {e.printStackTrace();}

            XMLOutputter xmlOutputter = new XMLOutputter(org.jdom2.output.Format.getPrettyFormat());

            //output
            try {xmlOutputter.output(document,writer);}
            catch (IOException e) {e.printStackTrace();}

            try {writer.close();}
            catch (IOException e) {e.printStackTrace();}
        }
    }

    //delete a xml file ("database")
    @Override
    public void DeleteDataBase(String dataBaseName)
    {
        File file = new File(dataBaseName + ".xml");

        //delete the file
        if (file.delete())
        {System.out.println("Database" + dataBaseName + " was deleted");}

        else
        {System.out.println("doesn't exist this database ");}
    }
}