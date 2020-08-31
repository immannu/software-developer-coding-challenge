#!/bin/bash
port=${1:-8080}

curl -X PUT \
  http://localhost:${port}/accounts/1/auction/1 \
  -H 'Postman-Token: 5c7977a7-10ee-490d-a48c-1a1a0617e2e0' \
  -H 'cache-control: no-cache'