package testflow;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import service.RestService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDetectiveBlogTest extends DefaultRestTest {

    @Test
    public void shouldRetrieveUserData() {
        RestService service = new RestService();
        service.getUserData("1");
    }

    @Test
    public void shouldAddNewUser() {
        RestService service = new RestService();
        service.addNewUser("lukaszroslonek", "www.google.com");
    }

    @Test
    public void shouldAddHealthcheckMapping() {
        RestService service = new RestService();
        service.postHealthcheckMapping();
    }

    @Test
    public void shouldGetHealthcheckPong() {
        RestService service = new RestService();
        service.getHealthcheckPing();
    }

}
