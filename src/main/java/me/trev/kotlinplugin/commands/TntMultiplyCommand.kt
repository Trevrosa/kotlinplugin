package me.trev.kotlinplugin.commands

import me.trev.kotlinplugin.KotlinPlugin
import me.trev.kotlinplugin.listeners.TntMultiplier
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList

object TntMultiplyCommand : CommandExecutor {
    private var enabled = false

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            enabled = !enabled

            if (!enabled) {
                HandlerList.unregisterAll(TntMultiplier)
                sender.sendMessage("disabled")
                return true
            }

            KotlinPlugin.instance?.registerEvent(TntMultiplier)
            sender.sendMessage("enabled")
            return true
        }

        sender.sendMessage("you not player")
        return true
    }
}