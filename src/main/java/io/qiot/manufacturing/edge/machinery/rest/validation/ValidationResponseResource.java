package io.qiot.manufacturing.edge.machinery.rest.validation;

import javax.enterprise.context.ApplicationScoped;
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

import io.qiot.manufacturing.edge.machinery.domain.event.chain.ValidationFailedEvent;
import io.qiot.manufacturing.edge.machinery.domain.event.chain.ValidationSuccessfullEvent;
import io.qiot.manufacturing.factory.commons.domain.productionvalidation.ValidationResponseDTO;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
public class ValidationResponseResource {

    @Inject
    Logger LOGGER;

    @Inject
    Event<ValidationSuccessfullEvent> successEvent;

    @Inject
    Event<ValidationFailedEvent> failureEvent;

    @Transactional
    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getValidationResponse(ValidationResponseDTO messageDTO)
            throws Exception {
        LOGGER.debug(
                "Received validation result "
                        + "for STAGE {} on ITEM {} / PRODUCTLINE {}",
                messageDTO.stage, messageDTO.itemId,
                messageDTO.productLineId);
        if (messageDTO.valid) {
            ValidationSuccessfullEvent event = new ValidationSuccessfullEvent();
            event.productLineId = messageDTO.productLineId;
            event.itemId = messageDTO.itemId;
            event.stage = messageDTO.stage;
            successEvent.fire(event);
        } else {
            ValidationFailedEvent event = new ValidationFailedEvent();
            event.productLineId = messageDTO.productLineId;
            event.itemId = messageDTO.itemId;
            event.stage = messageDTO.stage;
            failureEvent.fire(event);
        }

        return Response.status(Status.ACCEPTED).build();
    }
}