package WebService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Main REST Webservice class
 */
@Path("/eeg")
@RequestScoped
public class EEGWebservice {

    @Inject
    private DataCollector dataCollector;

    @GET
    @Path("{id}")
    public Response listSamples(@PathParam("id") Integer id) {

        return Response.ok(dataCollector.getRequest(id)).build();
    }

    @GET
    @Path("{value1}/{value2}")
    public Response getChannelSamples(@PathParam("value1") Integer requestId, @PathParam("value2") Integer channelID) {

        return Response.ok(dataCollector.getRequest(requestId, channelID)).build();
    }

    @PUT
    @Path("{id}")
    public Response updateAcquisitionTime(@PathParam("id") Integer time) {

        dataCollector.setTimePeriod(time);
        return Response.ok("Success").build();
    }
}
