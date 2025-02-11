package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String CN_STRING   = "jdbc:mysql://localhost:3306/Thread_App_Database?useSSL=false&serverTimezone=UTC";
    private static final String USER        = "kaito";
    private static final String PASS        = "1207";
    private static DBManager    self;

    private DBManager() {
        try {
            Class.forName(JDBC_DRIVER);
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBCドライバのダウンロードに失敗しました : " + e.getMessage());
        }
    }

    /**
     * インスタンスを取得するメソッド
     * 
     * @return インスタンス
     */
    public static DBManager getInstance() {
        if (self == null) {
            self = new DBManager();
        }
        return self;
    }

    /**
     * コネクションを取得
     * 
     * @return              生成されたコネクション
     * @throws SQLException
     */
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CN_STRING, USER, PASS);
    }

    /**
     * テスト用メインメソッド
     * 
     * @param args
     */
    public static void main(String[] args) {
        DBManager dbManager = DBManager.getInstance();

        try (Connection con = dbManager.getConnection()) {
            System.out.println("接続成功: " + con);
        }
        catch (SQLException e) {
            System.err.println("DB接続エラー: " + e.getMessage());
        }

    }
}
