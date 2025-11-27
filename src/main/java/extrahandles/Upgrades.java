package extrahandles;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;

import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.monster.breeze.Breeze;
import net.minecraft.world.entity.animal.armadillo.Armadillo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.Util;
import net.minecraft.ChatFormatting;

import java.util.List;

public class Upgrades {
	public static final DeferredRegister.Items registry = DeferredRegister.createItems(ExtraHandlesMod.MODID);
	public static final DeferredItem<Item> Wooden = registry.register("wooden_upgrade_template", () -> createUpgrade("wooden", empty("stick")));
	public static final DeferredItem<Item> Armadillo = registry.register("armadillo_upgrade_template", () -> createUpgrade("armadillo", empty("armadillo_scute")));
	public static final DeferredItem<Item> Amethyst = registry.register("amethyst_upgrade_template", () -> createUpgrade("amethyst", slot("amethyst_shard")));
	public static final DeferredItem<Item> Bone = registry.register("bone_upgrade_template", () -> createUpgrade("bone", empty("bone")));
	public static final DeferredItem<Item> Prismarine = registry.register("prismarine_upgrade_template", () -> createUpgrade("prismarine", empty("prismarine_shard")));
	public static final DeferredItem<Item> Breeze = registry.register("breeze_upgrade_template", () -> createUpgrade("breeze", empty("breeze_rod")));
	public static final DeferredItem<Item> Scute = registry.register("scute_upgrade_template", () -> createUpgrade("scute", empty("turtle_scute")));
	public static final DeferredItem<Item> Echo = registry.register("echo_upgrade_template", () -> createUpgrade("echo", empty("echo_shard")));
	public static final List icons = List.of(slot("sword"), slot("pickaxe"), slot("axe"), slot("shovel"), slot("hoe"), empty("bow"), empty("mace"), empty("brush"), empty("fishing_rod"));

	public static SmithingTemplateItem createUpgrade(String type, ResourceLocation material) {
		return new SmithingTemplateItem(makeDesc(type, "applies_to", true), makeDesc(type, "ingredients", true), makeDesc(type), makeDesc(type, "base_slot_description", false), makeDesc(type, "additions_slot_description", false), icons,
				List.of(material));
	}

	public static Component makeDesc(String type) {
   		return Component.translatable(Util.makeDescriptionId("upgrade", resource("smithing_template." + type + "_upgrade"))).withStyle(ChatFormatting.GRAY);
    }

	public static Component makeDesc(String type, String useage, boolean format) {
    	String translationKey = Util.makeDescriptionId("item", resource("smithing_template." + type + "_upgrade." + useage));
   		return format ? Component.translatable(translationKey).withStyle(ChatFormatting.BLUE) : Component.translatable(translationKey);
    }

	public static ResourceLocation resource(String location) {
		return ResourceLocation.fromNamespaceAndPath(ExtraHandlesMod.MODID, location);
	}

	public static ResourceLocation slot(String location) {
		return ResourceLocation.withDefaultNamespace("item/empty_slot_" + location);
	}

	public static ResourceLocation empty(String location) {
		return resource("item/empty_slot_" + location);
	}
}