<%-- 
    Document   : registroproblematecnico
    Created on : 18 abr. 2022, 20:36:22
    Author     : lenin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <fieldset>
    <legend>Describir Problema Tecnico</legend>
        <form>
            <div id="indicaciones4">
                <h1>A continuación debera de seleccionar el id de la maquina y describir el problema</h1>
            </div>
            <br>
            <div id="elegirmaquina">
                <label>
                    Seleccione la maquina
                    <select name="maquinaid" id="MaquinaID" required>
                        <option value=""></option>
                </select>
                </label>
            </div>
            <br>
            <div id="descripcionproblematecnico">
                <label>
                    Describa el problema técnico:
                    <input type="text" id="problematec" name="Describirproblema" size="100" maxlength="250" required>
                </label>
            </div>
            <br>
            <div id="botonreprob">
                <input type="submit" value="Enviar Problema tecnico" id="Boton17">

            </div>
            <br>
        </form>
        </fieldset>
        <div id="botonvolveralinicio4">
                <button onclick="window.location='./inicio.jsp'">Volver al Menú</button>
        </div>
    </body>
</html>
