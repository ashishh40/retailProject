
### 5/03/2025  
- Watched some of the React session on Cornerstone  
- Documented the thoughts on how to go about making it  
- Mapped out the database and what columns I need and what extra could be added  
- Created an empty Spring Boot application with dependencies  
- Researched what functionalities I might need and how I could do it  


### 5/03/2025
- Created a model for the database and added the model to the code
- User,Item,Cart are the databases
- added the controller files which will handle the requests
- the classes handle the registration request, login request
- and the user and admin requests seperately.


### 6/03/2025
- Started working on the registration process 
- from controller to service, saw how i can save the data of registered user in DB
- added a repository package and a class which can handle user Query 
- successfully registered a user with the endpoint 
- login functionality done
- with the count and blocking functionality


### 7/03/2025
- started to work on adding to the cart 
- added a endpoint which takes stores into the cart database the item which was stored and the user who stored it 
- have to stop a blocked user from adding the item
- will be working on cart endpoints next
- Added the functionality that a blocked user cant add to cart 
- Added the get endpoint which fetches the data for the cart table for a particular user
- Added the delete endpoint for cart 
- Added the checkout endpoint just the functionality for fetching the data and just ouputing the total amount in cart
- will have to finish the checkout endpoint 




### 
- added the basic ui to register,login, main page
- now user can register and login through ui
- also user can see all the items on the page and  add to the cart 
- added the endpoint to get all the blocked users from admin side
- added the endpoint to unblock a blocked user
- need to figure out how to restrict user from accessing blocked list 


## 11/03/2025
- solve - one user can add to cart only once      
- Adding a cart page in react
- found error in backend the data was not being sent just the response 200 was sent so updated that 
- added the cart page where a particular user will have their cart items
- added the remove button in the cart 
- added admin page in frontend , the admin can login and unblock a user
- admin auth error    
- added a checkout page where total bill can be displayed
- once the order is placed the cart has to be emptied will have to work on that    
- added so that now the user cant add an item twice in a cart, an item has to be uniqued
- adding filteBy endpoint 



