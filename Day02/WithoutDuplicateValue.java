import java.util.Scanner;

public class WithoutDuplicateValue {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the rows: ");
        int rows = sc.nextInt();
        System.out.print("Enter the cols: ");
        int cols = sc.nextInt();

        int arr[][] = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean isDuplicate;
                do {
                    isDuplicate = false;
                    System.out.print("Enter value for position (" + i + "," + j + "): ");
                    int val = sc.nextInt();

                    for (int h = 0; h < i; h++) {
                        for (int k = 0; k < cols; k++) {
                            if (arr[h][k] == val) {
                                isDuplicate = true;
                                break;
                            }
                        }
                        if (isDuplicate) break;
                    }

                    for (int k = 0; k < j; k++) {
                        if (arr[i][k] == val) {
                            isDuplicate = true;
                            break;
                        }
                    }

                    if (!isDuplicate) {
                        arr[i][j] = val;
                    } else {
                        System.out.println("Duplicate value found. Please enter a different value.");
                    }
                } while (isDuplicate);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
