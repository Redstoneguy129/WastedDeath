package me.redstoneguy129.mods.wasteddeath.network;

import me.redstoneguy129.mods.wasteddeath.WastedScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SendClientTextMessage {

    private int playerID;
    private String text;

    public SendClientTextMessage(int playerID, String text) {
        this.playerID = playerID;
        this.text = text;
    }

    public SendClientTextMessage(PacketBuffer buffer) {
        this.playerID = buffer.readInt();
        this.text = buffer.readString();
    }

    public void toBytes(PacketBuffer buffer) {
        buffer.writeInt(this.playerID);
        buffer.writeString(this.text);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if(playerID == Minecraft.getInstance().player.getEntityId()) {
                WastedScreen.randText = text;
            }
        });
        ctx.get().setPacketHandled(true);
    }

}
