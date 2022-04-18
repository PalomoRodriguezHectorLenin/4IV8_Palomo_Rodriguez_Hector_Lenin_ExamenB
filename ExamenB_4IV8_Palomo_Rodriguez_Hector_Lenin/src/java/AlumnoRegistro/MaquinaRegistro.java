/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AlumnoRegistro;

import jakarta.servlet.ServletConfig;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenin
 */
@WebServlet(name = "MaquinaRegistro", urlPatterns = {"/MaquinaRegistro"})
public class MaquinaRegistro extends HttpServlet {

        private Connection con;
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
        
        String marca = request.getParameter("Marca");
        String modelo = request.getParameter("Modelo");
        String laboratorio = request.getParameter("Laboratorio");
        String maquina = request.getParameter("Maquina");
        String fregistro = request.getParameter("Fecharegistro");
        
        
        
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MaquinaRegistro</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MaquinaRegistro at " + request.getContextPath() + "</h1>");
            out.println("<h2>La marca es: "+marca+"</h2>");
            out.println("<h2>El Modelo es: "+modelo+" </h2>");
            out.println("<h2>El Laboratorio es: "+laboratorio+"</h2>");
            out.println("<h2>LEL que la registro fue: "+maquina+"</h2>");
            out.println("<h2>La fecha fue en: "+fregistro+"</h2>");
            out.println("<button onclick=\"window.location='./inicio.jsp'\"></buttton");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private int consultaRegistroMaquina(String marca,
            String modelo,
            String laboratorio,
            String maquina,
            String fregistro) throws SQLException {
        
            String isql ="insert into maquina_registro(modelo,laboratorio,maquina,fregistro)" + "values (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(isql);
            ps.setString(1, modelo);
            ps.setString(2, laboratorio);
            ps.setString(3, maquina);
            ps.setString(4, fregistro);
            return 0;
    
    }
    
     public void destroy() {
        try {
            con.close();
        } catch (Exception e) {
            super.destroy();
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(MaquinaRegistro.class.getName()).log(Level.SEVERE, null, ex);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(MaquinaRegistro.class.getName()).log(Level.SEVERE, null, ex);
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

}
