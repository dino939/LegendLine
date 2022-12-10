package ru.hld.legendline.api.event.events;

import ru.hld.legendline.api.event.*;

public class EventRender2D extends Event
{
    float partialTicks;
    
    public EventRender2D(final float partialTicks) {
        this.partialTicks = partialTicks;
    }
    
    public float getPartialTicks() {
        return this.partialTicks;
    }
}
