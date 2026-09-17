# Celestial Genesis Roadmap

## v0.6 — System Rework / Celestial Interface

Goal: stabilize existing gameplay systems first, then replace placeholder HUDs with a complete Celestial Genesis UI layer.

### Phase 1 — Core system repair

- [ ] Replace the temporary Creation Energy persistent-data implementation with a proper player-data abstraction.
- [ ] Make Creation Energy server-authoritative.
- [ ] Sync Creation Energy to clients on login, respawn, dimension change, and relevant value changes.
- [ ] Support modified max Creation Energy from equipment, affixes, set bonuses, and future awakening systems.
- [ ] Support modified regeneration rate from equipment and affixes.
- [ ] Verify Exploration Progress persistence and client synchronization.
- [ ] Verify boss-defeat progression in singleplayer and multiplayer.
- [ ] Keep dungeon unlock state consistent across dimension changes and reconnects.
- [ ] Stabilize Astral Realm entry requirements.
- [ ] Audit Genesis Forging persistence.
- [ ] Preserve rarity, affixes, sockets, weapon level, and future awakening data during reforging.
- [ ] Prevent invalid duplicate affixes.
- [ ] Normalize Affix Tier handling.
- [ ] Stabilize Star Socket insertion/removal.
- [ ] Verify boss bars, affixed mobs, and dungeon loot synchronization.

### Phase 2 — Celestial HUD

- [ ] Add a dedicated client HUD package instead of rendering placeholder text directly from `CelestialGenesisClient`.
- [ ] Use texture-backed HUD assets.
- [ ] Support GUI scale and common aspect ratios, including 16:9, 16:10, and ultrawide.
- [ ] Add configurable HUD anchors and offsets.
- [ ] Creation Energy gauge.
- [ ] Numeric Creation Energy readout.
- [ ] Energy gain animation.
- [ ] Energy spend animation.
- [ ] Low-energy warning state.
- [ ] Weapon skill icon and cooldown indicator.
- [ ] Skill-ready highlight animation.
- [ ] Astral progression display.
- [ ] Boss-defeat / progression notifications.
- [ ] Active Affix proc indicators.
- [ ] Temporary buff indicators.

Suggested HUD layout:

```text
                  ✦ 74 / 100
             ═══════◈═══════
               CREATION

                         [Q] Lunar Sweep
                              ◉ READY

       SSS
     STYLE
```

### Phase 3 — Genesis Forge GUI

- [ ] Open a dedicated GUI when interacting with the Genesis Forge.
- [ ] Equipment slot.
- [ ] Catalyst slot.
- [ ] Material slot.
- [ ] Star Socket slot area.
- [ ] Reforge button.
- [ ] Reforge cost display.
- [ ] Current rarity display.
- [ ] Affix list.
- [ ] Socket list.
- [ ] Current-vs-result stat comparison.
- [ ] Damage delta.
- [ ] Attack-speed delta.
- [ ] Creation Energy modifier delta.
- [ ] Affix delta.
- [ ] Rarity-specific visual treatment.
- [ ] Special Celestial / Genesis animation.
- [ ] PARADOX / NULL glitch presentation reserved for later tiers.

Suggested Forge layout:

```text
╔══════════════════════════════════════╗
║         ✦ GENESIS FORGING ✦        ║
║                                      ║
║               [WEAPON]               ║
║            LUNAR GENESIS             ║
║                                      ║
║  RARITY                   CELESTIAL  ║
║                                      ║
║  AFFIX                               ║
║  ★ Lunar IV               +24%       ║
║  ★ Swift III              +13%       ║
║  ★ Creator's II           -12% CE    ║
║                                      ║
║  SOCKET                              ║
║      ◆      ◇      ◇                 ║
║                                      ║
║             [ REFORGE ]              ║
╚══════════════════════════════════════╝
```

### Phase 4 — Equipment detail UI

- [ ] Dedicated equipment detail screen.
- [ ] Weapon preview area.
- [ ] Rarity.
- [ ] Weapon Level.
- [ ] Awakening Stage placeholder.
- [ ] Affix list.
- [ ] Socket list.
- [ ] Skill list.
- [ ] Estimated DPS.
- [ ] Lunar Genesis special presentation.
- [ ] Celestial Armor set-bonus presentation.
- [ ] Equipped-set-piece counter.

### Phase 5 — Combat interface

- [ ] Celestial crosshair layer.
- [ ] Melee crosshair state.
- [ ] TaCZ firearm crosshair state.
- [ ] SERAPH-Ω crosshair state.
- [ ] Target-lock indicator.
- [ ] Target HP.
- [ ] Boss target presentation.
- [ ] Armor / break gauge foundation.
- [ ] Perfect Dodge notification.
- [ ] Perfect Guard notification.
- [ ] Combo counter.
- [ ] STYLE HUD: D, C, B, A, S, SS, SSS, CELESTIAL.
- [ ] Style rank-up animation and sound.
- [ ] Combo-break animation.

### Phase 6 — SERAPH-Ω interface

- [ ] Deployment UI.
- [ ] `SYSTEM ONLINE` state.
- [ ] Cannon lock indicator.
- [ ] Heat gauge.
- [ ] Charge gauge.
- [ ] Target lock.
- [ ] Astral Link percentage.
- [ ] CELESTIAL LANCE charge presentation.
- [ ] SIEGE MODE HUD.
- [ ] STELLAR ANNIHILATION HUD.
- [ ] Overheat warning.
- [ ] Emergency cooldown display.

### Phase 7 — Epic Fight / TaCZ UI integration

- [ ] Avoid overlap with TaCZ ammo UI.
- [ ] Celestial ammo / energy-ammo UI.
- [ ] Perfect Reload timing UI.
- [ ] Headshot / crit indicator.
- [ ] Avoid overlap with Epic Fight stamina and weapon-skill UI.
- [ ] Alter Celestial HUD presentation while Epic Fight combat mode is active.
- [ ] GENESIS-01 mode indicator.
- [ ] RIFLE MODE.
- [ ] BLADE MODE.
- [ ] Mode-shift transition animation.

### Phase 8 — Settings and accessibility

- [ ] Celestial Genesis settings screen.
- [ ] HUD enable/disable.
- [ ] HUD scale.
- [ ] HUD position / anchors.
- [ ] Animation intensity.
- [ ] Screen-shake intensity.
- [ ] Damage numbers enable/disable.
- [ ] Boss UI enable/disable.
- [ ] STYLE HUD enable/disable.
- [ ] Flash reduction.
- [ ] Glitch-effect reduction.
- [ ] Motion reduction.

### Phase 9 — DEV / debug interface

- [ ] DEV HUD.
- [ ] Set Creation Energy.
- [ ] Set progression stage.
- [ ] Apply Affix.
- [ ] Set rarity.
- [ ] Add socket.
- [ ] Spawn test bosses.
- [ ] Reset SERAPH-Ω heat.
- [ ] Debug overlay with energy, progression, weapon data, affix values, and compat status.
- [ ] Keep `Fish of Unreasonable Power` creative-only.
- [ ] Tooltip text must include: `可食部は見当たらない。`

### Phase 10 — UI polish and compatibility testing

- [ ] Dedicated font treatment where technically practical.
- [ ] Moon / star visual language.
- [ ] Base palette: white, black, blue/cyan emission.
- [ ] Celestial rarity glow.
- [ ] Genesis rarity high-end star effect.
- [ ] NULL rarity red/black glitch treatment.
- [ ] UI sound set: open, close, select, reforge, affix proc, energy full, style rank up.
- [ ] Vanilla HUD overlap tests.
- [ ] Epic Fight-only tests.
- [ ] TaCZ-only tests.
- [ ] Apotheosis-only tests.
- [ ] Epic Fight + TaCZ + Apotheosis combined tests.
- [ ] Multiplayer synchronization tests.

## v0.7 — Signature Armaments

After v0.6 is stable, continue into the signature weapon implementation pass:

- GENESIS EDGE // LUNAR-Ω.
- ASTRAL SIEGE // SERAPH-Ω.
- CELESTIAL ARMAMENT // GENESIS-01.
- Full Epic Fight / TaCZ combat integration.
- STYLE gameplay effects.
- Advanced weapon animations and transformation states.

## Priority rule

Do not add more major content before Phase 1 and the basic Phase 2 HUD are stable. Fix synchronization and data persistence before visual polish.