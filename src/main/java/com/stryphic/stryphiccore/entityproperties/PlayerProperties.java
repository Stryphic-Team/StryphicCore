package com.stryphic.stryphiccore.entityproperties;

import net.minecraft.nbt.NBTTagCompound;

public class PlayerProperties {
    private boolean hasBeenInitialized = false;
    private boolean hasSoul = false;
    public PlayerProperties(){

    }
    public boolean hasBeenInitialized() {
        return hasBeenInitialized;
    }

    public void setHasBeenInitialized(boolean hasBeenInitialized) {
        this.hasBeenInitialized = hasBeenInitialized;
    }

    public boolean hasSoul() {
        return hasSoul;
    }

    public void setHasSoul(boolean hasSoul) {
        this.hasSoul = hasSoul;
    }

    public void copyFrom(PlayerProperties oldPlayerProperties){
        this.hasBeenInitialized = oldPlayerProperties.hasBeenInitialized();
        this.hasSoul = oldPlayerProperties.hasSoul();
    }
    public void saveNBTData(NBTTagCompound compound){
        compound.setBoolean("hasBeenInitialized",hasBeenInitialized);
        compound.setBoolean("hasSoul",hasSoul);
    }
    public void loadNBTData(NBTTagCompound compound){
        this.hasBeenInitialized = compound.getBoolean("hasBeenInitialized");
        this.hasSoul = compound.getBoolean("hasSoul");

    }
}
