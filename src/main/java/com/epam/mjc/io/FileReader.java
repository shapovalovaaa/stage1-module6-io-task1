package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        return parseProfileData(readFileData(file));
    }

    private static String readFileData(File file) {
        StringBuilder data = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                data.append(new String(buffer, 0, bytesRead));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }

    private static Profile parseProfileData(String fileData) {
        Profile profile = new Profile();

        String[] lines = fileData.split("\n");
        for (String line : lines) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                String key = parts[0].trim();
                String value = parts[1].trim();

                if (key.equalsIgnoreCase("Name")) {
                    profile.setName(value);
                } else if (key.equalsIgnoreCase("Age")) {
                    int age = Integer.parseInt(value);
                    profile.setAge(age);
                } else if (key.equalsIgnoreCase("Email")) {
                    profile.setEmail(value);
                } else if (key.equalsIgnoreCase("Phone")) {
                    profile.setPhone(Long.valueOf(value));
                }
            }
        }

        return profile;
    }
}
