package adver.sarius.ssb.handler;

import net.minecraft.entity.monster.EntityGuardian;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LightningHandler {

	//TODO: multiple calls for one guardian....
	@SubscribeEvent
	public void onLightningStrike(EntityStruckByLightningEvent event){
		// only triggers on not remote
		if(event.getEntity() instanceof EntityGuardian){
			EntityGuardian guardian = ((EntityGuardian)event.getEntity());
			guardian.setElder(true);
			/** Doesnt work, persistence gets set again while reading nbt
			NBTTagCompound nbt = guardian.getEntityData();
			nbt.setBoolean("PersistenceRequired", false);
			nbt.setBoolean("Elder", true);
			guardian.readEntityFromNBT(nbt);
			**/			
		}
	}
}