package com.yossy.celestialgenesis.entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
public final class MoonlitGuardian extends AbstractGenesisBoss {
    public MoonlitGuardian(EntityType<? extends Zombie> type, Level level) { super(type, level); xpReward = 100; }
    public static AttributeSupplier.Builder attributes() { return Zombie.createAttributes().add(Attributes.MAX_HEALTH, 240).add(Attributes.ATTACK_DAMAGE, 14).add(Attributes.ARMOR, 12).add(Attributes.MOVEMENT_SPEED, .3).add(Attributes.KNOCKBACK_RESISTANCE, .7); }
}
