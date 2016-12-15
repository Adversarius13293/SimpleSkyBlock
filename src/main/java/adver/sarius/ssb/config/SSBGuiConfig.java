package adver.sarius.ssb.config;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import adver.sarius.ssb.SimpleSkyBlockMod;

public class SSBGuiConfig extends GuiConfig{

	public SSBGuiConfig(GuiScreen parentScreen){
		super(parentScreen, getConfigElements(), SimpleSkyBlockMod.MODID, false, false, GuiConfig.getAbridgedConfigPath(SSBConfig.configFile.toString()));
	}
	
	/** Compiles a list of config elements */
	private static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		list.add(categoryElement(SSBConfig.CATEGORY_GENERAL, "General", "ssb.config.gui.category.general"));
		list.add(categoryElement(SSBConfig.CATEGORY_GENERATION, "Generation", "sssb.config.gui.category.generation"));
		list.add(categoryElement(SSBConfig.CATEGORY_MECHANICS, "Mechanics", "sssb.config.gui.category.mechanics"));
		return list;
	}
	
	/** Creates a button linking to another screen where all options of the category are available */
    private static IConfigElement categoryElement(String category, String name, String tooltipKey) {
        return new DummyConfigElement.DummyCategoryElement(name, tooltipKey,
                new ConfigElement(SSBConfig.configFile.getCategory(category)).getChildElements());
    }
}