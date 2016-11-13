package adver.sarius.ssb.handler;

import adver.sarius.ssb.gen.WorldTypeSSB;
import net.minecraft.world.DimensionType;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FossilGeneratorHandler{

	@SubscribeEvent
	public void onDecorateStarting(DecorateBiomeEvent.Decorate event){
		// should I deny just every decoration event?
		if(event.getType() == Decorate.EventType.FOSSIL
				&& event.getWorld().getWorldType() instanceof WorldTypeSSB){
			event.setResult(Result.DENY);
		}
	}
}