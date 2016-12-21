Scenario:  Login with a valid credentials
Given am in login page
When enter Username as "usuario" and password "usuario"
And press Login
Then should go to "usuarios/home"

Scenario:  Login failed
Given am in login page
When have entered invalid Username as "234sdfss" and invalid password as "234sdfss"
And press Login
Then should go to "/usuarios/loginAction"