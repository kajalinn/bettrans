
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


class Main {
    public static void main(String[] args) throws IOException {

        Casino casino = new Casino(0);

        // Load player data
        String playerData = loadResourceAsString("../resources/player_data.txt");

        // Load match data
        String matchData = loadResourceAsString("../resources/match_data.txt");

        //Initialize player transactions
        PlayerTransactionProcessor playerProcessor = new PlayerTransactionProcessor();
        List<PlayerTransaction> playerTransactions = playerProcessor.getPlayerTransactionsFromInput(playerData);

        //Initialize match transactions
        MatchTransactionProcessor matchProcessor = new MatchTransactionProcessor();
        List<MatchTransaction> matchTransactions = matchProcessor.getMatchTransactionsFromInput(matchData);

        //Process both transactions
        TransactionProcessor transactionProcessor = new TransactionProcessor();
        transactionProcessor.processTransactions(playerTransactions, matchTransactions, casino);

    }

    private static String loadResourceAsString(String resourceName) throws IOException {
        Path resourcePath = Path.of(resourceName);
        return Files.readString(resourcePath, StandardCharsets.UTF_8);
    }

}
