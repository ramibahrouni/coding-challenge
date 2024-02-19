USE master;
GO

CREATE DATABASE BOOK_EVENTS;
GO

ALTER DATABASE BOOK_EVENTS
    SET CHANGE_TRACKING = ON
    (CHANGE_RETENTION = 2 DAYS, AUTO_CLEANUP = ON);
GO

USE BOOK_EVENTS
GO
EXEC sys.sp_cdc_enable_db
GO

CREATE TABLE BOOK_EVENTS.dbo.BOOK_ORDERS
(
    id    INT PRIMARY KEY,
    value VARCHAR(50) NOT NULL,
);
GO

CREATE TABLE BOOK_EVENTS.dbo.BOOK_ORDERS_OUTBOX
(
    id    INT PRIMARY KEY,
    value VARCHAR(50) NOT NULL,
);
GO
