package com.stryphic.stryphiccore.items;

import com.stryphic.stryphiccore.util.IHasModel;
import com.stryphic.stryphiccore.util.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import scala.util.Random;

public abstract class ItemFoodCore extends ItemFood implements IHasModel {
    protected Random random = new Random();
    public ItemFoodCore(String name, int amount, float saturation, boolean isWolfFood) {
        this(name, Reference.MODID,Reference.STRYPHIC_CORE_TAB,amount, saturation, isWolfFood);
    }

    public ItemFoodCore(String name, String namespace, CreativeTabs creativeTab, int amount, float saturation, boolean isWolfFood){
        super(amount,saturation,isWolfFood);
        setRegistryName(name);
        setUnlocalizedName(namespace + ":" + name);
        this.setCreativeTab(creativeTab);
    }
    public abstract void registerModels();

    public void CureEffect(EntityLivingBase entityLiving, Potion potion){

        String potionname = potion.getName();
        PotionEffect therighteffect = null;
        for (PotionEffect effect:entityLiving.getActivePotionEffects()){
            if (effect.getEffectName().equals(potionname)) {
                therighteffect = effect;
            }
        }
        if (therighteffect != null){ entityLiving.removePotionEffect(therighteffect.getPotion()); }
    }
}
