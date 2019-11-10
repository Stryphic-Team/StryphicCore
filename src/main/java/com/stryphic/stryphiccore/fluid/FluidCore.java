package com.stryphic.stryphiccore.fluid;

import com.stryphic.stryphiccore.util.CommonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidCore extends Fluid {
    public FluidCore(String name, ResourceLocation still, ResourceLocation flow)
    {
        super(CommonUtils.createUnlocalizedName(name), still, flow);
        this.setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
    }
}
