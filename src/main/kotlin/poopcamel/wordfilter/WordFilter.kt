package poopcamel.wordfilter

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.plugin.java.JavaPlugin

class WordFilter : JavaPlugin(), Listener {
    private val configFile
        get() = dataFolder.resolve("config.yml")
    override fun onEnable() {
        saveDefaultConfig()
        logger.info("卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 !")
        server.pluginManager.registerEvents(this, this)
    }

    override fun onDisable() {
        logger.info("卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 !")
    }
    // Makes config.yml!!!
    override fun saveDefaultConfig() {
        if (!configFile.exists()) {
            config.options().copyDefaults(true)
            saveConfig()
        }
    }
    //Replaces message and such!
    @EventHandler
    fun onPlayerChat(event: AsyncPlayerChatEvent) {
        val message = event.message
        val replacements = config.getConfigurationSection("replacements")?.getKeys(false)

        if (replacements != null) {
            var modifiedMessage = message
            for (key in replacements) {
                val replacement = config.getString("replacements.$key") ?: continue
                modifiedMessage = modifiedMessage.replace(key, replacement, true)
            }
            if (message != modifiedMessage) {
                event.message = modifiedMessage
            }
        }
    }
}
