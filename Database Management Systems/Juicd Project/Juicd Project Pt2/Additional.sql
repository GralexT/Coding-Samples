-- Q1. Write a stored function totalOrderCost (id INT) RETURNS DOUBLE that returns the total cost (in cents) of the order with order number id.
DROP FUNCTION IF EXISTS totalOrderCost;
DELIMITER //
CREATE FUNCTION totalOrderCost (id3 INT)
RETURNS DOUBLE
DETERMINISTIC
BEGIN
	DECLARE totalsum3 INT;
    DECLARE totalsum4 INT;
    	SELECT SUM(co.percentage/100*juicecup.size*juice.perMl*h.quantity) INTO totalsum3
		FROM juice, comprises co, juicecup, hasjuice h, customerorder cu
		WHERE juice.jid=co.juiceid
			AND co.cupid=h.cupid
			AND h.orderid=cu.orderid
			AND co.cupid=juicecup.cupId
			AND cu.orderid=id3;
		SELECT SUM(nonjuice.perItem*hasnonjuice.quantity*100) INTO totalsum4
		FROM customerorder cu, hasnonjuice, nonjuice
        WHERE nonjuice.prodId=hasnonjuice.prodId
			AND hasnonjuice.orderId=cu.orderID
            AND cu.orderID=id3;
    IF totalsum4 IS NULL THEN RETURN totalsum3;
    ELSEIF totalsum3 IS NULL THEN RETURN totalsum4;
    ELSE RETURN totalsum3+totalsum4;
    END IF;
END //
DELIMITER ;

Select totalordercost(1);

-- Q2. Create a view CustomerPriceOrder with columns date DATE, customerId INT, orderId INT and orderCost DOUBLE
-- that provides a more accessible way for the DB user to run queries regarding customer orders.
CREATE OR REPLACE VIEW juicepricedorder AS SELECT cu.orderid, ifnull(SUM(co.percentage/100*juicecup.size*juice.perMl*h.quantity), 0) AS juiceprice
FROM juice, comprises co, juicecup, hasjuice h, customerorder cu
WHERE juice.jid=co.juiceid
AND co.cupid=h.cupid
AND h.orderid=cu.orderid
AND co.cupid=juicecup.cupId
GROUP BY cu.orderid;	
            
CREATE OR REPLACE VIEW nonjuicepricedorder AS SELECT cu.orderid, ifnull(SUM(nonjuice.perItem*hasnonjuice.quantity*100), 0) AS notjuiceprice
FROM customerorder cu, hasnonjuice, nonjuice
WHERE nonjuice.prodId=hasnonjuice.prodId
AND hasnonjuice.orderId=cu.orderID
GROUP BY cu.orderid;

CREATE OR REPLACE VIEW totalpricedorder AS SELECT juicepricedorder.orderid, ifnull((juiceprice), 0)+ifnull((notjuiceprice), 0) AS totalprice
FROM juicepricedorder LEFT OUTER JOIN nonjuicepricedorder ON juicepricedorder.orderid = nonjuicepricedorder.orderid
GROUP BY juicepricedorder.orderid;

CREATE OR REPLACE VIEW CustomerPricedOrder AS SELECT cu.orderId, cu.date, cu.customerID, totalprice AS orderCost
FROM customerorder cu, totalpricedorder
WHERE cu.orderid=totalpricedorder.orderid;

SELECT * FROM customerpricedorder;