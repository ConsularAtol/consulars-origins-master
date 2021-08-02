package consular.consularsorigins.mixin;

import io.github.apace100.apoli.component.PowerHolderComponent;
import consular.consularsorigins.common.power.MobNeutralityPower;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinBrain.class)
public abstract class PiglinBrainMixin {
	@Inject(method = "wearsGoldArmor", at = @At("HEAD"), cancellable = true)
	private static void wearsGoldArmor(LivingEntity target, CallbackInfoReturnable<Boolean> callbackInfo) {
		PowerHolderComponent.getPowers(target, MobNeutralityPower.class).forEach(power -> {
			if (power.entityTypes.contains(EntityType.PIGLIN)) {
				callbackInfo.setReturnValue(true);
			}
		});
	}
	
	@Inject(method = "onGuardedBlockInteracted", at = @At("HEAD"), cancellable = true)
	private static void onGuardedBlockInteracted(PlayerEntity player, boolean blockOpen, CallbackInfo callbackInfo) {
		PowerHolderComponent.getPowers(player, MobNeutralityPower.class).forEach(power -> {
			if (power.entityTypes.contains(EntityType.PIGLIN)) {
				callbackInfo.cancel();
			}
		});
	}
}
