package extrahandles;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.client.multiplayer.ClientLevel;

import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.Codec;

public record ItemHandle() implements SelectItemModelProperty<HandleType> {
	public static final SelectItemModelProperty.Type<ItemHandle, HandleType> TYPE = SelectItemModelProperty.Type.create(MapCodec.unit(new ItemHandle()), HandleType.CODEC);

	public HandleType get(ItemStack stack, @Nullable ClientLevel world, @Nullable LivingEntity holder, int value, ItemDisplayContext context) {
		return stack.get(CustomComponents.Handle);
	}

	@Override
	public SelectItemModelProperty.Type<ItemHandle, HandleType> type() {
		return TYPE;
	}

	@Override
	public Codec<HandleType> valueCodec() {
		return HandleType.CODEC;
	}
}