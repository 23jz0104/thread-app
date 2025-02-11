package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Post;

public class PostDAO {
    Connection con;

    /**
     * コンストラクタ
     */
    public PostDAO() {
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
        }
        catch (SQLException e) {
            System.err.println("PostDAO()でエラー : " + e.getMessage());
        }
    }

    /**
     * 投稿IDを引数に投稿を取得
     * 
     * @param  id 投稿ID
     * @return    見つかれば Post / なければ null
     */
    public Post getPostById(int id) {
        String sql = "SELECT * FROM posts WHERE id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs2model(rs);
                }
            }
        }
        catch (SQLException e) {
            System.err.println("getPostById(int id)メソッドでエラー : " + e.getMessage());
        }

        return null;
    }

    /**
     * すべてのPostをList型で取得
     * 
     * @return List<Post> postList
     */
    public List<Post> getAllPostList() {
        List<Post> postList = new ArrayList<>();
        String     sql      = "SELECT * FROM posts ORDER BY created_date";

        try (PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                postList.add(rs2model(rs));
            }
        }
        catch (SQLException e) {
            System.err.println("PostDAO getAllPostList()メソッドでエラー : " + e.getMessage());
        }

        return postList;
    }

    /**
     * 新しい投稿をDBに挿入する処理
     * 
     * @param  user_id ユーザーID
     * @param  title   タイトル
     * @param  content 内容
     * @return         成功 true / 失敗 false
     */
    public boolean insertNewPost(int user_id, String title, String content) {
        String sql    = "INSERT INTO posts (user_id, title, content) VALUES (?, ?, ?)";
        int    result = 0;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, user_id);
            stmt.setString(2, title);
            stmt.setString(3, content);

            result = stmt.executeUpdate();
            return result > 0;
        }
        catch (SQLException e) {
            System.err.println("insertNewPost(int user_id, String title, String content)メソッドでエラー : " + e.getMessage());
        }

        return false;
    }

    /**
     * 投稿を更新する処理
     * 
     * @param  id      投稿ID
     * @param  title   タイトル
     * @param  content 内容
     * @return
     */
    public boolean updatePost(int id, String title, String content) {
        String sql    = "UPDATE posts SET title = ?, content = ? WHERE id = ?";
        int    result = 0;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, content);
            stmt.setInt(3, id);

            result = stmt.executeUpdate();
            return result > 0;
        }
        catch (SQLException e) {
            System.err.println("updatePost(int id, String title, String content)メソッドでエラー : " + e.getMessage());
        }

        return false;
    }

    /**
     * 指定されたidの投稿を削除するメソッド
     * 
     * @param  id 投稿ID
     * @return    削除成功 true / 削除失敗 false
     */
    public boolean deletePostById(int id) {
        String sql    = "DELETE FROM posts WHERE id = ?";
        int    result = 0;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
                result = stmt.executeUpdate();
                return result > 0;
        }
        catch (SQLException e) {
            System.err.println("deletePostById(int id)でエラー : " + e.getMessage());
        }

        return false;
    }

    /**
     * rs2model Post型に変換
     * 
     * @param  rs
     * @return    Post / null
     */
    private Post rs2model(ResultSet rs) {
        try {
            int           id          = rs.getInt("id");
            int           userId      = rs.getInt("user_id");
            String        title       = rs.getString("title");
            String        content     = rs.getString("content");
            LocalDateTime createdDate = rs.getTimestamp("created_date").toLocalDateTime();
            LocalDateTime updatedDate = rs.getTimestamp("updated_date").toLocalDateTime();

            return new Post(id, userId, title, content, createdDate, updatedDate);
        }
        catch (SQLException e) {
            System.err.println("postDAO rs2model(ResultSet rs)でエラー : " + e.getMessage());
        }

        return null;
    }
}
