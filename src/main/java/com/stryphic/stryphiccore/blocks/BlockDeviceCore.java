package com.stryphic.stryphiccore.blocks;

import com.stryphic.stryphiccore.tile.TileDeviceCore;
import com.stryphic.stryphiccore.tile.utils.handlers.ModItemHandler;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class BlockDeviceCore extends BlockCore implements ITileEntityProvider {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool ACTIVE = PropertyBool.create("active");
    private TileDeviceCore tileDeviceCore;

    public BlockDeviceCore(String name){
        super(name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, false));
        setHardness(15);
        setHarvestLevel("pickaxe",0);
    }
    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }
    @Nullable
    @Override
    public abstract TileEntity createNewTileEntity(World worldIn, int meta);
    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        this.setDefaultFacing(worldIn, pos, state);
    }
    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {ACTIVE,FACING});
    }
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing facing = EnumFacing.getFront(meta);
        if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
        return this.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }
    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }
    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }
    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
    public static void setState(boolean active,IBlockState currentState, World worldIn, BlockPos pos)
    {
        IBlockState defaultState = worldIn.getBlockState(pos).getBlock().getDefaultState();
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (active)
        {
            worldIn.setBlockState(pos, defaultState.withProperty(FACING, currentState.getValue(FACING)).withProperty(ACTIVE,true), 1);
            //worldIn.notifyBlockUpdate(pos,currentState,defaultState.withProperty(FACING,currentState.getValue(FACING)).withProperty(ACTIVE,true),3);
        }
        else
        {
            worldIn.setBlockState(pos, defaultState.withProperty(FACING, currentState.getValue(FACING)).withProperty(ACTIVE,false), 1);
            //worldIn.notifyBlockUpdate(pos,currentState,defaultState.withProperty(FACING,currentState.getValue(FACING)).withProperty(ACTIVE,false),3);
        }



        if (tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }


    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if(tileEntity instanceof TileDeviceCore){
            this.tileDeviceCore = (TileDeviceCore)tileEntity;
        }
        super.breakBlock(worldIn, pos, state);
    }
    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        ModItemHandler itemStackHandler = tileDeviceCore.getItemStackHadler();
        if(itemStackHandler != null){
            int slotCount = itemStackHandler.getSlots();
            for(int i = 0;i<slotCount;i++){
                drops.add(itemStackHandler.getStackInSlot(i));
            }
        }
        super.getDrops(drops,world,pos,state,fortune);
    }

    @Override
    public boolean canDropFromExplosion(Explosion explosionIn) {
        return false;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(worldIn, pos, state, rand);
    }
}
