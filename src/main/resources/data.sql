-- Drop existing tables 
DROP TABLE IF EXISTS booking;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS my_class;

-- Create the `member` table
CREATE TABLE member (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

-- Create the `my_class` table
CREATE TABLE my_class (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    start_time TIME NOT NULL,
    duration_in_minutes INT NOT NULL,
    capacity INT NOT NULL
);

-- Create the `booking` table
CREATE TABLE booking (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id BIGINT NOT NULL,
    my_class_id BIGINT NOT NULL,
    participation_date DATE NOT NULL,
    booking_date DATE NOT NULL,
    FOREIGN KEY (member_id) REFERENCES member(id),
    FOREIGN KEY (my_class_id) REFERENCES my_class(id)
);


INSERT INTO member (name, email) VALUES
('John Doe', 'john.doe@example.com'),
('Jane Smith', 'jane.smith@example.com'),
('Mike Jones', 'mike.jones@example.com');


INSERT INTO my_class (name, start_date, end_date, start_time, duration_in_minutes, capacity) VALUES
('Yoga Class', '2025-01-01', '2025-01-31', '08:00:00', 60, 20),
('HIIT Workout', '2025-02-15', '2025-03-15', '10:00:00', 45, 15),
('Meditation Session', '2025-04-01', '2025-04-20', '06:30:00', 30, 10);


-- Use the generated `id` values from `member` and `my_class` tables for relationships
INSERT INTO booking (member_id, my_class_id, participation_date, booking_date) VALUES
(1, 1, '2025-01-02', '2025-01-01'),
(2, 2, '2025-03-05', '2025-01-03'),
(3, 3, '2025-04-03', '2025-01-04');
