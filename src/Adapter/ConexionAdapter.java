package Adapter;

//Conexion class adapter
public interface ConexionAdapter
{
    public void Show(String dataBaseName);
    public void Insert(String dataBaseName, String tableName, String [] columns, String [] values);
    public void Alter(String dataBaseName, String table, String[] columns, String[]values);
    public void DeleteColumn(String dataBaseName, String column);
    public void DeleteDataBase(String dataBaseName);
}
