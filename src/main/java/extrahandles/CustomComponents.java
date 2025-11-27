package extrahandles;

import org.objectweb.asm.Handle;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.core.registries.Registries;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.client.renderer.item.ItemPropertyFunction;

public class CustomComponents {
	public static final DataComponentType<HandleType> Handle = new DataComponentType.Builder().persistent(HandleType.CODEC).networkSynchronized(HandleType.STREAM_CODEC).build();
	public static final DeferredRegister<DataComponentType<?>> REGISTRY = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, ExtraHandlesMod.MODID);
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<?>> HandleData = REGISTRY.register("handle_type", () -> Handle);
	public static final ItemPropertyFunction HandleProperty = (stack, world, holder, value) -> stack.getOrDefault(Handle, HandleType.Wooden).getId();
}