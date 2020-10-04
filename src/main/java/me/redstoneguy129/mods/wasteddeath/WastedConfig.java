package me.redstoneguy129.mods.wasteddeath;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class WastedConfig {

    public static void init(Path file)
    {
        final CommentedFileConfig configData = CommentedFileConfig.builder(file)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        SPEC.setConfig(configData);
    }

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    /*
     * SERVER
     */
    public static final String CATEGORY_SERVER = "server";
    private static final String CATEGORY_CATEGORY_SERVER_COMMENT =
            "These config settings are server-side only";

    public static final ForgeConfigSpec.ConfigValue<String> UNMADE_LIST_STRINGS;
    public static String UNMADE_LIST_STRINGS_DEFAULT = "WASTED,DIED,OOF";
    private static final String UNMADE_LIST_STRINGS_NAME = "List of possible death texts";
    private static final String UNMADE_LIST_STRINGS_COMMENT =
            "Split with ','. For example: WASTED,DIED,OOF";

    static
    {
        BUILDER.push(CATEGORY_SERVER).comment(CATEGORY_CATEGORY_SERVER_COMMENT);
        UNMADE_LIST_STRINGS = BUILDER
                .comment(UNMADE_LIST_STRINGS_COMMENT)
                .define(UNMADE_LIST_STRINGS_NAME, UNMADE_LIST_STRINGS_DEFAULT);
        BUILDER.pop();
    }

    public static final ForgeConfigSpec SPEC = BUILDER.build();
}
