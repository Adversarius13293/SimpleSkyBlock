package adver.sarius.ssb.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HarvestDropsHandler {

	// only called once by server
	@SubscribeEvent
	public void onHarvestDrops(HarvestDropsEvent event){
		// only triggers on not remote
		if(event.getState().getBlock() == Blocks.GRAVEL && !event.isSilkTouching()){
			EntityPlayer player = event.getHarvester();
			if(player != null){
				ItemStack tool = player.getHeldItemMainhand();
				if(tool != null){
					if(tool.getItem() instanceof ItemPickaxe){
						event.setDropChance(1f);
						event.getDrops().clear();
						event.getDrops().add(new ItemStack(Blocks.SAND));
					}
				}
			}
		}
	}
}