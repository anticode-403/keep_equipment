package me.anticode.keep_equipment.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {
    @WrapOperation(method = "restoreFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z"))
    private boolean restoreInventory(GameRules instance, GameRules.Key<GameRules.BooleanValue> key, Operation<Boolean> original) {
        return true;
    }

    @WrapOperation(method = "restoreFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;setScore(I)V", ordinal = 1))
    private void setExperienceOnRespawn(ServerPlayer instance, int i, Operation<Void> original) {
        ServerPlayer player = ((ServerPlayer)(Object)this);
//        int totalExperience = player.totalExperience;
        player.setExperienceLevels(Mth.floor(instance.experienceLevel * 0.7));
    }
}
