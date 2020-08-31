# Steps to run Application


## 1: Start Mysql docker instance 
docker-compose up -d
user name is user
password is password
dbname is auction

## 2: Run Spring boot application (TradeRevAuctionApplication.java)
this will run  migration and bring up db

## 3: Run createAccount.sh
this will invoke Account controller and create an accounts for application.Run as many as need to create application

## 4: Run AddVehicle.sh
this will invoke Vehicle controller and create few vehicles entity for bidding for application.Run as many as need to create Vehicle

## 5: Run CreateAuction.sh
this will  create an auction for one of application

## 6: Run startAuction.sh
this will  start an auction for one of application

## 6: Run bidAnAuction.sh
this will invoke api to bid for application

###Approach/Assumptions for UI application

#####1. Only registered account holders can add vehicle to system 
#####2. Only registered account holders who added vehicle can create An Auction and Start Auction.
#####Auction should have Start time and duration which helps to compute end time
#####3. All other account holders(except primary) can bid for vehicle
#####4. All other account holders(except primary) can bid for vehicle
#####5. Once Auction End time is reached, A batch job will update status of auction to Completed


##Note: All Apis are regular API except /accounts/{accId}/vehicle/{vId}/auction - this will return SSE(server sent events). 
##This will be more efficient in UI, where we dont have to keep pooling for ,latest bid.this will return last 3 bids back to user as events

 

###Below are endpoints
#### @GetMapping("/accounts") get all accounts
#### @GetMapping("/accounts/{id}") get a specific account
#### @PostMapping ("/accounts") create new accounts
#### @PutMapping("/accounts/{id}") update account
#### @DeleteMapping("/accounts/{id}") delete Account


#### @GetMapping("/accounts/{acId}/vehicle") get all vehicle listed by account
#### @GetMapping("/accounts/{acId}/vehicle/{vId}") get a specific vehcile
#### @PostMapping ("/accounts/{acId}/vehicle") list a new vehicle under an account


#### @PostMapping("/accounts/{accId}/vehicle/{vId}/auction") create a new vehicle Auction
#### @PutMapping("/accounts/{accId}/auction/{aucId}") start auction
#### @PutMapping("/accounts/{accId}/auction/{aucId}/bid") bid an auction
#### @GetMapping(value = "/accounts/{accId}/vehicle/{vId}/auction"- SSE (server Sent event) endpoint which will hep UI to show latest bid value
#### @GetMapping(value = "vehicle/{vId}/auction/history" Get Vehcile auction bid history
 


