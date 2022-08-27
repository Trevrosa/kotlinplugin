package me.trev.kotlinplugin.commands

import me.trev.kotlinplugin.commands.PigCommand.speed
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object ManageSpeedCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            if (args.isNullOrEmpty()) {
                sender.sendMessage("the current speed is $speed")
                return true
            }
            if (args.first().toDoubleOrNull() == null) {
                sender.sendMessage("the current speed is $speed")
                return true
            }
            if (speed == args.first().toDouble()) {
                sender.sendMessage("the speed is already $speed")
                return true
            }
            if (!sender.isOp) {
                sender.sendMessage("you need to have op to set the speed")
                return true
            }

            sender.sendMessage("changed the speed from $speed to ${args.first().toDouble()}")
            speed = args.first().toDouble()
        }
        else {
            sender.sendMessage("only players can use this command")
        }

        return true
    }
}