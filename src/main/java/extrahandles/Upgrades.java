package extrahandles;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;

import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.Rarity;
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
	public static final DeferredItem<Item> Wooden = registry.registerItem("wooden_upgrade_template", (properties) -> createUpgrade("wooden", slot("amethyst_shard"), properties.rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> Armadillo = registry.registerItem("armadillo_upgrade_template", (properties) -> createUpgrade("armadillo", slot("amethyst_shard"), properties.rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> Amethyst = registry.registerItem("amethyst_upgrade_template", (properties) -> createUpgrade("amethyst", slot("amethyst_shard"), properties.rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> Bone = registry.registerItem("bone_upgrade_template", (properties) -> createUpgrade("bone", slot("amethyst_shard"), properties.rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> Prismarine = registry.registerItem("prismarine_upgrade_template", (properties) -> createUpgrade("prismarine", slot("amethyst_shard"), properties.rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> Breeze = registry.registerItem("breeze_upgrade_template", (properties) -> createUpgrade("breeze", slot("amethyst_shard"), properties.rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> Scute = registry.registerItem("scute_upgrade_template", (properties) -> createUpgrade("scute", slot("amethyst_shard"), properties.rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> Echo = registry.registerItem("echo_upgrade_template", (properties) -> createUpgrade("echo", slot("amethyst_shard"), properties.rarity(Rarity.UNCOMMON)));

	public static final List<ResourceLocation> icons = List.of(slot("sword"), slot("pickaxe"), slot("axe"), slot("shovel"), slot("hoe"));

	public static SmithingTemplateItem createUpgrade(String type, ResourceLocation material, Item.Properties properties) {
		return new SmithingTemplateItem(makeDesc(type, "applies_to", true), makeDesc(type, "ingredients", true), makeDesc(type, "base_slot_description", false), makeDesc(type, "additions_slot_description", false), icons, List.of(material),
				properties);
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
		return ResourceLocation.withDefaultNamespace("container/slot/" + location);
	}
}