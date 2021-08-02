package consular.consularsorigins.mixin;

import io.github.apace100.apoli.component.PowerHolderComponent;
import consular.consularsorigins.common.power.MobNeutralityPower;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(FollowTargetGoal.class)
public abstract class FollowTargetGoalMixin<T extends LivingEntity> {
	@Shadow
	protected TargetPredicate targetPredicate;
	
	@Inject(method = "<init>(Lnet/minecraft/entity/mob/MobEntity;Ljava/lang/Class;IZZLjava/util/function/Predicate;)V", at = @At("TAIL"))
	private void init(MobEntity mob, Class<T> targetClass, int reciprocalChance, boolean checkVisibility, boolean checkCanNavigate, @Nullable Predicate<LivingEntity> predicate, CallbackInfo callbackInfo) {
		Predicate<LivingEntity> originalPredicate = ((TargetPredicateAccessor) targetPredicate).eo_getTargetPredicate();
		if (originalPredicate == null) {
			originalPredicate = entity -> true;
		}
		targetPredicate.setPredicate(originalPredicate.and(target -> {
			for (MobNeutralityPower power : PowerHolderComponent.getPowers(target, MobNeutralityPower.class)) {
				if (power.entityTypes.contains(mob.getType())) {
					return false;
				}
			}
			return true;
		}));
	}
}
