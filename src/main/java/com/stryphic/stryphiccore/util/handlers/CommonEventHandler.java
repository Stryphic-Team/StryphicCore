package com.stryphic.stryphiccore.util.handlers;

import com.stryphic.stryphiccore.entityhandlers.LivingHandlerBase;
import com.stryphic.stryphiccore.entityhandlers.PlayerHandlerBase;
import com.stryphic.stryphiccore.entityhandlers.WorldHandlerBase;
import com.stryphic.stryphiccore.entityproperties.CorePlayerCapability;
import com.stryphic.stryphiccore.entityproperties.PlayerProperties;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;

public class CommonEventHandler {
    public static ArrayList<PlayerHandlerBase> PLAYER_HANDLERS = new ArrayList<PlayerHandlerBase>();
    public static ArrayList<LivingHandlerBase> LIVING_HANDLERS = new ArrayList<LivingHandlerBase>();
    public static ArrayList<WorldHandlerBase> WORLD_HANDLERS = new ArrayList<WorldHandlerBase>();





    @SubscribeEvent
    public void livingDamage(LivingDamageEvent event){
        //PlayerHandler.livingDamage(event,event.getEntityLiving());
        if(!event.getEntity().getEntityWorld().isRemote) {
            for (LivingHandlerBase livingHandler : LIVING_HANDLERS) {
                livingHandler.livingDamage(event.getEntityLiving(), event.getSource(), event.getAmount());
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void livingTimer(LivingEvent.LivingUpdateEvent event){
        //PlayerHandler.livingTick(event.getEntityLiving());
        if(!event.getEntity().getEntityWorld().isRemote) {
            for (LivingHandlerBase livingHandler : LIVING_HANDLERS) {
                livingHandler.livingTick(event.getEntityLiving());
            }
        }


    }

    @SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            if (event.getOriginal().hasCapability(CorePlayerCapability.PLAYER_PROPERTIES, null)) {
                PlayerProperties oldStore = event.getOriginal().getCapability(CorePlayerCapability.PLAYER_PROPERTIES, null);
                PlayerProperties newStore = CorePlayerCapability.getPlayerProperties(event.getEntityPlayer());
                newStore.copyFrom(oldStore);
            }
        }
    }
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void playerTimer(TickEvent.PlayerTickEvent event){
        // server side
        if(!event.player.getEntityWorld().isRemote) {
            if (event.phase == TickEvent.Phase.END) {
                for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
                    playerHandler.playerTick(event.player);
                }
            }
        // client side (ONLY USED BY THE HEART RATE MONITOR)
        }else{
            if (event.phase == TickEvent.Phase.END) {
                for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
                    playerHandler.clientPlayerTick(event.player);
                }
            }
        }
    }

    @SubscribeEvent (priority = EventPriority.LOW)
    public void blockBroken(BlockEvent.BreakEvent event){
        //PlayerHandler.playerBrokeBlock(event);
        if(!event.getPlayer().getEntityWorld().isRemote) {
            for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
                playerHandler.playerBreakBlock(event.getPlayer(), event.getState());

            }
        }
    }

    @SubscribeEvent
    public void leaveServer(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent event){
        //PlayerHandler.playerLeft(event.player);
        if(!event.player.getEntityWorld().isRemote) {
            for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
                playerHandler.playerLeave(event.player);
            }
        }
    }

    @SubscribeEvent
    public void respawn(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent event){
        //PlayerHandler.playerRespawn(event.player);
        if(!event.player.getEntityWorld().isRemote) {
            for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
                playerHandler.playerRespawn(event.player);
            }
        }
    }



    @SubscribeEvent(priority = EventPriority.LOW)
    public void smeltItem(net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent event){
        //PlayerHandler.playerSmelted(event, event.player);
        if(!event.player.getEntityWorld().isRemote) {
            for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
                playerHandler.playerSmeltItem(event.player, event.smelting);
            }
        }
    }
    @SubscribeEvent
    public void joinedServer(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event){
        //PlayerHandler.playerJoined(event.player);
        if(!event.player.getEntityWorld().isRemote) {


            PlayerProperties playerProperties = event.player.getCapability(CorePlayerCapability.PLAYER_PROPERTIES, null);
            if (playerProperties != null) {
                if (!playerProperties.hasBeenInitialized()) {

                    for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
                        playerHandler.playerPreInitialization(event.player);
                        playerHandler.playerInitialization(event.player);
                    }
                    playerProperties.setHasBeenInitialized(true);
                    playerProperties.saveNBTData(event.player.getEntityData());
                    for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
                        playerHandler.playerPostInitialization(event.player);
                    }
                }
            }
            for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
                playerHandler.playerJoined(event.player);
            }
        }
    }
    @SubscribeEvent
    public void livingDied(LivingDeathEvent event){
        if(!event.getEntity().getEntityWorld().isRemote) {
            for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
                if (event.getEntity() instanceof EntityPlayer) {
                    playerHandler.playerDied((EntityPlayer) event.getEntity());
                }
            }
            for (LivingHandlerBase livingHandler : LIVING_HANDLERS) {
                if (event.getEntity() instanceof EntityCreature) {
                    livingHandler.livingDied((EntityLivingBase) event.getEntity());
                }
            }
        }
    }
    @SubscribeEvent
    public void worldTick(TickEvent.WorldTickEvent event){
        if(event.phase == TickEvent.Phase.END && !event.world.isRemote){
            for(WorldHandlerBase worldHandler: WORLD_HANDLERS){
                worldHandler.worldTick(event.world);
            }
        }
    }
    @SubscribeEvent
    public void playerCaughtFish(ItemFishedEvent event){
        for(PlayerHandlerBase playerHandler: PLAYER_HANDLERS){
            playerHandler.playerCaughtFish(event.getEntityPlayer(),event.getDrops());
        }
    }
    @SubscribeEvent
    public void playerKilledMob(LivingDeathEvent event){
        if(event.getSource().getTrueSource() instanceof EntityPlayer){
            for(PlayerHandlerBase playerHandler: PLAYER_HANDLERS){
                playerHandler.playerKilledLiving((EntityPlayer)event.getSource().getTrueSource(),event.getEntity());
            }
        }
    }
}
