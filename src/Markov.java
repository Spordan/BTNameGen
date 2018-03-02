import java.util.*;

public class Markov
{
    private ArrayList<String> wordList;
    private Random rand;
    private String newName;
    private int chanceOfEnd = 0;

    /**
     * Constructor taking in word list
     * @param wordList the array of names
     */
    public Markov(ArrayList<String> wordList) {
        this.wordList = wordList;

        //init random
        rand = new Random();
    }

    public String GetName() {
        GetStartOfName();

        return newName;
    }

    public void GetStartOfName() {
        //get a random word from array
        int wordIndex = rand.nextInt(wordList.size());
        String startName = wordList.get(wordIndex);

        //get initial letter
        int charIndex = rand.nextInt(startName.length());
        char startLetter = startName.toLowerCase().charAt(charIndex);

        newName = "" + startLetter;

        GetNextLetter(startLetter + "");
    }

    /**
     * Get the next possible letter for the sequence
     * @param aLetter 1-3 letters to test against
     */
    private void GetNextLetter(String aLetter) {
        //test if word should end
        if(newName.length() > 4) {
            chanceOfEnd = chanceOfEnd + 10;
        }
        if(rand.nextInt(100) < chanceOfEnd) {
            newName = newName.substring(0,1).toUpperCase() + newName.substring(1); //capitalise
            return;
        }

        //create a list to hold proceeding letters found
        ArrayList<Character> followingLetter = new ArrayList<>();

        int charEnds = 0; //how often char was end of word

        //iterate through each name
        for(String s : wordList) {
            s = s.toLowerCase(); //convert to lowercase to match letters against

            if(s.toLowerCase().contains(""+aLetter)) {

                //split string where sequence found
                String segments[] = s.split(aLetter);

                if(segments.length > 0) {
                    followingLetter.add(segments[segments.length-1].charAt(0));
                }
            }
        }

        //pick a letter from the array
        char nextChar;
        if(followingLetter.size() > 0) {
            int result = rand.nextInt(followingLetter.size());
            nextChar = followingLetter.get(result);
        } else {
            GetNextLetter(newName.substring(Math.max(newName.length() - 1, 0)));
            return;
        }

        //assign new character to name variable
        newName = newName + nextChar;

        if(newName.length() >= 3) {
            GetNextLetter(newName.substring(Math.max(newName.length() - 3, 0)));
        } else if(newName.length() >= 2) {
            GetNextLetter(newName.substring(Math.max(newName.length() - 2, 0)));
        } else {
            GetNextLetter(nextChar + "");
        }
    }
}