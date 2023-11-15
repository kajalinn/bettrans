
import java.util.List;
import java.util.stream.Collectors;

public class MatchTransactionProcessor {
    public List<MatchTransaction> getMatchTransactionsFromInput(String data){
        return data.lines().map(this::createMatchTransaction).collect(Collectors.toList());
    }

    private MatchTransaction createMatchTransaction(String line){
        String[] fields = line.split(",");
        //initialize match data
        String playerID = fields[0];
        Double returnRate_A = Double.parseDouble(fields[1]);
        Double returnRate_B = Double.parseDouble(fields[2]);
        String matchResult = fields[3];

        return new MatchTransaction(playerID, returnRate_A, returnRate_B, matchResult);
    }
}
