<%-- 
    Document   : consultardatosdelamaquina
    Created on : 18 abr. 2022, 18:45:30
    Author     : lenin
--%>
<%
  String id = (String)session.getAttribute("id");
  String marca = (String)session.getAttribute("marca");
  String modelo = (String)session.getAttribute("modelo");
  String laboratorio = (String)session.getAttribute("laboratorio");
  String boleta = (String)session.getAttribute("boleta");
  String fregistro = (String)session.getAttribute("fregistro");
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
        <div id="1dato">
            <label>
                ID: <%=id%>
            </label>
        </div>
            <br>
         <div id="4dato">
            <label>
                Marca: <%=marca%>
            </label>
        </div>
        <br>
         <div id="3odato">
            <label>
                Modelo: <%=modelo%>
            </label>
        </div>
        <br>
         <div id="2dato">
            <label>
                Laboratorio: <%=laboratorio%>
            </label>
        </div>
        <br>
         <div id="5dato">
            <label>
                ¿Quién registro la maquina?: <%=boleta%>
            </label>
        </div>
        <br> 
        <div id="6dato">
            <label>
                Fecha de registro: <%=fregistro%>
            </label>
        </div>
        <br>
        <div id="botonvolverinicio5">
            <button onclick="window.location='./inicio.jsp'">Volver al Menú</button>
        </div>
    </body>
</html>
