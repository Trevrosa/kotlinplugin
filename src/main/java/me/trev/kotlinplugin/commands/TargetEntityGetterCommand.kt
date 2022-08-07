package me.trev.kotlinplugin.commands

import me.trev.kotlinplugin.commands.TargetEntitySetterCommand.targetEntity
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Server
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object TargetEntityGetterCommand : CommandExecutor {

    private fun Server.say(text: String, color: TextColor = TextColor.color(255, 255, 255)) {
        val component = Component.text(text).color(color)
        this.sendMessage(component)
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            sender.server.say("the current targeted entity is a $targetEntity")
        }
        else {
            sender.server.say("only players can use this command")
        }

        return true
    }
}