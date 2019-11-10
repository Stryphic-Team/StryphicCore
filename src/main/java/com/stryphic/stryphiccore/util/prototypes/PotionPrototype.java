package com.stryphic.stryphiccore.util.prototypes;

import com.stryphic.stryphiccore.util.CommonUtils;
import com.stryphic.stryphiccore.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PotionPrototype {
    private Potion potion;
    private PotionType potionType;
    private PotionEffect potionEffect;
    private int duration;

    /**
     * Takes a name, duration, isBad, color, IconIndexX, IconIndexY and creates a potion
     * and potion effect and registers the item
     * @param name
     * @param duration
     * @param isBadPotion
     * @param color
     * @param IconIndexX
     * @param IconIndexY
     */
    public PotionPrototype(String name, int duration, boolean isBadPotion, int color, int IconIndexX, int IconIndexY){
        String namespaced_name = CommonUtils.createUnlocalizedName(name);
        //creates the base of the potion
        potion = new PotionDrugBase(name,isBadPotion,color,IconIndexX,IconIndexY);
        //assigns a new potion effect to the the new potion
        potionEffect = new PotionEffect(potion,duration);

        potionType = new PotionType(name,new PotionEffect[] {
            potionEffect
        }).setRegistryName(namespaced_name);
        this.duration = duration;
    }

    /**
     * Registers the potion and the potion type for the new potion
     * NEEDS TO BE RUN AFTER INSTANTIATED
     */
    public void registerPotion(){
        ForgeRegistries.POTIONS.register(potion);
        ForgeRegistries.POTION_TYPES.register(potionType);
    }

    /**
     * Gets the duration of the potion effects
     * @return int
     */
    public int getDuration() {
        return duration;
    }

    /**
     * returns the potion type
     * @return PotionType
     */
    public PotionType getPotionType() {
        return potionType;
    }

    /**
     * returns the Potion
     * @return Potion
     */
    public Potion getPotion() {
        return potion;
    }

    /**
     * returns the potion effect
     * @return PotionEffect
     */
    public PotionEffect getPotionEffect() { return potionEffect; }
    public class PotionDrugBase extends Potion {
        public PotionDrugBase(String name, boolean isBadPotion, int color,int IconIndexX,int IconIndexY){
            super(isBadPotion,color);
            setPotionName("effect." + name); // THIS is what the lang file uses
            setIconIndex(IconIndexX,IconIndexY);
            setRegistryName(new ResourceLocation(Reference.RESOURCE_PREFIX + name));
        }

        @Override
        public boolean hasStatusIcon(){
            ResourceLocation loc = new ResourceLocation(Reference.RESOURCE_PREFIX + "textures/gui/potion_effects.png");
            Minecraft.getMinecraft().getTextureManager().bindTexture(loc);
            return true;
        }
    }
}
