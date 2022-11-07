# Project Overview
This is the backend for FlyBox. Description of FlyBox TBD...

## Author
Kyle Robison is a full stack web developer. More info TBD...

## Technologies Used
- Java (Server)
- Spring Boot, REST, Data, Security
- MongoDB (Database)
- React.js and JSX(Front End)
- Bootstrap, CSS (Styling)
- Joi (Validation)

## REST API

| HTTP Method | URI                     | Request Body                     | Response Body        | Purpose                               |
|-------------|-------------------------|----------------------------------|----------------------|---------------------------------------|
| POST        | `/register`             | User object with required fields | User object          | Create a new user                     |
| GET         | `/api/users/{username}` |                                  | User object          | Get a user for a given username       |
| GET         | `/api/users/{email}`    |                                  | User object          | Get a user for a given email          |
| GET         | `/api/flybox`           |                                  | Array of Fly objects | Get a list of a user's flies          |
| POST        | `/api/flybox`           | Fly object with required fields  | Fly object           | Add a fly to the user's flybox        |
| GET         | `/api/flybox/{flyName}` |                                  | Fly object           | Get a single fly from a user's flybox |
| PUT         | `/api/flybox/{flyName}` | Fly object with required fields  | Fly object           | Update a fly in the user's flybox     |
| DELETE      | `/api/flybox/{flyName}` |                                  | Fly object           | Delete a fly in the user's flybox     |

