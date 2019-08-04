# MVVMRoomDBLiveData
This is a experiment project which aims to provide fundamental idea about ANDROID ARCHITECTURE COMPONENTS, ViewModel, LiveData, RoomDB

/*
* What is ROOM db?
* 
* db layer top of sqlite/ provide an abstraction layer over sqlite to allow
* fluent database access
* Room will map our db object to java object
* COMPONENTS OF ROOM :
* Entity : defines schema of db table   / a representation of a plan for db table
* so it basically represnts table in sqlite db
* DAO : contains method to access db(sqlite) meaning provide API for reading & writting
* into db. It communicates with sqlite.[Room --> Sqlite <==> Dao] 
* Database : db holder class which serves as a main access point
*
*
* ViewModel : holding and preparing data for the UI 
* SO activity / frag connects this 
* viewmodel and gets all necessary data fm there and reporting user interaciton
* to viewmodel and then viewmodel forward this user interaciton to underline layout 
* of the app either to load new data or to make change to dataset.
* SO viewmodel works as a gateway for the UI controller (activity/frag)
* and rest of the app.
* 
*
* */
