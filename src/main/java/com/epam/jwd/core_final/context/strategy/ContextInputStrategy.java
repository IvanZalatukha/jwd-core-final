package com.epam.jwd.core_final.context.strategy;

import java.io.IOException;
import java.util.List;

public class ContextInputStrategy {

    private InputStrategy inputStrategy;

    public ContextInputStrategy(){
    }

    public void setInputStrategy(InputStrategy inputStrategy) {
        this.inputStrategy = inputStrategy;
    }

    public List<String> executeStrategy(String filepath) throws IOException {
        return inputStrategy.execute(filepath);
    }


}
