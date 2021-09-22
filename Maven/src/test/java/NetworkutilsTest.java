import org.junit.Test;

public class NetworkutilsTest {
    @Test(timeout = 1000)
    public void getConnectionShouldReturnFasterThanOneSecond() {
        NetworkUtils.getConnection();
    }
}
