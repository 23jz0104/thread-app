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
 * Servlet implementation class PostDeleteServlet
 */
@WebServlet("/PostDeleteServlet")
public class PostDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * doGet
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * doPost
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String url;

        if (!Logic.isSessionActive(request)) { //セッションが存在するか判定
            url = "LoginServlet";
            Logic.redirect(response, url);
            return;
        }

        int postId = 0;

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

        if (post != null) { //投稿が見つかれば削除ページにフォワード

            if (postDAO.deletePostById(postId)) { //削除成功
                url = "ThreadServlet";
                Logic.redirect(response, url);
            }
            else { //削除失敗
                request.setAttribute("message", "エラーが発生しました。");
                url = "WEB-INF/jsp/home.jsp";
                Logic.forward(request, response, url);
            }
        }
        else { //エラーであればホームにフォワード
            request.setAttribute("message", "エラーが発生しました。");
            url = "WEB-INF/jsp/home.jsp";
            Logic.forward(request, response, url);
        }
    }

}
