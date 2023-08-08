# Vegapay

These APIs allows you to manage credit card limits and offers for customers.
![Vegapay-schema](https://github.com/punyaslokdutta/Vegapay/assets/13198518/b56af65a-e4b5-4a93-8ac1-e0159dd34bdc)

One-to-One Multiplicity && Uni-directional Association
- Each `LimitOffer` corresponds to a single `Account`.
- Each `Account` can have multiple associated `LimitOffers`.
- Given a `LimitOffer`, you can access the associated `Account`.
- However, a direct reference from an `Account` to its associated `LimitOffers` might not be present.

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



