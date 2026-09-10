package com.yossy.celestialgenesis.entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
public final class AstralSentinel extends Zombie {
    public AstralSentinel(EntityType<? extends Zombie> type, Level level) { super(type, level); xpReward = 18; }
    public static AttributeSupplier.Builder attributes() { return Zombie.createAttributes().add(Attributes.MAX_HEALTH, 40).add(Attributes.ATTACK_DAMAGE, 8).add(Attributes.ARMOR, 6).add(Attributes.MOVEMENT_SPEED, .27); }
}
