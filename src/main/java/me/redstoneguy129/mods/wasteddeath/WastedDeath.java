package me.redstoneguy129.mods.wasteddeath;

import me.redstoneguy129.mods.wasteddeath.network.Networking;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

import java.util.Random;

@Mod("wasteddeath")
public class WastedDeath {
    public static Random random = new Random();

    public WastedDeath() {
        ModLoadingContext.get().registerConfig(
                ModConfig.Type.SERVER,
                WastedConfig.SPEC
        );
        WastedConfig.init(FMLPaths.CONFIGDIR.get().resolve("wasteddeath" + "-server.toml"));
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            MinecraftForge.EVENT_BUS.register(new GUIHandler());
        });
    }

    private void setup(final FMLCommonSetupEvent event) {
        Networking.registerMessages();
    }


}
