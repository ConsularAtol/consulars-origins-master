package consular.consularsorigins.common.registry;

import consular.consularsorigins.common.ConsularsOrigins;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class EOTags {
	public static final Tag<Item> GOLDEN_ARMOR = TagRegistry.item(new Identifier(ConsularsOrigins.MODID, "golden_armor"));
	public static final Tag<Item> GOLDEN_TOOLS = TagRegistry.item(new Identifier(ConsularsOrigins.MODID, "golden_tools"));
	public static final Tag<Item> NETHERITE_ARMOR = TagRegistry.item(new Identifier(ConsularsOrigins.MODID, "netherite_armor"));
	public static final Tag<Item> NETHERITE_TOOLS = TagRegistry.item(new Identifier(ConsularsOrigins.MODID, "netherite_tools"));
}
