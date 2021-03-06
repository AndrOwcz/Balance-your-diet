drop table authorities;
create table authorities(username varchar(256), authority varchar(256));

INSERT INTO users (firstName, lastName, username, password, requiredCalories, requiredCarbs, requiredFats, requiredProtein, enabled) VALUES('Jan', 'Kowalski', 'jan.kowal@gmail.com', '$2a$10$rYZzHsfYHWonu29P3g2UZOm9YD0PtcsN7VdQvHyiL.sM8IHwSZSQm', 2000, 300, 70, 50, true);
INSERT INTO authorities(username, authority) VALUES ('jan.kowal@gmail.com', 'admin');

INSERT INTO users (firstName, lastName, username, password, requiredCalories, requiredCarbs, requiredFats, requiredProtein, enabled) VALUES('Bronek', 'Jankowski', 'bronek@gmail.com', '$2a$10$rYZzHsfYHWonu29P3g2UZOm9YD0PtcsN7VdQvHyiL.sM8IHwSZSQm', 2500, 400, 100, 100, true);
INSERT INTO authorities(username, authority) VALUES ('bronek@gmail.com', 'admin');

INSERT INTO categories(name) VALUE ('Vegetables');
INSERT INTO categories(name) VALUE ('Fruits');
INSERT INTO categories(name) VALUE ('Milk & Dairy Products');
INSERT INTO categories(name) VALUE ('Bread');
INSERT INTO categories(name) VALUE ('Meat');
INSERT INTO categories(name) VALUE ('Drinks');

INSERT INTO products(name,calories,carbs,fats,protein, categoryEntity_id) VALUES('Apple', 46, 12, 1, 1, 2);
INSERT INTO products(name,calories,carbs,fats,protein, categoryEntity_id) VALUES('Mozarella', 248, 2, 19, 18, 3);
INSERT INTO products(name,calories,carbs,fats,protein, categoryEntity_id) VALUES('Wholegrain bread', 216, 44, 2, 6, 4);
INSERT INTO products(name,calories,carbs,fats,protein, categoryEntity_id) VALUES('Tomato', 18, 4, 0, 1, 2);
INSERT INTO products(name,calories,carbs,fats,protein, categoryEntity_id) VALUES('Ham', 96, 0, 3, 17, 5);
INSERT INTO products(name,calories,carbs,fats,protein, categoryEntity_id) VALUES('Natural yogurt', 69, 6, 3, 5, 3);
INSERT INTO products(name,calories,carbs,fats,protein, categoryEntity_id) VALUES('Egg', 143, 0, 9, 12, 3);

INSERT INTO productPortions(portion, productEntity_id) VALUES(1,1);
INSERT INTO productPortions(portion, productEntity_id) VALUES(2,2);
INSERT INTO productPortions(portion, productEntity_id) VALUES(3,3);
INSERT INTO productPortions(portion, productEntity_id) VALUES(4,4);
INSERT INTO productPortions(portion, productEntity_id) VALUES(5,5);
INSERT INTO productPortions(portion, productEntity_id) VALUES(6,6);

INSERT INTO meals(name, description, created, mealCalories, mealCarbs, mealFats, mealProtein, userEntity_id) VALUES ('English breakfast with apple', '3 eggs, 1 slice whole grain toast with 1 tablespoon almond butter and an apple', NOW(), 542, 16, 39, 37, 1);
INSERT INTO meals(name, description, created, mealCalories, mealCarbs, mealFats, mealProtein, userEntity_id) VALUES ('meal2', 'description of meal 2', NOW(), 720, 148, 6, 22, 1);
INSERT INTO meals(name, description, created, mealCalories, mealCarbs, mealFats, mealProtein, userEntity_id) VALUES ('meal3', 'description of meal 3', NOW(), 894, 36, 33, 115, 1);
INSERT INTO meals(name, description, created, mealCalories, mealCarbs, mealFats, mealProtein, userEntity_id) VALUES ('meal4', 'description of meal 4', NOW(), 720, 148, 6, 22, 2);

INSERT INTO meals_productPortions(MealEntity_id, productPortions_id) VALUES (1,1);
INSERT INTO meals_productPortions(MealEntity_id, productPortions_id) VALUES (1,2);
INSERT INTO meals_productPortions(MealEntity_id, productPortions_id) VALUES (2,3);
INSERT INTO meals_productPortions(MealEntity_id, productPortions_id) VALUES (2,4);
INSERT INTO meals_productPortions(MealEntity_id, productPortions_id) VALUES (3,5);
INSERT INTO meals_productPortions(MealEntity_id, productPortions_id) VALUES (3,6);

INSERT INTO dailyPlans(name, description, userEntity_id) VALUES ('plan1', 'desc of plan 1', 1);

INSERT INTO dailyPlans_meals(DailyPlanEntity_id, mealEntities_id) VALUES (1,1);
INSERT INTO dailyPlans_meals(DailyPlanEntity_id, mealEntities_id) VALUES (1,2);

INSERT INTO comments(content, userEntity_id) VALUES ('Best meal ever - try it!!!', 1);
INSERT INTO comments(content, userEntity_id) VALUES ('its ok', 2);

INSERT INTO meals_comments(MealEntity_id, comments_id) VALUES (1,1);
INSERT INTO meals_comments(MealEntity_id, comments_id) VALUES (1,2);

CREATE TRIGGER add_authority AFTER INSERT ON users FOR EACH ROW BEGIN INSERT INTO authorities(username, authority) VALUES(new.username, 'admin'); END;
