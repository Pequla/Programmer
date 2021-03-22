package com.pequla.bot.modules.commands.meme;

import com.pequla.bot.modules.commands.GuildCommand;
import com.pequla.bot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.utils.MarkdownUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.Instant;

public class MemeCommand implements GuildCommand {

    @Override
    public void execute(Member member, TextChannel channel, String[] args) {
        try {
            Meme meme = retrieveMeme();
            if (meme != null) {
                channel.sendMessage(
                        new EmbedBuilder()
                                .setColor(Main.ORANGE_COLOR)
                                .setTitle(meme.getTitle(), meme.getLink())
                                .setImage(meme.getImage())
                                .setTimestamp(Instant.now())
                                .build()
                ).queue();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            channel.sendMessage(
                    new EmbedBuilder()
                            .setColor(Main.RED_COLOR)
                            .setTitle(MarkdownUtil.bold("Exception: " + e.getClass().getName()))
                            .setDescription(e.getMessage())
                            .setTimestamp(Instant.now())
                            .build()
            ).queue();
        }
    }

    private Meme retrieveMeme() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        URL api = new URL("https://meme-api.herokuapp.com/gimme");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(api.openConnection().getInputStream()));
        String lines;
        while ((lines = bufferedReader.readLine()) != null) {
            JSONArray array = new JSONArray();
            array.add(parser.parse(lines));
            for (Object o : array) {
                JSONObject jsonObject = (JSONObject) o;
                return new Meme((String) jsonObject.get("postLink"), (String) jsonObject.get("title"), (String) jsonObject.get("url"));
            }
        }
        return null;
    }
}
