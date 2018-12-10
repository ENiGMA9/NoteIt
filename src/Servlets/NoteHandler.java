package Servlets;

import General.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import General.StringGenerator;

import static General.Constants.HOME_ADDRESS;
import static General.Constants.VIEW_ADDRESS;


@WebServlet(name = "NoteHandler")
public class NoteHandler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title;
        String note;
        String privacy;
        String password;


        title = request.getParameter("noteTitle");
        note = request.getParameter("noteContent");
        privacy = request.getParameter("privacyCheck");
        password = request.getParameter("password");




            if(password==null)
                password = new String();

            if (note.isEmpty())
                response.sendRedirect(HOME_ADDRESS);


            if (privacy.contains("private") && password.isEmpty()) {
                password = new StringGenerator(20).nextString();
            }


            String newURL;

            do {
                newURL = new StringGenerator(25).nextString();
            } while (DBManager.UrlExists(newURL));


            String token;

            token = new StringGenerator(24).nextString();


            if (DBManager.InsertNote(title, note, newURL, password, token) == false) {
                System.out.println("Note insert failed");
            } else {

                HttpSession session = request.getSession();
                session.setAttribute("token", token);
                session.setAttribute("password", password);
                session.setAttribute("alert", "You successfuly added a note!");
                response.sendRedirect(VIEW_ADDRESS + "?id=" + newURL);
            }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
System.out.println("DOGET");
    }
}
