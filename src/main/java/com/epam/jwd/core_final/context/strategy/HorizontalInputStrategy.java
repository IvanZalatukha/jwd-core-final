package com.epam.jwd.core_final.context.strategy;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class HorizontalInputStrategy implements InputStrategy {

    @Override
    public List<String> execute(String filePath) throws IOException {
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            if (reader.readLine().startsWith("#")){
                reader.skip(0);
            }
            return Arrays.asList(reader.readLine().split(";"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
