import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.log.Log;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by jiahui.chen on 2016/1/4.
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");

        String mcc = request.getParameter("mcc");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        out.print("hello world :" + mcc);

        try {
            //加载MySql的驱动类
            String driver = "com.mysql.jdbc.Driver";
            String dbName = "myroadrecord";
            String pwd = "root";
            String user = "root";
            String url = "jdbc:mysql://localhost:3306/" + dbName;
            String sql = "select * from user";
            Class.forName(driver);

            Connection connection = (Connection) DriverManager.getConnection(url, user, pwd);

            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //userName
                out.println(rs.getString(1));
            }
            //关闭记录集
            if (rs != null) {
                rs.close();
            }
            //关闭声明
            if (ps != null) {
                ps.close();
            }
            //关闭连接
            if (connection != null) {
                connection.close();
            }

            out.println("成功");
        } catch (ClassNotFoundException e) {
            out.println("找不到驱动程序类 ，加载驱动失败！");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
