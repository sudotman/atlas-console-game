import java.util.Scanner;
import java.util.stream.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Iterator;

public class Game{

    static int round=1;
    static String entered;
    ArrayList<String> results = new ArrayList<String>();
    boolean notValid = false;
    char lastLetterCPU;
    char lastLetterUser;
    int numberOfRounds;
    boolean isRepeating;
    public static void main(String...args){
        Game gameObj = new Game();
        gameObj.StartGame();
    }

    void StartGame(){
    try{
        Scanner number= new Scanner(System.in);
        if(round ==1){
            System.out.println("Welcome to Antakshri! \n");
        
            System.out.println("How many rounds would you like to play?\n");
            numberOfRounds = number.nextInt();
        }

        System.out.println("This is round " + round + ". \n");

        System.out.println("Enter your word, must be valid.\n");

        
        Scanner s= new Scanner(System.in);
        entered = s.nextLine();
        entered = entered.toLowerCase();
        File file = new File("D:\\stuff\\words.txt");
    
        BufferedReader br = new BufferedReader(new FileReader(file));
         
        String st;

        notValid = true;
        while((st=br.readLine()) != null){
            if(round==1){
                if(st.equals(entered)){
                    results.add(entered);
                    lastLetterCPU= entered.charAt(entered.length()-1); //last letter
                    notValid=false;
                    System.out.println("Correct word!\n");
                    break;
                }
            }
            else if(round>1){
                if(st.equals(entered) && st.charAt(0) == lastLetterUser){
                    for(int i=0;i<results.size(); i++){
                        if(entered.equals(results.get(i))){
                            System.out.println("hawww match");
                            System.exit(0);
                            break;
                        }
                        else{
                            results.add(entered);
                            lastLetterCPU= entered.charAt(entered.length()-1); //last letter
                            notValid=false;
                            System.out.println("Correct word!\n");
                            break;
                        }
                    }
                    
                }
            }
        }

        if(notValid){
            System.out.println("Please enter a valid word\n");
            System.exit(0);
        }
        else{
            String output;
            BufferedReader br2 = new BufferedReader(new FileReader(file));
            

            isRepeating = false;
            outerloop:
            while((output=br2.readLine())!= null){
                if(output.charAt(0) == lastLetterCPU){
                    if(results.isEmpty() == false){
                        for(int i=0;i<results.size();i++){
                            System.out.println(i);
                            if(output.equals(results.get(i))){
                                System.out.println("hawww match");
                                continue outerloop;
                                
                            }
                            else{
                                OutputAWord(output);
                                break;
                            }
                        }
                    }
                    else if(round ==1){
                        OutputAWord(output);
                        break;
                    }
                }
            }
            
        }
    }
    catch(IOException e){
        System.out.println("Exception");
    }

    }

    void OutputAWord(String word){
        System.out.println("Computer says: "+ word);
        results.add(word);
        round++;
        if(round>numberOfRounds){
            System.out.println("\n Thank you for playing. Goodbye!");
            for(int i=0;i<results.size();i++){
                System.out.println(results.get(i));
            }
            System.exit(0);
        }
        else{
            lastLetterUser = word.charAt(word.length()-1);
            StartGame();
        } 
    }
}
