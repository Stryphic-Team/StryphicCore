package com.stryphic.stryphiccore.util.prototypes;

import com.stryphic.stryphiccore.client.ModStateMapper;
import com.stryphic.stryphiccore.fluid.BlockFluidCore;
import com.stryphic.stryphiccore.fluid.FluidCore;
import com.stryphic.stryphiccore.init.CoreBlocks;
import com.stryphic.stryphiccore.init.CoreMaterials;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

import static com.stryphic.stryphiccore.util.Reference.MODID;

/**
 * This helps with fluid registration
 */
public class FluidPrototype {
    private ResourceLocation fluidStill;
    private ResourceLocation fluidFlow;
    private Fluid fluid;
    private BlockFluidCore blockFluidCore;
    private String name;
    private Material material;

    /**
     * Creates a new fluid with the name provided
     * @param name
     */
    public FluidPrototype(String name){
        this(name,null,null,null,null);
    }

    /**
     * Creates a fluid with the name provided and applies a material and a FluidBase
     * @param name
     * @param material
     * @param fluidBase
     */
    public FluidPrototype(String name, Material material, FluidCore fluidBase){
        this(name,fluidBase,null,null,material);
    }

    /**
     * Creates a fluid with the name provided and applies a FluidBase
     * @param name
     * @param fluidBase
     */
    public FluidPrototype(String name, FluidCore fluidBase){
        this(name,fluidBase,null,null,null);
    }

    /**
     * Creates a fluid with the name provided and applies Material
     * @param name
     * @param material
     */
    public FluidPrototype(String name, Material material){
        this(name,null,null,null,material);
    }

    /**
     * Base function not intended to be used externally
     * @param name
     * @param fluid
     * @param fluidStill
     * @param fluidFlow
     * @param material
     */
    public FluidPrototype(String name, @Nullable FluidCore fluid, @Nullable ResourceLocation fluidStill, @Nullable ResourceLocation fluidFlow, @Nullable Material material){
        this.name = name;
        this.fluidStill = fluidStill != null ? fluidStill : new ResourceLocation(MODID,"fluids/"+name+"_still");
        this.fluidFlow = fluidFlow != null ? fluidFlow : new ResourceLocation(MODID,"fluids/"+name+"_flow");
        this.fluid = fluid != null ? fluid : new FluidCore(this.name, this.fluidStill, this.fluidFlow);
        this.material = material != null ? material : CoreMaterials.MODLIQUID;
    }

    /**
     * Is to be run after registerBlock
     */
    @SideOnly(Side.CLIENT)
    public void registerRender(){
        //ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(getBlockFluidBase()),new ModItemMeshDefinition(getName()));
        ModelLoader.setCustomStateMapper(getBlockFluidCore(), new ModStateMapper(getName()));
    }

    /**
     * Needs to be run after register fluid
     */
    public void registerBlock(){
        CoreBlocks.BLOCKS.add(blockFluidCore);
    }

    /**
     * needs to be run first
     */
    public void registerFluid(){
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
        blockFluidCore = new BlockFluidCore(name,fluid, material);
    }

    /**
     * Returns the fluidStill
     * @return ResourceLocation
     */
    public ResourceLocation getFluidStill() {
        return fluidStill;
    }

    /**
     * Returns the fluidFlow
     * @return ResourceLocation
     */
    public ResourceLocation getFluidFlow() {
        return fluidFlow;
    }

    /**
     * Returns the fluid
     * @return Fluid
     */
    public Fluid getFluid() {
        return fluid;
    }
    /**
     * Returns the blockFluidBase
     * @return BlockFluidBase
     */
    public BlockFluidCore getBlockFluidCore() {
        return blockFluidCore;
    }


    /**
     * returns the name
     * @return String
     */
    public String getName() {
        return name;
    }
    /**
     * returns the material
     * @return Material
     */
    public Material getMaterial() {
        return material;
    }
}
