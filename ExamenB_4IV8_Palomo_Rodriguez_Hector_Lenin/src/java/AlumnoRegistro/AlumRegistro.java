/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AlumnoRegistro;

import jakarta.servlet.ServletConfig;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenin
 */
public class AlumRegistro extends HttpServlet {

    /**
     * Para poder establecer una conexion con bd es necesario contar con 3 tipos
     * de objeto muy especificos los cuales son: Connection es el encargado de
     * establecer la conexion con el servidor BD Statement sirve para poder
     * definir y manipular los diferentes objetos de las querrys como por
     * ejemplo: create, delete, insert, update, drop, etc ResultSet el cual
     * sirve para poder realizar las consultas a la BD
     */
    private Connection con;
    private Statement set;
    private ResultSet rs;

    public void init(ServletConfig scg) throws ServletException {
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AlumRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AlumRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String boleta = request.getParameter("Boleta");

        String nombre = request.getParameter("Nombre");
        String primerApellido = request.getParameter("Primerapellido");
        String segundoApellido = request.getParameter("Segundoapellido");
        String semestre = request.getParameter("Semestre");
        String turno = request.getParameter("Turno");
        String password = request.getParameter("Password");
        int resultado = 0;
        PrintWriter out = response.getWriter();
        String mensajeError = "";
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AlumRegistro</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<button onclick=\"window.location='./index.html'\">Inicio</button>");
            resultado = altaCuenta(boleta, nombre, primerApellido, segundoApellido, semestre, turno, password);
            if (resultado == 1) {
                out.println("<h1>El registro fue satisfactorio</h1>");
            }    
        } catch (SQLException e) {
          resultado = 0;
          mensajeError = e.getMessage();
          if(e.getErrorCode() == 1062){
          out.println("<h1>El registro de la cuenta no fue satisfactorio debido a que ya existe un registro con el número de boleta:"+boleta+"</h1>");
          }else{
              out.println("<h1>El registro no fue satisfactorio, codigo de error:"+e.getErrorCode()+": "+mensajeError+"</h1>");
          }
          
        }
        finally
        {
            
            out.println("<button onclick=\"window.location='./registroalumno.html'\">Regresar</button>");
            out.println("</body>");
            out.println("</html>");
        }

//        try ( PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet AlumRegistro</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet AlumRegistro at " + request.getContextPath() + "</h1>");
//            out.println("<h1>Resultado:"+resultado+"</h1>");
//            out.println("<h1>Boleta:"+boleta+"</h1>");
//            out.println("<h1>Nombre:"+nombre+"</h1>");
//            out.println("<h1>Primer Apellido:"+primerApellido+"</h1>");
//            out.println("<h1>Segundo Apellido:"+segundoApellido+"</h1>");
//            out.println("<h1>Semestre:"+semestre+"</h1>");
//            out.println("<h1>Turno:"+turno+"</h1>");
//            out.println("<h1>Contraseña:"+password+"</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

    private int altaCuenta(String boleta,
            String nombre,
            String primerApellido,
            String segundoApellido,
            String semestre,
            String turno,
            String password) throws SQLException {
        String isql = "insert into cuenta_alumno(boleta,nombres,primer_apellido,segundo_apellido,semestre,turno,password)"
                + " values(?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(isql);
        ps.setLong(1, Long.parseLong(boleta));
        ps.setString(2, nombre);
        ps.setString(3, primerApellido);
        ps.setString(4, segundoApellido);
        ps.setInt(5, Integer.parseInt(semestre));
        ps.setString(6, turno);
        ps.setString(7, password);
        int resultado = ps.executeUpdate();
        return resultado;

    }

    public void destroy() {
        try {
            con.close();
        } catch (Exception e) {
            super.destroy();
        }
    }
}
