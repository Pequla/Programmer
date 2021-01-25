package com.pequla.bot.programmer.modules.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public interface GuildCommand {
    void execute(Member member, TextChannel channel, String[] args);
}
