package me.redstoneguy129.mods.wasteddeath.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class Networking {

    public static SimpleChannel INSTANCE;
    private static int id = 0;

    public static void registerMessages() {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation("wasteddeath", "networking"), () -> "1.0", s -> true, s -> true);
        INSTANCE.registerMessage(id++, SendClientTextMessage.class, SendClientTextMessage::toBytes, SendClientTextMessage::new, SendClientTextMessage::handle);
        INSTANCE.registerMessage(id++, GetServerTextMessage.class, GetServerTextMessage::toBytes, GetServerTextMessage::new, GetServerTextMessage::handle);
    }

}
