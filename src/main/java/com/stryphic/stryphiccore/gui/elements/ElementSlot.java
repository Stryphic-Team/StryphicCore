package com.stryphic.stryphiccore.gui.elements;

import com.stryphic.stryphiccore.gui.DeviceContainerGuiBase;
import com.stryphic.stryphiccore.util.Reference;
import net.minecraft.util.ResourceLocation;


public class ElementSlot extends ElementBase {
    public ElementSlot(DeviceContainerGuiBase gui, int startX, int startY) {
        super(gui, startX, startY);
        resourceLocation = new ResourceLocation(Reference.MODID,"textures/gui/elements/input_slot.png");
        textureWidth = 18;
        textureHeight = 18;
    }

    @Override
    public void drawElement() {
        gui.bindTexture(resourceLocation);
        drawTexturedModalRect(gui.getGuiLeft() + startX - 1,gui.getGuiTop() + startY - 1,0,0,textureWidth,textureHeight);
    }

    @Override
    public void drawForeground() {

    }
}
