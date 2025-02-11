package servlet;

import java.io.IOException;

import dao.PostDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.Logic;
import model.User;

/**
 * Servlet implementation class CreatePostServlet
 */
@WebServlet("/PostCreateServlet")
public class PostCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * doGet
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String url;

        if (!Logic.isSessionActive(request)) { //セッションが存在しなければログインページにリダイレクト
            url = "LoginServlet";
            Logic.redirect(response, url);
            return;
        }

        HttpSession session = request.getSession();
        User        user    = (User)session.getAttribute("user");

        if (Logic.hasEmptyValues(user.getName())) { //アカウントに名前が未登録であればメッセージ表示
            request.setAttribute("message", "投稿するには<a href=\"RegisterUserServlet?action=registerUserName\">名前を登録</a>してください");
            url = "WEB-INF/jsp/home.jsp";
            Logic.forward(request, response, url);
            return;
        }

        url = "WEB-INF/jsp/postCreate.jsp";
        Logic.forward(request, response, url);
    }

    /**
     * doPost
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        String url;

        if (!Logic.isSessionActive(request)) { //セッションが存在するか判定
            url = "LoginServlet";
            Logic.redirect(response, url);
            return;
        }

        if ("postCreate".equals(action)) { //投稿する処理
            url = createPost(request);

            if (url.startsWith("WEB-INF")) { //フォワード
                Logic.forward(request, response, url);
            }
            else { //リダイレクト
                Logic.redirect(response, url);
            }

        }
    }

    /**
     * 投稿を登録する処理
     * 
     * @param  request
     * @return
     */
    private String createPost(HttpServletRequest request) {
        String title   = request.getParameter("title");
        String content = request.getParameter("content");

        if (Logic.hasEmptyValues(title, content)) { //空文字判定
            request.setAttribute("title", title);
            request.setAttribute("content", content);
            request.setAttribute("message", "入力内容が不足しています。");
            return "WEB-INF/jsp/postCreate.jsp";
        }

        HttpSession session = request.getSession();
        User        user    = (User)session.getAttribute("user");
        int         userId  = user.getId();
        PostDAO     postDAO = new PostDAO();

        if (postDAO.insertNewPost(userId, title, content)) { //データが正しく挿入出来たら
            return "ThreadServlet";
        }
        else {
            request.setAttribute("message", "投稿に失敗しました");
            return "WEB-INF/jsp/postCreate.jsp";
        }
    }

}
