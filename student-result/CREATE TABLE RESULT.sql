CREATE TABLE RESULT(
RESULT_ID INT  AUTO_INCREMENT ,
STUDENT_ID INT ,
TOTAL_MARKS INT,
MARKS_OBTAINED INT,
PERCENTAGE DECIMAL,
DIVISION INT,
YEAR_OF_EXAM INT,
PRIMARY KEY (RESULT_ID),
FOREIGN KEY (STUDENT_ID) REFERENCES STUDENT_DETAILS(STUDENT_ID)
)