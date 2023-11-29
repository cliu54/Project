drop table Pass cascade;
drop table Public cascade;
drop table Members cascade;
drop table Services cascade;
drop table MembershipType cascade;
drop table AdditionalServices cascade;
drop table Instructors cascade;
drop table Have cascade;
drop table Merchandise cascade;
drop table Buy cascade;

create table Pass
(
    receiptNumber char (30) primary key,
    duration char (30),
    -- sessionNumber char(30),
    PassPrice real
    check (PassPrice >= 0 )
);
-- drop-ins, to a set of 10 drop-ins, to a quarterly pass, to an annual pass
insert into Pass values ('100 800 9001', '10 drop-ins', 50.00);
insert into Pass values ( '100 800 9002', '10 drop-ins',50.00);
insert into Pass values ('200 800 7461', 'quarterly pass', 160.00);
insert into Pass values ('200 800 7468', 'quarterly pass', 160.00);
insert into Pass values ('300 800 3409', 'annual pass', 450.00);
insert into Pass values ('300 800 3410', 'annual pass', 450.00);


create table Public
(
    
    name_p char(30),
    social_id char(30),
    phoneNumber char(30),
    receiptNumber char(30),
    foreign key(receiptNumber) references Pass(receiptNumber), --DEFAULT 'NOT PURCHASED'
    primary key(name_p, social_id)
    --  receiptNumber 
);

insert into Public values('Jack', '948 363 777', '204 884 6565', '300 800 3409');
insert into Public values('Emma', '743 921 323', '604 721 1320', '300 800 3410');
insert into Public values('Rick', '923 231 130', '778 875 1023', '200 800 7461');
insert into Public values('Dave', '992 321 465', '778 231 1467', '200 800 7468');
insert into Public values('Francis', '962 151 775', '604 819 1111', '100 800 9001');
insert into Public values('Louis', '878 321 456', '204 999 5512', '100 800 9002');
insert into Public(name_p,social_id,phoneNumber) values ('Brandon', '432 950 001', '542 229 1313');
insert into Public(name_p,social_id,phoneNumber) values ('Bella', '623 999 000', '832 123 1111');
insert into Public(name_p,social_id,phoneNumber) values ('Marlin', '811 123 094', '754 232 1132');
insert into Public(name_p,social_id,phoneNumber) values ('Joshua', '213 232 111', '592 232 1111');
insert into Public(name_p,social_id,phoneNumber) values ('Tom', '332 233 999', '924 976 6324');
insert into Public(name_p,social_id,phoneNumber) values ('Hulk', '123 322 444', '402 323 3333');
insert into Public(name_p,social_id,phoneNumber) values ('Julia', '888 929 000', '604 721 3433');


create table Members
(
    name_m char(30),
    social_id char (30),
    foreign key(name_m, social_id) references Public(name_p,social_id),
    membership_id char(30),
    mailing_address char(30),
    contact_detail char(30),
    member_status char(30),
    primary key(membership_id)
);

insert into Members values('Jack','948 363 777','A#990321', 'jack@gmail.com', '8392 King Rd', 'full');
insert into Members values('Emma','743 921 323','A#780225', 'emma@gmail.com', '4312 Main Rd', 'full');
insert into Members values('Rick','923 231 130','A#321323', 'rick@gmail.com', '9320 Fraser Street', 'guest');
insert into Members values('Dave','992 321 465','A#210921', 'dave@gmail.com', '3292 Knight Street', 'guest');
insert into Members values('Francis','962 151 775','A#782121', 'francis01@hotmail.com', '1023 Queen Rd', 'drop-in');
insert into Members values('Louis','878 321 456','A#140821', 'jack@gmail.com', '3210 Marine Drive', 'drop-in');


create table Services
(
    services_id char(30),
    primary key (services_id)
);

insert into Services values ('S01');
insert into Services values ('S02');
insert into Services values ('S03');
insert into Services values ('S04');
insert into Services values ('S05');
insert into Services values ('S06');
insert into Services values ('S07');
insert into Services values ('S08');
insert into Services values ('S09');



create table MembershipType
(   
    name_guest char(30),
    guest_socialId char(30),
    membership_id char(30),
    services_id char (30),
    foreign key (name_guest,guest_socialId) references Public (name_p,social_id),
    foreign key (membership_id) references Members (membership_id),
    foreign key (services_id) references Services(services_id),
    fullName char(30),
    transaction_number char(30) primary key
    -- MembershipPrice real
);
-- fit or climb -- Fit plus -- Fit and Climb
insert into MembershipType values('Jack','948 363 777', 'A#990321','S01','Fit','#889394');
insert into MembershipType values('Emma', '743 921 323','A#780225','S02','Climb','#757233');
insert into MembershipType values('Rick', '923 231 130','A#321323','S03','Fit','#384123');
insert into MembershipType values('Dave', '992 321 465','A#210921','S04','Fit','#594213');
insert into MembershipType values('Francis', '962 151 775','A#782121','S05','Climb','#123434');
insert into MembershipType values('Louis', '878 321 456','A#140821','S06','Climb','#964232');


-- drop table Owns cascade;
-- create table Owns
-- (   
--     fullName char (30),
--     membership_id char (30),
--     foreign key (fullName) references MembershipType (fullName),
--     foreign key (membership_id) references Members (membership_id)
-- );




create table AdditionalServices
(   
    service_name char(30) primary key,
    services_id char(30),
    -- foreign key(sessionNumber) references Services(sessionNumber),
    enrolledMembers char (100),
    schedule char(30),
    price real
    check (price >= 0 ),
    foreign key(services_id) references Services(services_id)
);

insert into AdditionalServices values('yoga#A01', 'S02','Jean, Kim, Angela, Sunshine, Nitasha, Jenny, Jennifer, Emma, Julia','Mons: 13:00 - 15:30',80.00);
insert into AdditionalServices values('Bike#C01', 'S03', 'Kevin, Bob, Nick, Jiwang, Francis, Don, David, Dave, Rick, Tom', 'Weds: 13:00 - 15:30',65.00);
insert into AdditionalServices values('Bike#C02', 'S04', 'Kate, Melo, Kris, Rose, Patrick, Rick, Tom','Weds: 13:00 - 15:30',65.00);
insert into AdditionalServices values('Workout#D01','S05' ,'James, Issac, Mike Tom','Tues, Thurs: 12:50 - 13:50',120.00);


create table Instructors
(   
    name_I char(30),
    social_id char (30),
    -- foreign key(name_I) references Public(name_p),
    foreign key (name_I,social_id) references Public (name_p,social_id),
    teaching_session char (30)
);

insert into Instructors values('Tom','332 233 999','Bike#C01');
insert into Instructors values('Hulk','123 322 444','Bike#C02');
insert into Instructors values('Tom','332 233 999','Workout#D01');
insert into Instructors values('Julia','888 929 000','yoga#A01');

create table Have
(   
    name_I_have char(30) ,
    Instructors_id_have char (30),
    service_name_have char (30),
    services_id_have char (30),
    -- expiredDate_H time,
    foreign key (name_I_have,Instructors_id_have) references Public(name_p,social_id),
    foreign key (service_name_have) references AdditionalServices (service_name),
    foreign key (services_id_have) references Services(services_id)
);
insert into Have values('Tom','332 233 999','Bike#C01', 'S07');
insert into Have values('Tom','332 233 999','Workout#D01','S07');
insert into Have values('Hulk','123 322 444','Bike#C02','S08');
insert into Have values('Julia','888 929 000','yoga#A01','S09');


create table Merchandise
(
    itemNumber char (30) primary key,
    item char(30),
    price real DEFAULT 0.0
    check (price >= 0 ) 
);
insert into Merchandise values('#item0001', 'shirt', '20.00');
insert into Merchandise values('#item0002', 'shirt', '20.00');
insert into Merchandise values('#item0003', 'shirt', '20.00');
insert into Merchandise values('#item1001', 'socks', '5.00');
insert into Merchandise values('#item1002', 'socks', '5.00');
insert into Merchandise values('#item1003', 'socks', '55.00');
insert into Merchandise values('#item2001', 'shoes', '55.00');
insert into Merchandise values('#item2002', 'shoes', '55.00');
insert into Merchandise values('#item2003', 'shoes', '55.00');



create table Buy
(   
    name_guest char(30),
    social_id char (30),
    itemNumber char (30),
    orderNumber char (30),
    
    foreign key (itemNumber) references Merchandise (itemNumber),
    foreign key (name_guest, social_id) references Public (name_p,social_id)
);
--'Jack','948 363 777'
--'Tom','332 233 999'
--'Francis', '962 151 775'
insert into Buy values('Jack','948 363 777', '#item1001','R#322112' );
insert into Buy values('Tom','332 233 999','#item2001','R#234124' );
insert into Buy values('Francis','962 151 775','#item0001','R#123123' );

