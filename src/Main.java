
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


class Main {
    public static void main(String[] args) throws IOException {

//        Path playerInputData = Path.of("resources/player_data.txt");
//        Path matchInputData = Path.of("resources/match_data.txt");
//
//
//        String playerData = Files.readString(playerInputData);
//        String matchData = Files.readString(matchInputData);

//        // Load player data
//        String playerData = loadResourceAsString("../resources/player_data.txt");
//
//        // Load match data
//        String matchData = loadResourceAsString("../resources/match_data.txt");




        // Load player data
        String playerData = loadResourceAsString("../resources/player_data.txt");

        // Load match data
        String matchData = loadResourceAsString("../resources/match_data.txt");



        PlayerTransactionProcessor playerProcessor = new PlayerTransactionProcessor();
        List<PlayerTransaction> playerTransactions = playerProcessor.getPlayerTransactionsFromInput(playerData);


        MatchTransactionProcessor matchProcessor = new MatchTransactionProcessor();
        List<MatchTransaction> matchTransactions = matchProcessor.getMatchTransactionsFromInput(matchData);


        TransactionProcessor transactionProcessor = new TransactionProcessor();
        transactionProcessor.processTransactions(playerTransactions, matchTransactions);
    }

    private static String loadResourceAsString(String resourceName) throws IOException {
        Path resourcePath = Path.of(resourceName);
        return Files.readString(resourcePath, StandardCharsets.UTF_8);
    }

}
