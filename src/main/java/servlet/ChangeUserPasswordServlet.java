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

        String action = request.getParameter("action");

        if ("passwordConfirm".equals(action)) {
            url = passwordConfirm(request);
            Logic.forward(request, response, url);
        }
        else if ("changePassword".equals(action)) {
            url = changePassword(request);
            Logic.forward(request, response, url);
        }

    }

    /**
     * パスワード確認要処理
     * 
     * @param  request リクエスト
     * @return         遷移先URL
     */
    private String passwordConfirm(HttpServletRequest request) {
        String password = request.getParameter("password");

        if (Logic.hasEmptyValues(password)) { //空文字判定
            request.setAttribute("message", "パスワードが入力されていません。");
            return "WEB-INF/jsp/changeUserPasswordConfirm.jsp";
        }

        HttpSession session = request.getSession();
        UserDAO     userDAO = new UserDAO();
        User        user    = (User)session.getAttribute("user");
        int         userId  = user.getId();
        user = userDAO.getUserByIdAndPassword(userId, password);

        if (user != null) { //ユーザーが見つかってれば
            return "WEB-INF/jsp/changeUserPassword.jsp";

        }
        else { //ユーザーが見つからなければ
            request.setAttribute("message", "パスワードが間違っています。");
            return "WEB-INF/jsp/changeUserPasswordConfirm.jsp";
        }
    }

    /**
     * パスワード更新処理
     * 
     * @param  request リクエスト
     * @return         遷移先URL
     */
    private String changePassword(HttpServletRequest request) {
        String password        = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (Logic.hasEmptyValues(password, confirmPassword)) {
            request.setAttribute("message", "入力内容が不足しています。");
            return "WEB-INF/jsp/changePassword";
        }

        if (password.equals(confirmPassword)) { //パスワードが一致していれば
            HttpSession session = request.getSession();
            User        user    = (User)session.getAttribute("user");
            int         userId  = user.getId();
            UserDAO     userDAO = new UserDAO();

            if (userDAO.updateUserPassword(userId, password)) { //パスワードの更新に成功したら
                user = userDAO.getUserById(userId);
                session.setAttribute("user", user);
                return "WEB-INF/jsp/changeUserPasswordComplete.jsp";
            }
            else {
                request.setAttribute("message", "パスワードの更新に失敗しました。");
                return "WEB-INF/jsp/changePassword";
            }
        }
        else {
            request.setAttribute("message", "パスワードが一致しません");
            return "WEB-INF/jsp/changePassword";
        }
    }

}
