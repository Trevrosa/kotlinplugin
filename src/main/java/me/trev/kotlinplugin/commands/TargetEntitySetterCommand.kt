package me.trev.kotlinplugin.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Server
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object TargetEntitySetterCommand : CommandExecutor {

    private fun Server.say(text: String, color: TextColor = TextColor.color(255, 255, 255)) {
        val component = Component.text(text).color(color)
        this.sendMessage(component)
    }

    var targetEntity = "player"

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            if (sender.isOp && args != null && args.isNotEmpty()) {
                if (args.first().lowercase() == "pig") {
                    sender.server.say("you cannot set the target entity to a pig")
                }
                else if (args.first().lowercase() == targetEntity) {
                    sender.server.say("the target entity is already a $targetEntity")
                }

                sender.server.say("changed target entity from $targetEntity to ${args.first().lowercase()}")
                targetEntity = args.first().lowercase()
            }
        }
        else {
            sender.server.say("only players can use this command")
        }

        return true
    }
}