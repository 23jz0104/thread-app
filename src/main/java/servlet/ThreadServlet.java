package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.PostDAO;
import dao.UserDAO;
import dto.PostDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.Logic;
import model.Post;
import model.User;

/**
 * Servlet implementation class ThreadServlet
 */
@WebServlet("/ThreadServlet")
public class ThreadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * doGet
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String url;

        if (!Logic.isSessionActive(request)) {
            url = "LoginServlet";
            response.sendRedirect(url);
            return;
        }

        UserDAO       userDAO     = new UserDAO();
        PostDAO       postDAO     = new PostDAO();
        List<Post>    postList    = postDAO.getAllPostList();
        List<PostDTO> postDTOList = new ArrayList<>();

        for (Post post : postList) { //投稿の数だけDTOインスタンス作成
            int  id   = post.getUserId();
            User user = userDAO.getUserById(id);

            postDTOList.add(new PostDTO(user, post));
        }

        request.setAttribute("postDTOList", postDTOList);
        url = "WEB-INF/jsp/home.jsp";
        Logic.forward(request, response, url);
    }

    /**
     * doPost
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
