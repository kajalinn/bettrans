public class Bet {
    public String matchID;
    public String playerID;
    public String playerBetChoice;
    public String matchResult;
    double betAmount;
    double returnRateA;
    double returnRateB;
    double profit;

    public Bet(String matchID, String playerID, String playerBetChoice, String matchResult, double betAmount, double returnRateA, double returnRateB){
        this.matchID = matchID;
        this.playerID = playerID;
        this.playerBetChoice = playerBetChoice;
        this.matchResult = matchResult;
        this.betAmount = betAmount;
        this.returnRateA = returnRateA;
        this.returnRateB = returnRateB;
    }
    public String getPlayerID() {
        return playerID;
    }
    public double getBetAmount() {
        return betAmount;
    }

    public String getResult(){
        if(playerBetChoice.equals(matchResult)){
            return "WIN";
        } else if (matchResult.equals("DRAW")){
            return "DRAW";
        } else {
            return "LOSE";
        }
    }
    public double getProfit(){
        if ("A".equals(matchResult)) {
            profit = betAmount * returnRateA;
        } else if ("B".equals(matchResult)) {
            profit = betAmount * returnRateB;
        }
        return profit;
    }


}
