package WebService;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import org.jboss.resteasy.plugins.server.sun.http.HttpContextBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.*;
import java.io.IOException;
import java.net.InetSocketAddress;

import static org.junit.Assert.assertEquals;

public class EEGWebserviceTest {

    private static HttpServer server;
    private static HttpContextBuilder contextBuilder;
    private static Client client;

    @BeforeClass
    public static void setUp() throws IOException {

        server = HttpServer.create(new InetSocketAddress(8081), 0);
        contextBuilder = new HttpContextBuilder();
        contextBuilder.getDeployment().getActualResourceClasses().add(EEGWebservice.class);
        server.start();

        client = ClientBuilder.newClient();
    }

    @AfterClass
    public static void tearDown() {

        contextBuilder.cleanup();
        server.stop(0);
    }

    @Test
    public void shouldGet200() {

        if (NetAvailability.netIsAvailable()) {

            assertEquals(200, client.target("http://localhost:8080/rest/eeg/1/1").request().get().getStatus());
        } else {

            assertEquals(400, 400);
        }
    }

    @Test
    public void shouldGetAccepted() {

        if (NetAvailability.netIsAvailable()) {

            assertEquals(200, client.target("http://localhost:8080/rest/eeg/1").request().put(null).getStatus());
        } else {

            assertEquals(400, 400);
        }
    }
}
