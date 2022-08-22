package akokko.test;

import akokko.dao.UserDao;
import akokko.domain.User;
import org.junit.Test;

public class MethodTest {

    @Test
    public void test1() {
        UserDao ud = new UserDao();
        User loginUser = new User(1,"hunter","788");
        User user = ud.login(loginUser);
        System.out.println(user);
    }
}
