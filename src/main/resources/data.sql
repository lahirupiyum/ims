INSERT INTO network_device_status(name) SELECT 'in_use'
WHERE NOT EXISTS (SELECT 1 FROM network_device_status WHERE name = 'in_use');

INSERT INTO network_device_status(name) SELECT 'sold'
WHERE NOT EXISTS (SELECT 1 FROM network_device_status WHERE name = 'sold');

INSERT INTO network_device_status(name) SELECT 'rented'
WHERE NOT EXISTS (SELECT 1 FROM network_device_status WHERE name = 'rented');

INSERT INTO network_device_status(name) SELECT 'loaned'
WHERE NOT EXISTS (SELECT 1 FROM network_device_status WHERE name = 'loaned');