--Q1: What are the all members’ names?
select name_m from Members;

--Q2: Find out all the members whose member’s status is drop-in.
select name_m from Members where member_status = 'drop-in';

--Q3: Find out all the non-members’ names appear in the table sorted by alphabets.

select name_p from Public
except select name_m from Members
order by name_p;

--Q4: Find out and illustrate all the names public who has purchased pass. 
--(names sorted by alphabets )

select all name_p from Public
where receiptnumber is not NULL
order by name_p;

--Q5: Find out all the public who have already purchased the 
--passed and showed their pass and names, name sorted by alphabets.

select all name_p, duration from Public
natural join Pass
where receiptnumber is not NULL
order by name_p;

-- Q6: How much does the consumer spend the most in merchandise?

select MAX(price) as maxPrice from Merchandise natural join Buy;

--Q7. Suppose the maintainance fee for gym is $30/ per person 
-- how much profits could gym earn from each members from the passes?

select name_p,receiptnumber, duration, passprice, passprice - 30 
as passProfit from Pass
natural join Public;

--Q8: Calculate the summation cost by 100 800 9001  and 300 800 3409

select distinct ((select passprice from Pass where receiptnumber = '300 800 3409') + (select passprice from Pass 
        where receiptnumber = '100 800 9001'))as totalMoney from Pass;

-- Q9: What is the total values of one same item products stored in the stock?
select item ,sum(price) as totalMoney from Merchandise group by item;

-- Q10: What is the most costly additional service in our diagram?
select service_name, price
from AdditionalServices
where price >= all (select price from AdditionalServices);

