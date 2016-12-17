package adver.sarius.ssb.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import adver.sarius.ssb.SimpleSkyBlockMod;
import adver.sarius.ssb.config.SSBConfig;
import adver.sarius.ssb.recipe.ModRecipes;

// only called on server
public class LoginHandler {

	// TODO: Use proxies instead of SideOnly?
	@SideOnly(Side.SERVER)
	@SubscribeEvent
	public void onLoginDedicated(PlayerLoggedInEvent event){
		if(event.player instanceof EntityPlayerMP){
			SimpleSkyBlockMod.network.sendTo(new ConfigPacket(SSBConfig.disableNetherSpawnerChange, SSBConfig.enableCraftingRecipes, SSBConfig.enableForesterVillager), (EntityPlayerMP) event.player);			
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onLoginIntegrated(PlayerLoggedInEvent event){
		if(event.player instanceof EntityPlayerMP && SSBConfig.needsResync){
			SSBConfig.syncConfig();
			ModRecipes.syncRecipesWithConfig();
		}
	}
}