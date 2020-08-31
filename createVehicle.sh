#!/bin/bash
port=${1:-8080}

curl -H "content-type: application/json" -d '{
	"year": "2014",
	"make":"Nissan",
	"model":"Corolla",
	"trim":"2.5 LE",
	"vin":"QJHQQ1J8989HJHJHJHJHJHJ2"
}' http://localhost:${port}/accounts/1/vehicle # <1>