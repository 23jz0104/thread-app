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
 * Servlet implementation class ChangeUserPasswordServlet
 */
@WebServlet("/ChangeUserPasswordServlet")
public class ChangeUserPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * doGet
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String url;

        if (!Logic.isSessionActive(request)) { //セッションが存在するか判定
            url = "LoginServlet";
            Logic.forward(request, response, url);
            return;
        }

        url = "WEB-INF/jsp/changeUserPasswordConfirm.jsp";
        Logic.forward(request, response, url);
    }

    /**
     * doPost
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String url;

        if (!Logic.isSessionActive(request)) { //セッションが存在するか判定
            url = "LoginServlet";
            Logic.forward(request, response, url);
            return;
        }

        String password = request.getParameter("password");

        if (Logic.hasEmptyValues(password)) { //空文字判定
            request.setAttribute("message", "パスワードが入力されていません。");
            url = "WEB-INF/jsp/changeUserPasswordConfirm.jsp";
            Logic.forward(request, response, url);
            return;
        }

        HttpSession session = request.getSession();
        UserDAO     userDAO = new UserDAO();
        User        user    = (User)session.getAttribute("user");
        int         userId  = user.getId();
        user = userDAO.getUserByIdAndPassword(userId, password);

        if (user != null) { //ユーザーが見つかってれば

        }

    }

}
