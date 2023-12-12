# To-Do UMS
- [X] Redesign api routes: protected, public, admin, users
  - [X] Admin: delete, edit users.
  - [X] User: Login, edit profile, change password
  - [X] Public: test api
- [X] Add feature: email verification and forgot password
- [ ] Redo user roles
    - [X] Setup an admin account via initialization in db
    - [X] Test roles between spring sec and db
    - [ ] Revise update service
    - [ ] Limit access as to what users can edit for their profile
    - [ ] Allow admin to set roles
- [ ] Refactor code to add exception handling
- [ ] <mark> Create login controller </mark>
- [ ] Add user attributes in model, schema.sql, constructor in model class
- [X] Migrate to postgres database


## Use case
A user management system which allows users to register themselves.
User would have to verify their email first before being able to log in.
Hence, email verification feature is required
Upon successful account creation, users can then edit their profile freely 
Bear in mind, they are somehow limited to edit some fields within their account
Upon starting up the app, an admin account has been created to ensure there is an admin in the sytem.

### Feature: email verification
- [X] Send verification mail
- [X] Implement confirmation
- [X] Enabled account upon verification
- [X] Test for protected api

### Feature: Forgot password

- [X] Send a post request containing user's email to check if user exist
- [X] if exists, generate token then send email with the token
- [X] Success will send an email, containing link to reset-password?token=<token>
- [X] reset-password will accept and inspect the token, and new password