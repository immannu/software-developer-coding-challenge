#!/bin/bash
port=${1:-8080}


curl -X PUT \
  http://localhost:${port}/accounts/1/auction/1/bid \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: fe565e70-4a8f-4b8d-b224-32f1f733b6b4' \
  -H 'cache-control: no-cache' \
  -d '{
	"auctionId":"2",
	"price":"56200"
}'