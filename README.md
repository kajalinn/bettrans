# Transaction Processor

The Transaction Processor is a Java application designed to handle player transactions and bets in a casino environment. It processes various player actions such as deposits, bets, and withdrawals, updating player and casino balances accordingly.

## Getting Started

These instructions will help you set up and run the Transaction Processor on your local machine.

### Prerequisites

- Java Development Kit (JDK) installed
- IDE (Integrated Development Environment) for Java (e.g., IntelliJ IDEA, Eclipse)

### Installation

1. Clone the repository to your local machine.
```bash
git clone git@github.com:kajalinn/bettrans.git
```
2. Open the project in your preferred IDE.
3. Run the Main class to execute the transaction processing.

### Usage

The Transaction Processor reads player and match data from input files, processes the transactions, and provides output based on the specified requirements.

### Input Files

 - player_data.txt: Contains information about player transactions, including deposits, bets, and withdrawals.
 - match_data.txt: Contains information about match transactions, including match IDs, results, and return rates.

### Output

The processor generates an output file named results.txt. The output includes:

 - Legitimate Players:
   List of legitimate player IDs, final balance, and betting win rate.
   Example: 163f23ed-e9a9-4e54-a5b1-4e1fc86f12f4 4321 0.80

 - Illegitimate Players:
   List of illegitimate players represented by their first illegal operation.
   Example: 163f23ed-e9a9-4e54-a5b1-4e1fc86f12f4 BET abae2255-4255-4304-8589-737cdff61640 5000 A

 - Casino Coin Changes:
   Coin changes in the casino host balance due to player BET operations.
