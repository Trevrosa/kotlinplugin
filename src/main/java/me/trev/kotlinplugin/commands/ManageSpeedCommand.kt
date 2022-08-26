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
                sender.sendMessage("you did not specify anything")
                return true
            }
            if (args.first().lowercase() != "get" && args.first().lowercase() != "set") {
                sender.sendMessage("thats not correct (try using /speed get or /speed set)")
                return true
            }

            if (args.first().lowercase() == "get") {
                sender.sendMessage("the current speed is $speed")
            }
            else if (args.first().lowercase() == "set") {
                if (!sender.isOp) {
                    sender.sendMessage("you need to have op to set the speed")
                    return true
                }
                if (args.last().toDoubleOrNull() == null) {
                    sender.sendMessage("you did not specify a number")
                    return true
                }
                if (speed == args.last().toDouble()) {
                    sender.sendMessage("the speed is already $speed")
                    return true
                }

                sender.sendMessage("changed the speed from $speed to ${args.last().toDouble()}")

                speed = args.last().toDouble()
            }
        }
        else {
            sender.sendMessage("only players can use this command")
        }

        return true
    }
}