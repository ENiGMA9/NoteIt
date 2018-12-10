package Servlets;

import General.DBManager;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ViewHandler")
public class ViewHandler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      /*  if(request.getParameter("url").isEmpty())
            request.setAttribute("content", DBManager.GetNote(request.getRequestURL().substring(request.getRequestURL().indexOf("="+1))));*/

          String DBQuery[] = DBManager.GetNote(request.getParameterValues("id")[0]);
          request.setAttribute("title",DBQuery[0]);
       request.setAttribute("content",DBQuery[1]);
        request.setAttribute("password",DBQuery[2]);
        request.setAttribute("token",DBQuery[3]);
        request.setAttribute("views",DBQuery[4]);
        String scope;
        scope = request.getParameter("scope");

        String lastAction;

        lastAction=request.getParameter("alert");

   /*     if(lastAction!=null)
        {
            request.setAttribute("alert",lastAction);
        }*/

   HttpSession session = request.getSession();
   if(session.getAttribute("alert")!=null) {
       request.setAttribute("alert", session.getAttribute("alert").toString());
        session.setAttribute("alert",null);

   }

        if(scope!=null) {
            if (scope.contains("passwordAuth")) {
                session = request.getSession();
                session.setAttribute("password", request.getParameter("pass").trim());
            }

            if (scope.contains("tokenAuth")) {
                 session = request.getSession();
                session.setAttribute("token", request.getParameter("token").trim());
            }

            if (scope.contains("editMode")) {
                 session = request.getSession();
                session.setAttribute("editMode","true");
            }

            if(scope.contains("update")){
                session = request.getSession();
                session.setAttribute("editMode",null);

                String token = request.getParameter("token");
                if(token==null)
                    return;

               if(DBManager.UpdateNote(token,request.getParameter("newTitle"),request.getParameter("newContent"))==true)
               {
                //   request.setAttribute("alert","Successfuly updated!");
               }

                request.setAttribute("title",request.getParameter("newTitle"));
                request.setAttribute("content",request.getParameter("newContent"));

            }

        }
        request.getRequestDispatcher("/WEB-INF/view.jsp").forward(request, response);
    }
}
