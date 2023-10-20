import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Brint {
    /*
     * Should be used like &red& Hi there! to print out red text
     */

    private static Map<String,String> colours = new HashMap<String, String>();
    static {
        colours.put("reset","\u001B[0m");

        colours.put("bold", "\u001b[1m");
        colours.put("ul", "\u001b[4m");
        colours.put("italic", "\u001b[3m");

        colours.put("red","\u001B[31m");
        colours.put("blue","\u001B[34m");
        colours.put("green","\u001B[32m");
        colours.put("yellow","\u001b[33m");
    }

    public static void printEmpty()
    {
        System.out.println();
    }

    public static void print(String str)
    {
        String[] splitString = str.split("&");
        String finalString = "";
        for (String split : splitString)
        {
            if (colours.containsKey(split))
            {
                finalString += colours.get(split);
            }
            else
            {
                finalString += split;
            }
        }

        System.out.println(colours.get("reset") + finalString + colours.get("reset"));
    }

    public static void printList(String title, ArrayList<String> strL, boolean style)
    {
        print("");
        print(title);
        for (int i = 0; i < strL.size(); i++)
        {
            if (style){
                if (i == 0)
                {
                    System.out.print("⎧ ");
                }
                else if (i == strL.size() - 1)
                {
                    System.out.print("⎩ ");
                }
                else
                {
                    System.out.print("⎪ ");
                }
            }

            print(strL.get(i));
        }
        print("");
    }

    public static void printList(String[] strL, boolean style)
    {
        print("");
        for (int i = 0; i < strL.length; i++)
        {
            if (style){
                if (i == 0)
                {
                    System.out.print("⎧ ");
                }
                else if (i == strL.length - 1)
                {
                    System.out.print("⎩ ");
                }
                else
                {
                    System.out.print("⎪ ");
                }
            }

            System.out.println(strL[i]);
        }
        print("");
    }
}