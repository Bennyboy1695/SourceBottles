package io.github.bennyboy1695.sourcebottles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemGlassBottle;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = SourceBottles.MOD_ID, name = SourceBottles.MOD_NAME, version = SourceBottles.VERSION, acceptableRemoteVersions = "*")
public class SourceBottles {

    public static final String MOD_ID = "sourcebottles";
    public static final String MOD_NAME = "SourceBottles";
    public static final String VERSION = "1.0.0";

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static SourceBottles INSTANCE;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }


    @SubscribeEvent
    public void onBottleUse(PlayerInteractEvent.RightClickBlock event) {
        if (event.getItemStack() != null && event.getItemStack().getItem() instanceof ItemGlassBottle) {

            EntityPlayer player = event.getEntityPlayer();
            BlockPos pos = new BlockPos(event.getHitVec());
             if (event.getWorld().getBlockState(pos).getBlock() == Blocks.WATER) {
                 try {
                     event.getWorld().playSound(player, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0f, 1.0f);
                     player.getHeldItem(event.getHand()).shrink(1);
                     player.inventory.addItemStackToInventory(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.WATER));
                     event.getWorld().setBlockToAir(pos);
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
        }

    }

}
