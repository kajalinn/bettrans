
import java.util.List;
import java.util.stream.Collectors;

public class PlayerTransactionProcessor {
    public List<PlayerTransaction> getPlayerTransactionsFromInput(String data){
        return data.lines().map(this::createPlayerTransaction).collect(Collectors.toList());
    }

    //initialize player data
    public PlayerTransaction createPlayerTransaction(String line){
        String[] fields = line.split(",");
        String playerID = fields[0];
        String actionType = fields[1];
        String matchID = fields[2].isEmpty() ? null : fields[2];
        double amount = Integer.parseInt(fields[3]);
        String betChoice = fields.length > 4 && !fields[4].isEmpty() ? fields[4] : null;

        return new PlayerTransaction(playerID, actionType, matchID, amount, betChoice);
    }
    
}
