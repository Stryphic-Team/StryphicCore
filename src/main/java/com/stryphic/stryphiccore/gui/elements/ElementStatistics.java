package com.stryphic.stryphiccore.gui.elements;


import com.stryphic.stryphiccore.gui.DeviceContainerGuiBase;
import com.stryphic.stryphiccore.tile.TileDeviceCore;
import com.stryphic.stryphiccore.util.Reference;
import com.stryphic.stryphiccore.util.helpers.FormatHelper;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class ElementStatistics extends ElementBase {
    public ElementStatistics(DeviceContainerGuiBase gui, int startX, int startY) {
        super(gui, startX, startY);
        this.resourceLocation = new ResourceLocation(Reference.MODID, "textures/gui/elements/statistics.png");
        this.textureWidth = 80;
        this.textureHeight = 44;
    }

    @Override
    public void drawElement() {
        gui.bindTexture(resourceLocation);
        drawTexturedModalRect(gui.getGuiLeft() + startX, gui.getGuiTop() + startY, 0, 0, 80, 44);


    }

    @Override
    public void drawForeground() {
        TileDeviceCore tileDeviceCore = gui.getTileEntity();
        ArrayList<String> stringList = new ArrayList<String>();

        if(tileDeviceCore.getEnergyHandler() != null){
            stringList.add("RF: "+ FormatHelper.formatNumber(tileDeviceCore.getEnergyHandler().getEnergyStored()));
        }
        if(tileDeviceCore.getFluidHandler() != null){
            stringList.add("Fluid: "+ FormatHelper.formatNumber(tileDeviceCore.getFluidHandler().getFluidTank().getFluidAmount()));
        }
        String temperature = String.valueOf(tileDeviceCore.getThermalHandler().getCurrentTemperature());
        stringList.add("Temp: "+ temperature.substring(0,Math.min(temperature.length(),5)));
        for(int i = 0;i<stringList.size();i++){
            gui.drawText(startX + 4,startY+(i*8)+5,stringList.get(i));
        }

    }
}
