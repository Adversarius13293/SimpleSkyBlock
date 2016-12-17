package adver.sarius.ssb.handler;

import java.util.Random;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import adver.sarius.ssb.config.SSBConfig;
import adver.sarius.ssb.villager.VillagerTradingChanger;

public class VillagerSpawningHandler {

	private Random rand = new Random();
	
	// fired on both sides. Server can change entity before
	@SubscribeEvent
	public void onEntitySpawn(EntityJoinWorldEvent event){
		int i = 0;
		if(event.getEntity() instanceof EntityZombie && !SSBConfig.enableForesterVillager){
			// TODO: Not in use, since the forge code seems bugged.
			EntityZombie zombie = (EntityZombie)event.getEntity();
			while(zombie.getVillagerTypeForge() == VillagerTradingChanger.prof && i < 30){
				VillagerRegistry.setRandomProfession(zombie, rand);
				i++;
			}
			// Make it a normal zombie after too many failed random changes.
			if(zombie.getVillagerTypeForge() == VillagerTradingChanger.prof){
				zombie.setVillagerType(null);
			}
		} else if(event.getEntity() instanceof EntityVillager && !SSBConfig.enableForesterVillager){
			EntityVillager villager = (EntityVillager)event.getEntity();
			while(villager.getProfessionForge() == VillagerTradingChanger.prof && i < 30){
				VillagerRegistry.setRandomProfession(villager, rand);
				i++;
			}
			// Use first profession after too many fails, even if it is still my villager? 
			if(villager.getProfessionForge() == VillagerTradingChanger.prof){
				villager.setProfession(0);
			}
			if(i>0){
				NBTTagCompound compound = new NBTTagCompound();
				villager.writeEntityToNBT(compound);
				// Random career
				compound.setInteger("Career", 0);
				// Start with first trade again
		        compound.setInteger("CareerLevel", 0);
		        // remove old trades
		        compound.removeTag("Offers");
		        villager.readEntityFromNBT(compound);
			}
		}
	}
}