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
 * Servlet implementation class ChangeUserNameServlet
 */
@WebServlet("/ChangeUserNameServlet")
public class ChangeUserNameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String url;

        if (!Logic.isSessionActive(request)) { //セッションが存在するか判定
            url = "LoginServlet";
            Logic.forward(request, response, url);
            return;
        }

        url = "WEB-INF/jsp/changeUserName.jsp";
        Logic.forward(request, response, url);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String url;

        if (!Logic.isSessionActive(request)) { //セッションが存在するか判定
            url = "LoginServlet";
            Logic.redirect(response, url);
            return;
        }

        String name = request.getParameter("name");

        if (Logic.hasEmptyValues(name)) { //空文字判定
            request.setAttribute("message", "メールアドレスが入力されていません。");
            url = "WEB-INF/jsp/changeUserName.jsp";
            Logic.forward(request, response, url);
            return;
        }

        UserDAO userDAO = new UserDAO();

        if (userDAO.getUserByName(name) != null) { //ユーザー名が使用されていれば
            request.setAttribute("message", "入力されたユーザー名は既に使用済みです。");
            url = "WEB-INF/jsp/changeUserName.jsp";
            Logic.forward(request, response, url);
            return;
        }

        HttpSession session = request.getSession();
        User        user    = (User)session.getAttribute("user");
        int         userId  = user.getId();

        if (userDAO.updateUserName(userId, name)) { //メールアドレスの更新が成功すれば
            user = userDAO.getUserById(userId);
            session.setAttribute("user", user);
            url = "WEB-INF/jsp/changeUserNameComplete.jsp";
            Logic.forward(request, response, url);
        }
        else { //エラー
            request.setAttribute("message", "エラーが発生しました。");
            url = "WEB-INF/jsp/changeUserName.jsp";
            Logic.forward(request, response, url);
        }
    }

}
