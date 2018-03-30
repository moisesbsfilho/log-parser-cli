--Find elements where theshold is >= 200 hourly
select count(l.ip) as count, l.ip from wallet_hub.logs as l
where request_date between '2017-01-01.15:00:00' and '2017-01-01.15:59:59'
group by l.ip
having count(l.ip) > 200;

--Find elements where threshold id >= 500 dialy
select count(l.ip) as count, l.ip from wallet_hub.logs as l
where request_date between '2017-01-01.00:00:00' and '2017-01-01.23:59:59'
group by l.ip
having count(l.ip) >= 500;
