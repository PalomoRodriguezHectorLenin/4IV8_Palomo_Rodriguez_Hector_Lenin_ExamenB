<%-- 
    Document   : consultardatosdelamaquina
    Created on : 18 abr. 2022, 18:45:30
    Author     : lenin
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.io.IOException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%!
    static Connection con = null;

    public void jspInit(){
    // se deben de establecer los elementos para la conexion con bd
        String url = "jdbc:mysql://localhost:3306/maquinas";
        //controlador:motorBD:puerto/IP/nombreBD
        String username = "root";
        String password = "L3usM4fi3r-";

        try {
            //internat concetar a la bd
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            System.out.println("No conecto");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
    public void jspDestroy(){
         try {
            con.close();
        } catch (Exception e) {
            super.destroy();
        }
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
         <div id="indicaciones5">
            <h1>Estos son los datos de la maquina registrada</h1>
        </div>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Marca</th>
                <th>Modelo</th>
                <th>Laboratorio</th>
                <th>Boleta</th>
                <th>Fecha de Registro</th>
            </tr>

            <%
                String boleta = (String) session.getAttribute("usuario");

                String isql = "SELECT * FROM maquinas.maquina_registro as mr where mr.boleta=?";
                PreparedStatement ps = con.prepareStatement(isql);
                ps.setString(1, boleta);
                ResultSet rs = ps.executeQuery();

                String id = null;
                String marca = null;
                String modelo = null;
                String laboratorio = null;
                String fregistro = null;

                while (rs.next()) {
                    id = rs.getString("id");
                    marca = rs.getString("marca");
                    modelo = rs.getString("modelo");
                    laboratorio = rs.getString("laboratorio");
                    fregistro = rs.getString("fregistro");
            %>
            <tr>
                <td><%=id%></td>
                <td><%=marca%></td>
                <td><%=modelo%></td>
                <td><%=laboratorio%></td>
                <td><%=boleta%></td>
                <td><%=fregistro%></td>
            </tr>
            <%
                }
            rs.close();
            ps.close();
            %>
        </table>
        
        <br>
        <div id="botonvolverinicio5">
            <button onclick="window.location = './inicio.jsp'">Volver al Menú</button>
        </div>
    </body>
</html>
