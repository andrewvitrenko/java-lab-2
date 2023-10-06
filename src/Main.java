import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner;

    public static void main(String[] args) {
        Main.scanner = new Scanner(System.in);
        try {
            int rowsA = Main.getParam("rows", "A");
            int columnsA = Main.getParam("columns", "A");
            int rowsB = Main.getParam("rows", "B");
            int columnsB = Main.getParam("columns", "B");

            byte[][] A = Main.generateMatrix(rowsA, columnsA);
            byte[][] B = Main.generateMatrix(rowsB, columnsB);

            System.out.printf("A - %s\n", Arrays.deepToString(A));
            System.out.printf("B - %s\n", Arrays.deepToString(B));

            int[][] C = Main.sum(A, B);

            System.out.printf("C - %s\n", Arrays.deepToString(C));

            int result = Main.analyze(C);

            System.out.printf("result - %d\n", result);

        } catch (LabException e) {
            System.out.println("Lab exception: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static byte[][] generateMatrix(int rows, int columns) {
        byte[][] matrix = new byte[rows][columns];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (byte) (Math.random() * 10);
            }
        }

        return matrix;
    }

    private static int[][] sum(byte[][] matrixA, byte[][] matrixB) throws LabException {
        Main.checkDimensions(matrixA, matrixB);

        int[][] result = new int[matrixA.length][matrixA[0].length];

        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                result[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }

        return result;
    }

    private static void checkDimensions(byte[][] A, byte[][] B) throws LabException {
        boolean isEqualRows = A.length == B.length;
        boolean isEqualColumns = A[0].length == B[0].length;

        if (!isEqualColumns || !isEqualRows) {
            throw new LabException("Matrices with different dimensions");
        }
    }

    private static int analyze(int[][] matrix) {
        int result = 0;

        for (int i = 0; i < matrix[0].length; i++) {

            int[] column = Main.getColumn(matrix, i);

            if (i % 2 == 0) {
                result += Arrays.stream(column).min().getAsInt();
            } else {
                result += Arrays.stream(column).max().getAsInt();
            }

        }

        return result;
    }

    private static int[] getColumn(int[][] matrix, int columnIndex) {
        int[] column = new int[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            column[i] = matrix[i][columnIndex];
        }

        return column;
    }

    private static int getParam(String param, String matrix) {
        int value = 0;

        do {
            try {
                System.out.printf("Enter value for %s in matrix %s: ", param, matrix);
                int input = scanner.nextInt();
                if (input <= 0) {
                    throw new LabException("Value should be greater than zero");
                }

                value = input;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid value");
                scanner.nextLine();
            } catch (LabException e) {
                System.out.println("Lab exception: " + e.getMessage());
                scanner.nextLine();
            }
        } while (value <= 0);

        return value;
    }
}