drop schema space_base;
create schema if not exists space_base;
use space_base;

create table if not exists crew_member_role (
crew_member_role_id int primary key not null,
crew_member_role varchar(20) not null);

create table if not exists crew_member_rank (
crew_member_rank_id int primary key not null,
crew_member_rank varchar(20) not null);

create table if not exists mission_status (
mission_status_id int primary key not null,
mission_status varchar(20) not null);

create table if not exists crew_members (
member_id int primary key not null auto_increment,
member_name varchar(20) not null,
role_id int ,
foreign key (role_id) references crew_member_role (crew_member_role_id),
rank_id int,
foreign key (rank_id) references crew_member_rank (crew_member_rank_id),
is_ready_for_next_mission boolean default true);

create table if not exists spaceship (
spaceship_id int primary key not null auto_increment,
spaceship_name varchar(20) not null,
flight_distance int not null ,
is_ready_for_next_mission boolean default true);

create table if not exists flight_mission (
flight_mission_id int primary key  not null  auto_increment,
flight_mission_name varchar(20) not null,
mission_start_date datetime not null default now(),
mission_start_end datetime not null,
flight_mission_distance int not null,
mission_status int not null,
foreign key (mission_status) references mission_status (mission_status_id));

create table if not exists spaceship_crew (
spaceship_id int primary key,
number_of_crew_members int not null,
foreign key (spaceship_id) references spaceship(spaceship_id));

create table if not exists flight_missions_spaceships(
flight_mission_id int,
spaceship_id int,
primary key (flight_mission_id, spaceship_id),
foreign key (flight_mission_id) references flight_mission (flight_mission_id),
foreign key (spaceship_id) references spaceship (spaceship_id));

create table if not exists crew_members_assigned_to_a_mission (
    flight_mission_id int,
    crew_member_id int,
    primary key (flight_mission_id, crew_member_id),
    foreign key (flight_mission_id) references flight_mission (flight_mission_id),
    foreign key (crew_member_id) references crew_members (member_id)
);

