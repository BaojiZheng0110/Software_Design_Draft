import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Wordle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

                String word2 = "apple";
        List<Character> list2 = stringToList(word2);

        while(true){
            String word1 = sc.nextLine(); 

            List<Character> list1 = stringToList(word1);
            
            compareList(list1, list2);

            if (word1.equals(word2)) {
                System.out.println("Correct");
                break;

            } else {
                System.out.println("Try again");
                }
            }
        
        sc.close(); 
    }

    public static List<Character> stringToList(String word) {

    List<Character> list = new ArrayList<>();

        for (char c : word.toCharArray()) {
            list.add(c);
        }
        return list;
    }

    public static void compareList(List<Character> list1, List<Character>list2) {
        for (int i = 0; i < list1.size(); i++) {
            if(list1.get(i).equals(list2.get(i))) {
                System.out.println("position " + i + " completely match");
            } else if (list2.contains(list1.get(i))) {
                System.out.println("The word contains " + list1.get(i) + ", but position is wrong");
            } else {
                System.out.println("The word does not contain letter " + list1.get(i));
            }
            }
        }
    }
