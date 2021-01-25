package com.pequla.bot.programmer.modules.commands.joke;

import com.pequla.bot.programmer.Main;
import com.pequla.bot.programmer.modules.commands.GuildCommand;
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

public class JokeCommand implements GuildCommand {
    @Override
    public void execute(Member member, TextChannel channel, String[] args) {
        try {
            Joke joke = retrieveJoke();
            if (joke != null) {
                channel.sendMessage(
                        new EmbedBuilder()
                                .setColor(Main.ORANGE_COLOR)
                                .setTitle(MarkdownUtil.bold("Random Joke"))
                                .setDescription(joke.getSetup() + System.lineSeparator() + "  - " + joke.getPunchline())
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
    //

    private Joke retrieveJoke() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        URL api = new URL("https://official-joke-api.appspot.com/random_joke");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(api.openConnection().getInputStream()));
        String lines;
        while ((lines = bufferedReader.readLine()) != null) {
            JSONArray array = new JSONArray();
            array.add(parser.parse(lines));
            for (Object o : array) {
                JSONObject jsonObject = (JSONObject) o;
                return new Joke((String) jsonObject.get("setup"), (String) jsonObject.get("punchline"));
            }
        }
        return null;
    }

}
