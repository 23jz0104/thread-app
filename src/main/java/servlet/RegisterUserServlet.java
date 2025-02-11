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
 * Servlet implementation class AccountCreateServlet
 */
@WebServlet("/RegisterUserServlet")
public class RegisterUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        String url    = null;

        if (action == null || action.isEmpty()) {
            url = "WEB-INF/jsp/registerUserEmail.jsp";
        }
        else if ("registerUserName".equals(action)) {
            url = "WEB-INF/jsp/registerUserName.jsp";
        }

        Logic.forward(request, response, url);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String action = request.getParameter("action"); //パラメータ
        String url;

        if ("continue".equals(action)) { //メールアドレスが入力され続けるボンタンを押されたとき
            url = continueAction(request);
            Logic.forward(request, response, url);
        }
        else if ("register".equals(action)) {
            url = registerAction(request);
            Logic.forward(request, response, url);
        }
        else if ("registerUserName".equals(action)) {
            url = registerUserNameAction(request);
            Logic.forward(request, response, url);
        }
    }

    /**
     * ユーザー登録画面からメールアドレスが入力されたときの処理
     * 
     * @param  request
     * @return         遷移先URL
     */
    private String continueAction(HttpServletRequest request) {
        String  email   = request.getParameter("email");
        UserDAO userDAO = new UserDAO();

        if (Logic.hasEmptyValues(email)) { //空文字判定
            request.setAttribute("message", "メールアドレスが入力されていません");
            return "WEB-INF/jsp/registerUserEmail.jsp";
        }

        if (!Logic.checkEmailFormat(email)) {
            request.setAttribute("message", "メールアドレスの形式が不正です。");
            return "WEB-INF/jsp/registerUserEmail.jsp";
        }

        if (userDAO.isEmailRegistered(email)) { //メールアドレスが存在していた場合はエラー
            request.setAttribute("message", "メールアドレスは既に使用されています。<a href=\"index.jsp\">ログイン</a>");
            return "WEB-INF/jsp/registerUserEmail.jsp";
        }

        HttpSession session = request.getSession();
        session.setAttribute("email", email);

        return "WEB-INF/jsp/registerUserPassword.jsp";
    }

    /**
     * ユーザー登録画面からパスワードが入力されたときの処理
     * 
     * @param  request リクエスト
     * @return         遷移先URL
     */
    private String registerAction(HttpServletRequest request) {
        String      password        = request.getParameter("password");
        String      confirmPassword = request.getParameter("confirmPassword");

        HttpSession session         = request.getSession();
        String      email           = (String)session.getAttribute("email");

        if (Logic.hasEmptyValues(email)) { //セッションからemailが取得できているかどうか判定
            request.setAttribute("message", "セッションが切れています。最初からやり直してください。");
            return "WEB-INF/jsp/registerUserEmail.jsp";
        }

        if (Logic.hasEmptyValues(password, confirmPassword) || !password.equals(confirmPassword)) { //空文字判定と一致判定
            request.setAttribute("message", "パスワードが一致しないか、入力内容が不十分です。");
            return "WEB-INF/jsp/registerUserPassword.jsp";
        }

        UserDAO userDAO = new UserDAO();

        if (userDAO.insertToNewUser(email, password)) { //データが挿入できれば
            request.setAttribute("email", email);
            session.removeAttribute("email");

            return "WEB-INF/jsp/registerUserComplete.jsp";
        }
        else { //データ挿入失敗
            request.setAttribute("message", "登録に失敗しました。最初からやり直してください。");
            return "WEB-INF/jsp/registerUserEmail.jsp";
        }

    }

    /**
     * 名前を登録、変更するメソッド
     * 
     * @param  request
     * @return         遷移先URL
     */
    private String registerUserNameAction(HttpServletRequest request) {
        String name = request.getParameter("name");

        if (Logic.hasEmptyValues(name)) { //空文字判定
            request.setAttribute("message", "名前は必ず入力してください。");
            return "WEB-INF/jsp/registerUserName.jsp";
        }

        UserDAO userDAO = new UserDAO();

        if (userDAO.getUserByName(name) != null) { //重複判定
            request.setAttribute("message", "入寮された名前は既に使用されています。");
            return "WEB-INF/jsp/registerUserName.jsp";
        }

        HttpSession session = request.getSession();
        User        user    = (User)session.getAttribute("user");
        int         id      = user.getId();

        if (userDAO.registerUserName(id, name)) { //登録成功
            request.setAttribute("name", name);
            user = userDAO.getUserById(user.getId());
            session.setAttribute("user", user);
            return "WEB-INF/jsp/registerUserNameComplete.jsp";
        }
        else { //登録失敗
            request.setAttribute("message", "登録に失敗しました");
            return "WEB-INF/jsp/registerUserName";
        }
    }

}
