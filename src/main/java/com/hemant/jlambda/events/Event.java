package com.hemant.jlambda.events;

import java.util.function.Consumer;

import com.hemant.jlambda.model.LambdaConfig;

public interface Event {
    public void execute(LambdaConfig config);
}
