package me.redstoneguy129.mods.wasteddeath;

import com.google.common.eventbus.Subscribe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "wasteddeath")
public class WastedSounds {

    static ResourceLocation location = new ResourceLocation("wasteddeath", "sound");
    public static SoundEvent sound = new SoundEvent(location);

    @SubscribeEvent
    public void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().register(sound);
    }
}
