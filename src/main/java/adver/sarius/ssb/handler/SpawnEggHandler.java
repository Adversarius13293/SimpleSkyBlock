package adver.sarius.ssb.handler;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DimensionType;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import adver.sarius.ssb.SimpleSkyBlockMod;
import adver.sarius.ssb.gen.WorldTypeSSB;

public class SpawnEggHandler {
	
	// called twice. Need to cancel both, but message only once
	@SubscribeEvent
	public void onSpawnerRightClick(PlayerInteractEvent.RightClickBlock event){
		if(event.getWorld().provider.getDimensionType() == DimensionType.NETHER
				&& event.getWorld().getWorldType() instanceof WorldTypeSSB){
			if(event.getWorld().getBlockState(event.getPos()).getBlock() == Blocks.MOB_SPAWNER){
				if(event.getItemStack() != null && event.getItemStack().getItem() == Items.SPAWN_EGG){
					event.setUseItem(Result.DENY);
					if(!event.getWorld().isRemote){
						event.getEntityPlayer().sendMessage(new TextComponentString("[" + SimpleSkyBlockMod.NAME + "] You are not allowed to change spawners in the Nether."));
					}
				}
			}
		}
	}
}