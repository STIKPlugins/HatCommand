# HatCommand - Highly Configurable Hat Plugin

**HatCommand** is a customizable Minecraft plugin that adds a `/hat` command, allowing players to wear blocks on their head with adjustable mechanics.

**Join our** [**Discord**](https://discord.gg/YGzA4UxzFB) — discover other cool plugins, suggest new features, or get help with configuration.

### Features ✨

* **Instant Block Hats**
  - Wear any block you’re holding as a helmet
  - Optional whitelist of permitted materials
  
- **Cooldown Control**
  - Configurable cooldown in milliseconds
  - Prevents spammy hat changes
  
* **Permission System**
  - Separate perms for using vs. reloading
  - Keeps your server secure

- **Easy Reload**
  - `/hat reload` to apply config changes on the fly

### Main config (`config.yml`) ⚙️

<details>
  <summary><strong>config.yml</strong></summary>

```yaml
# Join our discord - https://discord.gg/YGzA4UxzFB you can find another good plugins there.
# Permissions
# "hat.use"    - permission required to use "/hat" command
# "hat.reload" - permission required to use "/hat reload" command

# cooldown in milliseconds for "/hat" command
cooldown: 5000

# if enabled: true, "/hat" will work only with permitted materials
materialsEnabled: false
# materials that can be used in "/hat" command
materials:
    - GLASS
    - STONE
    - IRON_BLOCK
```

</details>

### Messages Config (`messages.yml`)

<details>
  <summary><strong>messages.yml</strong></summary>

```yaml
notPlayer:          "<red>✘ <white>Only players can use this command!"
noPermission:       "<red>✘ <white>You don't have permission!"
noItem:             "<red>✘ <white>You're not holding any item!"
needOneItem:        "<red>✘ <white>You must hold exactly 1 item!"
wrongMaterial:      "<red>✘ <white>You can't use this material!"

# %cooldown% will be replaced with actual cooldown
cooldown:           "<red>✘ <white>You must wait %cooldown%s before using this command again!"
successful:         "<green>✔ <white>You placed the item on your head!"

noPermissionToReload: "<red>✘ <white>You don't have permission!"
configReloaded:       "<green>✔ <white>Config reloaded!"
```

</details>

### Permissions 🔐

| Permission Node | Description                 |
| --------------- | --------------------------- |
| `hat.use`       | Basic `/hat` command access |
| `hat.reload`    | Permission to reload config |

### Commands 📟

| Command       | Description                   |
| ------------- | ----------------------------- |
| `/hat`        | Wear the block you’re holding |
| `/hat reload` | Reload the plugin config      |
