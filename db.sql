DROP TABLE IF EXISTS "order1";
DROP TABLE IF EXISTS "car_option";
DROP TABLE IF EXISTS "car";
DROP TABLE IF EXISTS "option";
DROP TABLE IF EXISTS "account";

CREATE TABLE "account" (
	"id" SERIAL PRIMARY KEY,
	"login" VARCHAR(50) UNIQUE,
	"password" VARCHAR(50),
	"firstname" VARCHAR(50),
	"lastname" VARCHAR(50),
	"phone" VARCHAR(50),
	"rating" FLOAT,
	"status_user" varchar(50),
	"role" varchar(50)
);
CREATE TABLE "option" (
	"id" SERIAL PRIMARY KEY,
	"text" VARCHAR(100) UNIQUE
);
CREATE TABLE "car" (
	"id" SERIAL PRIMARY KEY,
	"account_id" INTEGER REFERENCES "account"(id),
	"number" VARCHAR(10) UNIQUE,
	"car_type" VARCHAR(50)
);
CREATE TABLE "car_option" (
	"id" SERIAL PRIMARY KEY,
	"car_id" INTEGER REFERENCES "car"(id) ON DELETE CASCADE,
	"option_id" INTEGER REFERENCES "option"(id) ON DELETE CASCADE
);
CREATE TABLE "order1" (
	"id" SERIAL PRIMARY KEY,
	"comment" VARCHAR(200),
	"location" VARCHAR(100),
	"target" VARCHAR(100),
	"price" INTEGER,
	"account_id" INTEGER REFERENCES "account"(id),
	"car_id" INTEGER REFERENCES "car"(id),
	"status_order" VARCHAR(50)
);

INSERT INTO "account" ("login", "password", "firstname", "lastname", "phone", "rating", "status_user", "role") VALUES ('pas', 'pas', 'Pas', 'Pas', '+375299701065', 5.0, 'ACTIVE', 'ROLE_PASSENGER');
INSERT INTO "account" ("login", "password", "firstname", "lastname", "phone", "rating", "status_user", "role") VALUES ('max', '0706025b2bbcec1ed8d64822f4eccd96314938d0', 'Maxim', 'Karpik', '+375292222222', 5.0, 'ACTIVE', 'ROLE_PASSENGER');
INSERT INTO "account" ("login", "password", "firstname", "lastname", "phone", "rating", "status_user", "role") VALUES ('ivan', 'ivan', 'Ivan', 'Ivan', '+375293333333', 5.0, 'BANNED', 'ROLE_PASSENGER');
INSERT INTO "account" ("login", "password", "firstname", "lastname", "phone", "rating", "status_user", "role") VALUES ('drv', '19e5a9d057ae3469acf8d991c931e7354cc2c659', 'Drv', 'Drv', '+37529444444', 5.0, 'ACTIVE', 'ROLE_DRIVER');
INSERT INTO "account" ("login", "password", "firstname", "lastname", "phone", "rating", "status_user", "role") VALUES ('vlad', 'vlad', 'Vlad', 'Vlad', '+375295555555', 5.0, 'ACTIVE', 'ROLE_DRIVER');
INSERT INTO "account" ("login", "password", "firstname", "lastname", "phone", "rating", "status_user", "role") VALUES ('dima', 'dima', 'Dima', 'Dima', '+375296666666', 5.0, 'ACTIVE', 'ROLE_DRIVER');
INSERT INTO "account" ("login", "password", "firstname", "lastname", "phone", "rating", "status_user", "role") VALUES ('moder', 'b36c04f4f2496b9525646ec01ff51e2007e79a2c', 'Moder', 'Moder', '+375297777777', 5.0, 'ACTIVE', 'ROLE_MODER');
INSERT INTO "account" ("login", "password", "firstname", "lastname", "phone", "rating", "status_user", "role") VALUES ('admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'Admin', 'Admin', '+375298888888', 5.0, 'ACTIVE', 'ROLE_ADMIN');
INSERT INTO "option" ("text") VALUES ('Child chair');
INSERT INTO "option" ("text") VALUES ('Without music');
INSERT INTO "option" ("text") VALUES ('Ultra fast driver');
INSERT INTO "option" ("text") VALUES ('Food at car');
INSERT INTO "option" ("text") VALUES ('TV at car');
INSERT INTO "option" ("text") VALUES ('test');
INSERT INTO "car" ("account_id", "number", "car_type") VALUES (4, 'A777MR', 'SEDAN');
INSERT INTO "car" ("account_id", "number", "car_type") VALUES (5, 'Q123WE', 'SEDAN');
INSERT INTO "car" ("account_id", "number", "car_type") VALUES (6, 'G234HS', 'UNIVERSAL');
INSERT INTO "car" ("account_id", "number", "car_type") VALUES (null, 'K618LJ', 'PICKUP');
INSERT INTO "car" ("account_id", "number", "car_type") VALUES (null, 'K893GD', 'MINIVAN');
INSERT INTO "car" ("account_id", "number", "car_type") VALUES (null, 'L917QW', 'MINIBUS');
INSERT INTO "car_option" ("car_id", "option_id") VALUES (1, 1);
INSERT INTO "car_option" ("car_id", "option_id") VALUES (1, 2);
INSERT INTO "car_option" ("car_id", "option_id") VALUES (2, 3);
INSERT INTO "car_option" ("car_id", "option_id") VALUES (2, 1);
INSERT INTO "car_option" ("car_id", "option_id") VALUES (3, 2);
INSERT INTO "car_option" ("car_id", "option_id") VALUES (3, 4);
INSERT INTO "car_option" ("car_id", "option_id") VALUES (4, 1);
INSERT INTO "car_option" ("car_id", "option_id") VALUES (4, 2);
INSERT INTO "car_option" ("car_id", "option_id") VALUES (4, 4);
INSERT INTO "car_option" ("car_id", "option_id") VALUES (5, 3);
INSERT INTO "car_option" ("car_id", "option_id") VALUES (5, 4);
INSERT INTO "car_option" ("car_id", "option_id") VALUES (5, 5);
INSERT INTO "order1" ("comment", "location", "target", "price", "account_id", "car_id", "status_order") VALUES ('Drive!!!', 'A', 'B', 5, 2, 1, 'FAILED');
INSERT INTO "order1" ("comment", "location", "target", "price", "account_id", "car_id", "status_order") VALUES ('To home', '1', '2', 5, 1, 1, 'RUN');
INSERT INTO "order1" ("comment", "location", "target", "price", "account_id", "car_id", "status_order") VALUES ('I`m super passenger', 'Start', 'Finish', 5, 2, 1, 'RUN');
INSERT INTO "order1" ("comment", "location", "target", "price", "account_id", "car_id", "status_order") VALUES ('aaaaa', 'Work', 'Home', 5, 1, 1, 'DONE');
INSERT INTO "order1" ("comment", "location", "target", "price", "account_id", "car_id", "status_order") VALUES ('qqqqqqq', 'From', 'To', 5, 2, 2, 'DONE');
INSERT INTO "order1" ("comment", "location", "target", "price", "account_id", "car_id", "status_order") VALUES ('sssssss', 'X', 'Y', 5, 2, null, 'AWAIT');
INSERT INTO "order1" ("comment", "location", "target", "price", "account_id", "car_id", "status_order") VALUES ('dddddd', 'dsf', 'dgj', 5, 2, null, 'CANCELLED');
INSERT INTO "order1" ("comment", "location", "target", "price", "account_id", "car_id", "status_order") VALUES ('fffffff', 'werf', 'ned', 5, 3, null, 'CANCELLED');
INSERT INTO "order1" ("comment", "location", "target", "price", "account_id", "car_id", "status_order") VALUES ('ggggggggg', 'retv', 'yt', 5, 3, 3, 'DONE');
INSERT INTO "order1" ("comment", "location", "target", "price", "account_id", "car_id", "status_order") VALUES ('hhhhhhhhh', 'bdfs', 'weh', 5, 3, 3, 'DONE');

select * from "car";
select * from "order1";
select * from "option";
select * from "car_option";
select * from "account";




































