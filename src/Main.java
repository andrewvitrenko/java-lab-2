import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        byte[][] A = Main.generateMatrix(3, 5);
        byte[][] B = Main.generateMatrix(3, 5);

        System.out.printf("A - %s\n", Arrays.deepToString(A));
        System.out.printf("B - %s\n", Arrays.deepToString(B));

        int[][] C = Main.sum(A, B);

        System.out.printf("C - %s\n", Arrays.deepToString(C));

        int result = Main.analyze(C);

        System.out.printf("result - %d\n", result);
    }

    public static byte[][] generateMatrix(int rows, int columns) {
        byte[][] matrix = new byte[rows][columns];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (byte) (Math.random() * 10);
            }
        }

        return matrix;
    }

    public static int[][] sum(byte[][] matrixA, byte[][] matrixB) throws Exception {
        Main.checkDimensions(matrixA, matrixB);

        int[][] result = new int[matrixA.length][matrixA[0].length];

        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                result[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }

        return result;
    }

    public static void checkDimensions(byte[][] A, byte[][] B) throws Exception {
        boolean isEqualRows = A.length == B.length;
        boolean isEqualColumns = A[0].length == B[0].length;

        if (!isEqualColumns || !isEqualRows) {
            throw new Exception("Matrices with different dimensions");
        }
    }

    public static int analyze(int[][] matrix) {
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

    public static int[] getColumn(int[][] matrix, int columnIndex) {
        int[] column = new int[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            column[i] = matrix[i][columnIndex];
        }

        return column;
    }
}