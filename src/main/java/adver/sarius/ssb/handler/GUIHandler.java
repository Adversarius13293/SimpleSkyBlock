package adver.sarius.ssb.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import adver.sarius.ssb.NamesSRG;
import adver.sarius.ssb.SimpleSkyBlockMod;
import adver.sarius.ssb.gui.GuiCreateWorldSSB;

public class GUIHandler {

	@SubscribeEvent
	public void onGUIOpen(GuiOpenEvent event){
		GuiScreen gui = event.getGui();
		if(gui instanceof GuiCreateWorld){
			GuiCreateWorld guiNew;
			if(Minecraft.getMinecraft().currentScreen instanceof GuiWorldSelection){
				guiNew = new GuiCreateWorldSSB(Minecraft.getMinecraft().currentScreen);
				// auto select ssb world type
				ReflectionHelper.setPrivateValue(GuiCreateWorld.class, guiNew, SimpleSkyBlockMod.WORLD_TYPE_SSB.getWorldTypeID(), 
						NamesSRG.SELECTEDINDEX.srg, NamesSRG.SELECTEDINDEX.readable);
				ReflectionHelper.setPrivateValue(GuiCreateWorld.class, guiNew, true, 
						NamesSRG.BONUSCHESTENABLED.srg, NamesSRG.BONUSCHESTENABLED.readable);
			} else{
				// comes from recreateWorld, now GuiScreenWorking is current screen.
				GuiScreen parent = ReflectionHelper.getPrivateValue(GuiCreateWorld.class, (GuiCreateWorld)gui, 
						NamesSRG.PARENTSCREEN.srg, NamesSRG.PARENTSCREEN.readable);
				guiNew = new GuiCreateWorldSSB(parent);
				// simulate recreateFromExistingWorld() if it comes from recreateWorld button
				this.recreateFromExisting((GuiCreateWorld)gui, guiNew);
			}
			event.setGui(guiNew);
		}
	}	
	
	private void recreateFromExisting(GuiCreateWorld from, GuiCreateWorld to){
		Class<GuiCreateWorld> c = GuiCreateWorld.class;
		ReflectionHelper.setPrivateValue(c, to, ReflectionHelper.getPrivateValue(c, from, NamesSRG.WORLDNAME.srg, NamesSRG.WORLDNAME.readable), NamesSRG.WORLDNAME.srg, NamesSRG.WORLDNAME.readable);
		ReflectionHelper.setPrivateValue(c, to, ReflectionHelper.getPrivateValue(c, from, NamesSRG.WORLDSEED.srg, NamesSRG.WORLDSEED.readable), NamesSRG.WORLDSEED.srg, NamesSRG.WORLDSEED.readable);
		ReflectionHelper.setPrivateValue(c, to, ReflectionHelper.getPrivateValue(c, from, NamesSRG.SELECTEDINDEX.srg, NamesSRG.SELECTEDINDEX.readable), NamesSRG.SELECTEDINDEX.srg, NamesSRG.SELECTEDINDEX.readable);
		ReflectionHelper.setPrivateValue(c, to, ReflectionHelper.getPrivateValue(c, from, NamesSRG.CHUNKPROVIDERSETTINGSJSON.srg, NamesSRG.CHUNKPROVIDERSETTINGSJSON.readable), NamesSRG.CHUNKPROVIDERSETTINGSJSON.srg, NamesSRG.CHUNKPROVIDERSETTINGSJSON.readable);
		ReflectionHelper.setPrivateValue(c, to, ReflectionHelper.getPrivateValue(c, from, NamesSRG.GENERATESTRUCTURESENABLED.srg, NamesSRG.GENERATESTRUCTURESENABLED.readable), NamesSRG.GENERATESTRUCTURESENABLED.srg, NamesSRG.GENERATESTRUCTURESENABLED.readable);
		ReflectionHelper.setPrivateValue(c, to, ReflectionHelper.getPrivateValue(c, from, NamesSRG.ALLOWCHEATS.srg, NamesSRG.ALLOWCHEATS.readable), NamesSRG.ALLOWCHEATS.srg, NamesSRG.ALLOWCHEATS.readable);
		ReflectionHelper.setPrivateValue(c, to, ReflectionHelper.getPrivateValue(c, from, NamesSRG.GAMEMODE.srg, NamesSRG.GAMEMODE.readable), NamesSRG.GAMEMODE.srg, NamesSRG.GAMEMODE.readable);
	}
}