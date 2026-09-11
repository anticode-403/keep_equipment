package me.anticode.keep_equipment.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.anticode.keep_equipment.KeepEquipment;
import me.anticode.keep_equipment.api.InventoryApi;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @Shadow
    @Final
    private Inventory inventory;

    @Shadow
    public abstract boolean isSpectator();

    @Shadow
    public int totalExperience;

    @Shadow
    public int experienceLevel;

    @Shadow
    public abstract int getXpNeededForNextLevel();

    @Shadow
    public float experienceProgress;

    @Shadow
    public abstract void giveExperiencePoints(int i);

    @WrapOperation(method = "dropEquipment", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Inventory;dropAll()V"))
    public void dropEquipment(Inventory instance, Operation<Void> original) {
        int i = 0;
        for(ItemStack itemStack : inventory.items) {
            if (i > 8) {
                if (!itemStack.isEmpty() && !itemStack.is(KeepEquipment.KEPT_EQUIPMENT)) {
                    ((Player)(Object)this).drop(itemStack, true, false);
                    inventory.items.set(i, ItemStack.EMPTY);
                }
            }
            i++;
        }
        if (!((Player)(Object)this).level().isClientSide()) {
            ((InventoryApi)inventory).keepEquipment$damageRemaining((ServerLevel)((Player)(Object)this).level());
        }
    }

    @Inject(method = "getBaseExperienceReward", at = @At("HEAD"), cancellable = true)
    public void dropPartialExperience(CallbackInfoReturnable<Integer> cir) {
        float total = 0;
        int level = this.experienceLevel;
        for (int i = 0; i <= level; i++) {
            this.experienceLevel = i;
            total += getXpNeededForNextLevel() * (i == level ? this.experienceProgress : 1.0F);
        }
        int totalExperience = Math.round(total);
        int safe_xp = Math.round(totalExperience * KeepEquipment.config.xpKeepPercentage);
        int drop_xp = Math.round(totalExperience * KeepEquipment.config.xpDropPercentage);

        this.experienceLevel = 0;
        this.experienceProgress = 0f;
        giveExperiencePoints(safe_xp);

        cir.setReturnValue(drop_xp);
    }
}
