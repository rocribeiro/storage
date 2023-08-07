USE product_storage;

CREATE TABLE suppliers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    creation_date DATETIME NOT NULL,
    last_update_date DATETIME NOT NULL
);

INSERT INTO suppliers (name, creation_date, last_update_date)
VALUES
    ('Fornecedor 1', NOW(), NOW()),
    ('Fornecedor 2', NOW(), NOW()),
    ('Fornecedor 3', NOW(), NOW());

CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    quantity_in_stock INT NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    supplier_id INT,
    creation_date DATETIME NOT NULL,
    last_update_date DATETIME NOT NULL,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
);