package io.qiot.manufacturing.edge.machinery.domain.event.chain;

import io.qiot.manufacturing.edge.machinery.domain.production.ItemDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class StageCompletedEvent {
    public ItemDTO item;
}
