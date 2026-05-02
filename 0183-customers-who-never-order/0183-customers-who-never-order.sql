SELECT name AS Customers
FROM Customers c
WHERE NOT EXISTS (
    SELECT id
    FROM Orders
    WHERE c.id = customerId
)