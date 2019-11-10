package com.stryphic.stryphiccore.client;

import com.stryphic.stryphiccore.util.CommonUtils;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;

public class ModItemMeshDefinition implements ItemMeshDefinition {
    private String name;
    public ModItemMeshDefinition(String name){
        this.name = name;
    }
    @Override
    public ModelResourceLocation getModelLocation(ItemStack stack) {
        return new ModelResourceLocation(CommonUtils.createUnlocalizedName(name), "fluid");
    }
}
