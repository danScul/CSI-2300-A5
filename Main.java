import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws IOException {
        int[][] mat1 = null;
        int[][] mat2 = null;

        if (args.length == 2) {
            mat1 = readFile(args[0]);
            mat2 = readFile(args[1]);

        } else if (args.length == 1 && isInteger(args[0])) {
            int size = Integer.parseInt(args[0]);
            mat1 = getRand(size);
            mat2 = getRand(size);

        } else {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter the name of your files or an integer: ");
            String input = sc.nextLine();

            if (input.contains(" ")) {
                String[] fileNames = input.split(" ");
                mat1 = readFile(fileNames[0]);
                mat2 = readFile(fileNames[1]);

            } else if (isInteger(input)) {
                int size = Integer.parseInt(input);
                mat1 = getRand(size);
                mat2 = getRand(size);

            } else
                System.out.println("Please enter valid input");
                sc.close();
        }
        int result[][] = multMatrix(mat1, mat2);
        
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.toString(result[i]));
        }

        FileWriter writer = new FileWriter("matrix3.txt");
        BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write(String.format("%d %d\n", result.length, result[0].length));
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    buffer.write(String.format("%d", result[i][j]));
                    if (j != result[i].length - 1)
                        buffer.write(" ");
                }
                if (i != result.length - 1)
                    buffer.write("\n");
            }
        buffer.close();
    }

    public static int[][] readFile(String fileName) throws IOException {
        int[][] matrix = null;
        int i, j;
        FileReader reader = new FileReader("matrix3.txt");
        BufferedReader br = new BufferedReader(reader);
        String line = br.readLine();
        String[] size = line.split("");
        int rows = Integer.parseInt(size[0]);
        int cols = Integer.parseInt(size[1]);

        int[][] mat4 = new int[rows][cols];
        for(i = 0; i < rows; i++){
         line = br.readLine();
         String[] row = line.split(" ");
         for(j = 0; j < cols; j++){
             mat4[i][j] = Integer.parseInt(row[j]);
         }
        }
        br.close();
        return matrix;
    }

    public static int[][] getRand(int size) {
        Random rand = new Random();
        int[][] matrix = new int[size][size];
        int i, j;
        for(i = 0; i< matrix.length;i++){
        for(j = 0; j < matrix[i].length; j++){
            matrix[i][j] = rand.nextInt(10);
            }
        }
        return matrix;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static int[][] multMatrix(int[][] mat1, int[][] mat2) {
        int rows1 = mat1.length;
        int cols1 = mat1[0].length;
        int rows2 = mat2.length;
        int cols2 = mat2[0].length;

        if (cols1 != rows2) {
            System.out.println("Error");
        }

        int[][] result = new int[rows1][cols2];
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                result[i][j] = 0;
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
        }
        return result;
    }


}
