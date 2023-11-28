# To-Do UMS
- [ ] Redo user roles
    - [X] Setup an admin account via initialization in db
    - [ ] Limit access as to what users can edit for their profile
    - [X] Test roles between spring sec and db
    - [ ] Allow admin to set roles
- [ ] <mark> Create login controller </mark>
- [ ] Add user attributes in model, schema.sql, constructor in model class
- [ ] Redesign api routes: protected, public, admin, users
    - [ ] Admin: delete, edit users. 
    - [ ] User: Login, edit profile, change password
    - [ ] Public: test api
- [ ] Add feature: email verification and <mark> forgot password </mark>
- [ ] Migrate to postgres database


## Use case
A user management system which allows users to register themselves.
User would have to verify their email first before being able to log in.
Hence, email verification feature is required
Upon successful account creation, users can then edit their profile freely 
Bear in mind, they are somehow limited to edit some fields within their account
Upon starting up the app, an admin account has been created to ensure there is an admin in the sytem.
