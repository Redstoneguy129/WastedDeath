package me.redstoneguy129.mods.wasteddeath;

import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GUIHandler {

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
