import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Position {
    private String filepath = "data/SinglePosition.txt";

    // Added to hold all lines of the file
    public List<String> allLines = new ArrayList<>();

    public String title = "";
    public String desc = "";
    public long salary = 0;
    public boolean manager = false;
    // Eventually change to the following:
    //public List<String> degrees = new ArrayList<>();
    public String degrees = "";
    public int experience = 0;
    // Same as degrees - make list later
    //public List<String> certs = new ArrayList<>();
    public String certs = "None";

    /**
     * This loads the information for the Position(s) being added to Employee.
     * @param newFile A file name (default directory) or a full filepath to load info from.
     */
    public Position(String newFile) {
        // Set full filepath if not Empty (full or just file) cause '/' seen
        if (newFile.contains("/") && !newFile.trim().isEmpty()) {
            filepath = newFile;
        } else if (!newFile.contains("/") && newFile.trim().isEmpty()) {
            // Set filepath with default directory of data (again if not empty)
            filepath = "data/"  + newFile;
        }
        // If file is just spaces or empty - ignore it & load normally
        loadFullFile();
        setPositionValues();
    }

    /**
     * Loads information for Position from default directory.
     */
    public Position() {
        // Default constructor to load default file
        loadFullFile();
        setPositionValues();
    }

    /**
     * This is used just to internally to load file for processing (into class Variable).
     */
    private void loadFullFile() {
        try {
            allLines = Files.readAllLines(Paths.get(filepath));
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    /**
     * Loads all the Position's Values into their appropriate class variables.
     */
    void setPositionValues() {
        for (int  i = 0; i < allLines.size(); i++) {
            System.out.println(i + ": " + allLines.get(i));
            if (!allLines.get(i).trim().startsWith("-")) {
                // Starts with "-" it's a new position but skip for now
                switch (i) {
                    // We could also just skip by starting with 1 (ignore 0) but...
                    case 1:
                        title = allLines.get(i).trim().toLowerCase();
                        break;
                    case 2:
                        desc = allLines.get(i).trim().toLowerCase();
                        break;
                    case 3:
                        salary = Long.parseLong(allLines.get(i).trim());
                        break;
                    case 4:
                        manager = allLines.get(i).equalsIgnoreCase("true");
                        break;
                    case 5:
                        degrees = allLines.get(i).trim().toLowerCase();
                        break;
                    case 6:
                        experience = Integer.parseInt(allLines.get(i).trim());
                        break;
                    case 7:
                        certs = allLines.get(i).trim().toLowerCase();
                        break;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Position{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", salary=" + salary +
                ", manager=" + manager +
                ", degrees='" + degrees + '\'' +
                ", experience=" + experience + " years" +
                ", certs='" + certs + '\'' +
                '}';
    }
}
