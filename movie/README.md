**REST API DOCUMENTATION**
----
  _This is the list of available REST API CALLS for the Movie Spring Boot App_
  
* **Add Movie**
    * URL: _/api/movies/_
    * Method: `POST`
    * Sample Call:
     
       `curl -X POST -H "Content-Type: application/json" -d '{"name": "titanic","director": "cameron","rating": 7.7}' "{ServerURL}/api/movies/"`
    * Success Response:
    
             Code: 200 
             Content: `{
                         "id": 2,
                         "name": "titanic",
                         "director": "cameron",
                         "rating": 7.7
                       }` 
* **Get All Movies**
    * URL: _/api/movies/_
    * Method: `GET`
    * Success Response:
    
             Code: 200 
             Content: `[
                         {
                           "id": 1,
                           "name": "titanic",
                           "director": "cameron",
                           "rating": 7.7
                         },
                         ... ` 
 
* **Get Movie By ID**
    * URL: _/api/movies/{id}_
    * Method: `GET`
    * Success Response:
    
             Code: 200 
             Content: `{
                         "id": 1,
                         "name": "titanic",
                         "director": "cameron",
                         "rating": 7.7
                       }` 
* **Get Movies By Name**
    * URL: _/api/movies/:name_
    * Method: `GET`
    * URL Params
     
       `name=[string]`
    * Success Response:
    
             Code: 200 
             Content: `[
                         {
                           "id": 4,
                           "name": "titanic",
                           "director": "cameron",
                           "rating": 7.7
                         }
                       ]` 

* **Update Movie From DB**
    * URL: _/api/movies/{id}_
    * Method: `PUT`
    * Sample Call:
     
       `curl -X PUT -H "Content-Type: application/json" -d '{"name": "titanic","director": "cameron","rating": 7.7}' "{ServerURL}/api/movies/2"`
    * Success Response:
    
             Code: 200 
             Content: `{
                         "id": 2,
                         "name": "titanic",
                         "director": "cameron",
                         "rating": 7.7
                       }` 

* **Delete Movie By ID**
    * URL: _/api/movies/{id}_
    * Method: `DELETE`

* **Delete All Movies**
    * URL: _/api/movies/_
    * Method: `DELETE`
