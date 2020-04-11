import java.io.*;
import java.lang.reflect.*;
import java.util.*;

/**
 * This is a framework designed for simplifying input handling during competitive programming situations.
 * Each question is instantiated in the constructor for its parent class
 * e.x. addTwo is instantiated when a Template instance is made
 *
 * BufferedReader and BufferedWriter instances are instantiated in main so they don't have to be called in every question.
 *
 * @author Mark Chen
 * @version 1.0
 * @since 2019-10-24
 */
public class Main {

    public static void main(String[] args) throws IOException {
        IO.open();
        Template t = new Template();
        IO.close();

        System.exit(0);
    }

    /**
     * Instantiates and runs a user-specified method from a user-specified class
     * @param classToRun The user-specified name of the homework's class file
     * @param methodToRun The user-specified name of the method that is to be tested in classToRun
     * @param problemArguments Variable length array that can accommodate a large amount of custom test case objects
     */
    public static void Judge(String classToRun, String methodToRun, Object[] problemArguments) {

        try {

            Class<?> HomeworkName = Class.forName(classToRun);
            Object homeWorkInstance = HomeworkName.getDeclaredConstructor().newInstance();

            Class<?>[] argTypes = new Class[] {Object[].class};
            Method homeworkProblem = HomeworkName.getDeclaredMethod(methodToRun, argTypes);

            homeworkProblem.setAccessible(true);

            Object o = homeworkProblem.invoke(homeWorkInstance, (Object) problemArguments);

        } catch (ClassNotFoundException util) {
            System.out.println("Please specify the correct class (homework) file!");
            util.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("ERROR: Method " + methodToRun + " attempted to access an argument parameter out of bounds!");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println("POSSIBLE ERROR: Class " + classToRun + "'s constructor is either missing or not valid");
            System.out.println("POSSIBLE ERROR: Method " + methodToRun + " attempted to access an argument parameter out of bounds!");
            System.out.println("POSSIBLE ERROR: Method " + methodToRun + " attempted to set a variable to something of a different type!");
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println("Please specify a correct method name for your homework!");
            e.printStackTrace();
        }

    }
}

class IO {
    static BufferedReader reader;
    static StringTokenizer tokenizer;
    static BufferedWriter writer;

    /**
     * Constructor method to initialize the reader for input stream
     * @return void
     */
    static void open() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
        tokenizer = new StringTokenizer("");
    }

    /**
     * Closes BufferedReader and flushes/closes BufferedWriter
     * @return void
     */
    static void close() throws IOException {
        reader.close();
        writer.flush();
        writer.close();
    }

    /**
     * Prints to standard output
     * @return void
     */
    static void w(Object o) throws IOException {
        writer.write(o.toString());
    }

    /**
     * Gets the next word
     * @return String
     */
    static String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    /**
     * Read in an integer
     * @return integer
     */
    static int i() throws IOException {
        return Integer.parseInt(next());
    }

    /**
     * Read in a double
     * @return double
     */
    static double d() throws IOException {
        return Double.parseDouble(next());
    }

    /**
     * Read in a long
     * @return long
     */
    static Long l() throws IOException {
        return Long.parseLong(next());
    }

    /**
     * Read in a string
     * @return String
     */
    static String s() throws IOException{
        return next();
    }

    /**
     * Create an n-length 1D array
     * @return int[]
     */
    static int[] makeArray(int n) throws IOException {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = IO.i();
        }
        return array;
    }

    /**
     * Create an n x m 2D array
     * @return int[][]
     */
    static int[][] makeArray(int n, int m) throws IOException {
        int[][] array = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                array[i][j] = IO.i();
            }
        }
        return array;
    }

    /**
     * Read an unknown number of lines as integers. Stop at empty line.
     * @return ArrayList<Integer>
     */
    static ArrayList<Integer> readIntegersToArrayList() throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        String str = null;
        do {
            str = IO.reader.readLine();
            if (str == null || str.length() == 0) break;

            list.add(Integer.parseInt(str));

        } while (str != null || str.length() != 0);
        return list;
    }

    /**
     * Read unknown number of lines as Strings. Stop at empty line.
     * @return ArrayList<String>
     */
    static ArrayList<String> readStringsToArrayList() throws IOException {
        ArrayList<String> list = new ArrayList<>();
        String str = null;
        do {
            str = IO.reader.readLine();
            if (str == null || str.length() == 0) break;
            list.add(str);
        } while (str != null || str.length() != 0);
        return list;
    }

    /**
     * Read unknown number of lines as Strings. Stop at EOF.
     * @return ArrayList<String>
     */
    static ArrayList<String> readStringsToArrayListUntilEOF() throws IOException {
        ArrayList<String> list = new ArrayList<>();
        String str = "";
        while ((str = IO.reader.readLine()) != null) {
            if (str.trim().length() == 0) continue;
            list.add(str.trim() + " ");
        }
        return list;
    }

    /**
     * Read unknown number of lines as Strings. Stop at specified character "b".
     * @return ArrayList<String>
     */
    static ArrayList<String> readStringsToArrayList(String b) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        String str = null;
        do {
            str = IO.reader.readLine();
            if (str == null || str.length() == 0 || str.equals(b)) break;
            list.add(str);
        } while (str != null || str.length() != 0);
        return list;
    }

    /**
     * Read one line as Strings into an ArrayList.
     * @return ArrayList<String>
     */
    static ArrayList<String> readStringsToArrayListSingleLine() throws IOException {
        ArrayList<String> list = new ArrayList<>();
        String str = null;
        String[] vals;

        str = IO.reader.readLine();
        if (str == null || str.length() == 0) return list;
        vals = str.split(" ");

        for (int i = 0, valsLength = vals.length; i < valsLength; i++) {
            String s = vals[i];
            list.add(s);
        }
        return list;
    }

    /**
     * Read one line as Integers into an ArrayList.
     * @return ArrayList<Integer>
     */
    static ArrayList<Integer> readIntegersToArrayListSingleLine() throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        String str = null;
        String[] vals;

        str = IO.reader.readLine();
        if (str == null || str.length() == 0) return list;
        vals = str.split(" ");

        for (int i = 0, valsLength = vals.length; i < valsLength; i++) {
            String s = vals[i];
            list.add(Integer.parseInt(s));
        }
        return list;
    }

    /**
     * Prints an array with an optional newline.
     * @return void
     */
    static void printArray(int[] array, boolean newLine) throws IOException {
        for (int i = 0; i <array.length; i++) {
            IO.w(i == array.length - 1 ? array[i] + (newLine ? "\n" : "") : array[i] + " ");
        }
    }
}