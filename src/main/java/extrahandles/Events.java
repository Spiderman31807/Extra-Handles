package extrahandles;

import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.resources.ResourceKey;

@EventBusSubscriber
public class Events {
	@SubscribeEvent
	public static void register(BuildCreativeModeTabContentsEvent event) {
		ResourceKey<CreativeModeTab> tab = event.getTabKey();
		if (tab == CreativeModeTabs.COMBAT) {
			insertVariants(Items.TRIDENT, event, true);
			insertVariants(Items.MACE, event, true);
			insertVariants(Items.BOW, event, true);
			insertVariants(event, false, Items.NETHERITE_AXE, Items.DIAMOND_AXE, Items.GOLDEN_AXE, Items.IRON_AXE, Items.STONE_AXE, Items.WOODEN_AXE);
			insertVariants(event, Items.NETHERITE_SWORD, Items.DIAMOND_SWORD, Items.GOLDEN_SWORD, Items.IRON_SWORD, Items.STONE_SWORD, Items.WOODEN_SWORD);
		} else if (tab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			insertVariants(Items.BRUSH, event, true);
			insertVariants(Items.FISHING_ROD, event, true);
			insertVariants(event, Items.NETHERITE_HOE, Items.DIAMOND_HOE, Items.GOLDEN_HOE, Items.IRON_HOE, Items.STONE_HOE, Items.WOODEN_HOE);
			insertVariants(event, Items.NETHERITE_AXE, Items.DIAMOND_AXE, Items.GOLDEN_AXE, Items.IRON_AXE, Items.STONE_AXE, Items.WOODEN_AXE);
			insertVariants(event, Items.NETHERITE_PICKAXE, Items.DIAMOND_PICKAXE, Items.GOLDEN_PICKAXE, Items.IRON_PICKAXE, Items.STONE_PICKAXE, Items.WOODEN_PICKAXE);
			insertVariants(event, Items.NETHERITE_SHOVEL, Items.DIAMOND_SHOVEL, Items.GOLDEN_SHOVEL, Items.IRON_SHOVEL, Items.STONE_SHOVEL, Items.WOODEN_SHOVEL);
		} else if (tab == CreativeModeTabs.INGREDIENTS) {
			CreativeModeTab.TabVisibility visibility = CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS;
			ItemStack netheriteTemplate = new ItemStack(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE);
			event.insertBefore(netheriteTemplate, new ItemStack(Upgrades.Wooden.get()), visibility);
			event.insertBefore(netheriteTemplate, new ItemStack(Upgrades.Armadillo.get()), visibility);
			event.insertBefore(netheriteTemplate, new ItemStack(Upgrades.Amethyst.get()), visibility);
			event.insertBefore(netheriteTemplate, new ItemStack(Upgrades.Bone.get()), visibility);
			event.insertBefore(netheriteTemplate, new ItemStack(Upgrades.Prismarine.get()), visibility);
			event.insertBefore(netheriteTemplate, new ItemStack(Upgrades.Breeze.get()), visibility);
			event.insertBefore(netheriteTemplate, new ItemStack(Upgrades.Scute.get()), visibility);
			event.insertBefore(netheriteTemplate, new ItemStack(Upgrades.Echo.get()), visibility);
		}
	}

	public static void insertVariants(BuildCreativeModeTabContentsEvent event, Item... items) {
		insertVariants(event, true, items);
	}

	public static void insertVariants(BuildCreativeModeTabContentsEvent event, boolean allowSearch, Item... items) {
		for (Item item : items) {
			insertVariants(item, event, allowSearch);
		}
	}

	public static void insertVariants(Item item, BuildCreativeModeTabContentsEvent event, boolean allowSearch) {
		CreativeModeTab.TabVisibility visibility = CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS;
		if (!allowSearch)
			visibility = CreativeModeTab.TabVisibility.PARENT_TAB_ONLY;
		ItemStack lastStack = null;
		event.remove(new ItemStack(item), visibility);
		for (HandleType handle : HandleType.values()) {
			ItemStack stack = getVariant(item, handle);
			if (lastStack == null)
				event.insertFirst(stack, visibility);
			else
				event.insertAfter(lastStack, stack, visibility);
			lastStack = stack;
		}
	}

	public static ItemStack getVariant(Item item, HandleType handle) {
		ItemStack stack = new ItemStack(item);
		stack.set(CustomComponents.Handle, handle);
		return stack;
	}
}