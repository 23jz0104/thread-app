package logic;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

public class Logic {

    /**
     * セッションが存在するか判定するメソッド
     * 
     * @param  request リクエスト
     * @return         存在すればtrue / なければfalse
     */
    public static boolean isSessionActive(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) { //セッションが存在しなければnullを返す
            return false;
        }

        return session.getAttribute("user") instanceof User;
    }

    /**
     * 空文字判定メソッド
     * 
     * @param  values 判定する文字列
     * @return        空文字が存在していれば true / なければ false
     */
    public static boolean hasEmptyValues(String... values) {
        for (String value : values) {
            if (value == null || value.isEmpty()) {
                return true;
            }
        }

        return false;
    }

    /**
     * メールアドレスが適切なフォーマットか確認するメソッド
     * 
     * @param  email メールアドレス
     * @return       適切な形式であれば true / でなければ false;
     */
    public static boolean checkEmailFormat(String email) {
        String emailFormat = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";

        return email.matches(emailFormat);
    }

    /**
     * フォワードするメソッド
     * 
     * @param  request          リクエスト
     * @param  response         レスポンス
     * @param  url              フォワード先URL
     * @throws ServletException
     * @throws IOException
     */
    public static void forward(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    /**
     * redirectするメソッド
     * 
     * @param  response    レスポンス
     * @param  url         レスポンス先URL
     * @throws IOException
     */
    public static void redirect(HttpServletResponse response, String url) throws IOException {
        response.sendRedirect(url);
    }

    /**
     * テスト用メインメソッド
     * 
     * @param args
     */
    public static void main(String[] args) {
        String[] emails = {
                "test@email.com", "testaemail.com", "test.email.com",
                "test@email", "test@email.jp"
        };

        for (String email : emails) {
            System.out.println(email + " = " + checkEmailFormat(email));
        }
    }
}
