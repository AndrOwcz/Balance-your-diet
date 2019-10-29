drop table authorities;
create table authorities(username varchar(256), authority varchar(256));

INSERT INTO users (firstName, lastName, username, password, requiredCalories, requiredCarbs, requiredFats, requiredProtein, enabled) VALUES('Jan', 'Kowalski', 'jan.kowal@gmail.com', '$2a$10$rYZzHsfYHWonu29P3g2UZOm9YD0PtcsN7VdQvHyiL.sM8IHwSZSQm', 2000, 1000, 1000, 100, true);
INSERT INTO authorities(username, authority) VALUES ('jan.kowal@gmail.com', 'admin');

INSERT INTO users (firstName, lastName, username, password, requiredCalories, requiredCarbs, requiredFats, requiredProtein, enabled) VALUES('Bronek', 'Jankowski', 'bronek@gmail.com', '$2a$10$rYZzHsfYHWonu29P3g2UZOm9YD0PtcsN7VdQvHyiL.sM8IHwSZSQm', 2000, 500, 300, 800, true);
INSERT INTO authorities(username, authority) VALUES ('bronek@gmail.com', 'admin');


INSERT INTO categories(name) VALUE ('warzywa');
INSERT INTO categories(name) VALUE ('owoce');
INSERT INTO categories(name) VALUE ('nabial i jaja');
INSERT INTO categories(name) VALUE ('pieczywo');
INSERT INTO categories(name) VALUE ('mieso');
INSERT INTO categories(name) VALUE ('napoje');

INSERT INTO products(name,calories,carbs,fats,protein, categoryEntity_id) VALUES('Jablko', 46, 12, 1, 1, 2);
INSERT INTO products(name,calories,carbs,fats,protein, categoryEntity_id) VALUES('Ser Mozarella', 248, 2, 19, 18, 3);
INSERT INTO products(name,calories,carbs,fats,protein, categoryEntity_id) VALUES('Chleb pelnoziarnisty', 216, 44, 2, 6, 4);
INSERT INTO products(name,calories,carbs,fats,protein, categoryEntity_id) VALUES('Pomidor', 18, 4, 0, 1, 2);
INSERT INTO products(name,calories,carbs,fats,protein, categoryEntity_id) VALUES('Szynka', 96, 0, 3, 17, 5);
INSERT INTO products(name,calories,carbs,fats,protein, categoryEntity_id) VALUES('Jogurt Naturalny', 69, 6, 3, 5, 3);

INSERT INTO productPortions(portion) VALUES(10);
INSERT INTO productPortions(portion) VALUES(20);
INSERT INTO productPortions(portion) VALUES(30);
INSERT INTO productPortions(portion) VALUES(40);
INSERT INTO productPortions(portion) VALUES(50);
INSERT INTO productPortions(portion) VALUES(60);

INSERT INTO productPortions_products(ProductPortionEntity_id, productEntities_id) VALUES(1,1);
INSERT INTO productPortions_products(ProductPortionEntity_id, productEntities_id) VALUES(2,2);
INSERT INTO productPortions_products(ProductPortionEntity_id, productEntities_id) VALUES(3,3);
INSERT INTO productPortions_products(ProductPortionEntity_id, productEntities_id) VALUES(4,4);
INSERT INTO productPortions_products(ProductPortionEntity_id, productEntities_id) VALUES(5,5);
INSERT INTO productPortions_products(ProductPortionEntity_id, productEntities_id) VALUES(6,6);

INSERT INTO meals(name, description, created, userEntity_id) VALUES ('meal1', 'description of meal 1', NOW(), 1);
INSERT INTO meals(name, description, created, userEntity_id) VALUES ('meal2', 'description of meal 2', NOW(), 1);
INSERT INTO meals(name, description, created, userEntity_id) VALUES ('meal3', 'description of meal 3', NOW(), 1);

INSERT INTO meals_productPortions(MealEntity_id, productPortions_id) VALUES (1,1);
INSERT INTO meals_productPortions(MealEntity_id, productPortions_id) VALUES (1,2);
INSERT INTO meals_productPortions(MealEntity_id, productPortions_id) VALUES (2,3);
INSERT INTO meals_productPortions(MealEntity_id, productPortions_id) VALUES (2,4);
INSERT INTO meals_productPortions(MealEntity_id, productPortions_id) VALUES (3,5);
INSERT INTO meals_productPortions(MealEntity_id, productPortions_id) VALUES (3,6);

