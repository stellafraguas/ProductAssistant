INSERT INTO product (
  id, name, description, price, active, created_by, last_updated_by, created_at, last_updated_at, category_id
) VALUES
  (1, 'Maçã Fuji', 'Maçã Fuji /kg', 12.50, TRUE, 'system', NULL, NOW(), NULL, 4),
  (2, 'Alface Crespa', 'Alface Crespa /kg', 7.00, TRUE, 'system', NULL, NOW(), NULL, 3),
  (3, 'Arroz Branco', 'Arroz Branco Tipo 1 5kg', 20.10, TRUE, 'system', NULL, NOW(), NULL, 6),
  (4, 'Banana Nanica', 'Banana Nanica /kg', 4.60, TRUE, 'system', NULL, NOW(), NULL, 4),
  (5, 'Lentilha Verde', 'Lentilha Verde 1kg', 44.99, TRUE, 'system', NULL, NOW(), NULL, 6),
  (6, 'Caixa Plastica 50L', 'Caixa Plastica Agricola 50L', 29.50, TRUE, 'system', NULL, NOW(), NULL, 8);