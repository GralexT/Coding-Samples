-- Q1. What SQL query will list the total number of customer orders per outlet (listed by outlet address) per day of the week)
SELECT o.address, DAYNAME(c.date) Day_Of_Week, COUNT(c.orderID) Total_Orders 
FROM outlet o, customerorder c 
WHERE c.outletID=o.jStoreId
GROUP BY o.address, DAYOFWEEK(c.date);

-- Q2. What SQL query will list the cup ids of all JuiceCup that use more than three different ingredients?
SELECT DISTINCT c1.cupid
FROM comprises c1 
WHERE c1.cupid IN (SELECT c2.cupid 
					FROM comprises c2 
					WHERE c1.cupid=c2.cupid 
					HAVING COUNT(juiceid)>3);

-- Q3. What single SQL query will list the names and addresses of all Juicd employees who are not working at any of the outlets at the moment?
SELECT e.name, e.address 
FROM employee e 
LEFT JOIN worksat w 
ON e.jEmpId=w.jEmpId 
WHERE w.jEmpId IS NULL 
ORDER BY e.name;

-- Q4. What SQL query will list all the customer orders (by orderid) that consist only of juices (no non-juice items)?
SELECT c.orderid 
FROM customerorder c 
WHERE c.orderid NOT IN (SELECT n.orderid 
						FROM hasnonjuice n) 
ORDER BY c.orderID;

-- Q5. Write a function juicecupcost(id int) RETURNS DOUBLE that will be called with the id of a juicecup and then return the cost (in cents) of that particular juicecup.
-- For example, juice cup 10 is a 400ml juice that is a 50-50 mix of raspberry-pear and costs $3.40.
DROP FUNCTION IF EXISTS juicecupcost;
DELIMITER //
CREATE FUNCTION JuiceCupCost (id INT)
RETURNS DOUBLE
DETERMINISTIC
BEGIN
	DECLARE sum1 INT;
	SELECT SUM(c.percentage/100*juicecup.size*juice.perMl) INTO sum1
		FROM juice, comprises c, juicecup
		WHERE juice.jid=c.juiceid
			AND c.cupid=juicecup.cupId
			AND c.cupid=id;
	RETURN sum1;
END //
DELIMITER ;

SELECT juicecupcost(10);

-- Q6. Write a function juiceordercost (id INT) RETURNS DOUBLE that returns the total price of the juice-component of an order.
DROP FUNCTION IF EXISTS JuiceOrderCost;
DELIMITER //
CREATE FUNCTION JuiceOrderCost (oid INT)
RETURNS DOUBLE
DETERMINISTIC
BEGIN
	DECLARE totalsum2 INT;
	SELECT SUM(co.percentage/100*juicecup.size*juice.perMl*h.quantity) INTO totalsum2
		FROM juice, comprises co, juicecup, hasjuice h, customerorder cu
		WHERE juice.jid=co.juiceid
			AND co.cupid=h.cupid
			AND h.orderid=cu.orderid
			AND co.cupid=juicecup.cupId
			AND cu.orderid=oid;
	RETURN totalsum2;
END //
DELIMITER ;

SELECT juiceordercost(1);