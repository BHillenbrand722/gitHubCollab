package githubcollab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GithubCollab {

    public static ArrayList<String> resultList = new ArrayList<>();
    public static ArrayList<String> files = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getAllFiles();
        readAllFiles();
        resultList.forEach((n) -> System.out.println(n));
    }

    private static void getAllFiles() throws IOException {
        String userDir = System.getProperty("user.dir");
        File file = new File(userDir + "/.." + "\\CollabWithGithub");
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i].toString());
            }
            if (tempList[i].isDirectory()) {
            }
        }
    }

    private static void readAllFiles() {
        files.forEach((f) -> {
            try {
                readFile(f);
            } catch (IOException ex) {
                Logger.getLogger(GithubCollab.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private static void readFile(String fileName) throws FileNotFoundException, IOException {
        File inputFile = new File(fileName);
        try (Scanner in = new Scanner(inputFile)) {
            String result = "";
            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.contains("Username")) {
                    result += line + " ";
                }

                if (line.contains("Password")) {
                    result += line;
                }
            }
            if (result.length() != 0) {
                resultList.add(result);
            }
        }
    }

}
