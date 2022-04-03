Postman collection: https://www.postman.com/research-meteorologist-93401167/workspace/superdevs/collection/19959360-9b7a2d92-8d20-4071-909d-9140969523ac?action=share&creator=19959360

Setup: 

1. Create database hospital, username: pjanowiak/pjanowiak or reconfigure spring boot.
2. Create first staff member using code generated at the startup e.g.
    ```
   2022-04-03 23:57:32.173  INFO 52594 --- [           main] com.superdevs.hospitalmigration.App      : INITIAL SECURITY KEY IS: 83f547a6-20a6-4012-8f15-104ff086f586
   ```
   '83f547a6-20a6-4012-8f15-104ff086f586' code should be used as initial x-staff-uuid header.
3. I would be glad if we can discuss any issues with the code. It is based on some assumptions and style. 
4. Tests use the same database as "production one".