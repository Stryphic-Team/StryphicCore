package com.stryphic.stryphiccore.util.prototypes;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;


public class SoundEventPrototype {
    private final ResourceLocation resourceLocation;
    private final SoundEvent soundEvent;

    public SoundEventPrototype(ResourceLocation resourceLocation, SoundEvent soundEvent){

        this.resourceLocation = resourceLocation;
        this.soundEvent = soundEvent;
    }

    public ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    public SoundEvent getSoundEvent() {
        return soundEvent;
    }
}
