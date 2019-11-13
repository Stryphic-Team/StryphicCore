package com.stryphic.stryphiccore.gui.elements;

import com.stryphic.stryphiccore.gui.DeviceContainerGuiBase;
import com.stryphic.stryphiccore.util.Reference;
import net.minecraft.util.ResourceLocation;


public class ElementOutputSlot extends ElementBase{
    public ElementOutputSlot(DeviceContainerGuiBase gui, int startX, int startY) {
        super(gui, startX, startY);
        resourceLocation = new ResourceLocation(Reference.MODID,"textures/gui/elements/output_slot.png");
        textureWidth = 26;
        textureHeight = 26;
    }

    @Override
    public void drawElement() {
        gui.bindTexture(resourceLocation);
        drawTexturedModalRect(gui.getGuiLeft()+startX-5,gui.getGuiTop()+startY-5,0,0,textureWidth,textureHeight);
    }

    @Override
    public void drawForeground() {

    }
}
