package com.stryphic.stryphiccore.entityhandlers;

import com.stryphic.stryphiccore.util.RandomUtils;
import com.stryphic.stryphiccore.util.handlers.CommonEventHandler;
import net.minecraft.world.World;
import scala.util.Random;

public abstract class WorldHandlerBase {
    protected Random random = RandomUtils.random;
    public WorldHandlerBase(){
        CommonEventHandler.WORLD_HANDLERS.add(this);
    }
    public void worldTick(World world){}
}
