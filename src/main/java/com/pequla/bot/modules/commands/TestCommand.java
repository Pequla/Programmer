package com.pequla.bot.modules.commands;

import com.pequla.bot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.utils.MarkdownUtil;

import java.time.Instant;

public class TestCommand implements GuildCommand{
    @Override
    public void execute(Member member, TextChannel channel, String[] args) {
        channel.sendMessage(
                new EmbedBuilder()
                        .setColor(Main.ORANGE_COLOR)
                        .setTitle(MarkdownUtil.bold("Linkovi ka MMTutor platformi"))
                        .addField("Test (Danijelova)","https://test.singidunum.ac.rs/",false)
                        .addField("TestUS (Kumodraska)","https://testus.singidunum.ac.rs/",false)
                        .setFooter(member.getEffectiveName(),member.getUser().getEffectiveAvatarUrl())
                        .setTimestamp(Instant.now())
                        .build()
        ).queue();
    }
}
