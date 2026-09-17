# Code X / Codex Entry Point

Use `AGENTS.md` as the implementation ruleset and `ROADMAP.md` as the source of truth for task order.

## Current target

**Celestial Genesis v0.6 — System Rework / Celestial Interface**

Start with Phase 1 data synchronization and persistence repair. Do not begin SERAPH-Ω, LUNAR-Ω, GENESIS-01, or major new content until the Phase 1 data layer and the basic Phase 2 HUD are stable.

## First implementation task

1. Inspect `CreationEnergy.java`, `CreationEnergyEvents.java`, `CelestialGenesisClient.java`, progression code, and existing networking code.
2. Introduce server-authoritative synchronized Creation Energy state without breaking old saves.
3. Move temporary HUD rendering out of `CelestialGenesisClient` into a dedicated client HUD package.
4. Render a basic Creation Energy bar from synchronized client state.
5. Run the Gradle build and fix compile/runtime registration errors before continuing.

After this task is working, continue sequentially through `ROADMAP.md`.
