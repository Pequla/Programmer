package com.pequla.bot.programmer.modules.commands.joke;

public class Joke {
    private final String setup;
    private final String punchline;

    public Joke(String setup, String punchline) {
        this.setup = setup;
        this.punchline = punchline;
    }

    public String getSetup() {
        return setup;
    }

    public String getPunchline() {
        return punchline;
    }
}
