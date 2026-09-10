
# Celestial Genesis

NeoForge 1.21.1 mod. Current version: **v0.3.0**.

## v0.3 Astral Exploration

Adds the Astral Realm, Astral Wastes biome, three worldgen structure entries, dungeon loot, keys and boss seals,
Astral Sentinel dungeon mobs, Moonlit Guardian and Astral Watcher bosses, boss bars and drops, exploration stages,
dungeon-only forging, Astral Star Shards, a gameplay HUD, and datapack registries for dungeon/boss definitions.

## v0.2 Genesis Forging

- Craft and place a Genesis Forge.
- Hold supported equipment in one hand and a Genesis Catalyst in the other, then use the forge to roll or reroll rarity and affixes.
- Hold a Star Shard in one hand and forged equipment in the other to fill a Star Socket.
- Sneak-use the forge with equipment to remove its most recently inserted shard.
- Common, Rare, Epic, and Celestial rarities control affix and socket capacity.
- Weapon and armor affixes affect damage, skill cost/power, resistance, recovery, and Creation Energy regeneration.
- Public compatibility API v1 exposes immutable forging snapshots, modifier lookup, custom equipment predicates, and external affix registration.
- Epic Fight item capabilities give Lunar Genesis a longsword moveset and Celestial armor stun/weight data.
- TaCZ guns are detected as Genesis Forge weapons and receive forged damage modifiers.
- Apotheosis affix-loot entries allow Celestial equipment to participate in its affix system.

## v0.1

- Lunar Genesis (netherite-tier sword)
- Lunar Sweep right-click ability (10 damage, 6-block cone, 3-second cooldown)
- Creation Energy foundation (100 maximum, 25 skill cost, 1/second regeneration)
- Celestial Hood, Coat, Leggings, and Boots (diamond-tier baseline)
- Celestial Genesis creative tab
- Reserved compatibility entry points for Epic Fight, TaCZ, and Apotheosis

The v0.1 item models deliberately use vanilla placeholder textures. They can be replaced under
`assets/celestialgenesis/textures/item` without changing gameplay code.

## Development

Installation information
=======

This template repository can be directly cloned to get you started with a new
mod. Simply create a new repository cloned from this one, by following the
instructions provided by [GitHub](https://docs.github.com/en/repositories/creating-and-managing-repositories/creating-a-repository-from-a-template).

Once you have your clone, simply open the repository in the IDE of your choice. The usual recommendation for an IDE is either IntelliJ IDEA or Eclipse.

If at any point you are missing libraries in your IDE, or you've run into problems you can
run `gradlew --refresh-dependencies` to refresh the local cache. `gradlew clean` to reset everything 
{this does not affect your code} and then start the process again.

Mapping Names:
============
By default, the MDK is configured to use the official mapping names from Mojang for methods and fields 
in the Minecraft codebase. These names are covered by a specific license. All modders should be aware of this
license. For the latest license text, refer to the mapping file itself, or the reference copy here:
https://github.com/NeoForged/NeoForm/blob/main/Mojang.md

Additional Resources: 
==========
Community Documentation: https://docs.neoforged.net/  
NeoForged Discord: https://discord.neoforged.net/
