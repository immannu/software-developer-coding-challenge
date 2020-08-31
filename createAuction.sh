#!/bin/bash
port=${1:-8080}

curl -X POST \
  http://localhost:${port}/accounts/1/vehicle/1/auction \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
    "basePrice": 10000,
    "auctionDurationHrs": "1",
    "auctionStartTime": "1598852719",
    "status":"NOT_STARTED"
}'