# Description

An application that reads bets from a csv file or optionally runs a
REST api service that accepts a list of bets and produces two reports.

## build
```bash
./mvnw clean package
```
## Test
```bash
./mvnw test
```

## Run
java -jar target/report-generator-1.0.jar

## Http Request (for the REST version)
```bash
curl --header "Content-Type: application/json" --request POST --data '[{"betId":"xyz","timestamp":123454, "selectionId":13, "selectionName":"WinniePooh", "stake": 2.0, "price": 3.1, "currency":"EUR"}, {"betId":"xyz-2","timestamp":123454, "selectionId":13, "selectionName":"WinniePooh", "stake": 1.0, "price": 3.2, "currency":"EUR"}]' 'localhost:8080'
```

## Input CSV
The csv input can be found in ./src/main/resources

## Output CSV 
- selection-liability-by-currency-report.csv
- total-liability-by-currency-report.csv