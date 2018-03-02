import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BTNameGen {

    public String GenerateName() {

        ReadFromFile("adjectives.txt");

        return GenerateFirstName() + " " + GenerateLastName();
    }

    private String GenerateFirstName() {
        return GenerateRandomFrom(ReadFromFile("first_names.txt"));
    }

    private String GenerateLastName() {
        return GenerateRandomFrom(ReadFromFile("last_names.txt"));
    }


    public String GenerateShip() {
        return "The " + SelectRandomFrom(ReadFromFile("adjectives.txt")) + " " + SelectRandomFrom(ReadFromFile("animal.txt"));
    }

    private ArrayList<String> ReadFromFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            ArrayList<String> lines = new ArrayList<String>();
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }

            return lines;

        } catch(FileNotFoundException e) {
            return null;
        }
    }

    /**
     * Generate a new name using Markov chains
     * @param nameArray
     * @return
     */
    private String GenerateRandomFrom(ArrayList<String> nameArray) {
        //10% chance of 'regular' name
        Random rand = new Random();
        if(rand.nextInt(100) <= 10) {
            int result = rand.nextInt(nameArray.size());
            return nameArray.get(result);
        }


        //otherwise Markov chain name
        Markov markov = new Markov(nameArray);

        return markov.GetName();
    }

    /**
     * Pick a random name from the options
     * @param nameArray
     * @return
     */
    private String SelectRandomFrom(ArrayList<String> nameArray) {
        Random rand = new Random();
        int index = rand.nextInt(nameArray.size());
        String result = nameArray.get(index);

        result = result.substring(0,1).toUpperCase() + result.substring(1); //capitalise

        return result;
    }

    /**
     * Clean up a text file of names
     * @param nameArray
     */
    public void FormatTextHelper(ArrayList<String> nameArray) {
        for(int i = 0; i < nameArray.size(); i++) {

            String tmp = nameArray.get(i);

            tmp = tmp.replace("\"", "");
            tmp = tmp.replace("\'", "");
            tmp = tmp.replace(",", "");
            tmp = tmp.replace("-", "");
            tmp = tmp.replace("\t", " ");
            if(tmp.contains(" ")) {
                tmp = tmp.substring(0, tmp.indexOf(" "));
            }

            nameArray.set(i, tmp);

            System.out.println(nameArray.get(i));
        }
    }
}
