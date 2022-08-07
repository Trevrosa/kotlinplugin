@file:Suppress("SpellCheckingInspection")

package me.trev.pigs

// import me.trev.pigs.commands.PigCommand
import me.trev.pigs.commands.PigCommand
import me.trev.pigs.listeners.ExpBottleListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class Pigs : JavaPlugin() {
    companion object {
        var instance: Pigs? = null
            private set
    }

    override fun onEnable() {
        logger.info("registar the listener...")
        Bukkit.getPluginManager().registerEvents(ExpBottleListener, this)
        logger.info("registar the commands...")
        getCommand("test")?.setExecutor(PigCommand)
        instance = this
        logger.info("finishd")
    }

    override fun onDisable() {
        logger.info("not loaded anymore :sob:")
    }
}