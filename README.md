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
* so it basically represents table in sqlite db
* DAO : contains method to access db(sqlite) meaning provide API for reading & writting
* into db. It communicates with sqlite. we define all the
* db operations want to made in this contact entity.[Room --> Sqlite <==> Dao] 
* Database : It connects all this different parts like dao, entity 
* and actual instance of db. so basically it is a
* db holder class which serves as a main access point. we have to make singleton of this class
* which means we cant create multiple instance of this db instead we always use same instance 
* everywhere in our app.
* Repository : provides abstraction layer of different data sources.
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
