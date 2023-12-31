

public class PlayerTransaction {
    String player_id;
    String action_type;
    String match_id;
    double amount;
    String bet_choice;

    public PlayerTransaction(String player_id, String action_type, String match_id, Double amount, String bet_choice){
        this.player_id = player_id;
        this.action_type = action_type;
        this.match_id = match_id;
        this.amount = amount;
        this.bet_choice = bet_choice;
    }

    public String getPlayerID() {
        return player_id;
    }

    public String getActionType() {
        return action_type;
    }

    public String getMatchID() {
        return match_id;
    }

    public Double getAmount() {
        return amount;
    }

    public String getBetChoice() {
        return bet_choice;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %d %s",
                getPlayerID(), getActionType(), getMatchID(), getAmount().longValue(), getBetChoice());
    }


}



