CREATE TABLE m_user(
user_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(100) NOT NULL,
`password` VARCHAR(100) NOT NULL,
disk_id INT,
identity INT
)

CREATE TABLE m_disk(
disk_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
user_id INT,
size INT DEFAULT '0',
maxSize INT DEFAULT '1048576',
FOREIGN KEY(user_id) REFERENCES m_user(user_id)
)

CREATE TABLE folder(
folder_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
folder_name VARCHAR(100) NOT NULL,
parent_folder_id INT,
disk_id INT,
my_type VARCHAR(100) DEFAULT 'folder',
FOREIGN KEY(disk_id) REFERENCES m_disk(disk_id)
)

CREATE TABLE m_file(
file_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
file_name VARCHAR(100) NOT NULL,
parent_folder_id INT,
file_path VARCHAR(200),
upload_time DATE,
`format` VARCHAR(200),
size INT,
examed INT DEFAULT '0',
disk_id INT,
my_type VARCHAR(100) DEFAULT 'file',
FOREIGN KEY (parent_folder_id) REFERENCES folder(folder_id),
FOREIGN KEY (disk_id) REFERENCES m_disk(disk_id)
)




