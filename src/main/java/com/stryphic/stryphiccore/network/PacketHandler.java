package com.stryphic.stryphiccore.network;

import com.stryphic.stryphiccore.StryphicCore;
import com.stryphic.stryphiccore.gui.GuiSync;
import com.stryphic.stryphiccore.network.handlers.MessageTileSyncHandler;
import com.stryphic.stryphiccore.network.messagestypes.MessageSyncMachineGui;
import com.stryphic.stryphiccore.network.messagestypes.MessageTileSync;
import com.stryphic.stryphiccore.util.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;



public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);

    public static void init() {
        StryphicCore.logger.info("Registering network messages");


        INSTANCE.registerMessage(GuiSync.class, MessageSyncMachineGui.class, 3, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileSyncHandler.class, MessageTileSync.class, 6, Side.CLIENT);


    }
}
