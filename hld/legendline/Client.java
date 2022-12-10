package ru.hld.legendline;

import ru.hld.legendline.api.hudeditor.*;
import ru.hld.legendline.api.clickgui.*;
import ru.hld.legendline.api.notification.*;
import ru.hld.legendline.api.setting.*;
import ru.hld.legendline.api.shit.*;
import ru.hld.legendline.impl.modules.Render.*;
import java.awt.*;
import ru.hld.legendline.api.event.events.*;
import ru.hld.legendline.api.module.*;
import java.util.*;
import ru.hld.legendline.api.event.*;
import java.nio.charset.*;
import java.io.*;
import java.net.*;
import ru.hld.legendline.api.utils.*;
import java.security.*;

public class Client
{
    public static HudEditor hudEditor;
    public static ClickGuiScreen gui;
    public static NotificationsManager notificationsManager;
    public static PlayerHook playerHook;
    public static EventManager eventManager;
    public static BlurUtil blurUtil;
    public static SettingManager settingManager;
    public static ModuleManager moduleManager;
    public static cfg conf;
    
    public static int textcolor() {
        return ColorUtils.TwoColoreffect(new Color((int)ClickGui.textred2.getVal(), (int)ClickGui.textgreen2.getVal(), (int)ClickGui.textblue2.getVal()), new Color((int)ClickGui.textred.getVal(), (int)ClickGui.textgreen.getVal(), (int)ClickGui.textblue.getVal()), Math.abs(System.currentTimeMillis() / 10L) / 100.0 + 0.16999999999999998).getRGB();
    }
    
    @EventTarget
    public void onKey(final EventKeyBoard eventKeyBoard) {
        for (final Module module : Client.moduleManager.modules) {
            if (module.getKey() == eventKeyBoard.getKey()) {
                module.toggle();
            }
        }
    }
    
    public static int getGuiColor() {
        return new Color((int)ClickGui.RedGui.getVal(), (int)ClickGui.GreenGui.getVal(), (int)ClickGui.BlueGui.getVal(), (int)ClickGui.AlphA.getVal()).getRGB();
    }
    
    public static int getColor() {
        return ColorUtils.TwoColoreffect(new Color((int)ClickGui.RedFirst.getVal(), (int)ClickGui.GreenFirst.getVal(), (int)ClickGui.BlueFirst.getVal(), (int)ClickGui.AlphA.getVal()), new Color((int)ClickGui.RedSecond.getVal(), (int)ClickGui.GreenSecond.getVal(), (int)ClickGui.BlueSecond.getVal(), (int)ClickGui.AlphA.getVal()), Math.abs(System.currentTimeMillis() / 10L) / 100.0 + 0.16999999999999998).getRGB();
    }
    
    private static String AP2iKAwcS2gFL8cX8z944ZiJp2zS54T68Tp39nr2rJAwh(final InputStream inputStream) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        final Throwable t = null;
        try {
            final StringBuilder sb = new StringBuilder();
            while (true) {
                final String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
            }
            final String value = String.valueOf(sb);
            if (bufferedReader != null) {
                if (t != null) {
                    try {
                        bufferedReader.close();
                    }
                    catch (Throwable t2) {
                        t.addSuppressed(t2);
                    }
                }
                else {
                    bufferedReader.close();
                }
            }
            return value;
        }
        catch (Throwable t3) {
            throw t3;
        }
    }
    
    public static int getColor2() {
        return ColorUtils.TwoColoreffect(new Color((int)ClickGui.RedSecond.getVal(), (int)ClickGui.GreenSecond.getVal(), (int)ClickGui.BlueSecond.getVal(), (int)ClickGui.AlphA.getVal()), new Color((int)ClickGui.RedFirst.getVal(), (int)ClickGui.GreenFirst.getVal(), (int)ClickGui.BlueFirst.getVal(), (int)ClickGui.AlphA.getVal()), Math.abs(System.currentTimeMillis() / 10L) / 100.0 + 0.16999999999999998).getRGB();
    }
    
    public static int gColor2() {
        return ColorUtils.TwoColoreffect(new Color((int)ClickGui.RedSecond.getVal(), (int)ClickGui.GreenSecond.getVal(), (int)ClickGui.BlueSecond.getVal()), new Color((int)ClickGui.RedFirst.getVal(), (int)ClickGui.GreenFirst.getVal(), (int)ClickGui.BlueFirst.getVal()), Math.abs(System.currentTimeMillis() / 10L) / 100.0 + 0.16999999999999998).getRGB();
    }
    
    public static String requestURLSRC(final String s) throws IOException {
        final URLConnection openConnection = new URL(s).openConnection();
        openConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        return AP2iKAwcS2gFL8cX8z944ZiJp2zS54T68Tp39nr2rJAwh(openConnection.getInputStream());
    }
    
    public void init() {
        String requestURLSRC;
        try {
            requestURLSRC = requestURLSRC("https://docs.google.com/document/d/1h1bi7MNn_AfPlcSCtak_DYBmz3aT_EFwAOpm7mlblLw/edit?usp=sharing");
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println(requestURLSRC);
        if (requestURLSRC.contains("enable: true")) {
            if (!WebUtils.visitSite("https://pastebin.com/MgtwBSpG").contains(getHWID())) {
                System.exit(666);
            }
            Client.eventManager = new EventManager();
            Client.settingManager = new SettingManager();
            Client.moduleManager = new ModuleManager();
            Client.eventManager.register(this);
            Client.gui = new ClickGuiScreen();
            Client.hudEditor = new HudEditor();
            Client.notificationsManager = new NotificationsManager();
            Client.blurUtil = new BlurUtil();
            Client.conf = new cfg();
            final cfg conf = Client.conf;
            cfg.init();
            Client.blurUtil.init();
        }
    }
    
    public static String getHWID() {
        try {
            final String value = String.valueOf(new StringBuilder().append(System.getenv("COMPUTERNAME")).append(System.getProperty("user.name")).append(System.getenv("PROCESSOR_IDENTIFIER")).append(System.getenv("PROCESSOR_LEVEL")));
            final MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(value.getBytes());
            final StringBuffer sb = new StringBuffer();
            final byte[] digest = instance.digest();
            for (int length = digest.length, i = 0; i < length; ++i) {
                final String hexString = Integer.toHexString(0xFF & digest[i]);
                if (hexString.length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
            return sb.toString();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return "Error";
        }
    }
    
    public static int gColor() {
        return ColorUtils.TwoColoreffect(new Color((int)ClickGui.RedFirst.getVal(), (int)ClickGui.GreenFirst.getVal(), (int)ClickGui.BlueFirst.getVal()), new Color((int)ClickGui.RedSecond.getVal(), (int)ClickGui.GreenSecond.getVal(), (int)ClickGui.BlueSecond.getVal()), Math.abs(System.currentTimeMillis() / 10L) / 100.0 + 0.16999999999999998).getRGB();
    }
}
