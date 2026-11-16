package me.anticode.keep_equipment.mixin;

import me.anticode.keep_equipment.KeepEquipment;
import me.anticode.keep_equipment.api.InventoryApi;
import net.minecraft.core.NonNullList;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(Inventory.class)
public class InventoryMixin implements InventoryApi {

    @Shadow
    @Final
    private List<NonNullList<ItemStack>> compartments;

    @Shadow
    @Final
    public Player player;

    @Override
    public void keepEquipment$damageRemaining() {
        for (List<ItemStack> list : this.compartments) {
            for (ItemStack stack : list) {
                if (stack.isDamageableItem()) {
                    // Equipment should never be broken purely by dying.
                    stack.setDamageValue(Math.min(stack.getMaxDamage() - 1, stack.getDamageValue() + Mth.ceil(stack.getMaxDamage() * KeepEquipment.config.durabilityDamage)));
                }
            }
        }
    }
}
