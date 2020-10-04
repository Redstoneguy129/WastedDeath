package me.redstoneguy129.mods.wasteddeath;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.redstoneguy129.mods.wasteddeath.network.GetServerTextMessage;
import me.redstoneguy129.mods.wasteddeath.network.Networking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkDirection;
import org.lwjgl.opengl.GL11;

public class WastedScreen extends Screen {
    public static String randText;
    private static final ResourceLocation PRICEDOWN_FONT = new ResourceLocation("wasteddeath", "pricedown");
    private static final ResourceLocation WASTED_TEXTURE = new ResourceLocation("wasteddeath", "textures/gui/wasted.png");
    private static final Style PRICEDOWN_STYLE = Style.EMPTY.setFontId(PRICEDOWN_FONT);
    private final DeathScreen deathScreen;
    public int counter = 0;

    public WastedScreen(DeathScreen deathScreen) {
        super(new TranslationTextComponent("wasteddeath.screen"));
        this.deathScreen = deathScreen;
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.playSound(WastedSounds.sound, 10F, 1F);
        Networking.INSTANCE.sendTo(new GetServerTextMessage(), Minecraft.getInstance().player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_SERVER);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        GL11.glEnable(GL11.GL_BLEND);
        assert this.minecraft != null;
        int xSize = 128;
        int x = (this.width - xSize) / 2;
        int ySize = 23;
        int y = (this.height - ySize) / 2;
        this.minecraft.getTextureManager().bindTexture(WASTED_TEXTURE);
        blit(matrixStack, x, y, 256, 256, xSize, ySize);
        if(randText != null) this.font.func_238418_a_(getPricedown(this.font, randText), x+64-(this.font.getStringWidth(randText)/2), y+7,86 - this.font.getStringWidth(randText), 0xd40e00);
        if(counter == 100) {
            this.minecraft.displayGuiScreen(deathScreen);
        }
        counter++;
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        GL11.glDisable(GL11.GL_BLEND);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private ITextProperties getPricedown(FontRenderer fontRenderer, String string) {
        int maxWidth = 86 - this.font.getStringWidth(string);
        return fontRenderer.func_238420_b_().func_238358_a_((new StringTextComponent(string)).mergeStyle(PRICEDOWN_STYLE), maxWidth, Style.EMPTY);
    }

}
