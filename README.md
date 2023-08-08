# Vegapay

This API allows you to manage credit card limits and offers for customers.

## 1. Create Account

**Request:**

```shell
curl --location 'http://localhost:8080/v1/accounts' \
--header 'Content-Type: application/json' \
--data '{
    "customerId": 1432667,
    "accountLimit": 200000.0,
    "perTransactionLimit": 40000.0
}'
```
**Response:**

```shell
{
    "accountLimit": 200000.0,
    "perTransactionLimit": 40000.0
}
```
## 2. Get Account

**Request:**

```shell
curl --location 'http://localhost:8080/v1/accounts/{accountId}
```
**Response:**

```shell
{
    "accountLimit": 200000.0,
    "perTransactionLimit": 80000.0
}
```

## 3. Create LimitOffer for Account

**Request:**

```shell
curl --location 'http://localhost:8080/v1/limit-offers' \
--header 'Content-Type: application/json' \
--data '{
    "accountId": 7,
    "limitType": "ACCOUNT_LIMIT",
    "newLimit": 800000.0,
    "offerActivationTime": "2023-08-07T10:00:00",
    "offerExpiryTime": "2023-08-15T23:59:59"
}'
```
**Response:**

```shell
LimitOffer
{
    "accountId": 7,
    "limitType": "ACCOUNT_LIMIT",
    "newLimit": 800000.0,
    "offerActivationTime": "2023-08-07T10:00:00",
    "offerExpiryTime": "2023-08-15T23:59:59"
}

```


## 4. Get List of LimitOffer for an account

**Request:**

```shell
curl --location 'http://localhost:8080/v1/limit-offers/active/{accountId}'
```
**Response:**

```shell
[
    {
        "accountId": 7,
        "limitType": "ACCOUNT_LIMIT",
        "newLimit": 800000.0,
        "offerActivationTime": "2023-08-07T10:00:00",
        "offerExpiryTime": "2023-08-15T23:59:59"
    }
]

```

## 5. Update limitOffer status based on user input from PENDING ->  ACCEPTED , REJECTED.

**Request:**

```shell
curl --location --request PUT 'http://localhost:8080/v1/limit-offers/{limitOfferId}/status' \
--header 'Content-Type: application/json' \
--data '{
    "status": "ACCEPTED"
}'

```
**Response:**

```shell
{
    "accountId": 7,
    "limitType": "ACCOUNT_LIMIT",
    "newLimit": 800000.0,
    "offerActivationTime": "2023-08-08T10:00:00",
    "offerExpiryTime": "2023-08-15T23:59:59"
} 
```



