DROP TABLE IF EXISTS employees;
CREATE TABLE employees(
                          id uuid default gen_random_uuid() primary key,
                          first_name varchar(255) not null,
                          last_name varchar(255) not null,
                          email varchar(255) not null,
                          gender varchar(255) not null,
                          phone varchar(255),
                          department varchar(255) not null,
                          job_title varchar(255) not null,
                          years_of_experience int not null,
                          salary float not null
);
