# rcpatch
Small coremod to patch a method in Railcraft that causes a server crash with Futurepack.

# What this mod is for
Railcraft and Futurepack have an interaction at the moment where if you have a a Thruster in your inventory with metadata greater than 11, the server will crash as Railcraft assumes that metadata can be mapped directly to a blockstate. What this mod does is make Railcraft treat any item that would otherwise cause a crash as if it is an air block.

# What this mod does

Patches Railcraft 12.0.0 at runtime to add a try-catch around a statement that crashes with Futurepack

[Original code](https://github.com/Railcraft/Railcraft/blob/mc-1.12.2/src/main/java/mods/railcraft/common/util/inventory/InvTools.java#L488):
```java
return block == null ? Blocks.AIR.getDefaultState() : block.getStateFromMeta(stack.getItemDamage());
```

Patched code:
```java
try {
  return block == null ? Blocks.AIR.getDefaultState() : block.getStateFromMeta(stack.getItemDamage());
}
catch (IllegalArgumentException e) {
  return Blocks.AIR.getDefaultState();
}
```

# Why not just patch Railcraft directly?
Railcraft's license forbids distributing compiled binaries from modified source code. This means that even though I had already produced a patched jar which applied this fix to the source code, I could not use it on a private community server. Therefore I had to learn ASM to apply this change at runtime, which took twice as long as finding and patching the problem originally did.

# You probably should not use this mod
This is a coremod written in 2 days, with no prior knowledge of coremodding nor any experience with ASM.
This mod is just a hack to prevent the game from crashing. The underlying cause of the crash is not addressed by this mod. I will not be providing support for this mod. Feel free to leave issue reports, but I make no guarantees that they will be addressed.

However, if by chance you are playing a pack with Railcraft 12.0.0 and Futurepack, this will probably prevent crashes that force a player data reset, and will allow you to login again after even after causing such a crash.

#### Or you could just stick to the first 12 colors of thrusters.
