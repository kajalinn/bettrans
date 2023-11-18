import java.util.List;

public class Casino {
    public long totalBalance;

    public Casino(long initialBalance) {
        this.totalBalance = initialBalance;
    }
    public long getTotalBalance() {
        return totalBalance;
    }
    public void incrementCasinoBalance(double amount){
        totalBalance += (long) amount;
    }
    public void decrementTotalBalance(double amount) {
        totalBalance -= (long) amount;
    }

    public void cancelPlayerBets(List<Bet> bets){
        for (Bet bet: bets){
                // Cancel casino coin changes from illegal players
                if (bet.getResult().equals("WIN")) {
                    this.totalBalance += (long) bet.getProfit() - (long) bet.getBetAmount();
                } else if (bet.getResult().equals("LOSE")) {
                    this.totalBalance -= (long) bet.getBetAmount();
                }
        }
    }

}
