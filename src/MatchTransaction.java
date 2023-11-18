

public class MatchTransaction {
    String matchID;
    double returnRate_A;
    double returnRate_B;
    String matchResult;

    public MatchTransaction(String matchID, double returnRate_A, double returnRate_B, String matchResult){
        this.matchID = matchID;
        this.returnRate_A = returnRate_A;
        this.returnRate_B = returnRate_B;
        this.matchResult = matchResult;
    }

    public String getMatchID() {
        return matchID;
    }

    public double getReturnRate_A() {
        return returnRate_A;
    }

    public double getReturnRate_B() {
        return returnRate_B;
    }

    public String getMatchResult() {
        return matchResult;
    }


}
