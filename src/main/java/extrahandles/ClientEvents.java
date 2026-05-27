package extrahandles;

import net.neoforged.neoforge.client.event.RenderTooltipEvent;
import net.neoforged.neoforge.client.event.RegisterSelectItemModelPropertyEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;

import java.util.List;

import com.mojang.datafixers.util.Either;

@EventBusSubscriber
public class ClientEvents {
	@SubscribeEvent
	public static void tooltip(RenderTooltipEvent.GatherComponents event) {
		ItemStack stack = event.getItemStack();
		if (!stack.has(CustomComponents.Handle))
			return;
			
		HandleType handle = stack.get(CustomComponents.Handle);
		List<Either<FormattedText, TooltipComponent>> tooltips = event.getTooltipElements();
		Either<FormattedText, TooltipComponent> nameTip = tooltips.getFirst();
		tooltips.remove(nameTip);
		Component handleTip = Component.translatable("extra_handles.tooltip.handle", handle.toString()).withStyle(ChatFormatting.GRAY);
		tooltips.addFirst(Either.left(handleTip));
		tooltips.addFirst(nameTip);
	}

	@SubscribeEvent
	public static void registerProperties(RegisterSelectItemModelPropertyEvent event) {
		event.register(ResourceLocation.fromNamespaceAndPath("extra_handles", "type"), ItemHandle.TYPE);
	}
}