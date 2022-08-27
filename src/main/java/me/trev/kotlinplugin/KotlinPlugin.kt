@file:Suppress("SpellCheckingInspection")

package me.trev.kotlinplugin

import me.trev.kotlinplugin.commands.*
import me.trev.kotlinplugin.listeners.EntityBreedListener
import me.trev.kotlinplugin.listeners.InventoryDragListener
import me.trev.kotlinplugin.listeners.TntMultiplier
import org.bukkit.Bukkit
import org.bukkit.command.CommandExecutor
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask

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

    fun runTask(runnable: Runnable): BukkitTask {
        return Bukkit.getScheduler().runTask(this, runnable)
    }

    fun runTaskAsync(runnable: Runnable): BukkitTask {
        return Bukkit.getScheduler().runTaskAsynchronously(this, runnable)
    }

    fun runTaskTimer(runnable: Runnable, delay: Long, period: Long): BukkitTask {
        return Bukkit.getScheduler().runTaskTimer(this, runnable, delay, period)
    }

    @Suppress("unused")
    fun cancelTask(taskId: Int?) {
        if (taskId == null) {
            return
        }

        Bukkit.getScheduler().cancelTask(taskId)
    }

    fun cancelTasks(taskIds: List<Int>?) {
        if (taskIds.isNullOrEmpty()) {
            return
        }

        for (id in taskIds) {
            Bukkit.getScheduler().cancelTask(id)
        }
    }

    @Suppress("unused")
    fun cancelAllTasks() {
        Bukkit.getScheduler().cancelTasks(this)
    }

    private fun setCommandExecutor(name: String, executor: CommandExecutor) {
        getCommand(name)?.setExecutor(executor)
    }

    override fun onEnable() {
        instance = this

        logger.info("registar the listeners...")
        this.registerEvent(EntityBreedListener)
        this.registerEvent(InventoryDragListener)
        this.registerEvent(TntMultiplier)

        logger.info("registar the commands...")
        this.setCommandExecutor("pig", PigCommand)
        this.setCommandExecutor("target", ManageTargetEntityCommand)
        this.setCommandExecutor("speed", ManageSpeedCommand)
        this.setCommandExecutor("equip", EquipItemCommand)
        this.setCommandExecutor("mergeenchant", MergeEnchantsCommand)
        this.setCommandExecutor("entitycount", EntityCountCommand)
        this.setCommandExecutor("tntmultiply", TntMultiplyCommand)

        logger.info("finishd")
    }

    override fun onDisable() {
        logger.info("not loaded anymore :sob:")
    }
}