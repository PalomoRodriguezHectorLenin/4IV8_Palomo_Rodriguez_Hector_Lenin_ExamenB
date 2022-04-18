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
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenin
 */
public class Iniciosesion extends HttpServlet {
    
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
        String usuario = request.getParameter("Boleta");
        String password = request.getParameter("Password");
        
        iniciarSesion(request,response,usuario,password);
        
        
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Iniciosesion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Iniciosesion at " + request.getContextPath() + "</h1>");
            out.println("<h1></h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    private void iniciarSesion(HttpServletRequest request,HttpServletResponse response,String usuario, String password) throws SQLException, IOException{
        String isql = "SELECT * FROM maquinas.cuenta_alumno as ca where ca.boleta= ? and ca.password = ?";
        PreparedStatement ps = con.prepareStatement(isql);
        ps.setString(1, usuario);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        
        HttpSession session = request.getSession();
        String nombre = null;
        String primerApellido = null;
        String segundoApellido = null;
        String semestre = null;
        String turno = null;
        if(rs.next()){
            nombre = rs.getString("nombres");
            primerApellido = rs.getString("primer_apellido");
            segundoApellido = rs.getString("segundo_apellido");
            semestre = rs.getString("semestre");
            turno = rs.getString("turno");
            
            session.setAttribute("usuario", usuario);
            session.setAttribute("nombre", nombre);
            session.setAttribute("primerApellido", primerApellido);
            session.setAttribute("segundoApellido", segundoApellido);
            session.setAttribute("semestre", semestre);
            session.setAttribute("turno", turno);
            response.sendRedirect("./inicio.jsp");
            
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
            Logger.getLogger(Iniciosesion.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Iniciosesion.class.getName()).log(Level.SEVERE, null, ex);
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
