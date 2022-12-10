package ru.hld.legendline.api.utils;

import java.net.*;
import java.io.*;
import java.util.*;

public class WebUtils
{
    public static String agent1;
    public static String agent2;
    
    public static String visitSite(final String s) {
        final ArrayList<String> list = new ArrayList<String>();
        String value = "";
        try {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(s).openConnection();
            httpURLConnection.addRequestProperty(WebUtils.agent1, WebUtils.agent2);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
            while (true) {
                final String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                list.add(line);
            }
        }
        catch (Exception ex) {}
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            value = String.valueOf(new StringBuilder().append(value).append(iterator.next()));
        }
        return value;
    }
    
    static {
        WebUtils.agent1 = "User-Agent";
        WebUtils.agent2 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36";
    }
}
