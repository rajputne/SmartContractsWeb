/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.edu.Servlet;

import com.me.SmartContracts.Utils.DocumentReader;
import com.me.SmartContracts.Utils.Elastic;
import static com.me.SmartContracts.Utils.Elastic.getDocument;
import com.me.SmartContracts.Utils.PhraseDetection;
import com.me.SmartContracts.Utils.Stanford;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.NodeBuilder;

/**
 *
 * @author neera
 */
public class ElasticSearch extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    static Client client;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String filepath = request.getParameter("hiddenPath");
            String fileName = request.getParameter("hiddenFileName");
            /* TODO output your page here. You may use following sample code. */
            String docText = DocumentReader.readDocument(filepath, fileName);
            try {
                //Put The Defined Terms in the elastic search

                client = NodeBuilder.nodeBuilder()
                        .client(true)
                        .node()
                        .client();
                DocumentReader.parseString(docText, client);
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ElasticSearch</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Defined Terms" + request.getContextPath() + "</h1>");
                Map<String, Object> allDefinedTerms = Elastic.getDocument(out, client, "definedterms", "term", "term");

                StringBuilder htmlBuilder = new StringBuilder();
                htmlBuilder.append("<table>");

                for (Map.Entry<String, Object> entry : allDefinedTerms.entrySet()) {
                    htmlBuilder.append("<tr><td>"+entry.getKey()+"</td><td>"+entry.getValue()+"</td></tr>");
                            
                }

                htmlBuilder.append("</table>");
                //PhraseDetection.getPhrases(docText);
                //Get Phrase Detecttion Here.
                
                //docText = Stanford.cleanStopWords(docText);
                
                String html = htmlBuilder.toString();
                out.println(html);
                out.println("</body>");
                out.println("</html>");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ElasticSearch.class.getName()).log(Level.SEVERE, null, ex);
            }

        
        } catch (IOException ex) {
            Logger.getLogger(ElasticSearch.class.getName()).log(Level.SEVERE, null, ex);
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
