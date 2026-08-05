package me.anticode.keep_equipment.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public class PlayerMixin {

    @Shadow
    @Final
    private Inventory inventory;

    @WrapOperation(method = "dropEquipment", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Inventory;dropAll()V"))
    public void dropEquipment(Inventory instance, Operation<Void> original) {
        int i = 0;
        for(ItemStack itemStack : inventory.items) {
            if (i > 8) {
                if (!itemStack.isEmpty()) {
                    ((Player)(Object)this).drop(itemStack, true, false);
                    inventory.items.set(i, ItemStack.EMPTY);
                }
            }
            i++;
        }
    }
}
