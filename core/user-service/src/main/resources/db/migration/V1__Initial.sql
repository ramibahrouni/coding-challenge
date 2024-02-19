

USE users_db;
GO

CREATE TABLE users_db.dbo.users
(
    username    VARCHAR(25) PRIMARY KEY,
    password    VARCHAR(255) NOT NULL,
    role        VARCHAR(260) NOT NULL

);
GO