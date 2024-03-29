package com.stryphic.stryphiccore.network.handlers;

import com.stryphic.stryphiccore.network.messagestypes.MessageTileSync;
import com.stryphic.stryphiccore.tile.TileDeviceCore;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class MessageTileSyncHandler implements IMessageHandler<MessageTileSync, IMessage> {
    @Override
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(MessageTileSync message, MessageContext ctx) {
        World world = Minecraft.getMinecraft().world;
        BlockPos pos = message.getBlockPos();
        if(world != null) {
            TileEntity tileEntity = world.getTileEntity(pos);
            if (tileEntity instanceof TileDeviceCore) {
                TileDeviceCore tileDeviceCore = (TileDeviceCore) tileEntity;
                if(tileDeviceCore.getEnergyHandler() != null) {
                    tileDeviceCore.getEnergyHandler().setEnergyStorage(message.getEnergyStored());
                }

                tileDeviceCore.setProgress(message.getProgress());

            }
        }
        return null;
    }
}
