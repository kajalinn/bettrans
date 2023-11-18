
import java.util.List;
import java.util.stream.Collectors;

public class MatchTransactionProcessor {
    public List<MatchTransaction> getMatchTransactionsFromInput(String data){
        return data.lines().map(this::createMatchTransaction).collect(Collectors.toList());
    }

    //initialize match data
    private MatchTransaction createMatchTransaction(String line){
        String[] fields = line.split(",");
        String matchID = fields[0];
        double returnRate_A = Double.parseDouble(fields[1]);
        double returnRate_B = Double.parseDouble(fields[2]);
        String matchResult = fields[3];

        return new MatchTransaction(matchID, returnRate_A, returnRate_B, matchResult);
    }
}
