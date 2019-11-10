package com.stryphic.stryphiccore.fluid;

import com.stryphic.stryphiccore.util.CommonUtils;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidCore extends BlockFluidClassic {
    public BlockFluidCore(String name, Fluid fluid, Material material)
    {
        super(fluid, material);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        setRegistryName(name);
        //ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }


}
