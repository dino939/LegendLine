package ru.hld.legendline.api.notification;

import java.util.*;
import net.minecraft.client.gui.*;

public class NotificationsManager
{
    ArrayList notifications;
    
    public NotificationsManager() {
        this.notifications = new ArrayList();
    }
    
    public void render() {
        int n = GuiScreen.height - 25;
        for (int i = 0; i < this.notifications.size(); ++i) {
            ((Notification)this.notifications.get(i)).render((int)(GuiScreen.width - ((Notification)this.notifications.get(i)).getWidth() - 10.0f), n);
            n -= (int)(((Notification)this.notifications.get(i)).getHeight() + 4.0f);
            if (((Notification)this.notifications.get(i)).isTimeOut()) {
                this.notifications.remove(i);
            }
        }
    }
    
    public void add(final Notification notification) {
        this.notifications.add(notification);
    }
}
