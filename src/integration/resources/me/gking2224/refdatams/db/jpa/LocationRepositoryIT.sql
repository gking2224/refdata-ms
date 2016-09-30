
insert into country (country_id, code, name) values (1, 'UK', 'United Kingdom');
insert into country (country_id, code, name) values (2, 'IN', 'India');

insert into city (city_id, country_id, name) values (1, 1, 'London');
insert into city (city_id, country_id, name) values (2, 1, 'Edinburgh');
insert into city (city_id, country_id, name) values (3, 1, 'Bristol');
insert into city (city_id, country_id, name) values (4, 1, 'Manchester');
insert into city (city_id, country_id, name) values (5, 2, 'Delhi');
insert into city (city_id, country_id, name) values (6, 2, 'Chennai');
insert into city (city_id, country_id, name) values (7, 2, 'Mumbai');

insert into building (building_id, city_id, name) values (1, 1, '25 Old Broad Street');
insert into building (building_id, city_id, name) values (2, 1, '280 Liverpool Street');
insert into building (building_id, city_id, name) values (3, 1, '135');
insert into building (building_id, city_id, name) values (4, 1, 'Westminster Mews');
insert into building (building_id, city_id, name) values (5, 2, '');
insert into building (building_id, city_id, name) values (6, 3, 'Carlisle House');
insert into building (building_id, city_id, name) values (7, 5, 'Unitech 1');
insert into building (building_id, city_id, name) values (8, 5, 'Unitech 2');
insert into building (building_id, city_id, name) values (9, 7, 'Tower 1');

insert into location (location_id, name, country_id) values (1, 'UK', 1);
insert into location (location_id, name, country_id) values (2, 'India', 2);
insert into location (location_id, name, city_id) values (3, 'London', 1);
insert into location (location_id, name, city_id) values (4, 'Edinburgh', 2);
insert into location (location_id, name, building_id) values (5, '280', 2);
