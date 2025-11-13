-- --------------------------------------------------------
-- Hôte:                         51.68.224.121
-- Version du serveur:           8.0.43 - MySQL Community Server - GPL
-- SE du serveur:                Linux
-- HeidiSQL Version:             12.11.0.7065
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Listage des données de la table tabarico.accounting_reboot_information : ~1 rows (environ)
INSERT INTO `accounting_reboot_information` (`id`, `sales_locked`, `accounting_reboot_date`) VALUES(2, b'0', '2025-09-26 22:00:08.401325');

-- Listage des données de la table tabarico.consumable : ~0 rows (environ)
INSERT INTO `consumable` (`id`, `quantity`, `name`) VALUES(2, 17, 'Kit de réparation');

-- Listage des données de la table tabarico.contract : ~2 rows (environ)
INSERT INTO `contract` (`id`, `reduction`, `company`) VALUES(3, 20, 'Repairico'),(4, 25, 'EMS de Cayo'),(5, 20, 'Cantina de Cayo');

-- Listage des données de la table tabarico.customer_dirty_sale_rate : ~0 rows (environ)
INSERT INTO `customer_dirty_sale_rate` (`customer_dirty_sale_rate`, `id`) VALUES(35, 1);

-- Listage des données de la table tabarico.product : ~5 rows (environ)
INSERT INTO `product` (`clean_money`, `dirty_money`, `id`, `stock`, `name`) VALUES(70, 35, 3, 3751, 'Cigarette'),(90, 60, 4, 391, 'Menthol'),(210, 150, 5, 60, 'Cigare'),(1400, 700, 6, 0, 'Paquet de cigarette'),(700, 550, 7, 0, 'Vape vanille'),(3500, 3000, 8, 0, 'Kit vape');

-- Listage des données de la table tabarico.reward : ~3 rows (environ)
INSERT INTO `reward` (`id`, `reward_amount`, `position`) VALUES(1, 20000, '1er'),(2, 15000, '2eme'),(3, 10000, '3eme');

-- Listage des données de la table tabarico.role : ~4 rows (environ)
INSERT INTO `role` (`id`, `redistribution_rate`, `salary`, `name`) VALUES(1, 40, 40000, 'Patron'),(5, 40, 40000, 'Responsable'),(6, 40, 30000, 'CDI'),(7, 30, 30000, 'CDD'),(8,40,40000,'Rh');

-- Listage des données de la table tabarico.users : ~21 rows (environ)
INSERT INTO `users` (`clean_money_salary`, `clean_money_salary_previous_week`, `dirty_money_salary`, `dirty_money_salary_previous_week`, `end_of_holiday`, `exporter_quota`, `holiday`, `id`, `quota`, `role_id`, `warning1`, `warning2`, `first_name`, `identity_card_image`, `last_name`, `password`, `phone`, `date_of_hire`, `username`) VALUES(90925, 0, 0, 0, NULL, b'1', b'0', 1, b'1', 1, b'0', b'0', 'Ramon', NULL, 'Cruz', '$2a$10$AwZBzaXXJ/FRjmBAqq2Tf.UzJtHl0umXAjZMKxryfYgr1nzLFWnBm', '(205) 107-8133', '2025-09-27', 'ramon.cruz'),(0, 0, 0, 0, NULL, b'0', b'0', 11, b'0', 1, b'0', b'0', 'Maya', NULL, 'Soares', '$2a$10$eEWgQ.R5uaxrWCWUPIrFoOzzjBN.xOsow.pJepzSw.l.e9UEaLBxG', '(602) 654-7291', NULL, 'Maya.Soares'),(0, 0, 0, 0, NULL, b'0', b'0', 12, b'0', 1, b'0', b'0', 'Miguel Angel', NULL, 'Carreon', '$2a$10$jNx6OEtsj8gc5WAhTHZafuP9WZT.4SSab64q45KfeBi.duq5zu9Mu', '(205) 095-1096', NULL, 'miguelangel.carreon'),(0, 0, 0, 0, NULL, b'0', b'0', 13, b'0', 1, b'0', b'0', 'Andres', NULL, 'Torreto', '$2a$10$bMplgfJMSLowq1c86mo9a.gx3qaV60bjYLl/6kTDEVCzbjh9kuZru', '(480) 481-4493', NULL, 'andres.torreto'),(0, 0, 0, 0, NULL, b'0', b'0', 14, b'0', 6, b'0', b'0', 'Rayane', NULL, 'Bulbul', '$2a$10$g4HHNjFU9B6nn/6YxyQx9uGPHeUZ5s/BKnAWfjZi4txPCOV3AAQiK', '(602) 341-5297', NULL, 'Rayane.bulbul'),(141976, 113406, 0, 0, NULL, b'1', b'0', 15, b'1', 6, b'0', b'0', 'Ramy', 'CarteIdentite_ramy.kalwani.png', 'Kalwani', '$2a$10$s.LGYDUCcZJMVoBmlNvk0.Lj1Hz3xdnvG0BFwuB.UbPzBiS13enJS', '(205) 833-3525', NULL, 'ramy.kalwani'),(0, 0, 0, 0, NULL, b'0', b'0', 16, b'0', 6, b'0', b'0', 'Fernand', 'CarteIdentite_fernand.moonstone.png', 'Moonstone', '$2a$10$va3CcxVqP2yhwbzwsaZOZOFvd7Jy2UugeB2iY/g80PB50mpAen8jC', '(205)076-4148', NULL, 'fernand.moonstone'),(0, 0, 0, 0, NULL, b'0', b'0', 17, b'0', 6, b'1', b'0', 'Rhyad', NULL, 'Junior', '$2a$10$vvb7D8XOWfx.KqTmC8JhNebAEyEw3VbLFpH/p.jvoE3nrMZnjQmI6', '123', NULL, 'rhyad.junior'),(0, 0, 0, 0, NULL, b'0', b'0', 18, b'0', 6, b'0', b'0', 'Obara', 'CarteIdentite_obara.jamso.png', 'Jamso', '$2a$10$IJA8JrzbbmebZ7MJoDetFe1f/3tXUQukBiZucSL6jQzI0S8eU2CZy', '(907)147-5504', NULL, 'obara.jamso'),(0, 0, 0, 0, NULL, b'0', b'0', 19, b'0', 6, b'0', b'0', 'Nyxar', 'CarteIdentite_nyxar.veylac.png', 'Veylac', '$2a$10$CvgjxQaq.2DTJ0y0RCU1xeyX7uYiMSdu97WQ3gwVERyRK7tnGOrn2', '(205) 945-2131', NULL, 'nyxar.veylac'),(0, 0, 0, 0, NULL, b'0', b'0', 20, b'0', 6, b'0', b'0', 'Otto', 'CarteIdentite_Otto.png', 'Otto', '$2a$10$fyz96GhmfTgxkHjK4NMHa.Krhj5ctbjmqbQL6GraK2KQPk1qhLEBa', '123', NULL, 'Otto'),(0, 32322, 0, 0, '2025-10-06', b'0', b'1', 21, b'0', 6, b'0', b'0', 'Kayne', NULL, 'Hensley', '$2a$10$7PIUlULF8zt0BGebNmBfeeWgvkjUL3obsctk764C6zKzfmAReZ/n.', '(907)270-4078', NULL, 'kayne.hensley'),(0, 0, 0, 0, NULL, b'0', b'0', 22, b'0', 6, b'1', b'0', 'Meech', 'CarteIdentite_meech.royce.png', 'Royce', '$2a$10$MoPbw9ObBZTJ0f3VSbetmuq4./9tJIq1ii2m4IaP8Yx4ipbuIdZeO', '(520)811-7412', NULL, 'meech.royce'),(0, 0, 0, 0, NULL, b'0', b'0', 23, b'0', 6, b'0', b'0', 'Lonzo', 'CarteIdentite_lonzo.mendoza.png', 'Mendoza', '$2a$10$3KsaWy2UyZxu7M7u6YbQBe3o.vhzJcCvVtBzTRXVKXQN7pURjLvua', '(520)116-4896', NULL, 'lonzo.mendoza'),(0, 40261, 0, 0, NULL, b'0', b'0', 24, b'0', 6, b'0', b'0', 'Juliano', 'CarteIdentite_juliano.perez.png', 'Perez', '$2a$10$oxCr1aUgzDDC7CSiRmmqiOPfNbwKZ8GdZP67uxX8RnOmZznp0m2.O', '(907) 754-7041', NULL, 'juliano.perez'),(0, 0, 0, 0, NULL, b'0', b'0', 25, b'0', 6, b'0', b'0', 'Billy', 'CarteIdentite_billy.meyer.png', 'Meyer', '$2a$10$WO4r4f.ZaDYAV5jGsBvp9u3PVW6G4xqy2C9m1mHouTbcv5Iuk0jN2', '(205) 707-7225', NULL, 'billy.meyer'),(53868, 0, 0, 0, NULL, b'1', b'0', 26, b'1', 6, b'0', b'0', 'Willy', 'CarteIdentite_willy.haye.png', 'Haye', '$2a$10$ZLZ05JE4dsHa6aLhJVEZ2uPwFFaUNL0kVooGlUnt8NDPbiI4Y.6WG', '(520)572-8177', NULL, 'willy.haye'),(4455, 0, 0, 0, NULL, b'0', b'0', 27, b'0', 6, b'0', b'0', 'Milo', 'CarteIdentite_milo.perez.png', 'Perez', '$2a$10$EhlzFe5I467BKomMH6.qfOE1CImsnvHG5tEto3vAD7K6jHJ/skbie', '(520)861-4974', NULL, 'milo.perez'),(61829, 0, 0, 0, NULL, b'1', b'0', 28, b'1', 6, b'0', b'0', 'Salvador-hernan', 'CarteIdentite_hernan.vasquez.png', 'Vasquez Cruz', '$2a$10$rYgnRXzsCUExP57sAixSfeqh5iQ/dgYeRHfmQ/wfv9lpL..JC5PUm', '(520)405-6076', NULL, 'hernan.vasquez'),(0, 0, 0, 0, NULL, b'0', b'0', 29, b'0', 7, b'0', b'0', 'Mia', 'CarteIdentite_mia.aguilar.png', 'Aguilar', '$2a$10$hd2D4ndHvmmBu1fL5mxjb.5/hKTiNHW9Pu4Rac7V2voLT8gJQLxbG', '123', NULL, 'mia.aguilar'),(34256, 0, 0, 0, NULL, b'1', b'0', 30, b'1', 7, b'0', b'0', 'Wally', 'CarteIdentite_wally.haye.png', 'Haye', '$2a$10$zZoMDo0/RHehrXoZXVaVmuvZO3bylyPJKBFDzG7EALUMMn55dqfsG', '123', NULL, 'wally.haye'),(34256, 0, 0, 0, NULL, b'1', b'0', 31, b'1', 8, b'0', b'0', 'Rh', 'CarteIdentite_wally.haye.png', 'Test', '$2a$10$AwZBzaXXJ/FRjmBAqq2Tf.UzJtHl0umXAjZMKxryfYgr1nzLFWnBm', '123', NULL, 'test');

-- Listage des données de la table tabarico.stock : ~16 rows (environ)
INSERT INTO `stock` (`consumable_id`, `id`, `product_id`, `quantity_mouvement`, `type_of_stock_movement`, `user_id`, `date`) VALUES(NULL, 2, 3, 100, 1, 1, '2025-09-16 18:42:52.541190'),(NULL, 3, 4, 50, 1, 1, '2025-09-16 18:43:04.909428'),(NULL, 4, 5, 200, 1, 1, '2025-09-16 18:43:15.457819'),(NULL, 5, 4, 10, 0, 1, '2025-09-16 20:55:31.162929'),(NULL, 6, 3, 3761, 1, 11, '2025-09-19 18:21:35.318783'),(NULL, 7, 5, 100, 1, 11, '2025-09-19 18:22:50.407801'),(NULL, 8, 5, -100, 1, 11, '2025-09-19 18:23:02.687391'),(NULL, 9, 5, 100, 1, 11, '2025-09-19 18:23:17.397528'),(NULL, 10, 4, 351, 1, 11, '2025-09-19 18:23:47.568736'),(NULL, 11, 3, 50, 0, 1, '2025-09-19 21:29:06.557430'),(2, 12, NULL, -2, 1, 1, '2025-09-19 21:29:26.725138'),(NULL, 13, 3, -50, 1, 13, '2025-09-27 23:29:24.982623'),(NULL, 14, 3, -10, 1, 13, '2025-09-27 23:29:32.001531'),(NULL, 15, 5, -120, 1, 13, '2025-09-27 23:30:32.407645'),(2, 19, NULL, 19, 1, 13, '2025-09-27 23:31:26.834530'),(NULL, 25, 5, 120, 0, 1, '2025-09-28 00:50:57.251582');


-- Listage des données de la table tabarico.customer_sale : ~3 rows (environ)
INSERT INTO `customer_sale` (`amount`, `contract_id`, `id`, `product_id`, `quantity`, `type_of_sale`, `user_id`, `date`, `stock_id`) VALUES(720.00, 3, 1, 4, 10, 0, 1, '2025-09-16 20:55:31.158113', NULL),(3500.00, NULL, 2, 3, 50, 0, 1, '2025-09-19 21:29:06.555519', NULL),(25200.00, NULL, 4, 5, 120, 0, 1, '2025-09-28 00:50:57.251355', 25);

-- Listage des données de la table tabarico.exporter_sale : ~21 rows (environ)
INSERT INTO `exporter_sale` (`company_amount`, `employee_amount`, `id`, `level`, `quantity`, `user_id`, `date`, `verified`) VALUES(1404.00, 4680.00, 2, 100, 100, 1, '2025-09-16 18:47:31.201430', true),(11.00, 36.00, 3, 1, 1, 1, '2025-09-16 20:54:52.128611', false),(3134.00, 10445.00, 4, 6, 285, 25, '2025-09-19 18:26:32.265316', false),(6192.00, 20639.00, 5, 11, 555, 25, '2025-09-19 18:27:30.931124', false),(7315.00, 24383.00, 6, 14, 650, 25, '2025-09-19 18:27:47.274808', false),(158515.00, 528383.00, 7, 71, 12100, 15, '2025-09-19 18:31:41.966349', false),(5805.00, 19350.00, 8, 25, 500, 21, '2025-09-19 18:37:32.762636', false),(5696.00, 18985.00, 9, 8, 515, 24, '2025-09-19 18:39:04.914607', false),(13288.00, 44293.00, 10, 19, 1164, 24, '2025-09-19 18:39:27.511405', false),(15224.00, 50748.00, 11, 24, 1315, 24, '2025-09-19 18:41:01.768164', false),(34441.00, 114804.00, 12, 21, 3000, 26, '2025-09-19 18:42:59.203843', false),(5578.00, 18594.00, 13, 11, 500, 26, '2025-09-19 18:43:28.416496', false),(17221.00, 57402.00, 14, 21, 1500, 27, '2025-09-19 18:44:11.396135', false),(18987.00, 63291.00, 15, 24, 1640, 27, '2025-09-19 18:45:28.451204', false),(102113.00, 340376.00, 17, 100, 7273, 1, '2025-09-26 22:01:58.126167', false),(14851.00, 49502.00, 18, 29, 1265, 27, '2025-09-26 22:02:15.191356', false),(59670.00, 198900.00, 20, 35, 5000, 26, '2025-09-26 22:29:55.722091', false),(79574.00, 265248.00, 23, 76, 6000, 28, '2025-09-26 22:39:58.844920', false),(139883.00, 466277.00, 24, 83, 10370, 15, '2025-09-27 18:00:57.332730', false),(10319.00, 34397.00, 25, 83, 765, 15, '2025-09-27 18:01:07.471029', false),(129740.00, 432468.00, 26, 78, 9735, 15, '2025-09-27 18:05:15.309410', false),(14189.00, 47295.00, 27, 17, 1250, 30, '2025-09-27 18:08:39.176503', false);


/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
