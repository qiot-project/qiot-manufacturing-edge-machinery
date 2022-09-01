package io.qiot.manufacturing.edge.machinery.rest.productline;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;

import io.qiot.manufacturing.all.commons.domain.productline.ProductLineDTO;
import io.qiot.manufacturing.edge.machinery.domain.event.productline.ProductLineChangedEventDTO;

/**
 * @author andreabattaglia
 *
 */
@Path("/productline")
public class NewProductLineResource {

    @Inject
    Logger LOGGER;

    @Inject
    ObjectMapper MAPPER;

    @Inject
    Event<ProductLineChangedEventDTO> prodictLineChangedEvent;

    @Transactional
    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newProductLine(ProductLineDTO productLine)
            throws Exception {

        LOGGER.debug("Received new ProductLine: {}", productLine);

        ProductLineChangedEventDTO eventDTO = new ProductLineChangedEventDTO();
        eventDTO.productLine = productLine;
        prodictLineChangedEvent.fire(eventDTO);

        return Response.status(Status.ACCEPTED).build();
    }

}