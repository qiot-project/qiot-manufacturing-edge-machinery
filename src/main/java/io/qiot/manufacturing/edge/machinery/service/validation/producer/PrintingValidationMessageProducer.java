package io.qiot.manufacturing.edge.machinery.service.validation.producer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;

import io.qiot.manufacturing.edge.machinery.domain.ProductionChainStageEnum;
import io.qiot.manufacturing.edge.machinery.domain.event.chain.RequestPrintingValidationEvent;
import io.qiot.manufacturing.edge.machinery.domain.event.chain.RequestWeavingValidationEvent;

@ApplicationScoped
public class PrintingValidationMessageProducer
        extends AbstractValidationMessageProducer {

    @ConfigProperty(name = "qiot.production.chain.validation.printing.queue")
    String validationQueueName;

    @Inject
    Logger LOGGER;

    @PostConstruct
    void init() {
        super.doInit();
    }

    @PreDestroy
    void destroy() {
        context.close();
    }

    public void requestValidation(
            @Observes RequestPrintingValidationEvent event) {
        super.doRequestValidation(event);
    }

    @Override
    protected String getValidationQueueName() {
        return validationQueueName;
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    @Override
    protected ProductionChainStageEnum getStage() {
        return ProductionChainStageEnum.PRINTING;
    }
}