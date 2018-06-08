package WebService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/eeg")
@RequestScoped
public class EEGWebservice {

    @Inject
    private DataCollector dataCollector;

    @GET
    public Response listSamples() {

        return Response.ok(dataCollector.getRequest()).build();
    }

    @GET
    @Path("{id}")
    public Response getChannelSamples(@PathParam("id") Integer id) {

        return Response.ok(dataCollector.getRequest(id)).build();
    }

    @PUT
    @Path("{id}")
    public Response updateAcquisitionTime(@PathParam("id") Integer time) {

        dataCollector.setTimePeriod(time);
        return Response.ok("Success").build();
    }
}
