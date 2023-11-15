

public class MatchTransaction {
    String matchID;
    Double returnRate_A;
    Double returnRate_B;
    String matchResult;

    public MatchTransaction(String matchID, Double returnRate_A, Double returnRate_B, String matchResult){
        this.matchID = matchID;
        this.returnRate_A = returnRate_A;
        this.returnRate_B = returnRate_B;
        this.matchResult = matchResult;
    }

    public String getMatchID() {
        return matchID;
    }

    public Double getReturnRate_A() {
        return returnRate_A;
    }

    public Double getReturnRate_B() {
        return returnRate_B;
    }

    public String getMatchResult() {
        return matchResult;
    }
}
