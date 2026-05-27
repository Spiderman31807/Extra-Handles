package extrahandles.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.item.ItemStack;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponentHolder;

import extrahandles.CustomComponents;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements DataComponentHolder {
	private ItemStack self() {
		return (ItemStack) (Object) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(DataComponentType<? extends T> component) {
		if (component == DataComponents.MAX_DAMAGE && self().has(CustomComponents.Handle)) {
			int maxDamage = DataComponentHolder.super.get(DataComponents.MAX_DAMAGE);
			maxDamage = self().get(CustomComponents.Handle).getMaxDamage(maxDamage, self());
			return (T) Integer.valueOf(maxDamage);
		}

		return DataComponentHolder.super.get(component);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getOrDefault(DataComponentType<? extends T> component, T fallback) {
		if (component == DataComponents.MAX_DAMAGE && self().has(CustomComponents.Handle)) {
			int maxDamage = DataComponentHolder.super.get(DataComponents.MAX_DAMAGE);
			maxDamage = self().get(CustomComponents.Handle).getMaxDamage(maxDamage, self());
			return (T) Integer.valueOf(maxDamage);
		}

		return DataComponentHolder.super.getOrDefault(component, fallback);
	}
}