package ru.hld.legendline.api.event.events;

import ru.hld.legendline.api.event.*;

public class EventKeyBoard extends Event
{
    int key;
    
    public EventKeyBoard(final int key) {
        this.key = key;
    }
    
    public int getKey() {
        return this.key;
    }
}
