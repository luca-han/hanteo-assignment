import java.util.*;

public class Coin {
        

    //동전 조합을 계산하는 메서드
    public static void findCombinations(int[] coins, int sum, List<Integer> current, int index) {
        if (sum == 0) {
            System.out.println(current);
            return;
        }

        for (int i = index; i < coins.length; i++) {
            if (sum >= coins[i]) {
                current.add(coins[i]);
                findCombinations(coins, sum - coins[i], current, i);
                current.remove(current.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 받기
        System.out.println("동전의 종류를 입력하세요(,로 구분): ");
        String coinString = scanner.nextLine();
        
        System.out.print("목표 합계를 입력하세요: ");
        int sum = scanner.nextInt();

        
        String[] coinsString = coinString.split(",");
        int[] coins = new int[coinsString.length];
        for (int i = 0; i < coinsString.length; i++) {
            coins[i] = Integer.parseInt(coinsString[i]);
        }

        // 가능한 모든 조합 출력
        System.out.println("가능한 모든 조합: ");
        findCombinations(coins, sum, new ArrayList<>(), 0);

        scanner.close();
}

}
