package org.cog.rest.service.test;
import static junit.framework.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.cog.model.ShowTime;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class MovieTicketBookingRestlTest {

    static final URI BASE_URI = getBaseURI();

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port(8888).build();
    }

    
    @Test
    public void testGetDefaultUser() throws IOException {
        Client client = Client.create(new DefaultClientConfig());
        WebResource service = client.resource(getBaseURI());
        String resp = service.path("MovieTicketBookingSystem").path("rest").path("checkAval").path(ShowTime._11AM.toString())
                .accept(MediaType.APPLICATION_JSON)
                .get(String.class);
        System.out.println("Got stuff: " + resp);

        assertEquals("{\"MESSAGE\":\"50 Seats are Available.\"}", resp);
    }
    
    @Test
    public void bookTicket() throws IOException {
        Client client = Client.create(new DefaultClientConfig());
        WebResource service = client.resource(getBaseURI()).path("MovieTicketBookingSystem").path("rest").path("bookTicket");
        
        
        String input = "{\"showTime\":\"_11AM\",\"noOfSeat\":\"5\"}";
        
        ClientResponse response = service.type("application/json")
      		   .post(ClientResponse.class, input);
        String resp = response.getEntity(String.class);
        System.out.println("Got stuff: " + resp);

        assertEquals("{\"MESSAGE\":\"50 Seats are Available.\"}", resp);
    }
}