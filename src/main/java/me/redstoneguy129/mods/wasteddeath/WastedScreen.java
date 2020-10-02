package me.redstoneguy129.mods.wasteddeath;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.recipebook.FurnaceRecipeGui;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.EnchantmentScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.CraftingScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnchantmentNameParts;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.Util;
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class WastedScreen extends Screen {
    private final Random rand = new Random();
    private final String[] namePartsArray = new String[]{"Wasted", "Died", "Not Needed"};
    private static final ResourceLocation PRICEDOWN_FONT = new ResourceLocation("wasteddeath", "pricedown");
    private static final ResourceLocation WASTED_TEXTURE = new ResourceLocation("wasteddeath", "textures/gui/wasted.png");
    private static final Style PRICEDOWN_STYLE = Style.EMPTY.setFontId(PRICEDOWN_FONT);
    private final DeathScreen deathScreen;
    public int counter = 0;

    public WastedScreen(DeathScreen deathScreen) {
        super(new TranslationTextComponent("wasteddeath.screen"));
        this.deathScreen = deathScreen;
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.playSound(WastedDeath.clientGUI.sound, 10F, 1F);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        GL11.glEnable(GL11.GL_BLEND);
        assert this.minecraft != null;
        int xSize = 256;
        int x = (this.width - xSize) / 2;
        int ySize = 46;
        int y = (this.height - ySize) / 2;
        this.minecraft.getTextureManager().bindTexture(WASTED_TEXTURE);
        blit(matrixStack, x, y, 256, 256, xSize, ySize);
        matrixStack.push();
        String s = "WASTED";
        //matrixStack.scale(2F, 2F, 2F);
        this.font.func_238418_a_(getPricedown(this.font, s), x + 112, y + 25,86 - this.font.getStringWidth(s), 0xd40e00);
        matrixStack.pop();
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
