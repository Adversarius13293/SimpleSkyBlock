package adver.sarius.ssb.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
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
				ReflectionHelper.setPrivateValue(GuiCreateWorld.class, guiNew, SimpleSkyBlockMod.WORLD_TYPE_SSB.getWorldTypeID(), "selectedIndex");
				ReflectionHelper.setPrivateValue(GuiCreateWorld.class, guiNew, true, "bonusChestEnabled");
			} else{
				// comes from recreateWorld, now GuiScreenWorking is current screen.
				GuiScreen parent = ReflectionHelper.getPrivateValue(GuiCreateWorld.class, (GuiCreateWorld)gui, "parentScreen");
				guiNew = new GuiCreateWorldSSB(parent);
				// simulate recreateFromExistingWorld() if it comes from recreateWorld button
				this.recreateFromExisting((GuiCreateWorld)gui, guiNew);
			}
			event.setGui(guiNew);
		}
	}	
	
	private void recreateFromExisting(GuiCreateWorld from, GuiCreateWorld to){
		Class<GuiCreateWorld> c = GuiCreateWorld.class;
		ReflectionHelper.setPrivateValue(c, to, ReflectionHelper.getPrivateValue(c, from, "worldName"), "worldName");
		ReflectionHelper.setPrivateValue(c, to, ReflectionHelper.getPrivateValue(c, from, "worldSeed"), "worldSeed");
		ReflectionHelper.setPrivateValue(c, to, ReflectionHelper.getPrivateValue(c, from, "selectedIndex"), "selectedIndex");
		ReflectionHelper.setPrivateValue(c, to, ReflectionHelper.getPrivateValue(c, from, "chunkProviderSettingsJson"), "chunkProviderSettingsJson");
		ReflectionHelper.setPrivateValue(c, to, ReflectionHelper.getPrivateValue(c, from, "generateStructuresEnabled"), "generateStructuresEnabled");
		ReflectionHelper.setPrivateValue(c, to, ReflectionHelper.getPrivateValue(c, from, "allowCheats"), "allowCheats");
		ReflectionHelper.setPrivateValue(c, to, ReflectionHelper.getPrivateValue(c, from, "gameMode"), "gameMode");
	}
}