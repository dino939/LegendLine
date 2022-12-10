package ru.hld.legendline.api.event;

import java.lang.reflect.*;
import java.lang.annotation.*;
import java.util.*;

public class EventManager
{
    private static final Map REGISTRY_MAP;
    
    private static boolean isMethodBad(final Method method) {
        return method.getParameterTypes().length != 1 || !method.isAnnotationPresent(EventTarget.class);
    }
    
    public void cleanMap(final boolean b) {
        final Iterator<Map.Entry<K, ArrayHelper>> iterator = EventManager.REGISTRY_MAP.entrySet().iterator();
        while (iterator.hasNext()) {
            if (!b || iterator.next().getValue().isEmpty()) {
                iterator.remove();
            }
        }
    }
    
    public static void shutdown() {
        EventManager.REGISTRY_MAP.clear();
    }
    
    public static ArrayHelper get(final Class clazz) {
        return EventManager.REGISTRY_MAP.get(clazz);
    }
    
    private void sortListValue(final Class clazz) {
        final ArrayHelper arrayHelper = new ArrayHelper();
        final byte[] value_ARRAY = Priority.VALUE_ARRAY;
        final int length = value_ARRAY.length;
        int i = 0;
        while (i < length) {
            final byte b = value_ARRAY[i];
            for (final Data data : EventManager.REGISTRY_MAP.get(clazz)) {
                if (data.priority == b) {
                    arrayHelper.add(data);
                }
            }
            final int n = -1;
            final int n2 = 1;
            ++i;
            if (n >= n2) {
                return;
            }
        }
        EventManager.REGISTRY_MAP.put(clazz, arrayHelper);
    }
    
    public void removeEnty(final Class clazz) {
        final Iterator<Map.Entry<Class<?>, V>> iterator = EventManager.REGISTRY_MAP.entrySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getKey().equals(clazz)) {
                iterator.remove();
                break;
            }
        }
    }
    
    private static boolean isMethodBad(final Method method, final Class clazz) {
        return isMethodBad(method) || method.getParameterTypes()[0].equals(clazz);
    }
    
    static {
        REGISTRY_MAP = new HashMap();
    }
    
    private void register(final Method method, final Object o) {
        final Class<?> clazz = method.getParameterTypes()[0];
        final Data data = new Data(o, method, method.getAnnotation(EventTarget.class).value());
        if (!data.target.isAccessible()) {
            data.target.setAccessible(true);
        }
        if (EventManager.REGISTRY_MAP.containsKey(clazz)) {
            if (!EventManager.REGISTRY_MAP.get(clazz).contains(data)) {
                EventManager.REGISTRY_MAP.get(clazz).add(data);
                this.sortListValue(clazz);
            }
        }
        else {
            EventManager.REGISTRY_MAP.put(clazz, new ArrayHelper(data) {
                final EventManager this$0;
                final Data val$methodData;
                
                {
                    this.add(this.val$methodData);
                }
            });
        }
    }
    
    public void unregister(final Object o) {
        for (final ArrayHelper arrayHelper : EventManager.REGISTRY_MAP.values()) {
            for (final Data data : arrayHelper) {
                if (data.source.equals(o)) {
                    arrayHelper.remove(data);
                }
            }
        }
        this.cleanMap(true);
    }
    
    public void register(final Object o) {
        for (final Method method : o.getClass().getDeclaredMethods()) {
            if (!isMethodBad(method)) {
                this.register(method, o);
            }
        }
    }
    
    public void register(final Object o, final Class clazz) {
        for (final Method method : o.getClass().getDeclaredMethods()) {
            if (!isMethodBad(method, clazz)) {
                this.register(method, o);
            }
        }
    }
    
    public void unregister(final Object o, final Class clazz) {
        if (EventManager.REGISTRY_MAP.containsKey(clazz)) {
            for (final Data data : EventManager.REGISTRY_MAP.get(clazz)) {
                if (data.source.equals(o)) {
                    EventManager.REGISTRY_MAP.get(clazz).remove(data);
                }
            }
            this.cleanMap(true);
        }
    }
}
