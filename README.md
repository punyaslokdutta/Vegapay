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



