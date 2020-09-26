package com.hemant.jlambda.events;

import com.hemant.jlambda.model.LambdaConfig;

public class Generate implements Event {

    @Override
    public void execute(LambdaConfig config) {
        System.out.println(System.getProperty("user.dir"));
    }
}
