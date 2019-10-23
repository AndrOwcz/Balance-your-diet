INSERT INTO users (firstName, lastName, email, password, requiredCalories, requiredCarbs, requiredFats, requiredProtein) VALUES('Jan', 'Kowalski', 'jan.kowal@gmail.com', '1', 2000, 1000, 1000, 100);
INSERT INTO users (firstName, lastName, email, password, requiredCalories, requiredCarbs, requiredFats, requiredProtein) VALUES('Jan', 'Jankowski', 'bronek@gmail.com', '1', 2000, 500, 300, 800);

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