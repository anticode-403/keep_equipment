package me.anticode.keep_equipment.neoforge.yigd.mixin;

import com.b1n_ry.yigd.DeathHandler;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DeathHandler.class)
public class DeathHandlerMixin {
    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lcom/b1n_ry/yigd/components/ExpComponent;clearXp(Lnet/minecraft/server/level/ServerPlayer;)V"))
    private void doNotClearXp(ServerPlayer player, Operation<Void> original) {
        // This space intentionally left blank.
    }
}
