package ru.hld.legendline.api.font;

import ru.hld.legendline.api.utils.*;
import net.minecraft.util.*;
import java.awt.*;
import net.minecraft.client.*;

public final class FontUtils
{
    public static CFontRenderer fr;
    
    static {
        FontUtils.fr = Fonts.mntsb_20;
    }
    
    public static Font getFontFromTTF(final ResourceLocation resourceLocation, final float n, final int n2) {
        try {
            return Font.createFont(n2, Minecraft.getMinecraft().getResourceManager().getResource(resourceLocation).getInputStream()).deriveFont(n);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
