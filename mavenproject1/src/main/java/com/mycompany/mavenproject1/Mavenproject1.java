/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.mavenproject1;

/**
 *
 * @author clemente
 * * @objetivo: Prueba de conexion SAP -HANA Bussines Tegnology Plataforma
 * El jdbc debe desacargarse de la pagina de sap 
 * y agregar el jar en las libraries referencias 
 * la base debe estar corriendo en el BTP ya que en pruebas se apaga durante las noches
 * En esta version el odbc se carga de maven 
 * modificando el archivo pom.xml
 * 
 */
import java.sql.*;
import com.sap.db.jdbc.Driver;
public class Mavenproject1 {
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
                System.out.println("USER_NAME\tUSER_ID\tUSERGROUP_NAME" );
                
                Statement stmt = connection.createStatement();
                ResultSet resultSet = stmt.executeQuery("SELECT * from USERS;");
                while (resultSet.next()) {
                    String title = resultSet.getString(1);
                    String firstName = resultSet.getString(2);
                    String lastName = resultSet.getString(3);
                    System.out.println(title + " " + firstName + " " + lastName);
                }
            }
            catch (SQLException e) {
                System.err.println("Query failed!");
            }
        }
    }
}
