package lotto;

import java.util.ArrayList;
import java.util.List;

public class Count {
    private static final int counterSize = 6;
    public static int[] WinTypeCounter = new int[counterSize];
    public static int[] BonusCount(List<List<Integer>> GeneratedLotto) {
        int[] count = new int[GeneratedLotto.size()];
        for (int i = 0; i < GeneratedLotto.size(); i++) {
            if (GeneratedLotto.get(i).contains(Bonus.BonusNumber)) {
                count[i] += 1;
            }
        }
        return count;
    }

    public static List<Integer> WinningCount(List<List<Integer>> GeneratedLotto, List<Integer> numbers) {
        List<Integer> counts = new ArrayList<>();
        for (List<Integer> lotto : GeneratedLotto) {
            MatchNumber set = WinCount(lotto, numbers);
            if(set.getValue() != 0){
                WinTypeCounter[set.getRank()]++;
            }
            counts.add(set.getValue());
        }
        return counts;
    }

    public static MatchNumber WinCount(List<Integer> lotto, List<Integer> numbers) {
        int winningCount = 0;
        for (int j = 0; j < numbers.size(); j++) {
            if (lotto.contains(numbers.get(j))) {
                winningCount++;
            }
        }
        if(winningCount == 5 && numbers.contains(Bonus.BonusNumber)){
            return MatchNumber.FIVE_BONUS;
        }
        if(winningCount >= 3) {
            return findWinType(winningCount);
        }
        return MatchNumber.Zero;
    }

    public static MatchNumber findWinType(int number) {
        if(number == 3) return MatchNumber.THREE;
        if(number == 4) return MatchNumber.FOUR;
        if(number == 5) return MatchNumber.FIVE;
        return MatchNumber.SIX;
    }
}
