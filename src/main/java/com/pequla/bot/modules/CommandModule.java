package com.pequla.bot.modules;

import com.pequla.bot.modules.commands.AnnounceCommand;
import com.pequla.bot.modules.commands.GuildCommand;
import com.pequla.bot.modules.commands.joke.JokeCommand;
import com.pequla.bot.modules.commands.meme.MemeCommand;
import com.pequla.bot.modules.commands.TestCommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;

public class CommandModule extends ListenerAdapter {

    private final HashMap<String, GuildCommand> map;

    public CommandModule() {
        this.map = new HashMap<>();
        map.put("!test", new TestCommand());
        map.put("!testus", new TestCommand());
        map.put("!meme", new MemeCommand());
        map.put("!joke", new JokeCommand());
        map.put("!announce", new AnnounceCommand());
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            String[] message = event.getMessage().getContentRaw().trim().split("\\s+");
            GuildCommand command = map.get(message[0].toLowerCase());
            if (command != null) {
                command.execute(event.getMember(), event.getChannel(), Arrays.copyOfRange(message, 1, message.length));
            }
        }
    }
}
