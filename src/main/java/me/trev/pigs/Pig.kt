@file:Suppress("SpellCheckingInspection")

package me.trev.pigs

import me.trev.pigs.commands.PigCommand
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class Pig : JavaPlugin() {
    companion object {
        var instance: Pig? = null
            private set
    }

    override fun onEnable() {
        logger.info("registar the command...")
        getCommand("pig")?.setExecutor(PigCommand)
        instance = this
        logger.info("finishd")
    }

    override fun onDisable() {
        logger.info("not loaded anymore :sob:")
    }
}