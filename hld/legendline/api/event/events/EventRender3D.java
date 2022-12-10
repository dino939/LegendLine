package ru.hld.legendline.api.event.events;

import ru.hld.legendline.api.event.*;

public class EventRender3D extends Event
{
    float partialTicks;
    
    public float getPartialTicks() {
        return this.partialTicks;
    }
    
    public EventRender3D(final float partialTicks) {
        this.partialTicks = partialTicks;
    }
}
