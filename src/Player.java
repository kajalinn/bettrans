import java.util.ArrayList;
import java.util.List;

public class Player {

    String playerID;
    long totalBalance;
    Boolean isLegal;
    double bettingWinRate;
    int countWinBets;
    int countTotalBets;
    String illegalTransaction;
    private List<Bet> bets = new ArrayList<>();

    public Player(String playerID, long totalBalance, boolean isLegal, double bettingWinRate, int countWinBets){
        this.playerID = playerID;
        this.totalBalance = totalBalance;
        this.isLegal = isLegal;
        this.bettingWinRate = bettingWinRate;
        this.countWinBets = countWinBets;
    }

    public void setCountWinBets() {
        this.countWinBets += 1;
    }
    public void setCountTotalBets() {
        this.countTotalBets += 1;
    }
    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }
    public void setLegal(Boolean legal) {
        isLegal = legal;
    }

    public String getPlayerID() {
        return playerID;
    }

    public Long getTotalBalance() {
        return totalBalance;
    }

    public Boolean getLegal() {
        return isLegal;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public double getWinRate() {
        if (countTotalBets == 0) {
            System.out.println("No bets for player " + playerID);
            return 0.0;
        }
        double winRate = (double) countWinBets / countTotalBets;
        if (Double.isNaN(winRate)) {
            System.out.println("Win rate is NaN for player " + playerID);
        }
        return winRate;
    }

    public void deposit(double amount) {
        totalBalance += (long) amount;
    }

    public void incrementPlayerProfit(double profit){
        totalBalance += (long) profit;
    }
    public void decrementPlayerBalance(double amount){
        totalBalance -= (long) amount;
    }

    public void withdraw(double amount) {
        totalBalance -= (long)amount;
    }

    public void setIllegalTransaction(PlayerTransaction playerTransaction){
        illegalTransaction = playerTransaction.toString();
    }
    public String getIllegalTransaction(){
        return illegalTransaction;
    }

    @Override
    public String toString() {
        return String.format("%s, %d, %.2f]",
                getPlayerID(), getTotalBalance(), getWinRate());
    }

}
