
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class TransactionProcessor {
    private final Map<String, Player> playerMap = new HashMap<>();
    public void processTransactions(List<PlayerTransaction> playerTransactions, List<MatchTransaction> matchTransactions, Casino casino) {

        List<String> legitimatePlayers = new ArrayList<>();
        List<String> illegitimatePlayerTransactions = new ArrayList<>();

        //Process transaction action type
        for (PlayerTransaction playerTransaction : playerTransactions) {
            if ("DEPOSIT".equals(playerTransaction.getActionType())) {
                processDeposit(playerTransaction);
            } else if ("BET".equals(playerTransaction.getActionType())) {
                processBet(playerTransaction, matchTransactions, casino);
            } else if ("WITHDRAW".equals(playerTransaction.getActionType())) {
                processWithdraw(playerTransaction, casino);
            }
        }

        //Sort legal and illegal players
        List<Player> legitimatePlayersList = playerMap.values().stream()
                .filter(player -> player.getLegal().equals(true))
                .collect(Collectors.toList());

        List<Player> illegitimatePlayersList = playerMap.values().stream()
                .filter(player -> player.getLegal().equals(false))
                .collect(Collectors.toList());

        // Process legitimate players
        legitimatePlayersList.sort(Comparator.comparing(Player::getPlayerID));
        for (Player legitimatePlayer : legitimatePlayersList) {
            double winRate = legitimatePlayer.getWinRate();
            String result = String.format("%s %d %.2f", legitimatePlayer.getPlayerID(), legitimatePlayer.getTotalBalance(), winRate);
            legitimatePlayers.add(result);
        }

        // Process illegitimate transactions
        illegitimatePlayersList.sort(Comparator.comparing(Player::getPlayerID));
        for (Player illegitimatePlayer : illegitimatePlayersList) {
            String firstIllegalOperation = illegitimatePlayer.getIllegalTransaction();
            illegitimatePlayerTransactions.add(firstIllegalOperation);
        }

        //Final casino balance
        long casinoCoinChanges = casino.getTotalBalance();
        String coinChangeResult = String.valueOf(casinoCoinChanges);

        System.out.println(legitimatePlayers);
        System.out.println(illegitimatePlayerTransactions);
        System.out.println(List.of(coinChangeResult));

        // Write results to a file
//        try {
//            Path resultsFilePath = Path.of("src/result.txt");
//            if (!legitimatePlayers.isEmpty()) {
//                Files.write(resultsFilePath, legitimatePlayers, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
//                Files.write(resultsFilePath, System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
//            } else {
//                Files.write(resultsFilePath, System.lineSeparator().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
//            }
//
//            if (!illegitimatePlayerTransactions.isEmpty()) {
//                Files.write(resultsFilePath, illegitimatePlayerTransactions, StandardOpenOption.APPEND);
//                Files.write(resultsFilePath, System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
//            } else {
//                Files.write(resultsFilePath, System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
//            }
//
//            Files.write(resultsFilePath, List.of(coinChangeResult), StandardOpenOption.APPEND);
//            Files.write(resultsFilePath, System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


    public void processBet(PlayerTransaction playerTransaction, List<MatchTransaction> matchTransactions, Casino casino) {
        Map<String, MatchTransaction> matchTransactionMap = matchTransactions.stream()
                .collect(Collectors.toMap(MatchTransaction::getMatchID, mt -> mt));

        Player player = playerMap.get(playerTransaction.getPlayerID());
        MatchTransaction mt = matchTransactionMap.get(playerTransaction.getMatchID());

        //If player exist initialize the bet
        if (player != null) {
            Bet bet = new Bet(
                    mt.getMatchID(),
                    player.getPlayerID(),
                    playerTransaction.getBetChoice(),
                    mt.getMatchResult(),
                    playerTransaction.getAmount(),
                    mt.getReturnRate_A(),
                    mt.getReturnRate_B()
            );

            String betResult = bet.getResult();

            if (player.getTotalBalance() >= bet.getBetAmount()) {
                player.setCountTotalBets();
                player.decrementPlayerBalance(bet.getBetAmount());
                casino.incrementCasinoBalance(bet.getBetAmount());

                if (player.getBets() != null) {
                    player.getBets().add(bet);
                } else {
                    //If bet list is null, create a new list and add the bet
                    List<Bet> bets = new ArrayList<>();
                    bets.add(bet);
                    player.setBets(bets);
                }
                if ("WIN".equals(betResult)) {
                    player.setCountWinBets();
                    this.processWinBet(bet, casino);
                } else if ("DRAW".equals(betResult)) {
                    player.incrementPlayerProfit(bet.getBetAmount());
                    casino.decrementTotalBalance(bet.getBetAmount());
                }
            } else {
                player.setLegal(false);
                player.setIllegalTransaction(playerTransaction);
                casino.cancelPlayerBets(player.getBets());
            }
        }
    }

    public void processWinBet(Bet bet, Casino casino) {
        Player player = playerMap.get(bet.getPlayerID());
            player.incrementPlayerProfit(bet.getProfit());
            casino.decrementTotalBalance(bet.getProfit());
    }

    public void processDeposit(PlayerTransaction playerTransaction) {
            Player player = playerMap.get(playerTransaction.getPlayerID());
            if (player == null) {
                player = new Player(
                        playerTransaction.getPlayerID(),
                        0,
                        true,
                        0,
                        0
                );
                player.deposit(playerTransaction.getAmount());
                playerMap.put(playerTransaction.getPlayerID(), player);
            } else {
                player.deposit(playerTransaction.getAmount());
            }
    }

    public void processWithdraw(PlayerTransaction playerTransaction, Casino casino) {
            Player player = playerMap.get(playerTransaction.getPlayerID());
            if (player != null) {
                if (player.totalBalance > playerTransaction.getAmount() ) {
                    player.withdraw(playerTransaction.getAmount());
                } else {
                    player.setLegal(false);
                    player.setIllegalTransaction(playerTransaction);
                    casino.cancelPlayerBets(player.getBets());
                }
            } else {
                System.out.println("No player found");
            }
    }
}


//processTransactions method
//        Works but requires more resources and time with algorithm complexity O(n * m)
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


//processBet method
//        // Iterate over playerTransactions and check for matching pairs
//        playerTransactions.stream()
//                .filter(pt -> matchTransactionMap.containsKey(pt.getMatchID()))
//                .forEach(pt -> {
//                    MatchTransaction mt = matchTransactionMap.get(pt.getMatchID());
//                    Player player = playerMap.get(pt.player_id);
//
//                    if (player != null) {
//                        Bet bet = new Bet(mt.getMatchID(), player.getPlayerID(), pt.getBetChoice(), mt.getMatchResult(), pt.getAmount(), mt.getReturnRate_A(), mt.getReturnRate_B());
//
//                        String betResult = bet.getResult();
//
//                        if (player.getTotalBalance() >= pt.getAmount()){
//                            player.decrementPlayerBalance(pt.getAmount());
//                            casino.incrementCasinoBalance(pt.getAmount());
//
//                            if (player.getBets() != null) {
//                                player.getBets().add(bet);
//                            }
//
//                            if ("WIN".equals(betResult)) {
//                            List <Bet> bets = new Bet(mt.getMatchID(), mt.getMatchResult(), pt.getAmount());
//                                this.processWinBet(pt, mt, casino);
//                            } else if ("DRAW".equals(betResult)) {
//                                player.incrementPlayerProfit(pt.getAmount());
//                                casino.decrementTotalBalance(pt.getAmount());
//                            }
//                        } else {
//                            casino.cancelPlayerBets(player.getBets());
//                            player.cancelAllBets(pt.getPlayerID()); // to do
//                        }
//                    }
//
//
//                    System.out.println("Matching Pair: " + pt.bet_choice + " | " + matchTransaction.getMatchResult());
//                    System.out.println("Player actions and money: " + pt.getActionType() + "--->" + pt.getAmount());
//                    System.out.println(pt.getMatchID() + " | " + matchTransaction.getMatchID());
//
//                });