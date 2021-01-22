# Landing 
Whenever the user first hits our website, the Landing Page is the page that they will encounter. This folder is composed of 3 overall components:

##### Login 
Obtains all the information used to log in to your account. Once information is provided, there will be a request sent to log in. **_If successful_**, will render the Profile component. **_If fails_**, will remain on the login page and trigger a warning that the attempt failed. There is also a modal that will pop up that will allow for account recovery of password.

##### SignUp 
Obtains all information used to create an account. Succesful account creation renders the Profile component. Failure will not create an account and route to an error creating account page.

##### Overlay
This component will be used as an overlay to show text that will change out depending upon which component is currently active between the login and signup. It also contains the functions that trigger the switching of the components.
