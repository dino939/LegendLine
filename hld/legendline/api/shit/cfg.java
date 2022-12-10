package ru.hld.legendline.api.shit;

import net.minecraft.client.*;
import ru.hld.legendline.*;
import ru.hld.legendline.api.module.*;
import ru.hld.legendline.api.setting.*;
import ru.hld.legendline.api.setting.settings.*;
import com.google.gson.*;
import java.io.*;
import java.util.*;

public class cfg
{
    public static final File CONFIG_FOLDER;
    public static final File CLIENT_FOLDER;
    public static File file;
    
    static {
        CLIENT_FOLDER = new File(Minecraft.getMinecraft().mcDataDir, "AbobaCode");
        CONFIG_FOLDER = new File(cfg.CLIENT_FOLDER, "config");
    }
    
    public static void init() {
        cfg.CLIENT_FOLDER.mkdir();
        cfg.CONFIG_FOLDER.mkdir();
    }
    
    public static void save() {
        final JsonObject jsonObject = new JsonObject();
        final JsonArray jsonArray = new JsonArray();
        for (final Module module : Client.moduleManager.modules) {
            final JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("name", module.getName());
            jsonObject2.addProperty("enabled", Boolean.valueOf(module.isEnabled()));
            jsonObject2.addProperty("keybind", (Number)module.getKey());
            jsonObject2.addProperty("PosX", (Number)module.getPosX());
            jsonObject2.addProperty("PosY", (Number)module.getPosY());
            final JsonArray jsonArray2 = new JsonArray();
            for (final Setting setting : Client.settingManager.getSettings(module)) {
                final JsonObject jsonObject3 = new JsonObject();
                if (setting instanceof BooleanSetting) {
                    jsonObject3.addProperty(setting.getName(), Boolean.valueOf(((BooleanSetting)setting).getVal()));
                }
                else if (setting instanceof ModeSetting) {
                    jsonObject3.addProperty(setting.getName(), ((ModeSetting)setting).getVal());
                }
                else if (setting instanceof FloatSetting) {
                    jsonObject3.addProperty(setting.getName(), (Number)((FloatSetting)setting).getVal());
                }
                jsonArray2.add((JsonElement)jsonObject3);
            }
            jsonObject2.add("settings", (JsonElement)jsonArray2);
            jsonArray.add((JsonElement)jsonObject2);
        }
        jsonObject.add("modules", (JsonElement)jsonArray);
        try {
            final FileWriter fileWriter = new FileWriter(new File(cfg.CONFIG_FOLDER, "default.json"));
            fileWriter.write(new GsonBuilder().setPrettyPrinting().create().toJson((JsonElement)jsonObject));
            fileWriter.flush();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void load() {
        final JsonParser jsonParser = new JsonParser();
        try {
            final JsonObject asJsonObject = jsonParser.parse((Reader)new FileReader(new File(cfg.CONFIG_FOLDER, "default.json"))).getAsJsonObject();
            asJsonObject.get("auto save");
            for (final JsonElement jsonElement : asJsonObject.getAsJsonArray("modules")) {
                if (!(jsonElement instanceof JsonObject)) {
                    continue;
                }
                final Set entrySet = ((JsonObject)jsonElement).entrySet();
                for (final Module module : Client.moduleManager.modules) {
                    if (!contains(entrySet, "name", (JsonElement)new JsonPrimitive(module.getName()))) {
                        continue;
                    }
                    for (final Map.Entry<String, V> entry : entrySet) {
                        final String s = entry.getKey();
                        final JsonElement jsonElement2 = (JsonElement)entry.getValue();
                        try {
                            if (s.equals("enabled")) {
                                module.setEnabled(jsonElement2.getAsBoolean());
                                continue;
                            }
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        if (s.equals("keybind")) {
                            module.setKey(jsonElement2.getAsInt());
                        }
                        else if (s.equals("PosX")) {
                            module.setPosX(jsonElement2.getAsInt());
                        }
                        else if (s.equals("PosY")) {
                            module.setPosY(jsonElement2.getAsInt());
                        }
                        else {
                            if (!s.equals("settings")) {
                                continue;
                            }
                            for (final JsonElement jsonElement3 : jsonElement2.getAsJsonArray()) {
                                if (!(jsonElement3 instanceof JsonObject)) {
                                    continue;
                                }
                                final Set entrySet2 = ((JsonObject)jsonElement3).entrySet();
                                for (final Setting setting : Client.settingManager.getSettings(module)) {
                                    for (final Map.Entry<String, V> entry2 : entrySet2) {
                                        final String s2 = entry2.getKey();
                                        final JsonElement jsonElement4 = (JsonElement)entry2.getValue();
                                        if (setting instanceof BooleanSetting) {
                                            if (!s2.equals(setting.getName())) {
                                                continue;
                                            }
                                            ((BooleanSetting)setting).setVal(jsonElement4.getAsBoolean());
                                        }
                                        else if (setting instanceof ModeSetting) {
                                            if (!s2.equals(setting.getName())) {
                                                continue;
                                            }
                                            ((ModeSetting)setting).setVal(jsonElement4.getAsString());
                                        }
                                        else {
                                            if (!(setting instanceof FloatSetting) || !s2.equals(setting.getName())) {
                                                continue;
                                            }
                                            ((FloatSetting)setting).setVal(jsonElement4.getAsFloat());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (FileNotFoundException ex2) {
            ex2.printStackTrace();
        }
    }
    
    private static boolean contains(final Set set, final String s, final JsonElement jsonElement) {
        for (final Map.Entry<String, V> entry : set) {
            if (entry.getKey().equals(s) && ((JsonElement)entry.getValue()).equals(jsonElement)) {
                return true;
            }
        }
        return false;
    }
}
