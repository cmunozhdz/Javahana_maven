/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication6;

/**
 *
 * @author clemente 
 * @objetivo: Prueba de conexion SAP -HANA Bussines Tegnology Plataforma
 * El jdbc debe desacargarse de la pagina de sap 
 * y agregar el jar en las libraries referencias 
 * la base debe estar corriendo en el BTP ya que en pruebas se apaga durante las noches
 */
import java.sql.*;
import com.sap.db.jdbc.Driver;
public class JavaClient {
    public static void main(String[] argv) {
        System.out.println("Java version: " + com.sap.db.jdbc.Driver.getJavaVersion());
        System.out.println("Minimum supported Java version and SAP driver version number: " + com.sap.db.jdbc.Driver.getVersionInfo());
        Connection connection = null;
        try {  
            System.out.println("Espere conectando ...");
            
            connection = DriverManager.getConnection(  
                //Option 1, retrieve the connection parameters from the hdbuserstore
                //The below URL gets the host, port and credentials from the hdbuserstore.
                
                
                //Option2, specify the connection parameters
                //"jdbc:sap://10.11.123.134:39015/?encrypt=true&validateCertificate=false", "User1", "Password1");
                  "jdbc:sap://066607a9-8137-4fdb-8197-403321cdf12b.hana.trial-us10.hanacloud.ondemand.com:443/?encrypt=true&validateCertificate=true", "DBADMIN", "Kl3m3n73$$");       
                //As of SAP HANA Client 2.6, connections on port 443 enable encryption by default
                //validateCertificate should be set to false when connecting
                //to an SAP HANA, express edition instance that uses a self-signed certificate.

                //As of SAP HANA Client 2.7, it is possible to direct trace info to stdout or stderr
                //"jdbc:sap://10.11.123.134:39015/?encrypt=true&validateCertificate=false&traceFile=stdout&traceOptions=CONNECTIONS", "User1", "Password1");

        }
        catch (SQLException e) {
            System.err.println("Connection Failed:");
            System.err.println(e);
            return;
        }
        if (connection != null) {
            try {
                System.out.println("Connection to HANA successful!");
                Statement stmt = connection.createStatement();
                ResultSet resultSet = stmt.executeQuery("SELECT * from USERS;");
                //USER_NAME,USER_ID,USERGROUP_NAME,USER_MODE,EXTERNAL_IDENTITY,CREATOR,CREATE_TIME,VALID_FROM,VALID_UNTIL,LAST_SUCCESSFUL_CONNECT,LAST_INVALID_CONNECT_ATTEMPT,INVALID_CONNECT_ATTEMPTS,ADMIN_GIVEN_PASSWORD,LAST_PASSWORD_CHANGE_TIME,PASSWORD_CHANGE_NEEDED,IS_PASSWORD_LIFETIME_CHECK_ENABLED,USER_DEACTIVATED,DEACTIVATION_TIME,IS_PASSWORD_ENABLED,IS_KERBEROS_ENABLED,IS_SAML_ENABLED,IS_JWT_ENABLED,IS_LDAP_ENABLED,IS_X509_ENABLED,IS_RESTRICTED,IS_RESTRICTED_DETAILS,IS_CLIENT_CONNECT_ENABLED,HAS_REMOTE_USERS,AUTHORIZATION_MODE,COMMENTS,CREATE_PROVIDER_TYPE,CREATE_PROVIDER_NAME
                System.out.println("USER_NAME\tUSER_ID\tUSERGROUP_NAME" );
                while (resultSet.next()) {
                    String title = resultSet.getString("USER_NAME");
                    
                    String firstName = resultSet.getString("USER_ID");
                    String lastName = resultSet.getString("USERGROUP_NAME");
                    System.out.println(title + "\t" + firstName + "\t" + lastName);
                }
            }
            catch (SQLException e) {
                System.err.println("Query failed!");
            }
        }
    }
}
