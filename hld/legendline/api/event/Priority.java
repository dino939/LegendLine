package ru.hld.legendline.api.event;

public class Priority
{
    public static final byte[] VALUE_ARRAY;
    public static final byte SECOND;
    public static final byte FIFTH;
    public static final byte FOURTH;
    public static final byte THIRD;
    public static final byte FIRST;
    
    static {
        FIRST = 0;
        FIFTH = 4;
        FOURTH = 3;
        THIRD = 2;
        SECOND = 1;
        VALUE_ARRAY = new byte[] { 0, 1, 2, 3, 4 };
    }
}
