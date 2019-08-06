#insert into user values ("dduartec","Diego Duarte", "dduartec@unal.edu.co","3162401069","cll26#32-14","random");
#update user set username="dduartec1", name="Diego Duarte", email="diegoabdc@gmail.com", phone="3583639", address="cll27#32-14", authToken="randomupdate" where username="dduartec";
#delete from user where username="dduartec1";
#select * from user;
#select * from user where username="dduartec";
insert into creditcard (number,name,expirationDate,securityCode,User_username) values ("1234567891234567","diego duarte","05/20",123,"dduartec");
#update creditcard set number="123456789", name="Diego Duarte", expirationDate="2019-08-04", securityCode=456 where id=3;
#delete from creditcard where id=1;
#select * from creditcard;
select * from creditcard where User_username="dduartec";
#select * from creditcard where User_username="dduartec" and number="1234567891234567";


