package testflow;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.Timeout;

public abstract class DefaultRestTest {

    public static final String WIREMOCK_URI = "http://localhost:8080";

    @Rule public Timeout globalTimeout = new Timeout(200000);
    @Rule public TestName name = new TestName();

    @Before
    public void setUp() {
        System.out.println("## "+name.getMethodName());
    }

    @BeforeClass
    public static void setUpCaseFlow() {
        RestAssured.useRelaxedHTTPSValidation();
    }

}
