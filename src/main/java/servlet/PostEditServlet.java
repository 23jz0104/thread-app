package servlet;

import java.io.IOException;

import dao.PostDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.Logic;
import model.Post;

/**
 * Servlet implementation class PostEditServlet
 */
@WebServlet("/PostEditServlet")
public class PostEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * doGet
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        if (!Logic.isSessionActive(request)) { //セッションが存在していなければログインページにリダイレクト
            String url = "LoginServlet";
            Logic.redirect(response, url);
            return;
        }

        int    postId = 0;
        String url;

        try {
            postId = Integer.parseInt(request.getParameter("postId"));
        }
        catch (NumberFormatException e) {
            e.printStackTrace();

            request.setAttribute("message", "エラーが発生しました。");
            url = "WEB-INF/jsp/home.jsp";
            Logic.forward(request, response, url);
        }

        PostDAO postDAO = new PostDAO();
        Post    post    = postDAO.getPostById(postId);

        if (post != null) {
            request.setAttribute("post", post);
            url = "WEB-INF/jsp/postEdit.jsp";
            Logic.forward(request, response, url);
        }
        else {
            request.setAttribute("message", "エラーが発生しました。");
            url = "WEB-INF/jsp/home.jsp";
            Logic.forward(request, response, url);
        }

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

        String title   = request.getParameter("title");
        String content = request.getParameter("content");

        if (Logic.hasEmptyValues(title, content)) { //空文字判定
            request.setAttribute("title", title);
            request.setAttribute("content", content);
            request.setAttribute("message", "入力内容に不備ああります。");

            url = "WEB-INF/jsp/postEdit.jsp";
            Logic.forward(request, response, url);
            return;
        }

        int postId = 0;

        try {
            postId = Integer.parseInt(request.getParameter("postId"));
        }
        catch (NumberFormatException e) {
            request.setAttribute("message", "エラーが発生しました");
            url = "WEB-INF/jsp/postEdit.jsp";
            Logic.forward(request, response, url);
            return;
        }

        PostDAO postDAO = new PostDAO();

        if (postDAO.updatePost(postId, title, content)) { //更新が成功すればリダイレクト
            url = "ThreadServlet";
            Logic.redirect(response, url);
        }
        else { //失敗すれば編集ページにフォワード
            request.setAttribute("title", title);
            request.setAttribute("content", content);
            request.setAttribute("message", "更新に失敗しました。");

            url = "WEB-INF/jsp/postEdit.jsp";
            Logic.forward(request, response, url);
        }

    }

}
