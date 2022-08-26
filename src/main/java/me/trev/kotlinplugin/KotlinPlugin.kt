@file:Suppress("SpellCheckingInspection")

package me.trev.kotlinplugin

import me.trev.kotlinplugin.commands.*
import me.trev.kotlinplugin.listeners.EntityBreedListener
import me.trev.kotlinplugin.listeners.InventoryDragListener
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class KotlinPlugin : JavaPlugin() {
    companion object {
        var instance: KotlinPlugin? = null
            private set
    }

//    private fun Server.say(text: String, color: TextColor? = null)
//    {
//        if (color != null) {
//            val component = Component.text(text).color(color)
//            this.sendMessage(component)
//            return
//        }
//
//        val component = Component.text(text)
//        this.sendMessage(component)
//    }

    fun registerEvent(event: Listener) {
        Bukkit.getPluginManager().registerEvents(event, this)
    }

    override fun onEnable() {
        instance = this

        logger.info("registar the listeners...")
        Bukkit.getPluginManager().registerEvents(EntityBreedListener, this)
        Bukkit.getPluginManager().registerEvents(InventoryDragListener, this)

        logger.info("registar the commands...")
        getCommand("pig")?.setExecutor(PigCommand)
        getCommand("target")?.setExecutor(ManageTargetEntityCommand)
        getCommand("speed")?.setExecutor(ManageSpeedCommand)
        getCommand("equip")?.setExecutor(EquipItemCommand)
        getCommand("mergeenchant")?.setExecutor(MergeEnchantsCommand)
        getCommand("entitycount")?.setExecutor(EntityCountCommand)
        getCommand("tntmultiply")?.setExecutor(TntMultiplyCommand)

        logger.info("finishd")
    }

    override fun onDisable() {
        logger.info("not loaded anymore :sob:")
    }
}