package com.epam.jwd.core_final.context.strategy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VerticalInputStrategy implements InputStrategy {

    @Override
    public List<String> execute(String filePath) throws IOException {
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            List<String> spaceshipsStringList = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                spaceshipsStringList.add(line);
                line = reader.readLine();
            }
            spaceshipsStringList.removeIf(s -> s.startsWith("#"));
            return spaceshipsStringList;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
