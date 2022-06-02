/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import cart.Cart;
import entities.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionbeans.ProductFacade;

/**
 *
 * @author SE151470 Tran Vuong Anh Tuan
 */
@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    @EJB
    private ProductFacade pf;

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
                    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getAttribute("action").toString();
        switch (action) {
            case "index":
                index(request, response);
                break;
            case "add":
                add(request, response);
                break;
            case "update":
                update(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            case "empty":
                empty(request, response);
                break;
            default:
                request.setAttribute("controller", "error");
                request.setAttribute("action", "index");
        }
        request.getRequestDispatcher(App.LAYOUT).forward(request, response);
    }

    private void index(HttpServletRequest request, HttpServletResponse response) {

    }

    private void add(HttpServletRequest request, HttpServletResponse response) {
        //lay thong tin tu client
        int id = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        //lay cart tu session
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        //neu trong session chua co cart thi tao cart moi
        if (cart == null) {
            cart = new Cart();
        }
        //them item vao cart
        cart.add(id, quantity);
        //de cart vao session
        session.setAttribute("cart", cart);
        //hien home/index.jsp
        request.setAttribute("controller", "home");
        request.setAttribute("action", "index");
        //doc danh sach san pham
        List<Product> list = pf.findAll();
        request.setAttribute("list", list);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        //lay cart tu session
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        //xoa item trong cart
        cart.delete(id);
        //cho hien cart/index.jsp
        request.setAttribute("action", "index");

    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        //lay cart tu session
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        //xoa item trong cart
        cart.update(id, quantity);
        //cho hien cart/index.jsp
        request.setAttribute("action", "index");

    }
    private void empty(HttpServletRequest request, HttpServletResponse response) {
        
        //lay cart tu session
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        //xoa sach trong cart
        cart.empty();
        //cho hien cart/index.jsp
        request.setAttribute("action", "index");

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
        processRequest(request, response);
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
        processRequest(request, response);
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
