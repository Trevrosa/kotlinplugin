@file:Suppress("SpellCheckingInspection")

package me.trev.kotlinplugin

import me.trev.kotlinplugin.commands.ManageSpeedCommand
import me.trev.kotlinplugin.commands.ManageTargetEntityCommand
import me.trev.kotlinplugin.commands.PigCommand
import me.trev.kotlinplugin.listeners.EntityListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class KotlinPlugin : JavaPlugin() {
    companion object {
        var instance: KotlinPlugin? = null
            private set
    }

    override fun onEnable() {
        logger.info("registar the listeners...")
        Bukkit.getPluginManager().registerEvents(EntityListener, this)
        logger.info("registar the commands...")
        getCommand("pig")?.setExecutor(PigCommand)
        getCommand("target")?.setExecutor(ManageTargetEntityCommand)
        getCommand("speed")?.setExecutor(ManageSpeedCommand)
        instance = this
        logger.info("finishd")
    }

    override fun onDisable() {
        logger.info("not loaded anymore :sob:")
    }
}