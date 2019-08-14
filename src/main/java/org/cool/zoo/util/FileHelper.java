package org.cool.zoo.util;

import java.io.*;

public class FileHelper {

    public static void writeToFile(InputStream IS, String uploadedFileLocation) {
        OutputStream os = null;
        try {

            int read = 0;
            byte[] bytes = new byte[1024];
            os = new FileOutputStream(new File(uploadedFileLocation));

            while ((read = IS.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }

            os.flush();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not found" + e);

        } finally {

            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (IS != null) {
                try {
                    IS.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
