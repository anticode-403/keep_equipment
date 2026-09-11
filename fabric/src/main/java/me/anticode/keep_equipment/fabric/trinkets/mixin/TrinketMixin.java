package me.anticode.keep_equipment.fabric.trinkets.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketEnums;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Trinket.class)
public interface TrinketMixin {

    @ModifyReturnValue(method = "getDropRule", at = @At("TAIL"))
    default TrinketEnums.DropRule noDropRule(TrinketEnums.DropRule original) {
        return TrinketEnums.DropRule.KEEP;
    }
}
