# AGENTS.md — Celestial Genesis

This repository is a Minecraft 1.21.1 NeoForge mod using Java 21.

## Primary objective

Implement the roadmap in `ROADMAP.md` in small, buildable increments. The current priority is **v0.6 System Rework / Celestial Interface**.

## Non-negotiable rules

1. Keep the mod bootable without Epic Fight, TaCZ, or Apotheosis installed.
2. Optional integrations must remain isolated under compatibility code and guarded by mod-presence checks.
3. Server-authoritative gameplay state must not be inferred only from client-side HUD code.
4. Synchronize player-owned gameplay data explicitly when the client needs to render it.
5. Preserve existing save data where practical. If a migration is needed, add safe defaults and avoid crashing old worlds.
6. Do not delete existing v0.1-v0.3 gameplay while implementing v0.6.
7. Avoid putting major rendering logic directly in `CelestialGenesisClient`; move HUD and UI responsibilities into dedicated client packages.
8. Keep code organized and readable. Prefer small classes with clear responsibilities over monolithic event handlers.
9. Every implementation pass should compile before moving to the next phase.
10. Treat accessibility settings as first-class requirements for flashes, glitches, screen shake, and motion-heavy UI.

## Current code facts

- `CreationEnergy` is still a minimal persistent-data implementation and should be replaced/refactored before advanced HUD work.
- `CelestialGenesisClient` currently contains a temporary text HUD drawn directly from a GUI render event.
- Genesis Forging, Affixes, sockets, Astral progression, entities, worldgen, and optional compatibility scaffolding already exist and must be audited rather than re-created blindly.

## v0.6 implementation order

### 1. Data and synchronization

Start here before visual work.

- Introduce a dedicated player-state abstraction for Creation Energy.
- Keep the server authoritative.
- Add explicit client synchronization for energy and exploration/progression data.
- Sync on login, respawn, dimension change, and when values change.
- Verify multiplayer isolation: one player's state must never bleed into another player's HUD or progression.
- Audit forging persistence and protect rarity/affix/socket data through reforge operations.

Acceptance criteria:

- Energy changes on the server are reflected correctly in the HUD.
- Reconnect / respawn / dimension transitions keep correct values.
- Two players can have different energy and progression values without desync.

### 2. HUD architecture

Create a dedicated package similar to:

```text
com.yossy.celestialgenesis.client
├── hud
│   ├── CelestialHudRenderer.java
│   ├── CreationEnergyHud.java
│   ├── ProgressHud.java
│   ├── SkillHud.java
│   └── StyleHud.java
├── screen
└── config
```

Do not hardcode the final interface into one event subscriber.

HUD requirements:

- GUI-scale safe.
- Aspect-ratio safe.
- Configurable anchors/offsets where reasonable.
- Texture-backed presentation rather than only text rectangles.
- Graceful fallback if an optional compat mod is absent.

Initial HUD target:

```text
                  ✦ 74 / 100
             ═══════◈═══════
               CREATION

                         [Q] Lunar Sweep
                              ◉ READY

       SSS
     STYLE
```

### 3. Genesis Forge screen

Replace interaction-only behavior with a proper menu/screen architecture.

Recommended separation:

```text
menu/GenesisForgeMenu.java
screen/GenesisForgeScreen.java
blockentity/GenesisForgeBlockEntity.java
```

Only introduce a block entity if persistent inventory/state is actually required. Do not add one only for visuals.

The screen should eventually support:

- Equipment slot.
- Catalyst/material slots.
- Reforge action.
- Rarity and affix display.
- Socket display.
- Stat delta comparison.
- Rarity-specific animation states.

### 4. Equipment details

Create reusable presentation code for:

- Rarity.
- Affixes.
- Socket state.
- Weapon level.
- Awakening placeholder.
- Skills.
- Set bonuses.

Avoid duplicating formatting logic between tooltips and screens; centralize formatting/model conversion where practical.

### 5. Combat UI foundation

Implement only after core sync + base HUD are reliable.

Prepare APIs/interfaces for:

- Combo counter.
- STYLE rank.
- Perfect Dodge / Perfect Guard notifications.
- Target lock.
- Break gauge.
- Later TaCZ and Epic Fight integration.

Do not hard-depend on TaCZ or Epic Fight from core classes.

## Optional compatibility architecture

Target organization:

```text
compat/
├── apotheosis/
├── epicfight/
└── tacz/
```

Compatibility classes may be loaded conditionally. Core code must not directly reference optional-mod classes in code paths that load when the mod is absent.

## Signature weapons reserved for v0.7+

Do not prioritize these ahead of v0.6 stability:

- `GENESIS EDGE // LUNAR-Ω`
- `ASTRAL SIEGE // SERAPH-Ω`
- `CELESTIAL ARMAMENT // GENESIS-01`

Prepare extensible HUD/state APIs so they can plug in later.

## DEV content

Creative-only joke/debug equipment is intentional.

`Fish of Unreasonable Power` must remain creative/dev-only and its tooltip must include:

```text
可食部は見当たらない。
```

Do not make dev weapons obtainable through survival loot or normal recipes.

## Testing checklist

For each completed phase:

- Run the normal Gradle build.
- Verify dedicated-server compatibility where relevant.
- Test no optional compatibility mods installed.
- Test client login/reconnect.
- Test death/respawn.
- Test dimension changes.
- Test two-player state separation if networking changed.
- Confirm no duplicate event registration.
- Confirm HUD obeys `hideGui` and does not render on inappropriate screens.

## Change discipline

When implementing a roadmap item:

1. Inspect the existing implementation first.
2. Prefer adapting existing systems over duplicating them.
3. Make the smallest coherent change that moves one roadmap item to working state.
4. Build/test.
5. Update documentation/checklist state only after the behavior actually works.

See `ROADMAP.md` for the full v0.6 and v0.7 plan.