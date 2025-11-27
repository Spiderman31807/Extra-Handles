package extrahandles;

import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.monster.breeze.Breeze;
import net.minecraft.world.entity.animal.armadillo.Armadillo;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.ByIdMap;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.codec.ByteBufCodecs;

import java.util.function.IntFunction;

import io.netty.buffer.ByteBuf;

public enum HandleType implements StringRepresentable {
	Wooden(29), Armadillo(355), Amethyst(200), Bone(225), Prismarine(410), Breeze(250), Scute(590), Echo(845), Netherite(1015);

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
		return this.toString();
	}

	public int getMaxDamage(int original, Item item) {
		int reduction = item == Items.MACE ? 250 : 29;
		if(item instanceof TieredItem tierItem && tierItem.getTier() == Tiers.NETHERITE)
			reduction = 1015;
		return (original - reduction) + durability;
	}
}