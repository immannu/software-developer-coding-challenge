#!/bin/bash
port=${1:-8080}

curl -H "content-type: application/json" -d '{
	"name": "dfxzxsds",
	"accountType":"DEALER",
	"email":"te2tdcxc1@t2est.com",
	"phoneNumber":"212122222",
	"description":"Testing",
	"emailVerified":true,
	"deleted":false
}' http://localhost:${port}/accounts # <1>