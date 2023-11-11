--edit for PostGreSQL
CREATE DATABASE student_tracker;

--
-- Table structure for table `student`
--

CREATE TABLE student (
  id BIGSERIAL primary KEY,
  first_name varchar(45),
  last_name varchar(45),
  email varchar(45)
)

