package extrahandles;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.monster.breeze.Breeze;
import net.minecraft.world.entity.animal.armadillo.Armadillo;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.ByIdMap;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.core.component.DataComponents;

import java.util.function.IntFunction;

import io.netty.buffer.ByteBuf;
import net.minecraft.world.item.ItemStack;

public enum HandleType implements StringRepresentable {
	Wooden(30), Armadillo(65), Bone(90), Prismarine(125), Scute(225), Breeze(250), Amethyst(335), Echo(510), Netherite(635);

	public static final IntFunction<HandleType> BY_ID = ByIdMap.continuous(HandleType::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
	public static final StringRepresentable.EnumCodec<HandleType> CODEC = StringRepresentable.fromEnum(HandleType::values);
	public static final StreamCodec<ByteBuf, HandleType> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, HandleType::getId);
	private final int durability;
	private final int id;

	private HandleType(int durability) {
		this.durability = durability;
		this.id = this.ordinal();
	}

	public int getId() {
		return this.id;
	}

	public static HandleType byId(int id) {
		return BY_ID.apply(id);
	}

	@Override
	public String getSerializedName() {
		return this.toString().toLowerCase();
	}

	public int getMaxDamage(int original, ItemStack stack) {
		int reduction = stack.getItem() == Items.MACE ? 250 : (stack.getItem() == Items.TRIDENT ? 125 : 30);
		if (stack.hasNonDefault(DataComponents.DAMAGE_RESISTANT) && stack.get(DataComponents.DAMAGE_RESISTANT).types().equals(DamageTypeTags.IS_FIRE))
			reduction = 635;
		return (original - reduction) + durability;
	}
}