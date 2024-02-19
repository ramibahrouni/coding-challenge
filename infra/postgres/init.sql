
-- Create Schema
create SCHEMA dbz;

create table dbz.BOOK_NOTIF_EVENTS
(
    id   VARCHAR(50),
    order_id VARCHAR(100),
    notif_type  VARCHAR(400)
);

create table dbz.BOOK_NOTIF_OUTBOX
(
    notif_id   INTEGER,
    status VARCHAR(150)
);

