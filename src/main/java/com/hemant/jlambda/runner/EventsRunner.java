package com.hemant.jlambda.runner;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.hemant.jlambda.events.Event;
import com.hemant.jlambda.events.Generate;
import com.hemant.jlambda.model.LambdaConfig;

public class EventsRunner {

    public static void run(Runnable runnable) {
        runnable.run();
    }

    public enum ExecuteEvents {
        GENERATE, BUILD, PUBLISH;
    }
}
