package ru.hld.legendline.api;

import club.minnced.discord.rpc.*;

public class RPC
{
    private static final DiscordRichPresence discordRichPresence;
    private static final DiscordRPC discordRPC;
    
    public static void startRPC() {
        final DiscordEventHandlers discordEventHandlers = new DiscordEventHandlers();
        discordEventHandlers.disconnected = RPC::lambda$startRPC$0;
        RPC.discordRPC.Discord_Initialize("1000441368818241556", discordEventHandlers, true, null);
        RPC.discordRichPresence.startTimestamp = System.currentTimeMillis() / 1000L;
        RPC.discordRichPresence.details = "admin version";
        RPC.discordRichPresence.largeImageKey = "_";
        RPC.discordRichPresence.largeImageText = "SUS?";
        RPC.discordRichPresence.joinSecret = "MTI4NzM0OjFpMmhuZToxMjMxMjM= ";
        RPC.discordRichPresence.state = null;
        RPC.discordRPC.Discord_UpdatePresence(RPC.discordRichPresence);
    }
    
    private static void lambda$startRPC$0(final int n, final String s) {
        System.out.println(String.valueOf(new StringBuilder().append("Discord RPC disconnected, var1: ").append(n).append(", var2: ").append(s)));
    }
    
    static {
        discordRichPresence = new DiscordRichPresence();
        discordRPC = DiscordRPC.INSTANCE;
    }
    
    public static void stopRPC() {
        RPC.discordRPC.Discord_Shutdown();
        RPC.discordRPC.Discord_ClearPresence();
    }
}
