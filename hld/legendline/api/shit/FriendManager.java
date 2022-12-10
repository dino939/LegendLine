package ru.hld.legendline.api.shit;

import java.util.*;

public class FriendManager
{
    public static ArrayList friends;
    
    public static void add(final String s) {
        if (!FriendManager.friends.contains(s)) {
            FriendManager.friends.add(s);
        }
    }
    
    public static void remove(final String s) {
        if (FriendManager.friends.contains(s)) {
            FriendManager.friends.remove(s);
        }
    }
    
    public static boolean isFriend(final String s) {
        return FriendManager.friends.contains(s);
    }
    
    static {
        FriendManager.friends = new ArrayList();
    }
}
