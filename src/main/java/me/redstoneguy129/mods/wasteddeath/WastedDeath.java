package me.redstoneguy129.mods.wasteddeath;

import net.java.games.input.Keyboard;
import net.minecraft.client.KeyboardListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.CraftingScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.sql.Timestamp;

@Mod("wasteddeath")
public class WastedDeath {
    public WastedDeath() {
        MinecraftForge.EVENT_BUS.register(new clientGUI());
    }

    @OnlyIn(Dist.CLIENT)
    @Mod.EventBusSubscriber(modid = "wasteddeath")
    static
    class clientGUI {

        static ResourceLocation location = new ResourceLocation("wasteddeath", "sound");
        public static SoundEvent sound = new SoundEvent(location);

        @SubscribeEvent
        public void registerSounds(RegistryEvent.Register<SoundEvent> event) {
            event.getRegistry().register(sound);
        }

        private WastedScreen screen;

        @SubscribeEvent
        public void onDeathGUI(GuiOpenEvent event) {
            if(event.getGui() instanceof DeathScreen) {
                if(screen == null) {
                    screen = new WastedScreen((DeathScreen) event.getGui());
                    event.setGui(screen);
                } else {
                    if(screen.counter > 100) {
                        screen = null;
                    }
                }
            }
        }
    }

}
