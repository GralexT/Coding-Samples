-- Q1. What SQL query will produce the row of customer information for the customer with Juicd card number 1000;
SELECT *
FROM customer
WHERE jcardnum=1000;

-- Q2. What SQL query will determine the total number of orders in the database?
SELECT COUNT(orderid)
FROM customerorder;

-- Q3. What SQL query will list the names and addresses of the outlet managers?
SELECT e.name, e.address
FROM employee e, manages m
WHERE e.jEmpId=m.jEmpId
ORDER BY e.name;

-- Q4. What SQL query will list the names and addresses of the employees who work full-time at a single Juicd store?
SELECT e.name, e.address
FROM employee e
NATURAL JOIN worksat
WHERE percentage=100
ORDER BY e.name;

-- Q5. What SQL query will list the names, addresses and total working percentage of all Juicd employees?
SELECT e.name, e.address, SUM(w.percentage) AS Total_Working_Percentage
FROM employee e 
NATURAL JOIN worksat w
GROUP BY e.name, e.address;

-- Q6. What query will produce a table listing the minimum, maximum and average number of Juicd points outstanding?
SELECT MIN(jPoints), MAX(jPoints), AVG(Jpoints) 
FROM customer;

-- Q7. What SQL query will list each line manager (by name) together with the number of employees they supervise?
SELECT e.name, COUNT(l.supervisee)
FROM employee e, linemgr l
WHERE e.jempid=l.supervisor
GROUP BY e.name;

-- Q8. What SQL query will list the address of each outlet, together with the total number of orders that they have served?
SELECT o.address, COUNT(c.orderid)
FROM outlet o, customerorder c
WHERE o.jstoreid=c.outletid
GROUP BY o.address;
    
-- Q9. What SQL query will list the actual juices by name and their percentages for this particular juice cup (where cupid=1000)?
SELECT j.jname, c.percentage
FROM juice j, comprises c
WHERE j.jId=c.juiceId
	AND c.cupId=1000;
    
-- Q10. What SQL command will determine the price in cents of the Juice Cup with cupid=1000?
SELECT SUM(c.percentage/100*juicecup.size*juice.perMl) AS TotalJuiceCost_Cents
FROM juice, comprises c, juicecup
WHERE juice.jid=c.juiceid
	AND c.cupid=juicecup.cupId
	AND c.cupId=1000;