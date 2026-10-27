package com.example.squeegee;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class SqueegeeConfig {
    public static final List<String> ALIASES = List.of("clearchat", "cc", "cls", "wipe");

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Logger LOGGER = LoggerFactory.getLogger("squeegee");
    private static volatile SqueegeeConfig current = new SqueegeeConfig();
    private static Path configDirectory;

    /** Command aliases are enabled by default, including aliases added after the file was created. */
    public Map<String, Boolean> commands = new LinkedHashMap<>();
    public Permissions permissions = new Permissions();
    /** Blank system messages sent to each player to push old chat out of view. */
    public int blankLines = 120;
    public boolean announce = true;
    public String announceColor = "red";
    public String announceFormat = "{player} has cleared the chat";
    public String announceReasonFormat = "{player} has cleared the chat reason: {reason}";

    public static final class Permissions {
        public List<String> allowedTags = new ArrayList<>(List.of("admin"));
        public boolean allowOps = true;
        public int minOpLevel = 2;
        public boolean singleplayerHostAlwaysAllowed = true;
        public boolean allowTaggedPlayersToReload = false;
    }

    public SqueegeeConfig() {
        for (String alias : ALIASES) {
            commands.put(alias, true);
        }
    }

    public static SqueegeeConfig get() {
        return current;
    }

    public boolean isEnabled(String alias) {
        Boolean enabled = commands == null ? null : commands.get(alias);
        return enabled == null || enabled;
    }

    public static void load(Path configDir) {
        configDirectory = configDir;
        Path file = configDir.resolve("squeegee.json");

        if (!Files.exists(file)) {
            SqueegeeConfig defaults = new SqueegeeConfig();
            current = defaults;
            save(file, defaults);
            return;
        }

        try (Reader reader = Files.newBufferedReader(file)) {
            JsonElement document = JsonParser.parseReader(reader);
            if (!document.isJsonObject()) {
                LOGGER.error("[Squeegee] Could not read {}; keeping previous settings", file);
                return;
            }
            JsonObject object = document.getAsJsonObject();
            removeNullFields(object);
            boolean addedDefaults = addMissingConfigFields(object);
            SqueegeeConfig loaded = GSON.fromJson(document, SqueegeeConfig.class);
            loaded.fillMissingFields();
            current = loaded;
            if (addedDefaults) {
                saveJson(file, object);
            }
        } catch (IOException | RuntimeException e) {
            // Keep the previous settings and leave the broken file untouched so nothing is lost.
            LOGGER.error("[Squeegee] Could not read {}; keeping previous settings", file, e);
        }
    }

    public static void reload() {
        if (configDirectory != null) {
            load(configDirectory);
        }
    }

    private void fillMissingFields() {
        if (commands == null) {
            commands = new LinkedHashMap<>();
        }
        for (String alias : ALIASES) {
            commands.putIfAbsent(alias, true);
        }
        if (permissions == null) {
            permissions = new Permissions();
        }
        if (permissions.allowedTags == null) {
            permissions.allowedTags = new ArrayList<>();
        }
        if (announceColor == null) {
            announceColor = "red";
        }
        if (announceFormat == null) {
            announceFormat = "{player} has cleared the chat";
        }
        if (announceReasonFormat == null) {
            announceReasonFormat = "{player} has cleared the chat reason: {reason}";
        }
    }

    private static void removeNullFields(JsonObject object) {
        List<String> nullFields = new ArrayList<>();
        for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
            if (entry.getValue().isJsonNull()) {
                nullFields.add(entry.getKey());
            } else if (entry.getValue().isJsonObject()) {
                removeNullFields(entry.getValue().getAsJsonObject());
            }
        }
        nullFields.forEach(object::remove);
    }

    private static boolean addMissingConfigFields(JsonObject object) {
        JsonObject permissions;
        if (!object.has("permissions")) {
            permissions = new JsonObject();
            object.add("permissions", permissions);
        } else if (object.get("permissions").isJsonObject()) {
            permissions = object.getAsJsonObject("permissions");
        } else {
            return false;
        }

        if (permissions.has("allowTaggedPlayersToReload")) {
            return false;
        }
        permissions.addProperty("allowTaggedPlayersToReload", false);
        return true;
    }

    private static void save(Path file, SqueegeeConfig config) {
        try {
            Files.createDirectories(file.getParent());
            try (Writer writer = Files.newBufferedWriter(file)) {
                GSON.toJson(config, writer);
            }
        } catch (IOException e) {
            LOGGER.error("[Squeegee] Could not write {}", file, e);
        }
    }

    private static void saveJson(Path file, JsonObject document) {
        try {
            Files.createDirectories(file.getParent());
            try (Writer writer = Files.newBufferedWriter(file)) {
                GSON.toJson(document, writer);
            }
        } catch (IOException e) {
            LOGGER.error("[Squeegee] Could not write {}", file, e);
        }
    }
}
