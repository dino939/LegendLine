package ru.hld.legendline.api.event;

import ru.hld.legendline.*;
import java.lang.reflect.*;
import java.util.*;

public abstract class Event
{
    private boolean cancelled;
    
    private static void call(final Event event) {
        final EventManager eventManager = Client.eventManager;
        final ArrayHelper value = EventManager.get(event.getClass());
        if (value != null) {
            for (final Data data : value) {
                try {
                    data.target.invoke(data.source, event);
                }
                catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
                catch (InvocationTargetException ex2) {
                    ex2.printStackTrace();
                }
            }
        }
    }
    
    public Event call() {
        this.cancelled = false;
        call(this);
        return this;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
}
