insert into role(name, redistribution_rate, salary) values ('Patron', 40, 40000)
insert into role(name, redistribution_rate, salary) values ('Responsable', 40, 40000)
insert into role(name, redistribution_rate, salary) values ('CDI', 40, 30000)
insert into role(name, redistribution_rate, salary) values ('CDD', 30, 30000)

insert into users(first_name, last_name, password, phone, username, role_id, holiday, warning1, warning2, quota, exporter_quota, clean_money_salary, dirty_money_salary, clean_money_salary_previous_week, dirty_money_salary_previous_week) values ('Ramon', 'Cruz', '$2a$10$UCEisteDN7GY7d7voMGYZOeGncH4Jd4daExzPeninBel6v8ybqIzG', '123456789', 'ramon.cruz',1, false, false, false, false, false, 0, 0, 0, 0)
insert into users(first_name, last_name, password, phone, username, role_id, holiday, warning1, warning2, quota, exporter_quota, clean_money_salary, dirty_money_salary, clean_money_salary_previous_week, dirty_money_salary_previous_week) values ('test', '1', '$2a$10$UCEisteDN7GY7d7voMGYZOeGncH4Jd4daExzPeninBel6v8ybqIzG', '123456789', '1',2, false, false, false, true, false, 0, 0, 0, 0)
insert into users(first_name, last_name, password, phone, username, role_id, holiday, warning1, warning2, quota, exporter_quota, clean_money_salary, dirty_money_salary, clean_money_salary_previous_week, dirty_money_salary_previous_week) values ('test', '2', '$2a$10$UCEisteDN7GY7d7voMGYZOeGncH4Jd4daExzPeninBel6v8ybqIzG', '123456789', '2',2, false, false, false, true, false, 0, 0, 0, 0)
insert into users(first_name, last_name, password, phone, username, role_id, holiday, warning1, warning2, quota, exporter_quota, clean_money_salary, dirty_money_salary, clean_money_salary_previous_week, dirty_money_salary_previous_week) values ('test', '3', '$2a$10$UCEisteDN7GY7d7voMGYZOeGncH4Jd4daExzPeninBel6v8ybqIzG', '123456789', '3',2, false, false, false, true, false, 0, 0, 0, 0)
insert into users(first_name, last_name, password, phone, username, role_id, holiday, warning1, warning2, quota, exporter_quota, clean_money_salary, dirty_money_salary, clean_money_salary_previous_week, dirty_money_salary_previous_week) values ('test', '4', '$2a$10$UCEisteDN7GY7d7voMGYZOeGncH4Jd4daExzPeninBel6v8ybqIzG', '123456789', '4',2, false, false, false, true, false, 0, 0, 0, 0)
insert into users(first_name, last_name, password, phone, username, role_id, holiday, warning1, warning2, quota, exporter_quota, clean_money_salary, dirty_money_salary, clean_money_salary_previous_week, dirty_money_salary_previous_week) values ('test', '5', '$2a$10$UCEisteDN7GY7d7voMGYZOeGncH4Jd4daExzPeninBel6v8ybqIzG', '123456789', '5',2, false, true, false, true, false, 0, 0, 0, 0)
insert into users(first_name, last_name, password, phone, username, role_id, holiday, warning1, warning2, quota, exporter_quota, clean_money_salary, dirty_money_salary, clean_money_salary_previous_week, dirty_money_salary_previous_week) values ('test', '6', '$2a$10$UCEisteDN7GY7d7voMGYZOeGncH4Jd4daExzPeninBel6v8ybqIzG', '123456789', '6',3, false, false, false, true, false, 0, 0, 0, 0)
insert into users(first_name, last_name, password, phone, username, role_id, holiday, warning1, warning2, quota, exporter_quota, clean_money_salary, dirty_money_salary, clean_money_salary_previous_week, dirty_money_salary_previous_week) values ('test', '7', '$2a$10$UCEisteDN7GY7d7voMGYZOeGncH4Jd4daExzPeninBel6v8ybqIzG', '123456789', '7',3, false, false, false, true, false, 0, 0, 0, 0)
insert into users(first_name, last_name, password, phone, username, role_id, holiday, warning1, warning2, quota, exporter_quota, clean_money_salary, dirty_money_salary, clean_money_salary_previous_week, dirty_money_salary_previous_week) values ('test', '8', '$2a$10$UCEisteDN7GY7d7voMGYZOeGncH4Jd4daExzPeninBel6v8ybqIzG', '123456789', '8',3, false, true, true, false, false, 0, 0, 0, 0)
insert into users(first_name, last_name, password, phone, username, role_id, holiday, end_of_holiday, warning1, warning2, quota, exporter_quota, clean_money_salary, dirty_money_salary, clean_money_salary_previous_week, dirty_money_salary_previous_week) values ('test', '9', '$2a$10$UCEisteDN7GY7d7voMGYZOeGncH4Jd4daExzPeninBel6v8ybqIzG', '123456789', '9',3, true, '2025-09-15', false, false, false, false, 0, 0, 0, 0)

insert into consumable(name, quantity) values ('Kit de réparation', 100)

insert into product(name, clean_money, dirty_money, stock) values ('Cigarette', 70, 35, 1000)
insert into product(name, clean_money, dirty_money, stock) values ('Menthol', 90, 60, 100)

insert into contract(company, reduction) values ('Cantina', 10)
insert into contract(company, reduction) values ('Brasserie', 25)

insert into stock(date, type_of_stock_movement, product_id, quantity_mouvement, user_id) values ('2025-09-09 00:00:00.000000', 1, 1, 100, 1)

insert into customer_dirty_sale_rate(id, customer_dirty_sale_rate) values (1, 35)

insert into accounting_reboot_information(accounting_reboot_date, sales_locked) values ('2025-09-12 00:00:00', false)

insert into reward(position, reward_amount) values ('1er', 20000)
insert into reward(position, reward_amount) values ('2eme', 15000)
insert into reward(position, reward_amount) values ('3eme', 10000)
