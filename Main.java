import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        // Clear the terminal
        System.out.print("\033\143");

        double score = 0.0;

        ArrayList<String> suggestions = new ArrayList<String>();

        String writing = FileHelper.textToString("examples/writing2.txt");

        String[] splitWriting = writing.split("(?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)(?<=\\.|\\?)\\s");

        double avgSentLength = 0;
        double avgWordLength = 0;
        int sentenceSum = 0;
        int wordSum = 0;
        int wordNum = 0;

        int i = 0;
        
        Brint.print("&bold&Your Essay&reset&");
        System.out.println("\nNote: Format of the essay is (Sentence Word)");

        for (String sentence : splitWriting)
        {
            i++;

            // Print out the sentence with the word count and sentence number
            System.out.println();
            Brint.print("&italic&(" + i + " " + sentenceSum + ") &reset&" + sentence);

            if (!Character.isUpperCase(sentence.charAt(0)))
            {
                suggestions.add("&yellow&You should start probably start sentence " + i + " with a captial");
            }

            // Split the sentence into words
            String[] words = sentence.split(" ");
            sentenceSum += words.length;

            // Loop through each word in each of the sentences
            for (String word : words)
            {
                wordSum += word.length();
                wordNum ++;

                // Check if the word is in the misspelled list
                for (int j = 0; j < Dictionary.misspelled.length; j++)
                {
                    for (String misspelled : Dictionary.misspelled[j])
                    {
                        if (word.equals(misspelled))
                        {
                            suggestions.add("&yellow&Looks like you misspelled " + Dictionary.correctlySpelled[j] + " in sentence " + i);
                        }
                    }
                }                

            }
        }

        // Calculate Average

        avgSentLength = (double)sentenceSum/splitWriting.length;
        avgWordLength = (double)wordSum/wordNum;

        // Print out all the statistics

        System.out.println("");
        Brint.print("&blue&&bold&Overall Analysis");
        System.out.println("");
        Brint.print("&blue&⎧ Average Senetnece Length: " + Math.round(avgSentLength * 10) / 10.0);
        Brint.print("&blue&⎪ Average Word Length " + Math.round(avgWordLength * 100) / 100.0);
        Brint.print("&blue&⎪ Total Sentences: " + splitWriting.length);
        Brint.print("&blue&⎩ Total Words: " + sentenceSum);
        System.out.println("");

        // Check the average sentence length
        if (avgSentLength < 15){        
           Brint.print("&red&Looks like your sentences are short. consider increasing your sentence overall sentnece length.");
           System.out.println("");

           // Decrease the score by how far they are from the correct amount of length
           score -= (15 - avgSentLength) * 2;
        }
        else if (avgSentLength > 25){
            Brint.print("&red&Looks like your sentences are long. Consider decreasing your sentence length to make your ideas more consise.");
            System.out.println("");

            // Decrease the score by how far they are from the correct amount of length 
            score -= (avgSentLength - 25) * 2;
        }

        // Check if the average word length is low
        if (avgWordLength < 4.0)
        {
            Brint.print("&red&Seems like your average word length is low. Consider increasing your vocabulary or deleting useless words.");
            System.out.println("");

            // Reduce the score by how far they are from the requirement
            score -= (4 - avgWordLength) * 4;
        }

        // Print all spelling suggestions
        if (suggestions.size() == 0)
        {
            Brint.print("&yellow&No spelling suggestions.");
        }
        else 
        {
            Brint.print("&yellow&Spelling Suggestions");
            for (String suggestion : suggestions)
            {
                Brint.print("&yellow& - " + suggestion);
            }

            // Decrease the score by the formula ln(n^3)+1 as making multiple mistakes should decrease the score but not get overblown
            score -= Math.log(Math.pow(suggestions.size(), 3)) + 1;
        }

        // Add ln(num of words) to the score, encuraging longer essays but not overly giving score

        score += Math.log(sentenceSum);

        System.out.println("");


        // Print in either red or green text depending on if the score is positive or negative
        if (score > 0)
        {
            Brint.print("&green&Overall Score: &bold&" + Math.round(score * 100) / 100.0);
        }
        else
        {
            Brint.print("&red&Overall Score: &bold&" + Math.round(score * 100) / 100.0);
        }
        

    }


}