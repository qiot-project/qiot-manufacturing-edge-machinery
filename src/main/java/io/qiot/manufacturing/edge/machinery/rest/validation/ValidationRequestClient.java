package io.qiot.manufacturing.edge.machinery.rest.validation;

import java.util.concurrent.CompletionStage;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.qiot.manufacturing.factory.commons.domain.productionvalidation.ColoringValidationRequestEventDTO;
import io.qiot.manufacturing.factory.commons.domain.productionvalidation.PackagingValidationRequestEventDTO;
import io.qiot.manufacturing.factory.commons.domain.productionvalidation.PrintingValidationRequestEventDTO;
import io.qiot.manufacturing.factory.commons.domain.productionvalidation.WeavingValidationRequestEventDTO;

/**
 * @author andreabattaglia
 */
@Path("/v1")
@RegisterRestClient(configKey = "validationrequest-api")
public interface ValidationRequestClient {


    @POST
    @Path("/weaving")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    CompletionStage<Response> requestWeavingValidation(
            WeavingValidationRequestEventDTO validationRequestDTO);

    @POST
    @Path("/coloring")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    CompletionStage<Response> requestColoringValidation(
            ColoringValidationRequestEventDTO validationRequestDTO);

    @POST
    @Path("/printing")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    CompletionStage<Response> requestPrintingValidation(
            PrintingValidationRequestEventDTO validationRequestDTO);

    @POST
    @Path("/packaging")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    CompletionStage<Response> requestPackagingValidation(
            PackagingValidationRequestEventDTO validationRequestDTO);
}
