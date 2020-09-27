package com.hemant.jlambda.runner;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.hemant.jlambda.events.Build;
import com.hemant.jlambda.events.Event;
import com.hemant.jlambda.events.Generate;
import com.hemant.jlambda.model.LambdaConfig;

public class EventsRunner {

    public LambdaConfig config;
    public ParsedIntent parsedIntent;

    public void run() {
        onGenerate().ifPresent(event -> event.execute(config));

        onBuild().ifPresent(event -> event.execute(config));
    }

    private Optional<Event> onGenerate() {
        if (Objects.nonNull(parsedIntent.generate)) {
            return Optional.of(new Generate(parsedIntent.generate));
        }
        return Optional.empty();
    }

    private Optional<Event> onBuild() {
        if (Objects.nonNull(parsedIntent.build)) {
            return Optional.of(new Build());
        }
        return Optional.empty();
    }

    public EventsRunner withIntent(ParsedIntent parsedIntent) {
        this.parsedIntent = parsedIntent;
        return this;
    }

    public EventsRunner config(LambdaConfig lambdaConfig) {
        this.config = lambdaConfig;
        return this;
    }

    public static EventsRunner build() {
        return new EventsRunner();
    }
}
