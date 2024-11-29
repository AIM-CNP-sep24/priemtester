-- Testdata voor de afgelopen week
-- Vandaag (5 records)
INSERT INTO PriemTest (kandidaat, is_priem, gebruikersnaam, datum_tijd) VALUES (2, true, 'user1', NOW() + INTERVAL '1' HOUR);
INSERT INTO PriemTest (kandidaat, is_priem, gebruikersnaam, datum_tijd) VALUES (3, true, 'user2', NOW() + INTERVAL '2' HOUR);
INSERT INTO PriemTest (kandidaat, is_priem, gebruikersnaam, datum_tijd) VALUES (4, false, 'user3', NOW() + INTERVAL '3' HOUR);
INSERT INTO PriemTest (kandidaat, is_priem, gebruikersnaam, datum_tijd) VALUES (5, true, 'user4', NOW() + INTERVAL '4' HOUR);
INSERT INTO PriemTest (kandidaat, is_priem, gebruikersnaam, datum_tijd) VALUES (6, false, 'user5', NOW() + INTERVAL '5' HOUR);

-- Gisteren (5 records)
INSERT INTO PriemTest (kandidaat, is_priem, gebruikersnaam, datum_tijd) VALUES (7, true, 'user1', NOW() - INTERVAL '1' DAY + INTERVAL '1' HOUR);
INSERT INTO PriemTest (kandidaat, is_priem, gebruikersnaam, datum_tijd) VALUES (8, false, 'user2', NOW() - INTERVAL '1' DAY + INTERVAL '2' HOUR);
INSERT INTO PriemTest (kandidaat, is_priem, gebruikersnaam, datum_tijd) VALUES (11, true, 'user3', NOW() - INTERVAL '1' DAY + INTERVAL '3' HOUR);
INSERT INTO PriemTest (kandidaat, is_priem, gebruikersnaam, datum_tijd) VALUES (10, false, 'user4', NOW() - INTERVAL '1' DAY + INTERVAL '4' HOUR);
INSERT INTO PriemTest (kandidaat, is_priem, gebruikersnaam, datum_tijd) VALUES (13, true, 'user5', NOW() - INTERVAL '1' DAY + INTERVAL '5' HOUR);

-- Eergisteren (0 records)

-- Drie dagen geleden (10 records)
INSERT INTO PriemTest (kandidaat, is_priem, gebruikersnaam, datum_tijd) VALUES (17, true, 'user1', NOW() - INTERVAL '3' DAY + INTERVAL '1' HOUR);
INSERT INTO PriemTest (kandidaat, is_priem, gebruikersnaam, datum_tijd) VALUES (14, false, 'user2', NOW() - INTERVAL '3' DAY + INTERVAL '2' HOUR);
INSERT INTO PriemTest (kandidaat, is_priem, gebruikersnaam, datum_tijd) VALUES (19, true, 'user3', NOW() - INTERVAL '3' DAY + INTERVAL '3' HOUR);
INSERT INTO PriemTest (kandidaat, is_priem, gebruikersnaam, datum_tijd) VALUES (15, false, 'user4', NOW() - INTERVAL '3' DAY + INTERVAL '4' HOUR);
INSERT INTO PriemTest (kandidaat, is_priem, gebruikersnaam, datum_tijd) VALUES (23, true, 'user5', NOW() - INTERVAL '3' DAY + INTERVAL '5' HOUR);
INSERT INTO PriemTest (kandidaat, is_priem, gebruikersnaam, datum_tijd) VALUES (18, false, 'user1', NOW() - INTERVAL '3' DAY + INTERVAL '6' HOUR);
INSERT INTO PriemTest (kandidaat, is_priem, gebruikersnaam, datum_tijd) VALUES (29, true, 'user2', NOW() - INTERVAL '3' DAY + INTERVAL '7' HOUR);
INSERT INTO PriemTest (kandidaat, is_priem, gebruikersnaam, datum_tijd) VALUES (20, false, 'user3', NOW() - INTERVAL '3' DAY + INTERVAL '8' HOUR);
INSERT INTO PriemTest (kandidaat, is_priem, gebruikersnaam, datum_tijd) VALUES (31, true, 'user4', NOW() - INTERVAL '3' DAY + INTERVAL '9' HOUR);
INSERT INTO PriemTest (kandidaat, is_priem, gebruikersnaam, datum_tijd) VALUES (21, false, 'user5', NOW() - INTERVAL '3' DAY + INTERVAL '10' HOUR);