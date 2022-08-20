@file:Suppress("SpellCheckingInspection")

package me.trev.kotlinplugin

import me.trev.kotlinplugin.commands.*
import me.trev.kotlinplugin.listeners.EntityListener
import me.trev.kotlinplugin.listeners.InventoryDragListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class KotlinPlugin : JavaPlugin() {
    companion object {
        var instance: KotlinPlugin? = null
            private set
    }

    override fun onEnable() {
        instance = this

        logger.info("registar the listeners...")
        Bukkit.getPluginManager().registerEvents(EntityListener, this)
        Bukkit.getPluginManager().registerEvents(InventoryDragListener, this)

        logger.info("registar the commands...")
        getCommand("pig")?.setExecutor(PigCommand)
        getCommand("target")?.setExecutor(ManageTargetEntityCommand)
        getCommand("speed")?.setExecutor(ManageSpeedCommand)
        getCommand("equip")?.setExecutor(EquipItemCommand)
        getCommand("mergeenchant")?.setExecutor(MergeEnchantsCommand)

        logger.info("finishd")
    }

    override fun onDisable() {
        logger.info("not loaded anymore :sob:")
    }
}