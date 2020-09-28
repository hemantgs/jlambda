package com.hemant.jlambda.runner;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.hemant.jlambda.events.Build;
import com.hemant.jlambda.events.Event;
import com.hemant.jlambda.events.Generate;
import com.hemant.jlambda.events.Publish;
import com.hemant.jlambda.model.LambdaConfig;

public class EventsRunner {

    public LambdaConfig config;
    public ParsedIntent parsedIntent;

    public void run() {
        Consumer<Event> executeEvent = event -> event.execute();

        //Generate java lambda project
        onGenerate().ifPresent(executeEvent);

        //Build deployment package alone
        onBuild().ifPresent(executeEvent);

        //Build and publish
        onPublish().ifPresent(event -> {
            onBuild().ifPresentOrElse(executeEvent, () -> new IllegalAccessException("Cannot Build Packages"));
            executeEvent.accept(event);
        });
    }

    private Optional<Event> onGenerate() {
        if (Objects.nonNull(parsedIntent.generate)) {
            return Optional.of(new Generate(parsedIntent.generate));
        }
        return Optional.empty();
    }

    private Optional<Event> onBuild() {
        if (Objects.nonNull(parsedIntent.build)) {
            return Optional.of(new Build(parsedIntent.build));
        }
        if (Objects.nonNull(parsedIntent.publish)) {
            return Optional.of(new Build(parsedIntent.publish));
        }
        return Optional.empty();
    }

    private Optional<Event> onPublish() {
        if (Objects.nonNull(parsedIntent.publish)) {
            Publish.setLambdaConfig(PropertyLoader.loadProperties(parsedIntent.publish));
            return Optional.of(new Publish(parsedIntent.publish));
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
