package adver.sarius.ssb.handler;

import adver.sarius.ssb.SimpleSkyBlockMod;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FossilGeneratorHandler{

	// called only once, so I guess its only server side
	@SubscribeEvent
	public void onDecorateStarting(DecorateBiomeEvent.Decorate event){
		// TODO: should I deny just every decoration event?
		if(event.getType() == Decorate.EventType.FOSSIL
				&& SimpleSkyBlockMod.useSSBGen(event.getWorld())){
			event.setResult(Result.DENY);
		}
	}
}