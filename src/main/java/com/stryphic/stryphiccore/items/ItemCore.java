package com.stryphic.stryphiccore.items;

import com.stryphic.stryphiccore.StryphicCore;
import com.stryphic.stryphiccore.init.CoreItems;
import com.stryphic.stryphiccore.util.CommonUtils;
import com.stryphic.stryphiccore.util.IHasModel;
import com.stryphic.stryphiccore.util.Reference;
import net.minecraft.item.Item;

public abstract class ItemCore extends Item implements IHasModel {

    public ItemCore(String name){
        this(name,true);
    }
    public ItemCore(String name, boolean addToCreativeTab){
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        if(addToCreativeTab) {
            this.setCreativeTab(Reference.STRYPHIC_CORE_TAB);
        }
        CoreItems.ITEMS.add(this);
    }
    @Override
    public void registerModels()
    {
        StryphicCore.proxy.registerModel(this,0);

    }
}
