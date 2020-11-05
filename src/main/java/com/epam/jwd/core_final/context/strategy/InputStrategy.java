package com.epam.jwd.core_final.context.strategy;

import java.io.IOException;
import java.util.List;

public interface InputStrategy {

    List<String> execute(String filePath) throws IOException;
}
