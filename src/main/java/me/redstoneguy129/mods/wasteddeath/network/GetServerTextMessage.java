package me.redstoneguy129.mods.wasteddeath.network;

import me.redstoneguy129.mods.wasteddeath.WastedConfig;
import me.redstoneguy129.mods.wasteddeath.WastedDeath;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class GetServerTextMessage {

    public GetServerTextMessage() {}

    public GetServerTextMessage(PacketBuffer buffer) {}

    public void toBytes(PacketBuffer buffer) {}

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();
            if (player != null) {
                System.out.println(WastedConfig.UNMADE_LIST_STRINGS.get());
                String[] list = WastedConfig.UNMADE_LIST_STRINGS.get().split(",");
                for(String l : list) {
                    System.out.println(l);
                }
                System.out.println(list[WastedDeath.random.nextInt(list.length)]);
                Networking.INSTANCE.sendTo(new SendClientTextMessage(player.getEntityId(), list[WastedDeath.random.nextInt(list.length)]), player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
            }
        });
        ctx.get().setPacketHandled(true);
    }

}
