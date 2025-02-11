package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDAO {
    Connection con;

    /**
     * コンストラクタ
     */
    public UserDAO() {
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
        }
        catch (SQLException e) {
            System.err.println("コネクションの取得に失敗しました : " + e.getMessage());
        }
    }

    /**
     * idをもとにUserレコードを取得
     * 
     * @param  id
     * @return    見つかれば User / なければ null
     */
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs2model(rs);
                }
            }
        }
        catch (SQLException e) {
            System.err.println("UserDAO getUserById(int id)メソッドでエラー : " + e.getMessage());
        }

        return null;
    }

    /**
     * nameをもとにUserレコードを取得
     * 
     * @param  name
     * @return      見つかれば User / なければ null
     */
    public User getUserByName(String name) {
        String sql = "SELECT * FROM users WHERE name = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs2model(rs);
                }
            }
        }
        catch (SQLException e) {
            System.err.println("UserDAO getUserByName(String name)メソッドでエラー : " + e.getMessage());
        }

        return null;
    }

    /**
     * メールアドレスとパスワードをもとにユーザーを検索
     * 
     * @param  email    メールアドレス
     * @param  password パスワード
     * @return          見つかれば User / なければ null
     */
    public User getUserByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs2model(rs);
                }
            }
        }
        catch (SQLException e) {
            System.err.println("UserDAO getUserByEmailAndPassword(String email, String password)でエラー : " + e.getMessage());
        }

        return null;
    }

    /**
     * ユーザーIDとパスワードをもとにユーザーを検索
     * 
     * @param  id       ユーザーID
     * @param  password パスワード
     * @return 見つかれば User / 見つからなければ null
     */
    public User getUserByIdAndPassword(int id, String password) {
        String sql = "SELECT * FROM users WHERE id = ? AND password = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs2model(rs);
                }
            }
        }
        catch (SQLException e) {
            System.err.println("getUserByIdAndPassword(int id, String password)メソッドでエラー : " + e.getMessage());
        }

        return null;
    }

    /**
     * メールアドレスが使用されているかどうか判定
     * 
     * @param  email メールアドレス
     * @return       存在すれば true / なければ false
     */
    public boolean isEmailRegistered(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
        catch (SQLException e) {
            System.err.println("UserDAO isEmailRegistered(String email)メソッドエラー : " + e.getMessage());
            return false;
        }
    }

    /**
     * ユーザーを新しくユーザーテーブルに追加する処理
     * 
     * @param  email    メールアドレス
     * @param  password パスワード
     * @return          追加できれば true / 失敗 false
     */
    public boolean insertToNewUser(String email, String password) {
        String sql    = "INSERT INTO users (email, password) VALUES (?, ?)";
        int    result = 0;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            result = stmt.executeUpdate();
            return result > 0;
        }
        catch (SQLException e) {
            System.err.println("insertToNewUser(String email, String password)メソッドでエラー : " + e.getMessage());
        }

        return false;
    }

    /**
     * ユーザーネームを登録する処理
     * 
     * @param  id
     * @param  name
     * @return      登録完了 true / 失敗 false
     */
    public boolean registerUserName(int id, String name) {
        String sql    = "UPDATE users SET name = ? WHERE id = ?";
        int    result = 0;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, id);
            result = stmt.executeUpdate();
            return result > 0;
        }
        catch (SQLException e) {
            System.err.println("registerUserName(String name)メソッドでエラー : " + e.getMessage());
        }

        return false;
    }

    /**
     * メールアドレスを更新するメソッド
     * 
     * @param  id    ユーザーID
     * @param  email 新しいメールアドレス
     * @return       成功 true / 失敗 false
     */
    public boolean updateUserEmail(int id, String email) {
        String sql    = "UPDATE users SET email = ? WHERE id = ?";
        int    result = 0;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setInt(2, id);

            result = stmt.executeUpdate();
            return result > 0;
        }
        catch (SQLException e) {
            System.err.println("updateUserEmail(int id, String email)メソッドでエラー : " + e.getMessage());
        }

        return false;
    }

    /**
     * ユーザー名を更新するメソッド
     * 
     * @param  id    ユーザーID
     * @param  email 新しいユーザー名
     * @return       成功 true / 失敗 false
     */
    public boolean updateUserName(int id, String name) {
        String sql    = "UPDATE users SET name = ? WHERE id = ?";
        int    result = 0;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, id);

            result = stmt.executeUpdate();
            return result > 0;
        }
        catch (SQLException e) {
            System.err.println("updateUserName(int id, String name)メソッドでエラー : " + e.getMessage());
        }

        return false;
    }

    /**
     * Userモデルに変換
     * 
     * @param  rs
     * @return    User
     */
    private User rs2model(ResultSet rs) {
        try {
            int    id       = rs.getInt("id");
            String name     = rs.getString("name");
            String email    = rs.getString("email");
            String password = rs.getString("password");

            return new User(id, name, email, password);
        }
        catch (SQLException e) {
            System.err.println("UserDAO rs2modelでエラー : " + e.getMessage());
        }

        return null;
    }

    /**
     * テスト用メインメソッド
     * 
     * @param args
     */
    public static void main(String[] args) {

    }

}
