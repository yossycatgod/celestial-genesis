package com.yossy.celestialgenesis.entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
public final class AstralWatcher extends AbstractGenesisBoss {
    public AstralWatcher(EntityType<? extends Zombie> type, Level level) { super(type, level); xpReward = 160; }
    public static AttributeSupplier.Builder attributes() { return Zombie.createAttributes().add(Attributes.MAX_HEALTH, 360).add(Attributes.ATTACK_DAMAGE, 18).add(Attributes.ARMOR, 16).add(Attributes.MOVEMENT_SPEED, .32).add(Attributes.KNOCKBACK_RESISTANCE, .9); }
}
