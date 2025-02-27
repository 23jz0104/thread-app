package servlet;

import java.io.IOException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.Logic;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * doGet
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String url;

        if (Logic.isSessionActive(request)) { //セッションが有効であればホームにフォワード
            url = "ThreadServlet";
            Logic.redirect(response, url);
            return;
        }

        url = "WEB-INF/jsp/userLogin.jsp";
        Logic.forward(request, response, url);
    }

    /**
     * doPost
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String url;
        String email    = request.getParameter("email");
        String password = request.getParameter("password");

        if (Logic.hasEmptyValues(email, password)) {
            request.setAttribute("message", "入力内容が不足しています。");
            url = "WEB-INF/jsp/userLogin.jsp";
            Logic.forward(request, response, url);
            return;
        }

        if (!Logic.checkEmailFormat(email)) {
            request.setAttribute("message", "メールアドレスアドレスの形式が不正です。");
            url = "WEB-INF/jsp/userLogin.jsp";
            Logic.forward(request, response, url);
            return;
        }

        UserDAO userDAO = new UserDAO();
        User    user    = userDAO.getUserByEmailAndPassword(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            url = "LoginServlet";
            Logic.redirect(response, url);
        }
        else {
            request.setAttribute("message", "メールアドレスまたはパスワードに誤りがあります。");
            url = "WEB-INF/jsp/userLogin.jsp";
            Logic.forward(request, response, url);
        }
    }

}
