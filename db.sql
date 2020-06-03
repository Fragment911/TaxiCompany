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
	"text" VARCHAR(100)
);
CREATE TABLE "car" (
	"id" SERIAL PRIMARY KEY,
	"account_id" INTEGER REFERENCES "account"(id),
	"number" VARCHAR(10),
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
	"mark" INTEGER,
	"passenger_id" INTEGER REFERENCES "account"(id),
	"driver_id" INTEGER REFERENCES "account"(id),
	"car_id" INTEGER REFERENCES "car"(id),
	"status_order" VARCHAR(50)
);

INSERT INTO "account" ("login", "password", "firstname", "lastname", "phone", "rating", "status_user", "role") VALUES ('max', '0706025b2bbcec1ed8d64822f4eccd96314938d0', 'Maxim', 'Karpik', '+375291111111', 0.0, 'ACTIVE', 'ROLE_PASSENGER');
INSERT INTO "account" ("login", "password", "firstname", "lastname", "phone", "rating", "status_user", "role") VALUES ('ivan', '0706025b2bbcec1ed8d64822f4eccd96314938d0', 'Ivan', 'Ivanov', '+375292222222', 0.0, 'ACTIVE', 'ROLE_PASSENGER');
INSERT INTO "account" ("login", "password", "firstname", "lastname", "phone", "rating", "status_user", "role") VALUES ('petr', 'c327e92ea10f7773037429ba850de356a617881c', 'Petr', 'Petrov', '+375293333333', 0.0, 'BANNED', 'ROLE_PASSENGER');
INSERT INTO "account" ("login", "password", "firstname", "lastname", "phone", "rating", "status_user", "role") VALUES ('dima', 'bed3f98d0a894717be46c58ffa90302af9946688', 'Dmitry', 'Dmitriev', '+375294444444', 4.0, 'ACTIVE', 'ROLE_DRIVER');
INSERT INTO "account" ("login", "password", "firstname", "lastname", "phone", "rating", "status_user", "role") VALUES ('vlad', '85d5c8dcb7db39358916efb7e6f840502f547b2c', 'Vladislav', 'Vladov', '+375295555555', 4.0, 'ACTIVE', 'ROLE_DRIVER');
INSERT INTO "account" ("login", "password", "firstname", "lastname", "phone", "rating", "status_user", "role") VALUES ('lex', 'd1f3732a9a6a6d5ae438388e1df2164bdb35d371', 'Alexey', 'Alexeev', '+375296666666', 3.0, 'ACTIVE', 'ROLE_DRIVER');
INSERT INTO "account" ("login", "password", "firstname", "lastname", "phone", "rating", "status_user", "role") VALUES ('moder', 'b36c04f4f2496b9525646ec01ff51e2007e79a2c', 'Moder', 'Moderov', '+375297777777', 0.0, 'ACTIVE', 'ROLE_MODER');
INSERT INTO "account" ("login", "password", "firstname", "lastname", "phone", "rating", "status_user", "role") VALUES ('admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'Admin', 'Adminov', '+375298888888', 0.0, 'ACTIVE', 'ROLE_ADMIN');
INSERT INTO "option" ("text") VALUES ('Child chair');
INSERT INTO "option" ("text") VALUES ('Without music');
INSERT INTO "option" ("text") VALUES ('Ultra fast driver');
INSERT INTO "option" ("text") VALUES ('Food at car');
INSERT INTO "option" ("text") VALUES ('TV at car');
INSERT INTO "car" ("account_id", "number", "car_type") VALUES (4, 'A777MR', 'SEDAN');
INSERT INTO "car" ("account_id", "number", "car_type") VALUES (5, 'Q123WE', 'SEDAN');
INSERT INTO "car" ("account_id", "number", "car_type") VALUES (null, 'G234HS', 'UNIVERSAL');
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
INSERT INTO "order1" ("comment", "location", "target", "price", "mark", "passenger_id", "driver_id", "car_id", "status_order") VALUES ('My comment', '37 Norfolk St', '54  Straford Park', 5, 0, 1, null, null, 'AWAIT');
INSERT INTO "order1" ("comment", "location", "target", "price", "mark", "passenger_id", "driver_id", "car_id", "status_order") VALUES ('Drive!!!', '3347  Pooh Bear Lane', '245  Lake Floyd Circle', 5, 0, 2, null, null, 'AWAIT');
INSERT INTO "order1" ("comment", "location", "target", "price", "mark", "passenger_id", "driver_id", "car_id", "status_order") VALUES ('Drive!!!', '1015  Spirit Drive', '3689  Keyser Ridge Road', 7, 0, 3, 5, 2, 'RUN');
INSERT INTO "order1" ("comment", "location", "target", "price", "mark", "passenger_id", "driver_id", "car_id", "status_order") VALUES ('My comment', '3109  Joyce Street', '3109  Joyce Street', 6, 3, 1, 4, 4, 'DONE');
INSERT INTO "order1" ("comment", "location", "target", "price", "mark", "passenger_id", "driver_id", "car_id", "status_order") VALUES ('My comment', '4851  Aspen Court', '296  Washington Street', 5, 5, 1, 5, 2, 'DONE');
INSERT INTO "order1" ("comment", "location", "target", "price", "mark", "passenger_id", "driver_id", "car_id", "status_order") VALUES ('My comment', '4800  Camden Street', '1234  Fairfax Drive', 4, 4, 1, 6, 5, 'DONE');
INSERT INTO "order1" ("comment", "location", "target", "price", "mark", "passenger_id", "driver_id", "car_id", "status_order") VALUES ('My comment', '3356  Blane Street', '1790  Goff Avenue', 5, 5, 2, 4, 6, 'DONE');
INSERT INTO "order1" ("comment", "location", "target", "price", "mark", "passenger_id", "driver_id", "car_id", "status_order") VALUES ('My comment', '3082  Horseshoe Lane', '3578  Clement Street', 6, 2, 2, 5, 4, 'DONE');
INSERT INTO "order1" ("comment", "location", "target", "price", "mark", "passenger_id", "driver_id", "car_id", "status_order") VALUES ('My comment', '4373  Oak Way', '4471  Breezewood Court', 5, 2, 3, 6, 2, 'DONE');
INSERT INTO "order1" ("comment", "location", "target", "price", "mark", "passenger_id", "driver_id", "car_id", "status_order") VALUES ('My comment', '492  Raoul Wallenberg Place', '4245  Hemlock Lane', 4, 4, 3, 4, 1, 'DONE');
INSERT INTO "order1" ("comment", "location", "target", "price", "mark", "passenger_id", "driver_id", "car_id", "status_order") VALUES ('My comment', '3615  Oliverio Drive', '3802  Laurel Lane', 5, 5, 3, 5, 3, 'DONE');
INSERT INTO "order1" ("comment", "location", "target", "price", "mark", "passenger_id", "driver_id", "car_id", "status_order") VALUES ('To home', '3313  Short Street', '1854  Gateway Road', 5, 0, 1, null, null, 'CANCELLED');
INSERT INTO "order1" ("comment", "location", "target", "price", "mark", "passenger_id", "driver_id", "car_id", "status_order") VALUES ('I`m super passenger', '4459  Karen Lane', '2196  Conference Center Way', 5, 0, 2, null, null, 'CANCELLED');
INSERT INTO "order1" ("comment", "location", "target", "price", "mark", "passenger_id", "driver_id", "car_id", "status_order") VALUES ('My comment', '3563  Gateway Road', '4188  Memory Lane', 5, 0, 3, null, null, 'CANCELLED');
INSERT INTO "order1" ("comment", "location", "target", "price", "mark", "passenger_id", "driver_id", "car_id", "status_order") VALUES ('My comment', '1684  Diane Street', '4414  Honeysuckle Lane', 5, 0, 1, null, null, 'CANCELLED');
INSERT INTO "order1" ("comment", "location", "target", "price", "mark", "passenger_id", "driver_id", "car_id", "status_order") VALUES ('My comment', '3034  Dancing Dove Lane', '4810  Camden Street', 5, 0, 2, null, null, 'CANCELLED');

select * from "car";
select * from "order1";
select * from "option";
select * from "car_option";
select * from "account";



























