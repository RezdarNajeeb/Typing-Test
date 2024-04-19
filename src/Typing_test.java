import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Typing_test {

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static ArrayList<String> typed = new ArrayList<>();
    static boolean reset = true;

    public void start() throws InterruptedException , IOException {
        haveAccount();
    }
    public static String hashcode(String message){
        try{
            byte[] numOfMessage = message.getBytes(StandardCharsets.UTF_8);
            MessageDigest hashes = MessageDigest.getInstance("MD5");
            return String.format("%032x", new BigInteger(1, hashes.digest(numOfMessage)));
        } catch (Exception e) {
            System.out.println("Error");
            return "";
        }
    }
    public static void haveAccount() throws InterruptedException, IOException {
        File file = new File("src/Accounts.txt");

        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t>>>Typing tester<<<\n");

        while(reset) {
            System.out.println("Do you have any account?\n\n1 \u21FE Yes\n2 \u21FE No");
            String haveAccount = scanner.nextLine();

            if (haveAccount.equals("1") || haveAccount.equalsIgnoreCase("yes")) {
                accountLogging(file);
                difficulty();
                reset = false;
            }
            else if (haveAccount.equals("2") || haveAccount.equalsIgnoreCase("no")) {
                accountRegistering(file);
                accountLogging(file);
                difficulty();
                reset = false;
            }
            else {
                System.err.println("Please enter the valid value.\n");
                reset = true;
            }
        }
    }
    public static void accountRegistering(File Accounts) throws InterruptedException ,IOException{
        System.out.println("\n~~~Account registering~~~\n");
        System.out.print("Enter your username: ");
        String signup_username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String signup_password = scanner.nextLine();

        signup_username = hashcode(signup_username);
        signup_password = hashcode(signup_password);

        try{
            FileWriter writer = new FileWriter(Accounts,true);
            writer.write(signup_username + "^" + signup_password + "^");
            writer.close();
        }catch (IOException e){
            System.out.println("Error");
        }
        accountLogging(Accounts);
    }
    public static void accountLogging(File Accounts) throws InterruptedException, IOException {
        StringBuilder userN_P = new StringBuilder();

        try {
            FileReader reader = new FileReader(Accounts);
            int i;
            while ((i = reader.read()) != -1) {
                userN_P.append((char) i);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error");
            System.exit(0);
        }
        String[]  userName_Pass = userN_P.toString().split("\\^");
        System.out.println("\n~~~Account logging~~~\n");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        username = hashcode(username);
        password = hashcode(password);

        for(int i = 0; i<userName_Pass.length; i+=2){
            if(username.equals(userName_Pass[i]) && (password.equals(userName_Pass[i+1]))){
                System.out.println("\nLogin successful");
                difficulty();
                return;
            }
            else{
                System.err.println("Login unsuccessful\nIncorrect username or password\nPlease try again.\n");
                accountLogging(Accounts);
            }
        }
    }
    public static void difficulty() throws InterruptedException, IOException {

        String[] easy_words = {"a", "about", "all", "also", "and", "as", "at", "be", "because", "but", "by", "can", "come", "could", "day",
                "do", "even", "find", "first", "for", "from", "get", "give", "go", "have", "he", "her", "here", "him", "his",
                "how", "I", "if", "in", "into", "it", "its", "just", "know", "like", "look", "make", "man", "many", "me",
                "more", "my", "new", "no", "not", "now", "of", "on", "one", "only", "or", "other", "our", "out", "people",
                "say", "see", "she", "so", "some", "take", "tell", "than", "that", "the", "their", "them", "then", "there",
                "these", "they", "thing", "think", "this", "those", "time", "to", "two", "up", "use", "very", "want", "way",
                "we", "well", "what", "when", "which", "who", "will", "with", "would", "year", "you", "your"};

        String[] medium_words = {"abjure", "abrogate", "acumen", "adumbrate", "alacrity", "anathema", "antipathy", "approbation", "arrogate",
                "ascetic", "assiduous", "boon", "brusque", "burnish", "buttress", "cajole", "calumny", "capricious",
                "clemency", "cogent", "concomitant", "conflagration", "conundrum", "credulity", "cupidity", "cursory",
                "decry", "defile", "deleterious", "demure", "deprecate", "deride", "desecrate", "discursive", "dissemble",
                "ebullient", "Effrontery", "egregious", "enervate", "ephemeral", "eschew", "evanescent", "evince",
                "exculpate", "execrable", "expiate", "expunge", "extant", "extol", "fallacious", "fastidious", "fatuous",
                "feral", "fetid", "florid", "fractious", "garrulous", "gregarious", "hackneyed", "hapless", "harangue",
                "hegemony", "impassive", "imperious", "impertinent", "impervious", "impetuous", "impinge", "implacable",
                "inchoate", "incontrovertible", "indefatigable", "ineffable", "inexorable", "ingenuous", "inimical",
                "iniquity", "insidious", "inure", "invective", "inveterate", "jubilant", "juxtaposition", "laconic",
                "languid", "largess", "latent", "legerdemain", "licentious", "limpid", "maelstrom", "magnanimous",
                "malediction", "malevolent", "manifold", "maudlin", "mawkish", "mendacious", "mercurial", "modicum",
                "multifarious", "myriad", "nadir", "nascent", "nefarious", "neophyte"};

        String[] hard_words = {"obdurate", "obfuscate", "oblique", "obsequious", "obstreperous", "obtuse", "odious", "officious",
                "ostensible", "palliate", "pallid", "panacea", "paragon", "pariah", "parsimony", "pathos", "paucity",
                "pejorative", "pellucid", "perfidious", "perfunctory", "pernicious", "pertinacious", "pithy", "platitude",
                "plethora", "portent", "precocious", "primeval", "proclivity", "promulgate", "propensity", "propitious",
                "prosaic", "proscribe", "protean", "prurient", "puerile", "pulchritude", "punctilious", "quagmire",
                "querulous", "quixotic", "rancor", "rebuke", "recalcitrant", "rectitude", "replete", "reprobate", "reprove",
                "repudiate", "rescind", "restive", "ribald", "rife", "ruse", "sacrosanct", "sagacity", "salient",
                "sanctimonious", "sanguine", "scurrilous", "serendipity", "servile", "solicitous", "somnolent", "spurious",
                "staid", "stolid", "stupefy", "surfeit", "surmise", "surreptitious", "sycophant", "tacit", "taciturn",
                "tantamount", "temerity", "tenuous", "timorous", "torpid", "tractable", "transient", "transmute",
                "trenchant", "truculent", "turgid", "turpitude", "ubiquitous", "unctuous", "upbraid", "usurp", "vacillate",
                "vacuous", "vapid", "variegated", "venerate", "veracity", "verdant", "vex", "vicarious", "vicissitude",
                "vilify", "viscous", "vitriolic", "wanton", "winsome", "wistful", "zenith", "zephyr"};

        while (reset) {
            typed.clear();
            reset = false;
            try {
                System.out.println("\nChoose test difficulty:\n");
                System.out.println("1 \u21FE Easy\n2 \u21FE Medium\n3 \u21FE Hard");

                String level = scanner.next();

                switch (level) {
                    case "1" -> {
                        System.out.println("The test will start after 3 seconds:");
                        for (int i = 3; i > 0; i--) {
                            System.out.println("\t\t\t     " + i);
                            TimeUnit.SECONDS.sleep(1);
                        }
                        System.out.println("Start\n");
                        for (int i = 0; i < 10; i++) {
                            int random_words = random.nextInt(easy_words.length);
                            typed.add(i, easy_words[random_words]);
                            System.out.print(typed.get(i) + " ");
                        }
                    }
                    case "2" -> {
                        System.out.println("The test will start after 3 seconds:");
                        for (int i = 3; i > 0; i--) {
                            System.out.println("\t\t\t     " + i);
                            TimeUnit.SECONDS.sleep(1);
                        }
                        System.out.println("Start\n");
                        for (int i = 0; i < 15; i++) {
                            int random_words = random.nextInt(medium_words.length);
                            typed.add(i, medium_words[random_words]);
                            System.out.print(typed.get(i) + " ");
                        }
                    }
                    case "3" -> {
                        System.out.println("\nThe test will start after 3 seconds:");
                        for (int i = 3; i > 0; i--) {
                            System.out.println("\t\t\t     " + i);
                            TimeUnit.SECONDS.sleep(1);
                        }
                        System.out.println("Start\n");
                        for (int i = 0; i < 15; i++) {
                            int random_words = random.nextInt(hard_words.length);
                            typed.add(i, hard_words[random_words]);
                            System.out.print(typed.get(i) + " ");
                        }
                    }
                    default -> {
                        System.out.println("Please enter (1 or 2 or 3)\n");
                        reset = true;
                    }
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println();
        Accuracy_WPM();
        again();
    }
    public static void Accuracy_WPM(){

        double initialTime = System.nanoTime() / 1000000000.0;
        scanner.nextLine();
        String typing = scanner.nextLine();
        double finalTime = System.nanoTime() / 1000000000.0;

        double totalTime = (finalTime - initialTime) / 60;


        String[] TestWords = new String[typed.size()];
        String[] TypedWords = typing.split(" ");

        for (int i = 0; i < TestWords.length; i++) {
            TestWords[i] = typed.get(i);
        }

        double CorrectWords = 0.0;

        if(TestWords.length == TypedWords.length){
            for (int i = 0; i < TypedWords.length; i++){
                if(TestWords[i].equals(TypedWords[i])){
                    CorrectWords++;
                }
            }
        }
        else if(TestWords.length > TypedWords.length) {
            for (int i = 0; i < TypedWords.length; i++) {
                if (TestWords[i].equals(TypedWords[i])) {
                    CorrectWords++;
                }
            }
        }
        else {
            for(int i = 0; i< TestWords.length; i++){
                if (TestWords[i].equals(TypedWords[i])){
                    CorrectWords++;
                }
            }
        }

        System.out.println("\nWPM: " + String.format("%.2f", (TypedWords.length / totalTime)) + "WPM");
        System.out.println("Accuracy: " + (CorrectWords / TestWords.length) * 100 + "%");
        System.out.println("Error WPM: " + String.format("%.2f", ((TypedWords.length / totalTime) * (CorrectWords / TestWords.length))) + "WPM");
    }
    public static void again() throws InterruptedException, IOException {
        System.out.println("\nDo you want to test your typing again?\n\n1 \u21FE Yes\n2 \u21FE No");
        String again = scanner.next();

        if (again.equals("1") || again.equalsIgnoreCase("yes")) {
            reset = true;
            difficulty();
        }
        else if(again.equals("2") || again.equalsIgnoreCase("no")){
            System.exit(0);
        }
        else{
            System.out.println("Please enter a valid value");
            again();
        }
    }
}