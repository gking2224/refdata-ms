
insert into Person (person_id, firstName, surname) values (1, 'Alex',    'Powell');
insert into Person (person_id, firstName, surname) values (2, 'Monique', 'Richards');
insert into Person (person_id, firstName, surname) values (3, 'Verna',   'Nichols');
insert into Person (person_id, firstName, surname) values (4, 'Olivia',  'Russell');
insert into Person (person_id, firstName, surname) values (5, 'Jimmy',   'Figueroa');
insert into Person (person_id, firstName, surname) values (6, 'Anna',    'Gregory');
insert into Person (person_id, firstName, surname) values (7, 'Ivan',    'Ball');
insert into Person (person_id, firstName, surname) values (8, 'Angelo',  'Cannon');
insert into Person (person_id, firstName, surname) values (9, 'Glenda',  'Vega');
insert into Person (person_id, firstName, surname) values (10, 'Jackie',  'Schneider');
insert into Person (person_id, firstName, surname) values (11, 'Sabrina', 'Stanley');
insert into Person (person_id, firstName, surname) values (12, 'Raymond', 'Barnes');
insert into Person (person_id, firstName, surname) values (13, 'Ernest',  'King');

insert into Resource (resource_id, person_id, billRate, location_id, contract_type_id) values (1,  1,  400.00, 1, 3);
insert into Resource (resource_id, person_id, billRate, location_id, contract_type_id) values (2,  2,  400.00, 1, 3);
insert into Resource (resource_id, person_id, billRate, location_id, contract_type_id) values (3,  3,  400.00, 2, 3);
insert into Resource (resource_id, person_id, billRate, location_id, contract_type_id) values (4,  4,  400.00, 2, 3);
insert into Resource (resource_id, person_id, billRate, location_id, contract_type_id) values (5,  5,  400.00, 3, 3);
insert into Resource (resource_id, person_id, billRate, location_id, contract_type_id) values (6,  6,  400.00, 3, 3);
insert into Resource (resource_id, person_id, billRate, location_id, contract_type_id) values (7,  7,  400.00, 1, 3);
insert into Resource (resource_id, person_id, billRate, location_id, contract_type_id) values (8,  8,  400.00, 3, 2);
insert into Resource (resource_id, person_id, billRate, location_id, contract_type_id) values (9,  9,  400.00, 1, 2);
insert into Resource (resource_id, person_id, billRate, location_id, contract_type_id) values (10, 10, 400.00, 2, 2);
insert into Resource (resource_id, person_id, billRate, location_id, contract_type_id) values (11, 11, 400.00, 1, 2);
insert into Resource (resource_id, person_id, billRate, location_id, contract_type_id) values (12, 12, 400.00, 1, 2);
insert into Resource (resource_id, person_id, billRate, location_id, contract_type_id) values (13, 13, 400.00, 4, 2);


insert into LocationRate (location_id, contract_type_id, rate) values (1, 3, 100.00);
insert into LocationRate (location_id, contract_type_id, rate) values (2, 3, 150.00);
insert into LocationRate (location_id, contract_type_id, rate) values (3, 3, 200.00);
insert into LocationRate (location_id, contract_type_id, rate) values (4, 3, 250.00);
insert into LocationRate (location_id, contract_type_id, rate) values (1, 2, 300.00);
insert into LocationRate (location_id, contract_type_id, rate) values (2, 2, 350.00);
insert into LocationRate (location_id, contract_type_id, rate) values (3, 2, 400.00);
insert into LocationRate (location_id, contract_type_id, rate) values (4, 2, 450.00);
insert into LocationRate (location_id, contract_type_id, rate) values (1, 1, 500.00);
insert into LocationRate (location_id, contract_type_id, rate) values (2, 1, 550.00);
insert into LocationRate (location_id, contract_type_id, rate) values (3, 1, 600.00);
insert into LocationRate (location_id, contract_type_id, rate) values (4, 1, 650.00);

