
import java.util.List;

public class TransactionProcessor {

    public void processTransactions(List<PlayerTransaction> playerTransactions, List<MatchTransaction> matchTransactions) {
        // Compare and process both types of transactions
        for (PlayerTransaction playerTransaction : playerTransactions) {
            for (MatchTransaction matchTransaction : matchTransactions) {
                if
                (
                        playerTransaction != null &&
                        matchTransaction != null &&
                        playerTransaction.getMatchID() != null &&
                        playerTransaction.getMatchID().equals(matchTransaction.getMatchID())
                ){
                    System.out.println("Matching Pair: " + playerTransaction + " | " + matchTransaction);
                    //further operations here
                }
            }
        }
    }
}
