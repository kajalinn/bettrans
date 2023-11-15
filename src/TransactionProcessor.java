
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TransactionProcessor {

    public void processTransactions(List<PlayerTransaction> playerTransactions, List<MatchTransaction> matchTransactions) {
        // Compare and process both types of transactions

        // more concise and lightweight solution
        Map<String, MatchTransaction> matchTransactionMap = matchTransactions.stream()
                .collect(Collectors.toMap(MatchTransaction::getMatchID, mt -> mt));

        // Iterate over playerTransactions and check for matching pairs
        playerTransactions.stream()
                .filter(pt -> pt.getMatchID() != null && matchTransactionMap.containsKey(pt.getMatchID()))
                .forEach(pt -> {
                    MatchTransaction matchTransaction = matchTransactionMap.get(pt.getMatchID());
                    System.out.println("Matching Pair: " + pt.bet_choice + " | " + matchTransaction.getMatchResult());
                    // Perform further operations based on matching pair
                });







//        works but requires more resources and time with algorithm complexity O(n * m)
//        for (PlayerTransaction playerTransaction : playerTransactions) {
//            for (MatchTransaction matchTransaction : matchTransactions) {
//                if
//                (
//                        playerTransaction != null &&
//                        matchTransaction != null &&
//                        playerTransaction.getMatchID() != null &&
//                        playerTransaction.getMatchID().equals(matchTransaction.getMatchID())
//                ){
//                    System.out.println("Matching Pair: " + playerTransaction.getBetChoice() + " | " + matchTransaction.getMatchResult());
//                    //further operations here
//                }
//            }
//        }

    }

}
