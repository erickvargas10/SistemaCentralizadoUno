
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test {
    
    public static void main(String[] args) {
        
       //Aqui viene el menu de opciones para agregar
        
        Scanner scan = new Scanner(System.in);
        int opcion;

            String codigoS;
            int codigo;  
       
            String nombre;

            String precioS;
            double precio;  
        
        
        Test t  = new Test();
 
        do{
        
        System.out.println("\n\t Mi Supermercado ");
        
        System.out.println("¿ Qué desea Hacer ?");
        System.out.println(" 1. Insertar");
        System.out.println(" 2. Actualizar");
        System.out.println(" 3. Eliminar");        
        System.out.println(" 4. Visualizar el listado");
        System.out.println(" 5. Salir");
        
        System.out.print("Ingrese la opcion: ");
        opcion = scan.nextInt();
        
        switch(opcion){
            
        case 1:
               
            scan.nextLine();
            
            System.out.println("\nIngresar Producto");
            System.out.print("Codigo: ");
             codigoS = scan.nextLine();
             codigo=Integer.parseInt(codigoS);  
        
            System.out.print("Nombre: ");
             nombre = scan.nextLine();
       
            System.out.print("Precio: ");
             precioS = scan.nextLine();
             precio=Double.parseDouble(precioS);  
     
            t.insertar(codigo,nombre,precio);
 
            break;
                
        case 2:
            
            String nombreA = "MJ";
            
            System.out.println("\n Actualizar Producto");
            
            t.verLista();
            
            System.out.print("Ingrese el codigo del Producto a actualizar: ");
            int codigoA = scan.nextInt();
           
            System.out.print("Nombre: ");
            
            nombreA = scan.nextLine();
            nombreA = scan.nextLine();
       
            System.out.print("Precio: ");
            precioS = scan.nextLine();
            precio=Double.parseDouble(precioS);
            
            t.actualizar(codigoA, nombreA, precio);

            break;
                
        case 3:
                     
            System.out.println("\n Eliminar Producto ");
            t.verLista();
            
            System.out.print("Ingrese el codigo del Producto a eliminar: ");
            int codigoE = scan.nextInt();
         
            t.eliminar(codigoE);
        
            break;
                
        case 4:     
            
            System.out.println("\t\nSu Lista de Productos");
            t.verLista();
                     
            break;
            
        case 5:
                        
            System.out.println("\nEsta seguro de que desea Salir ?");
            System.out.println("1. Si");
            System.out.println("2. No");
            System.out.print("Ingrese Opcion = ");
            int opcSalir = scan.nextInt();
            if(opcSalir == 1){
                break;
            }else{
                opcion=0;
            }
            break;
                
        default:
                
                System.out.println("\nOpcion Incorrecta !");
                break;            
        }

        }while(opcion!=5);
        
    }
      
    public Connection getConexion(){
        Connection conexion = null;
 
        String servidor = "localhost";        
        String puerto = "5432";        
        String baseDatos = "supermercado_ap"; 
        String url = "jdbc:postgresql://" + servidor + ":" + puerto + "/" + baseDatos;  
        String usuario = "postgres";
        String clave = "adam1998";
        try {
            conexion = DriverManager.getConnection(url, usuario, clave);
            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexion;
    }

     public void insertar(int codigo, String nombre, double precio) {

        Connection conexion = getConexion();
        //String sql = "INSERT INTO producto (codigo,nombre,precio) VALUES (1,'AZUCAR',12.56)";
        String sql = "insert into producto values (" + codigo + ",'" + nombre + "','" + precio + "')";
        
        try (Statement st = conexion.createStatement()) {
          //EL executeUpdate ES LA EJECUCIÓN DE LA SENTENCIA
            st.executeUpdate(sql);   
        } catch (Exception e) {   
            e.printStackTrace();
        }
    }
    
     public void actualizar(int codigo, String nombre, double precio) {
         
        Connection conexion = getConexion();       
        String sql = "UPDATE producto SET nombre = '" + nombre + "'" + ", precio = " + precio + " WHERE codigo = " + codigo;
        try (Statement st = conexion.createStatement()) {           
            st.executeUpdate(sql);          
        } catch (Exception ex) {         
            ex.printStackTrace();
            System.out.println("\n F!");
        }
    }
    public void eliminar(int codigo) {
        
        Connection conexion = getConexion();   
        String sql = "DELETE FROM producto WHERE codigo = " + codigo;  
        try (Statement st = conexion.createStatement()) {
            st.executeUpdate(sql);           
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }   
    
     public void verLista() {
         
        Connection conexion = getConexion();
        String sql = "SELECT * FROM producto"; 
        
        try (Statement st = conexion.createStatement()) {           
           
        ResultSet result = st.executeQuery(sql);    
        while(result.next()){
      
            String codigo = result.getString("codigo");
            String nombre = result.getString("nombre");
            String precio = result.getString("precio");
   
            System.out.println("CODIGO: " + codigo + " NOMBRE: " + nombre + " PRECIO: " + precio);        
        } 
         
        }catch (Exception e) {         
            e.printStackTrace();
        }
    }
     
}