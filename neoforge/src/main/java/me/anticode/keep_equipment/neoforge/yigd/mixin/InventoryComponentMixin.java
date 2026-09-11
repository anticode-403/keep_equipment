package me.anticode.keep_equipment.neoforge.yigd.mixin;

import com.b1n_ry.yigd.components.InventoryComponent;
import com.b1n_ry.yigd.data.DeathContext;
import com.b1n_ry.yigd.data.GraveItem;
import com.b1n_ry.yigd.events.YigdEvents;
import com.b1n_ry.yigd.util.DropRule;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.anticode.keep_equipment.KeepEquipment;
import net.minecraft.core.NonNullList;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryComponent.class)
public class InventoryComponentMixin {
    @Shadow
    @Final
    private NonNullList<GraveItem> items;

    @WrapOperation(method = "handleDropRules", at = @At(value = "INVOKE", target = "Lcom/b1n_ry/yigd/events/YigdEvents$DropRuleEvent;getDropRule()Lcom/b1n_ry/yigd/util/DropRule;"))
    public DropRule dontGraveEquipment(YigdEvents.DropRuleEvent instance, Operation<DropRule> original) {
        if (instance.getSlot() < 9 || instance.getStack().is(KeepEquipment.KEPT_EQUIPMENT) || instance.getSlot() > 35) {
            return DropRule.KEEP;
        }
        else return original.call(instance);
    }

    @Inject(method = "handleDropRules", at = @At(value = "INVOKE", target = "Lcom/b1n_ry/yigd/compat/CompatComponent;handleDropRules(Lcom/b1n_ry/yigd/data/DeathContext;)V", shift = At.Shift.AFTER))
    private void damageKeptItems(DeathContext context, CallbackInfo ci) {
        for (GraveItem graveItem : items) {
            if (graveItem.stack.isDamageableItem() && graveItem.dropRule == DropRule.KEEP) graveItem.stack.hurtAndBreak(Mth.ceil(graveItem.stack.getMaxDamage() * KeepEquipment.config.durabilityDamage), context.world(), context.player(), (item) -> context.player().onEquippedItemBroken(graveItem.stack.getItem(), context.player().getEquipmentSlotForItem(graveItem.stack)));
        }
    }
}
