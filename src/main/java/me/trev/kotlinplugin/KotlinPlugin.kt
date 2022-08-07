@file:Suppress("SpellCheckingInspection")

package me.trev.kotlinplugin

import me.trev.kotlinplugin.commands.PigCommand
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class KotlinPlugin : JavaPlugin() {
    companion object {
        var instance: KotlinPlugin? = null
            private set
    }

    override fun onEnable() {
        logger.info("registar the commands...")
        getCommand("pig")?.setExecutor(PigCommand)
        instance = this
        logger.info("finishd")
    }

    override fun onDisable() {
        logger.info("not loaded anymore :sob:")
    }
}